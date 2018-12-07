package neet.com.youjidemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class IndexListViewAdapter extends BaseAdapter {
    private Context context;
    private List<Object> list = new ArrayList<>();
    private int layout_item_id;

    public IndexListViewAdapter(Context context, List<Object> list, int layout_item_id) {
        this.context = context;
        this.list = list;
        this.layout_item_id = layout_item_id;
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
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(layout_item_id, null);
        }
        return convertView;
    }
}
