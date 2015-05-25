package npu.student.app.service;

import npu.student.app.domain.Student;


public interface StudentService {
	public Student createStudent(Student student);
	public Student getStudent(int id);
	public Student updateStudentInformation(Student student);
	public boolean deleteStudent(int studentId);	
}
