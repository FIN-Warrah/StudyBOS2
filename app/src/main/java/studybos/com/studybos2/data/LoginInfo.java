package studybos.com.studybos2.data;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

public class LoginInfo extends DataSupport implements Serializable{
	
	private String userId;
	private String password;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
