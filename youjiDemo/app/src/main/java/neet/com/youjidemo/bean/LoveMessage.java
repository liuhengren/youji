package neet.com.youjidemo.bean;

public class LoveMessage {
    private int headsculpture;
    private String name;
    private String time;
    private String content;
    private int picture;
    public LoveMessage(int headsculpture, String name, String time, String content, int picture){
        this.headsculpture = headsculpture;
        this.name = name;
        this.time = time;
        this.content = content;
        this.picture = picture;
    }
    public int getHeadsculpture() {
        return headsculpture;
    }

    public void setHeadsculpture(int headsculpture) {
        this.headsculpture = headsculpture;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }
}
