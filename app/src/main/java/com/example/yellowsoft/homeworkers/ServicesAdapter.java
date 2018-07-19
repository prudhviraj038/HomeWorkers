package com.example.yellowsoft.homeworkers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by info on 04-07-2018.
 */

public class ServicesAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<Services> services;
    PartTimeWorkersActivity activity;

    public ServicesAdapter(Context context,ArrayList<Services> services,PartTimeWorkersActivity activity){
        this.context = context;
        this.services = services;
        inflater = LayoutInflater.from(context);
        this.activity = activity;
    }


    @Override
    public int getCount() {
        return services.size();
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
        View item_view = inflater.inflate(R.layout.nationality_religion_items,null);
        TextView title = (TextView) item_view.findViewById(R.id.title);
        title.setText(services.get(i).title);
        activity.service_id = services.get(i).id;
        Log.e("service_id",services.get(i).id);
        return item_view;
    }
}

