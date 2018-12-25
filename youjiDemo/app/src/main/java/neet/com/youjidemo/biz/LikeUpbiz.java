package neet.com.youjidemo.biz;

import java.util.List;

import neet.com.youjidemo.bean.LikeUp;
import neet.com.youjidemo.command.PostJson;

public class LikeUpbiz implements ILikeUp {
    private final String LikeUpurl="http://10.222.189.117:8080/youjiServer/LikeupServlet";

    @Override
    public boolean addLike(int user_id, int dynamic_id) {
        String msg="likeup_addlikeup";
        String url=LikeUpurl+"?message="+msg+"&user_id="+user_id+"&dynamic_id="+dynamic_id;
        boolean b = PostJson.PostByUrl(url);
        return b;
    }

    @Override
    public boolean cancelLike(int user_id, int dynamic_id) {
        String msg="likeup_deletelikeup";
        String url=LikeUpurl+"?message="+msg+"&user_id="+user_id+"&dynamic_id="+dynamic_id;
        boolean b = PostJson.PostByUrl(url);
        return b;
    }

    @Override
    public boolean ifLike(int user_id, int dynamic_id) {
        String msg="http://10.222.189.117:8080/youjiServer/DynamicServlet?message=dynamic_isLikeuped";
        String url=msg+"&user_id="+user_id+"&dynamic_id="+dynamic_id;
        boolean b = PostJson.PostByUrl(url);
        return b;
    }

    @Override
    public List<LikeUp> getListByDynamicId(int dynamic_id) {
        return null;
    }


}
