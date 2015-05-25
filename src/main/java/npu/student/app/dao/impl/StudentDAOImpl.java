package npu.student.app.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import npu.student.app.dao.StudentDAO;
import npu.student.app.domain.Student;
import npu.student.app.exception.StudentDoesNotExistException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("studentDAO")
@Transactional
public class StudentDAOImpl implements StudentDAO {
	
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	
	private NamedParameterJdbcTemplate dbTemplate;
	private SimpleJdbcInsert jdbcInsert;
	private StudentRowMapper studentRowMapper;
	
	
	@PostConstruct
	public void setup() {		
		dbTemplate = new NamedParameterJdbcTemplate(dataSource);
		studentRowMapper = new StudentRowMapper();	
		jdbcInsert = new SimpleJdbcInsert(dataSource)
		                 .withTableName("student")
		                 .usingGeneratedKeyColumns("student_id");		                 
	}

	@Override
	public Student findStudentById(int  id) throws SQLException, StudentDoesNotExistException {
		String selectStmt = "select * from student where student_id=:id";
		MapSqlParameterSource params = new MapSqlParameterSource("id", id);		
		List<Student> studentList = dbTemplate.query(selectStmt, params, studentRowMapper);	
		if(studentList != null && studentList.size() == 1) {
			return studentList.get(0);
		} else {
			throw new StudentDoesNotExistException("Student does not exist");
		}
	}

	@Override
	public boolean deleteStudent(int id) throws SQLException {
		String deleteStmt = "delete from student where student_id=:id";
		MapSqlParameterSource params = new MapSqlParameterSource("id", id);		
		int returnValue = dbTemplate.update(deleteStmt, params);		
		if(returnValue == 1) { 
	    	return true; 
	    } else {
	    	return false;
	    }
	}

	@Override
	public Student addStudent(Student student) throws SQLException {
		SqlParameterSource params = new BeanPropertySqlParameterSource(student);
	    Number value = jdbcInsert.executeAndReturnKey(params);
	    student.setId(value.intValue());
	    return student;
	}

	@Override
	public Student updateStudentInformation(Student student) throws SQLException {		
		
		String updateStmt = "update student set street=:street, state=:state, city=:city, zip=:zip where student_id=:id";
				
		SqlParameterSource params = new BeanPropertySqlParameterSource(student);
		int returnValue = dbTemplate.update(updateStmt, params);		
		if(returnValue == 1) { 
	    	return student; 
	    } else {
	    	return null;
	    }	
	}
	
	
}
