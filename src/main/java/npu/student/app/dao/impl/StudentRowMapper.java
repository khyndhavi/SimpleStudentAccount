package npu.student.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import npu.student.app.domain.Student;

import org.springframework.jdbc.core.RowMapper;



public class StudentRowMapper implements RowMapper<Student> {

	public Student mapRow(ResultSet resultSet, int row) throws SQLException {
		
		Student student = new Student();
		
		student.setId(resultSet.getInt("student_id"));
		student.setFirstName(resultSet.getString("first_name"));
		student.setLastName(resultSet.getString("last_name"));
		student.setMiddleName(resultSet.getString("middle_name"));
		student.setStreet(resultSet.getString("street"));
		student.setCity(resultSet.getString("city"));
		student.setState(resultSet.getString("state"));
		student.setZip(resultSet.getString("zip"));
		student.setEmail(resultSet.getString("email"));
		student.setPhoneNumber(resultSet.getString("phone_number"));
		return student;
	}

}
