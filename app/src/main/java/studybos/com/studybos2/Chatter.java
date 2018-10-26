package studybos.com.studybos2;

/**
 * Created by 机械革命 on 2018/10/17.
 */

public class Chatter {

    private static String chatter;

    private static int pos;

    public static void setPos(int pos) {
        Chatter.pos = pos;
    }

    public static int getPos() {

        return pos;
    }

    public static void setChatter(String chatter) {
        Chatter.chatter = chatter;
    }

    public static String getChatter() {

        return chatter;
    }
}
