package npu.student.app.service;

import java.util.List;

import npu.student.app.domain.Account;



public interface AccountService {	
	public Account getStudentAccount(int studentId, int accountId);
	public List<Account> getStudentAccounts(int studentId);
	public Account addStudentAccount(int studentId, Account account);
	public boolean deleteStudentAccount(int studentId, int accountId);
	public boolean addBalanceToAccount(int studentId, int accountId, double amountToAdd);
	public boolean deductBalanceFromAccount(int studentId, int accountId, double amountToDeduct);
}
