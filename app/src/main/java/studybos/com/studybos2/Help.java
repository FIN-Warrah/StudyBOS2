package studybos.com.studybos2;

/**
 * Created by 机械革命 on 2018/8/21.
 */

public class Help {

    private int number;
    private int imageId;
    private int askernumber;
    private int askerImageId;
    private String askerId;
    private String title;
    private String content;
    private byte[] imgByte;

    public Help(int number){
        this.number=number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setAskernumber(int askernumber) {
        this.askernumber = askernumber;
    }

    public int getAskernumber() {
        return askernumber;
    }

    public void setAskerImageId(int askerImageId) {
        this.askerImageId = askerImageId;
    }

    public int getAskerImageId() {
        return askerImageId;
    }

    public void setAskerId(String askerId) {
        this.askerId = askerId;
    }

    public String getAskerId() {
        return askerId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public byte[] getImgByte() {
        return imgByte;
    }

    public void setImgByte(byte[] imgByte) {
        this.imgByte = imgByte;
    }
}
