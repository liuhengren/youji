package neet.com.youjidemo.command;

import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;

import java.util.List;

import neet.com.youjidemo.adapter.SquareItemAdapter;

public class PullRefreshTask extends AsyncTask {

    private List list;
    private  SquareItemAdapter squareItemAdapter;
    private  SwipeRefreshLayout mySwipeRefreshLayout;

    public PullRefreshTask(List list, SquareItemAdapter squareItemAdapter,SwipeRefreshLayout mySwipeRefreshLayout) {
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
        list.add(1);
        squareItemAdapter.notifyDataSetChanged();
        mySwipeRefreshLayout.setEnabled(false);
    }


    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            Thread.sleep(1000);

            publishProgress();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
