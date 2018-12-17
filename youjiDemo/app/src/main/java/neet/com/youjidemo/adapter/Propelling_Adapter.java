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
import neet.com.youjidemo.bean.Message;
import neet.com.youjidemo.view.MessageFragment;

public class Propelling_Adapter extends BaseAdapter {
    private Context context;
    private int itemLayout;
    private List<com.example.administrator.youji_my.Message> messages = new ArrayList<>();
    public Propelling_Adapter(MessageFragment messageFragment, int propelling_listview_item, List<Message> messages){};
    public Propelling_Adapter(Context context, int itemLayout, List<com.example.administrator.youji_my.Message> messages) {
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
        ImageView head_sculpture = convertView.findViewById(R.id.lv_head_sculpture);
        head_sculpture.setImageResource(messages.get(position).getImage());
        TextView name = convertView.findViewById(R.id.tv_name);
        name.setText(messages.get(position).getName());
        TextView time = convertView.findViewById(R.id.tv_time);
        time.setText(messages.get(position).getTime());
        TextView content = convertView.findViewById(R.id.tv_content);
        content.setText(messages.get(position).getContent());
        return convertView;
    }
}
