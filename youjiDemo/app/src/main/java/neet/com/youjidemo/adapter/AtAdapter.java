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
import neet.com.youjidemo.bean.AtMessage;
import neet.com.youjidemo.view.MessageFragment;

public class AtAdapter extends BaseAdapter {
    private MessageFragment context;
    private int itemLayout;
    private List<AtMessage> messages = new ArrayList<>();
    public AtAdapter(MessageFragment context, int itemLayout, List<AtMessage> messages) {
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
            LayoutInflater layoutInflater = LayoutInflater.from(context.getContext());
            convertView = layoutInflater.inflate(itemLayout, null);
        }
        ImageView headphoto = convertView.findViewById(R.id.lv_head_sculpture);
        headphoto.setImageResource(messages.get(position).getHeadPhoto());
        TextView name = convertView.findViewById(R.id.tv_name);
        name.setText(messages.get(position).getName());
        TextView time = convertView.findViewById(R.id.tv_time);
        time.setText(messages.get(position).getTime());
        TextView atone = convertView.findViewById(R.id.tv_atone);
        atone.setText(messages.get(position).getAtOne());
        TextView content = convertView.findViewById(R.id.tv_content);
        content.setText(messages.get(position).getContent());
        return convertView;
    }
}
