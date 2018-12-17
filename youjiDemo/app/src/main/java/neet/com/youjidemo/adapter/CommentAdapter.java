package neet.com.youjidemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import neet.com.youjidemo.R;
import neet.com.youjidemo.bean.CommentMessae;
import neet.com.youjidemo.view.MessageFragment;

public class CommentAdapter extends BaseAdapter {
    private Context context;
    private int itemLayout;
    private List<CommentMessae> messages = new ArrayList<>();
    public CommentAdapter(MessageFragment context, int itemLayout, List<CommentMessae> messages){
        this.context = context;
        this.itemLayout = itemLayout;
        this.messages = messages;
    }
    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(itemLayout, null);
        }
        TextView replyme = convertView.findViewById(R.id.tv_reply_me);
        replyme.setText(messages.get(position).getReplyme());
        TextView reply = convertView.findViewById(R.id.tv_reply);
        reply.setText(messages.get(position).getReply());
        ImageView picture = convertView.findViewById(R.id.iv_picture);
        picture.setImageResource(messages.get(position).getPicture());
        TextView content = convertView.findViewById(R.id.tv_content);
        content.setText(messages.get(position).getContent());
        return convertView;
    }
}
