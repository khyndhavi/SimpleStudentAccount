package npu.student.app.dao;

import npu.student.app.domain.User;

public interface UserDAO {
	public User getUser(String userName, String password);
}
