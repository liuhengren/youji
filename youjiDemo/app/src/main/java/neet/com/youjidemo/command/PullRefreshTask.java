package neet.com.youjidemo.command;

import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;

import java.util.List;

import neet.com.youjidemo.adapter.SquareItemAdapter;
import neet.com.youjidemo.bean.ShowDynamicInAll;

public class PullRefreshTask extends AsyncTask {

    private List<ShowDynamicInAll> list;
    private  SquareItemAdapter squareItemAdapter;
    private  SwipeRefreshLayout mySwipeRefreshLayout;

    public PullRefreshTask(List<ShowDynamicInAll> list, SquareItemAdapter squareItemAdapter, SwipeRefreshLayout mySwipeRefreshLayout) {
        this.list = list;
        this.squareItemAdapter = squareItemAdapter;
        this.mySwipeRefreshLayout=mySwipeRefreshLayout;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }

    @Override
    protected void onProgressUpdate(Object[] values) {

        super.onProgressUpdate(values);
        squareItemAdapter.notifyDataSetChanged();
        mySwipeRefreshLayout.setRefreshing(false);//停止下拉刷新
    }


    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            Thread.sleep(2000);

            publishProgress();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
