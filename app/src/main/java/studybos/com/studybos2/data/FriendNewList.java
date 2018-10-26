package studybos.com.studybos2.data;

import java.util.ArrayList;

import studybos.com.studybos2.FriendNew;

/**
 * Created by 机械革命 on 2018/10/17.
 */

public class FriendNewList {
    private static ArrayList<FriendNew> friendNewArrayList=new ArrayList<>();

    public static void setFriendNewArrayList(ArrayList<FriendNew> friendNewArrayList) {
        FriendNewList.friendNewArrayList = friendNewArrayList;
    }

    public static ArrayList<FriendNew> getFriendNewArrayList() {

        return friendNewArrayList;
    }
}
