package studybos.com.studybos2;

/**
 * Created by 机械革命 on 2018/8/21.
 */

public class LiveCard {

    private String title;
    private String liverid;
    private String subject;
    private int audienceNumber;
    private int imageId;
    private int liverHeadImageId;

    public LiveCard(String title,String liverid,String subject,int audienceNumber,int imageId,int liverHeadImageId){
        this.title=title;
        this.liverid=liverid;
        this.audienceNumber=audienceNumber;
        this.subject=subject;
        this.imageId=imageId;
        this.liverHeadImageId=liverHeadImageId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setLiverid(String liverid) {
        this.liverid = liverid;
    }

    public String getLiverid() {
        return liverid;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setAudienceNumber(int audienceNumber) {
        this.audienceNumber = audienceNumber;
    }

    public int getAudienceNumber() {
        return audienceNumber;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }


    public void setLiverHeadImageId(int liverHeadImageId) {
        this.liverHeadImageId = liverHeadImageId;
    }

    public int getLiverHeadImageId() {
        return liverHeadImageId;
    }
}
