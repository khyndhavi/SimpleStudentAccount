package npu.student.app.service.impl;

import java.sql.SQLException;
import npu.student.app.dao.StudentDAO;
import npu.student.app.domain.Student;
import npu.student.app.exception.StudentDoesNotExistException;
import npu.student.app.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("studentService")
public class StudentServiceImpl implements StudentService {
	private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
	
	@Autowired
	StudentDAO studentDAO;
	
	public Student createStudent(Student student) {
		try {
			Student studentCreated = studentDAO.addStudent(student);
			return studentCreated;
		} catch(SQLException e) {
			logger.debug("#### caught exception:"+e + e.getStackTrace());
			return null;
		}		
	}
	
	public Student getStudent(int id) {
		try {
			return studentDAO.findStudentById(id);			
		} catch(SQLException e) {
			logger.debug("#### caught exception:"+e + e.getStackTrace());
			return null;
		} catch(StudentDoesNotExistException se) {
			logger.debug("#### caught exception:"+se + se.getStackTrace());
			return null;
		}
	}
	
	public Student updateStudentInformation(Student student) {
		try {
			return studentDAO.updateStudentInformation(student);			
		} catch(Exception e) {
			logger.debug("#### caught exception at updating student information:"+e + e.getStackTrace());
			return null;
		}
	}
	
	public boolean deleteStudent(int studentId) {
		try {
			return studentDAO.deleteStudent(studentId);			
		} catch(SQLException e) {
			logger.debug("#### caught exception when deleting the student:"+e + e.getStackTrace());
			return false;
		}
	}
	 
	
	
}
