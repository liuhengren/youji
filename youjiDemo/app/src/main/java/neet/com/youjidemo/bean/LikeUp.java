package neet.com.youjidemo.bean;

public class LikeUp {
    private int LikeUp_id;
    private int LikeUp_user_id;
    private int LikeUp_dynamic_id;

    public LikeUp(int likeUp_id, int likeUp_user_id, int likeUp_dynamic_id) {
        LikeUp_id = likeUp_id;
        LikeUp_user_id = likeUp_user_id;
        LikeUp_dynamic_id = likeUp_dynamic_id;
    }

    public int getLikeUp_id() {
        return LikeUp_id;
    }

    public void setLikeUp_id(int likeUp_id) {
        LikeUp_id = likeUp_id;
    }

    public int getLikeUp_user_id() {
        return LikeUp_user_id;
    }

    public void setLikeUp_user_id(int likeUp_user_id) {
        LikeUp_user_id = likeUp_user_id;
    }

    public int getLikeUp_dynamic_id() {
        return LikeUp_dynamic_id;
    }

    public void setLikeUp_dynamic_id(int likeUp_dynamic_id) {
        LikeUp_dynamic_id = likeUp_dynamic_id;
    }
}
