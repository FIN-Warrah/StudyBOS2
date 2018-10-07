package studybos.com.studybos2;

/**
 * Created by 机械革命 on 2018/10/7.
 */

import java.io.Serializable;

public class LoginInfo implements Serializable{

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
