package npu.student.app.dao.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import npu.student.app.dao.AccountDAO;
import npu.student.app.domain.Account;
import npu.student.app.exception.AccountTransactionFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("accountDAO")
public class AccountDAOImpl implements AccountDAO {
	
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	
	private NamedParameterJdbcTemplate dbTemplate;
	private SimpleJdbcInsert jdbcInsert;
	private AccountRowMapper accountRowMapper;
	
	
	@PostConstruct
	public void setup() {		
		dbTemplate = new NamedParameterJdbcTemplate(dataSource);
		accountRowMapper = new AccountRowMapper();
		jdbcInsert = new SimpleJdbcInsert(dataSource)
		                 .withTableName("account")
		                 .usingGeneratedKeyColumns("account_id");		                 
	}
	
	@Override
	@Transactional
	public Account addAccount(int studentId, Account account) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(account);
	    Number value = jdbcInsert.executeAndReturnKey(params);
	    int accountId = value.intValue();
	    account.setId(accountId);
	   
	    	    
	    String insertStatement = "insert into student_account (account_id, student_id) values(:account_id, :student_id)";
	    
	    MapSqlParameterSource mapParams = new MapSqlParameterSource("account_id", accountId);
	    mapParams.addValue("student_id", studentId);		
		int returnValue = dbTemplate.update(insertStatement, mapParams);		
		if(returnValue == 1) { 
	    	return account; 
	    } else {	    	
	    	return null;
	    }		
	}

	@Override
	@Transactional
	public Account getStudentAccount(int studentId, int accountId) {
		String selectStmt = "select account_id, account_type, description, balance, update_date"
				+ " from account a, student_account sa "
				+ "where sa.student_id=:studentId "
				+ "and sa.account_id=:accountId "
				+ "and sa.account_id = a.account_id";
		MapSqlParameterSource params = new MapSqlParameterSource("studentId", studentId);
		params.addValue("accountId",accountId);
		List<Account> studentAccount = dbTemplate.query(selectStmt, params, accountRowMapper);	
		if(studentAccount.size() == 1) { 
	    	return studentAccount.get(0); 
	    } else {
	    	return null;
	    }
	}

	@Override
	@Transactional
	public List<Account> getStudentAccounts(int studentId) {
		String selectStmt = "select account_id, account_type, description, balance, update_date"
				+ " from account a, student_account sa "
				+ "where sa.student_id=:studentId "				
				+ "and sa.account_id = a.account_id";
		MapSqlParameterSource params = new MapSqlParameterSource("studentId", studentId);
		
		List<Account> studentAccounts = dbTemplate.query(selectStmt, params, accountRowMapper);	
		return studentAccounts;
	}
	
	@Override
	@Transactional(rollbackFor={npu.student.app.exception.AccountTransactionFailedException.class})
	public boolean deleteAccount(int studentId, int accountId) throws AccountTransactionFailedException{
		String deleteStudentAccountStmt = "delete from student_account where student_id=:studentId and account_id=:accountId";
		String deleteAccountStmt = "delete from acccount where account_id=:accountId";
		MapSqlParameterSource params = new MapSqlParameterSource("studentId", studentId);
		params.addValue("accountId",accountId);
		int firstValue = dbTemplate.update(deleteStudentAccountStmt, params);
		int secondValue = dbTemplate.update(deleteAccountStmt, params);
		if(firstValue == 1 && secondValue == 1) {			
	    	return true; 
	    } else {
	    	throw new AccountTransactionFailedException("Failed in deleting account");	    	
	    }
	}

	@Override
	@Transactional(rollbackFor={npu.student.app.exception.AccountTransactionFailedException.class})
	public boolean addBalance(int studentId, int accountId, double amount) throws AccountTransactionFailedException {	
		
		String updateAccountStmt = "update account a set balance = balance + :amount "
				+ "where exists (  select * from student_account sa "
				+ "where sa.account_id=a.account_id and sa.student_id=:student_id) "
				+ "and account_id = :account_id";		
		
		String addTransactionStmt = "INSERT INTO transaction"
				+ "('account_id', 'amount', 'transaction_type', 'description','transaction_date') "
				+ "VALUES(:accountId, :amount, :transaction_type, :description, :transaction_date)";
		MapSqlParameterSource transactionParams = new MapSqlParameterSource("accountId", accountId);
		transactionParams.addValue("amount", amount);
		transactionParams.addValue("transaction_type", "Add");
		transactionParams.addValue("description", "Adding amount to account");
		transactionParams.addValue("transaction_date", new Date());
		
		MapSqlParameterSource updateAccParams = new MapSqlParameterSource("account_id", accountId);
		updateAccParams.addValue("amount", amount);
		updateAccParams.addValue("student_id", studentId);
		
		int firstValue = dbTemplate.update(updateAccountStmt, updateAccParams);
		int secondValue = dbTemplate.update(addTransactionStmt, transactionParams);
		
		if(firstValue == 1 && secondValue == 1) {			
	    	return true; 
	    } else {
	    	throw new AccountTransactionFailedException("Failed in adding balance");
	    }
	}

	@Override
	@Transactional(rollbackFor={npu.student.app.exception.AccountTransactionFailedException.class})
	public boolean deductBalance(int studentId, int accountId, double amount) throws AccountTransactionFailedException {
		String updateAccountStmt = "update account a set balance = balance - :amount "
				+ "where exists (  select * from student_account sa "
				+ "where sa.account_id=a.account_id and sa.student_id=:student_id) "
				+ "and account_id = :account_id";		
		
		String addTransactionStmt = "INSERT INTO transaction"
				+ "('account_id', 'amount', 'transaction_type', 'description','transaction_date') "
				+ "VALUES(:accountId, :amount, :transaction_type, :description, :transaction_date)";
		MapSqlParameterSource transactionParams = new MapSqlParameterSource("accountId", accountId);
		transactionParams.addValue("amount", amount);
		transactionParams.addValue("transaction_type", "Add");
		transactionParams.addValue("description", "Adding amount to account");
		transactionParams.addValue("transaction_date", new Date());
		
		MapSqlParameterSource updateAccParams = new MapSqlParameterSource("account_id", accountId);
		updateAccParams.addValue("amount", amount);
		updateAccParams.addValue("student_id", studentId);
		
		int firstValue = dbTemplate.update(updateAccountStmt, updateAccParams);
		int secondValue = dbTemplate.update(addTransactionStmt, transactionParams);
		
		if(firstValue == 1 && secondValue == 1) {			
	    	return true; 
	    } else {
	    	throw new AccountTransactionFailedException("Failed in deducting balance");
	    }
	}	
}
