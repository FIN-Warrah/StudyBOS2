package studybos.com.studybos2;

/**
 * Created by 机械革命 on 2018/8/21.
 */

public class Friend {

    private boolean care;//是否关注
    private boolean fan;//是否是粉丝
    private int number;
    private String id;
    private int imageId;
    private String lastMessage;

    public Friend(int number){
        this.number=number;
    }

    public void setCare(boolean care) {
        this.care = care;
    }

    public boolean getCare(){
        return care;
    }

    public void setFan(boolean fan) {
        this.fan = fan;
    }

    public boolean getFan(){
        return fan;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getLastMessage() {
        return lastMessage;
    }
}
