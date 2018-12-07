package neet.com.youjidemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class ContentAdapter extends BaseAdapter {
    private Context context;
    private List list;
    private int itemLayout;

    public ContentAdapter(Context context, List list, int itemLayout) {
        this.context = context;
        this.list = list;
        this.itemLayout = itemLayout;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(itemLayout, null);
        }
        return convertView;
    }
}
