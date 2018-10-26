package studybos.com.studybos2.data;

import java.util.ArrayList;

import studybos.com.studybos2.Help;

/**
 * Created by 机械革命 on 2018/10/14.
 */

public class HelpList {

    private static ArrayList<Help> helpList=new ArrayList<>();

    public static ArrayList<Help> getHelpList() {
        return helpList;
    }

    public static void setHelpList(ArrayList<Help> helpList) {
        HelpList.helpList = helpList;
    }
}
