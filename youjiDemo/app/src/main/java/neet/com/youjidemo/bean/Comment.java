package neet.com.youjidemo.bean;

public class Comment {
    private int comment_id;
    private String comment_text;
    private int comment_dynamic_id;
    private int comment_like_num;
    private int comment_user_id;
    private String comment_time;

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public int getComment_dynamic_id() {
        return comment_dynamic_id;
    }

    public void setComment_dynamic_id(int comment_dynamic_id) {
        this.comment_dynamic_id = comment_dynamic_id;
    }

    public int getComment_like_num() {
        return comment_like_num;
    }

    public void setComment_like_num(int comment_like_num) {
        this.comment_like_num = comment_like_num;
    }

    public int getComment_user_id() {
        return comment_user_id;
    }

    public void setComment_user_id(int comment_user_id) {
        this.comment_user_id = comment_user_id;
    }

    public String getComment_time() {
        return comment_time;
    }

    public void setComment_time(String comment_time) {
        this.comment_time = comment_time;
    }
}
