package neet.com.youjidemo.bean;

import java.io.Serializable;

/**
 * 用于展示动态的数据类型
 */
public class ShowDynamicInAll implements Serializable {
    private int user_id;
    private String username;
    private String user_touxiang;
    private String address;
    private int dyanmic_id;
    private String dynamic_text;
    private String dynamicImg_url;
    private int collection_num;
    private int comment_num;
    private int like_num;
    private String time;
    private boolean isFollow=false;
    private boolean isLike=false;
    private boolean isCollection=false;

    public boolean isCollection() {
        return isCollection;
    }

    public void setCollection(boolean collection) {
        isCollection = collection;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public ShowDynamicInAll(int user_id, String username, String user_touxiang, String address, int dyanmic_id, String dynamic_text, String dynamicImg_url, int collection_num, int comment_num, int like_num, String time, boolean isFollow) {
        this.user_id = user_id;
        this.username = username;
        this.user_touxiang = user_touxiang;
        this.address = address;
        this.dyanmic_id = dyanmic_id;
        this.dynamic_text = dynamic_text;
        this.dynamicImg_url = dynamicImg_url;
        this.collection_num = collection_num;
        this.comment_num = comment_num;
        this.like_num = like_num;
        this.time = time;
        this.isFollow = isFollow;
    }

    public boolean isFollow() {
        return isFollow;
    }

    public void setFollow(boolean follow) {
        isFollow = follow;
    }

    public ShowDynamicInAll(int user_id, String username, String user_touxiang, String address, int dyanmic_id, String dynamic_text, String dynamicImg_url, int collection_num, int comment_num, int like_num, String time) {
        this.user_id = user_id;
        this.username = username;
        this.user_touxiang = user_touxiang;
        this.address = address;
        this.dyanmic_id = dyanmic_id;
        this.dynamic_text = dynamic_text;
        this.dynamicImg_url = dynamicImg_url;
        this.collection_num = collection_num;
        this.comment_num = comment_num;
        this.like_num = like_num;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_touxiang() {
        return user_touxiang;
    }

    public void setUser_touxiang(String user_touxiang) {
        this.user_touxiang = user_touxiang;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDyanmic_id() {
        return dyanmic_id;
    }

    public void setDyanmic_id(int dyanmic_id) {
        this.dyanmic_id = dyanmic_id;
    }

    public String getDynamic_text() {
        return dynamic_text;
    }

    public void setDynamic_text(String dynamic_text) {
        this.dynamic_text = dynamic_text;
    }

    public String getDynamicImg_url() {
        return dynamicImg_url;
    }

    public void setDynamicImg_url(String dynamicImg_url) {
        this.dynamicImg_url = dynamicImg_url;
    }

    public int getCollection_num() {
        return collection_num;
    }

    public void setCollection_num(int collection_num) {
        this.collection_num = collection_num;
    }

    public int getComment_num() {
        return comment_num;
    }

    public void setComment_num(int comment_num) {
        this.comment_num = comment_num;
    }

    public int getLike_num() {
        return like_num;
    }

    public void setLike_num(int like_num) {
        this.like_num = like_num;
    }
}
