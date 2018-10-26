package studybos.com.studybos2.data;

/**
 * Created by 机械革命 on 2018/10/16.
 */

public class LoginId {

    private static String loginId;

    public static String getLoginId() {
        return loginId;
    }

    public static void setLoginId(String loginId) {
        LoginId.loginId = loginId;
    }
}
