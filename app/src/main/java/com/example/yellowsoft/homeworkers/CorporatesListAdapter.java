package com.example.yellowsoft.homeworkers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by info on 18-07-2018.
 */

public class CorporatesListAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<CorporateLists> corporateLists;
    public CorporatesListAdapter(Context context,ArrayList<CorporateLists> corporateLists){
        this.context = context;
        this.corporateLists = corporateLists;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return corporateLists.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View item_view  =  inflater.inflate(R.layout.home_worker_request_items,null);
        TextView date = (TextView) item_view.findViewById(R.id.date);
        TextView request_no = (TextView) item_view.findViewById(R.id.request_no);
        TextView status = (TextView) item_view.findViewById(R.id.status);
        date.setText(corporateLists.get(i).date);
        request_no.setText(corporateLists.get(i).id);
        status.setText(corporateLists.get(i).status);
        return item_view;
    }
}
