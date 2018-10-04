package studybos.com.studybos2.util;

import java.util.ArrayList;
import java.util.List;

import studybos.com.studybos2.Choose;
import studybos.com.studybos2.Friend;
import studybos.com.studybos2.Help;
import studybos.com.studybos2.R;

/**
 * Created by 机械革命 on 2018/8/23.
 */

public class InitUtil {

    public static List<Help> initHelp(Help[] helps){
        List<Help> helpList=new ArrayList<>();
        for (int i=0;i<20;i++){
            Help help=helps[0];
            help.setAskerImageId(R.drawable.icon_image);
            help.setAskerId("Warrah");
            help.setTitle("高数");
            help.setContent("这是一道高数题");
            help.setImageId(R.drawable.blue);
            helpList.add(help);
        }
        return helpList;
    }

    public static List<Friend> initFriends(Friend[] friends){
        List<Friend> friendList=new ArrayList<>();
        for (int i=0;i<20;i++){
            Friend friend=friends[0];
            friend.setImageId(R.drawable.icon_image);
            friend.setId("Warrah");
            friend.setLastMessage("Yes");
            friendList.add(friend);
        }
        return  friendList;
    }

    public static List<Choose> initChooses(Choose[] chooses){
        List<Choose> chooseList=new ArrayList<>();
        for (Choose choose:chooses){
            chooseList.add(choose);
        }
        return chooseList;
    }

}
