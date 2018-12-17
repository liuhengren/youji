package neet.com.youjidemo.bean;

public class AtMessage {
    private int headPhoto;
    private String name;
    private String time;
    private String atOne;
    private String content;
    private int uploadImages;
    public AtMessage(int headPhoto, String name, String time, String atOne, String content){
        this.headPhoto = headPhoto;
        this.name = name;
        this.time = time;
        this.atOne = atOne;
        this.content = content;
    }
    public int getUploadImages() {
        return uploadImages;
    }

    public void setUploadImages(int uploadImages) {
        this.uploadImages = uploadImages;
    }

    public int getHeadPhoto() {
        return headPhoto;
    }
    public void setHeadPhoto(int headPhoto) {
        this.headPhoto = headPhoto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAtOne() {
        return atOne;
    }

    public void setAtOne(String atOne) {
        this.atOne = atOne;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
