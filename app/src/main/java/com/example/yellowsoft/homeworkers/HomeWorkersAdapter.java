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

public class HomeWorkersAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<HomeWorkers> homeWorkers;

    public HomeWorkersAdapter(Context context,ArrayList<HomeWorkers> homeWorkers){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.homeWorkers = homeWorkers;
    }
    @Override
    public int getCount() {
        return homeWorkers.size();
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
        View item_view = inflater.inflate(R.layout.homeworkers_items,null);
        ImageView imageView = (ImageView) item_view.findViewById(R.id.image);
        TextView hw_id = (TextView) item_view.findViewById(R.id.hw_id);
        TextView hw_age = (TextView) item_view.findViewById(R.id.hw_age);
        TextView hw_nationality = (TextView) item_view.findViewById(R.id.hw_nationality);
        TextView hw_religion = (TextView) item_view.findViewById(R.id.hw_religion);
        TextView sal = (TextView) item_view.findViewById(R.id.sal);
        TextView amt = (TextView) item_view.findViewById(R.id.amt);
        TextView st_id = (TextView) item_view.findViewById(R.id.st_id);
        TextView st_age = (TextView) item_view.findViewById(R.id.st_age);
        TextView st_nationality = (TextView) item_view.findViewById(R.id.st_nationality);
        TextView st_religion = (TextView) item_view.findViewById(R.id.st_religion);
        TextView st_salary = (TextView) item_view.findViewById(R.id.st_salary);
        TextView st_amount = (TextView) item_view.findViewById(R.id.st_amount);
        Picasso.with(context).load(homeWorkers.get(i).image).into(imageView);
        hw_id.setText(homeWorkers.get(i).applicant_id);
        hw_age.setText(homeWorkers.get(i).age);
        hw_nationality.setText(homeWorkers.get(i).nationality_title);
        hw_religion.setText(homeWorkers.get(i).religion_title);
        sal.setText(homeWorkers.get(i).salary + " KD ");
        amt.setText(homeWorkers.get(i).amount + " KD ");
        Session.SetWorkerId(context,homeWorkers.get(i).id);
        st_id.setText(Session.GetWord(context,"Applicant ID"));
        st_age.setText(Session.GetWord(context,"Age"));
        st_nationality.setText(Session.GetWord(context,"Nationality"));
        st_religion.setText(Session.GetWord(context,"Religion"));
        st_salary.setText(Session.GetWord(context,"Salary"));
        st_amount.setText(Session.GetWord(context,"Amount"));
        return item_view;
    }
}
