package neet.com.youjidemo.bean;

public class CommentMessae {
    private String replyme;
    private String reply;
    private int picture;
    private String content;
    public CommentMessae(String replyme, String reply, int picture, String content){
        this.replyme = replyme;
        this.reply = reply;
        this.picture = picture;
        this.content = content;
    }
    public String getReplyme() {
        return replyme;
    }

    public void setReplyme(String replyme) {
        this.replyme = replyme;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
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
