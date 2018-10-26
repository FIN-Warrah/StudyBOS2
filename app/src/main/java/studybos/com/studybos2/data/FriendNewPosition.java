package studybos.com.studybos2.data;

/**
 * Created by 机械革命 on 2018/10/18.
 */

public class FriendNewPosition {
    private static int friendNewPosition;

    public static void setFriendNewPosition(int friendNewPosition) {
        FriendNewPosition.friendNewPosition = friendNewPosition;
    }

    public static int getFriendNewPosition() {

        return friendNewPosition;
    }
}
