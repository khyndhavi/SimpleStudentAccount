package npu.student.app.dao;

import java.sql.SQLException;

import npu.student.app.domain.Student;
import npu.student.app.exception.StudentDoesNotExistException;

public interface StudentDAO {
	public Student findStudentById(int id) throws SQLException, StudentDoesNotExistException;
	public boolean deleteStudent(int id) throws SQLException;
	public Student addStudent(Student student) throws SQLException;
	public Student updateStudentInformation(Student student) throws SQLException;	
}
