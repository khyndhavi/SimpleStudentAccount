package npu.student.app;

import java.util.List;

import npu.student.app.domain.Account;
import npu.student.app.domain.Student;
import npu.student.app.service.AccountService;
import npu.student.app.service.StudentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/studentrestapp")
public class RestController {
	
	private static final Logger logger = LoggerFactory.getLogger(RestController.class);
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private AccountService accountService;
	
	//Retrieves student information for specific id
	@RequestMapping(value = "/student/{id}", method = RequestMethod.GET)	
	public  @ResponseBody Student getStudent(@PathVariable("id") int id) {
		logger.info("Retrieving the student information...");		
		Student student = studentService.getStudent(id);
		return student;
	}
	
	//Creates a new student
	@RequestMapping(value = "/student", method = RequestMethod.POST, consumes={"application/json"})	
	public  @ResponseBody Student addStudent(@RequestBody Student student) {
		logger.info("Creating a new student...");		
		Student newStudent = studentService.createStudent(student);
		return newStudent;
	}
	
	//Updates student information
	@RequestMapping(value = "/student", method = RequestMethod.PUT, consumes={"application/json"})	
	public  @ResponseBody Student updateStudentInformation(@RequestBody Student student) {
		logger.info("Updating the student Information...");		
		Student newStudent = studentService.updateStudentInformation(student);
		return newStudent;
	}
	
	//Delete the student with id
	@RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE, consumes={"application/json"})	
	public  @ResponseBody boolean updateStudentInformation(@PathVariable("id") int id) {
		logger.info("Deleting the student...");		
		boolean studentDeleted = studentService.deleteStudent(id);
		return studentDeleted;
	}
	
	//Retrieves student with specific Id and accountId
	@RequestMapping(value = "/student/{id}/account/{accountId}", method = RequestMethod.GET)
	public  @ResponseBody Account getStudentAccount(@PathVariable("id") int id, @PathVariable("accountId") int accountId) {
		logger.info("Retrieving the student Account Information...");		
		Account account = accountService.getStudentAccount(id, accountId);
		return account;
	}
	
	//Retrieves all accounts of a student
	@RequestMapping(value = "/student/{id}/accounts", method = RequestMethod.GET)
	public  @ResponseBody List<Account> getStudentAccounts(@PathVariable("id") int id) {
		logger.info("Retrieving the student Accounts Information...");		
		List<Account> accounts = accountService.getStudentAccounts(id);
		return accounts;
	}
	
	//Adds a student account
	@RequestMapping(value = "/student/{id}/account", method = RequestMethod.POST)
	public  @ResponseBody Account addStudentAccount(@PathVariable("id") int studentId, @RequestBody Account account) {
		logger.info("Adding the student Account...");		
		Account newAccount = accountService.addStudentAccount(studentId, account);
		return newAccount;
	}
	
	//Deletes a student account
	@RequestMapping(value = "/student/{id}/account/{accountId}", method = RequestMethod.DELETE)
	public @ResponseBody boolean deleteStudentAccount(@PathVariable("id") int studentId, @PathVariable("accountId") int accountId) {
		logger.info("Deleting the student Account...");		
		boolean accountDeleted = accountService.deleteStudentAccount(studentId, accountId);
		return accountDeleted;
	}
	
	//Adds balance to an account
	@RequestMapping(value = "/student/{id}/account/{accountId}", method = RequestMethod.PUT)
	public @ResponseBody boolean addAmountToStudentAccount(@PathVariable("id") int studentId, @PathVariable("accountId") int accountId, @RequestBody Account account) {
		logger.info("Adding amount to student Account...");		
		boolean accountDeleted = accountService.addBalanceToAccount(studentId, accountId, account.getBalance());
		return accountDeleted;
	}
	
	//deducts balance from an account
	@RequestMapping(value = "/student/{id}/account/{accountId}", method = RequestMethod.PUT)
	public @ResponseBody boolean deductAmountFromStudentAccount(@PathVariable("id") int studentId, @PathVariable("accountId") int accountId, @RequestBody Account account) {
		logger.info("Deducting amount from student Account...");		
		boolean accountDeleted = accountService.deductBalanceFromAccount(studentId, accountId, account.getBalance());
		return accountDeleted;
	}
}
