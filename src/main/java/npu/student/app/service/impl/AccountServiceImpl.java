package npu.student.app.service.impl;

import java.util.List;

import npu.student.app.dao.AccountDAO;
import npu.student.app.domain.Account;
import npu.student.app.service.AccountService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("accountService")
@Transactional
public class AccountServiceImpl implements AccountService {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
	
	@Autowired
	AccountDAO accountDAO;
	
	@Override
	public Account addStudentAccount(int studentId, Account account) {
		try {
			return accountDAO.addAccount(studentId, account);
		} catch(Exception e) {
			logger.error("###Cannot add student account due to exception:"+e+e.getStackTrace());
			return null;
		}
	}	

	@Override
	public Account getStudentAccount(int studentId, int accountId) {
		try {
			return accountDAO.getStudentAccount(studentId, accountId);
		} catch(Exception e) {
			logger.error("###Cannot retrieve student account due to exception:"+e+e.getStackTrace());
			return null;
		}		
	}	

	@Override
	public List<Account> getStudentAccounts(int studentId) {
		try {
			return accountDAO.getStudentAccounts(studentId);
		} catch(Exception e) {
			logger.error("###Cannot retrieve student accounts due to exception:"+e+e.getStackTrace());
			return null;
		}
	}

	@Override
	public boolean deleteStudentAccount(int studentId, int accountId) {
		try {
			return accountDAO.deleteAccount(studentId, accountId);
		} catch(Exception e) {
			logger.error("###Cannot delete student account due to exception:"+e+e.getStackTrace());
			return false;
		}
	}

	@Override
	public boolean addBalanceToAccount(int studentId, int accountId, double amountToAdd) {
		try {
			return accountDAO.addBalance(studentId, accountId, amountToAdd);
		} catch(Exception e) {
			logger.error("###Cannot add balance to student account due to exception:"+e+e.getStackTrace());
			return false;
		}	
	}

	@Override
	public boolean deductBalanceFromAccount(int studentId, int accountId,
			double amountToDeduct) {
		try {
			return accountDAO.deductBalance(studentId, accountId, amountToDeduct);
		} catch(Exception e) {
			logger.error("###Cannot deduct balance from student account due to exception:"+e+e.getStackTrace());
			return false;
		}
	}	
}
