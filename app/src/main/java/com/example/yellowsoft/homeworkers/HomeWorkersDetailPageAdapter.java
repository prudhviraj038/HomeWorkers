package com.example.yellowsoft.homeworkers;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by info on 11-04-2018.
 */

public class HomeWorkersDetailPageAdapter extends RecyclerView.Adapter<HomeWorkersDetailPageAdapter.MyViewHolder> {
    Context context;
    ArrayList<WorkerComments> workerComments;





    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView member_name,comment;
        RatingBar rating;
        public MyViewHolder(View view) {
            super(view);
            member_name = (TextView) view.findViewById(R.id.member_name);
            comment = (TextView) view.findViewById(R.id.comment);
            rating = (RatingBar) view.findViewById(R.id.rating);



        }


    }


    public HomeWorkersDetailPageAdapter(Context context,ArrayList<WorkerComments> workerComments) {
        this.context = context;
        this.workerComments =workerComments;


    }


    @Override
    public HomeWorkersDetailPageAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("coming","coming or not");
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.homeworkersdetailspage_items, parent, false);
        return new HomeWorkersDetailPageAdapter.MyViewHolder(itemView);
    }
    String temp="";
    @Override
    public void onBindViewHolder(final HomeWorkersDetailPageAdapter.MyViewHolder holder, final int position) {
           holder.member_name.setText(workerComments.get(position).firstname+""+workerComments.get(position).lastname);
           holder.comment.setText(workerComments.get(position).comments);
           Float d = Float.valueOf(workerComments.get(position).rating);
           holder.rating.setRating(d);
           Log.e("ratingvalue",d.toString());
    }

    @Override
    public int getItemCount() {

        return workerComments.size();

    }

}

