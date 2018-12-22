package neet.com.youjidemo.bean;

public class ShowCommentBean {
    private int user_id;
    private int username;
    private String user_touxiang;
    private String time;
    private String comment_text;
    private int like_num;

    public ShowCommentBean(int user_id, int username, String user_touxiang, String time, String comment_text, int like_num) {
        this.user_id = user_id;
        this.username = username;
        this.user_touxiang = user_touxiang;
        this.time = time;
        this.comment_text = comment_text;
        this.like_num = like_num;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUsername() {
        return username;
    }

    public void setUsername(int username) {
        this.username = username;
    }

    public String getUser_touxiang() {
        return user_touxiang;
    }

    public void setUser_touxiang(String user_touxiang) {
        this.user_touxiang = user_touxiang;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public int getLike_num() {
        return like_num;
    }

    public void setLike_num(int like_num) {
        this.like_num = like_num;
    }
}
