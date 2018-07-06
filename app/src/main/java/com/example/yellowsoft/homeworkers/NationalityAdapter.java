package com.example.yellowsoft.homeworkers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by info on 25-05-2018.
 */

public class NationalityAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<Nationality> nationalities;

    public NationalityAdapter(Context context,ArrayList<Nationality> nationalities){
        this.context = context;
        this.nationalities = nationalities;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return nationalities.size();
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
        title.setText(nationalities.get(i).title);
        return item_view;
    }
}
