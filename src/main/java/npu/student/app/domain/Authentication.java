package npu.student.app.domain;

public class Authentication {
	private User user;
	private boolean authenticated;
	
	public Authentication(User user, boolean authenticated) {
		this.user = user;
		this.authenticated = authenticated;		
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public boolean isAuthenticated() {
		return authenticated;
	}
	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}
	
}
