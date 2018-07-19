package com.example.yellowsoft.homeworkers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by info on 11-04-2018.
 */

public class AvailableWorkersAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<AvailableWorkers> availableWorkers;

    public AvailableWorkersAdapter(Context context,ArrayList<AvailableWorkers> availableWorkers){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.availableWorkers = availableWorkers;
    }
    @Override
    public int getCount() {
        return availableWorkers.size();
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
        View item_view = inflater.inflate(R.layout.availableworkers_items,null);
        ImageView imageView = (ImageView) item_view.findViewById(R.id.aw_image);
        TextView aw_id = (TextView) item_view.findViewById(R.id.aw_id);
        TextView aw_age = (TextView) item_view.findViewById(R.id.aw_age);
        TextView aw_nationality = (TextView) item_view.findViewById(R.id.aw_nationality);
        TextView aw_religion = (TextView) item_view.findViewById(R.id.aw_religion);
        TextView aw_sal = (TextView) item_view.findViewById(R.id.aw_sal);
        TextView aw_amt = (TextView) item_view.findViewById(R.id.aw_amt);
        TextView st_id = (TextView) item_view.findViewById(R.id.st_id);
        TextView st_age = (TextView) item_view.findViewById(R.id.st_age);
        TextView st_nationality = (TextView) item_view.findViewById(R.id.st_nationality);
        TextView st_religion = (TextView) item_view.findViewById(R.id.st_religion);
        TextView st_salary = (TextView) item_view.findViewById(R.id.st_salary);
        TextView st_amount = (TextView) item_view.findViewById(R.id.st_amount);
        Picasso.with(context).load(availableWorkers.get(i).image).into(imageView);
        aw_id.setText(availableWorkers.get(i).name);
        aw_age.setText(availableWorkers.get(i).age);
        aw_nationality.setText(availableWorkers.get(i).nationality_title);
        aw_religion.setText(availableWorkers.get(i).religion_title);
        aw_sal.setText(availableWorkers.get(i).salary + " KD ");
        aw_amt.setText(availableWorkers.get(i).amount + " KD ");
        st_id.setText(Session.GetWord(context,"Applicant ID"));
        st_age.setText(Session.GetWord(context,"Age"));
        st_nationality.setText(Session.GetWord(context,"Nationality"));
        st_religion.setText(Session.GetWord(context,"Religion"));
        st_salary.setText(Session.GetWord(context,"Salary"));
        st_amount.setText(Session.GetWord(context,"Amount"));
        return item_view;
    }
}
