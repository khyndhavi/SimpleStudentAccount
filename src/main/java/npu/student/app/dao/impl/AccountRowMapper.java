package npu.student.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import npu.student.app.domain.Account;

import org.springframework.jdbc.core.RowMapper;

public class AccountRowMapper implements RowMapper<Account> {
	public Account mapRow(ResultSet resultSet, int row) throws SQLException {
		Account account = new Account();
		
		account.setId(resultSet.getInt("account_id"));
		account.setAccountType(resultSet.getString("account_type"));
		account.setBalance(resultSet.getDouble("balance"));
		account.setDescription(resultSet.getString("description"));
		account.setUpdateDate(resultSet.getTimestamp("update_date"));
		return account;		
	}
}
