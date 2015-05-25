package npu.student.app.service.impl;

import npu.student.app.dao.UserDAO;
import npu.student.app.domain.Authentication;
import npu.student.app.domain.User;
import npu.student.app.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional(readOnly=true)
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
	
	@Autowired	
	UserDAO userDAO;
	
	public Authentication authenticateUser(String userName, String password) {
		try {
			User user = userDAO.getUser(userName, password);
		
			if(user!= null) {
				return new Authentication(user, true);
			} else {
				return new Authentication(null, false);
			}
		} catch(Exception e) {
			logger.error("Failed due to exception:"+e+":"+e.getMessage());
			return new Authentication(null, false);
		}
		
	}
}
