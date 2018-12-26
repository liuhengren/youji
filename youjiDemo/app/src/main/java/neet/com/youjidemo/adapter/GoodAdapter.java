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
import neet.com.youjidemo.bean.GoodMessage;
import neet.com.youjidemo.view.Fragment.MessageFragment;

public class GoodAdapter extends BaseAdapter {
    private Context context;
    private int itemLayout;
    private List<GoodMessage> messages = new ArrayList<>();
    public GoodAdapter(Context context, int itemLayout, List<GoodMessage> messages) {
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
        ImageView headsculpture = convertView.findViewById(R.id.iv_good_head_sculpture);
        headsculpture.setImageResource(messages.get(position).getHead_sculpture());
        TextView name = convertView.findViewById(R.id.tv_name);
        name.setText(messages.get(position).getName());
        ImageView picture = convertView.findViewById(R.id.iv_picture);
        picture.setImageResource(messages.get(position).getPicture());
        TextView content = convertView.findViewById(R.id.tv_content);
        content.setText(messages.get(position).getContent());
        return convertView;
    }
}
