package npu.student.app.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import npu.student.app.dao.UserDAO;
import npu.student.app.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	
	private NamedParameterJdbcTemplate dbTemplate;
	private UserRowMapper userRowMapper;
	
	@PostConstruct
	public void setup() {		
		dbTemplate = new NamedParameterJdbcTemplate(dataSource);
		userRowMapper = new UserRowMapper();				                 
	}
	
	public User getUser(String userName, String password) {
		String selectStmt = "select * from user where user_name=:userName and user_password=:password";
		MapSqlParameterSource params = new MapSqlParameterSource("userName", userName);
		params.addValue("password",password);
		List<User> userList = dbTemplate.query(selectStmt, params, userRowMapper);	
		if(userList != null && userList.size() == 1) {
			return userList.get(0);
		} else {
			return null;
		}
	}
}
