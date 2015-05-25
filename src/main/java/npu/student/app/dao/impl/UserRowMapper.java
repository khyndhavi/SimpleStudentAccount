package npu.student.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import npu.student.app.domain.User;
import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		User user = new User();
		user.setUserId(resultSet.getInt("user_id"));
		user.setUserPassword(resultSet.getString("user_password"));
		user.setUserName(resultSet.getString("user_name"));
		user.setStudent_id(resultSet.getInt("student_id"));
		return user;
	}

}
