package npu.student.app.dao;

import java.util.List;

import npu.student.app.domain.Account;
import npu.student.app.exception.AccountTransactionFailedException;

public interface AccountDAO {
	public Account addAccount(int studentId, Account account);
	public Account getStudentAccount(int studentId, int accountId);
	public List<Account> getStudentAccounts(int studentId);
	public boolean deleteAccount(int studentId, int accountId) throws AccountTransactionFailedException;
	public boolean addBalance(int studentId, int accountId, double amount) throws AccountTransactionFailedException;
	public boolean deductBalance(int studentId, int accountId, double amount) throws AccountTransactionFailedException;
}
