package npu.student.app.service;

import npu.student.app.domain.Authentication;

public interface UserService {
	public Authentication authenticateUser(String userName, String password);
}
