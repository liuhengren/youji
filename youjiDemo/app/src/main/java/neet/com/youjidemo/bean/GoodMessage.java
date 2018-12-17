package neet.com.youjidemo.bean;

public class GoodMessage {
    private int head_sculpture;
    private String name;
    private int picture;
    private String content;
    public GoodMessage(int head_sculpture, String name, int picture, String content){
        this.head_sculpture = head_sculpture;
        this.name = name;
        this.picture = picture;
        this.content = content;
    }
    public int getHead_sculpture() {
        return head_sculpture;
    }

    public void setHead_sculpture(int head_sculpture) {
        this.head_sculpture = head_sculpture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
