package com.example.yellowsoft.homeworkers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by info on 29-05-2018.
 */

public class AmountAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<String> amount;

    public AmountAdapter(Context context,ArrayList<String> amount){
        this.context = context;
        this.amount = amount;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return amount.size();
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
        title.setText(amount.get(i));
        return item_view;
    }
}
