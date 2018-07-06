package com.example.yellowsoft.homeworkers;

import android.content.Context;

import com.google.gson.JsonObject;

import java.io.Serializable;

/**
 * Created by info on 04-07-2018.
 */

public class Services implements Serializable {
    public String id,title,title_ar,morning_price,evening_price;
    public Services(JsonObject jsonObject, Context context){
         id = jsonObject.get("id").getAsString();
         title = jsonObject.get("title").getAsString();
         title_ar = jsonObject.get("title_ar").getAsString();
         morning_price = jsonObject.get("morning_price").getAsString();
         evening_price = jsonObject.get("evening_price").getAsString();
    }
}
