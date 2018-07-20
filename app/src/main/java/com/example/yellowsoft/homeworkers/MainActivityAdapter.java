package com.example.yellowsoft.homeworkers;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by info on 11-04-2018.
 */

public class MainActivityAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<String> titles;
    ArrayList<String> images;
     float value = 3.0f;

    public MainActivityAdapter(Context context,ArrayList<String> titles,ArrayList<String> images){
        this.context = context;
        this.titles = titles;
        this.images = images;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return titles.size();
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
        View item_view = inflater.inflate(R.layout.home_items,null);
        TextView title = (TextView) item_view.findViewById(R.id.title);
        ImageView imageView = (ImageView) item_view.findViewById(R.id.image);
        TextView add_btn = (TextView) item_view.findViewById(R.id.add_btn);
        try {
            Picasso.with(context).load(images.get(i)).into(imageView);
        }catch (Exception e){
            e.printStackTrace();
        }

        title.setText(titles.get(i));


               if (titles.get(i).equals("Available Workers")){
             add_btn.setVisibility(View.VISIBLE);
             add_btn.setText("Add Available Workers");
             add_btn.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     if (Session.GetUserId(context).equals("-1")){
                         Intent intent = new Intent(context,RegisterActivity.class);
                         context.startActivity(intent);
                     }else {
                         Intent intent = new Intent(context, AddAvailableWorkersActivity.class);
                         context.startActivity(intent);
                     }
                 }
             });
        }

        if (titles.get(i).equals("Home Workers")){
             add_btn.setVisibility(View.VISIBLE);
             add_btn.setText("Request Home Workers");
             add_btn.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     if (Session.GetUserId(context).equals("-1")){
                         Intent intent = new Intent(context,RegisterActivity.class);
                         context.startActivity(intent);
                     }else {
                         Intent intent = new Intent(context,RequestHomeWorkersActivity.class);
                         context.startActivity(intent);
                     }
                 }
             });
        }

          if (titles.get(i).equals("Available Workers")){
             title.setText(Session.GetWord(context,"AVAILABLE  WORKERS"));
         }

        if (titles.get(i).equals("Home Workers")){
            title.setText(Session.GetWord(context,"HOME WORKERS"));
        }else if (titles.get(i).equals("Part-Time Workers")){
            title.setText(Session.GetWord(context,"PART TIME WORKERS"));
        }

        return item_view;
    }
}
