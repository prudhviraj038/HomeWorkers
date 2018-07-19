package com.example.yellowsoft.homeworkers;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by info on 06-07-2018.
 */

public class RequestAgeAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<String> ages;
    RequestHomeWorkersActivity activity;
    public RequestAgeAdapter(Context context,ArrayList<String> ages,RequestHomeWorkersActivity activity){
        this.context = context;
        this.ages = ages;
        this.activity = activity;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return ages.size();
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
        View item_view  = inflater.inflate(R.layout.nationality_religion_items,null);
        TextView title = (TextView) item_view.findViewById(R.id.title);
        title.setText(ages.get(i));
        activity.age.setText(ages.get(i));
        return item_view;
    }
}
