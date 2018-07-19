package com.example.yellowsoft.homeworkers;

import android.content.Context;

import com.google.gson.JsonObject;

import java.io.Serializable;

/**
 * Created by info on 18-07-2018.
 */

public class PartTimeWorkersList implements Serializable {
    public String id,type,area_id,area_title,area_title_ar,day,days,shift,workers,price,address,block,street,judda,house,
    request,status,date;
    public PartTimeWorkersList(JsonObject jsonObject, Context context){
        id =jsonObject.get("id").getAsString();
        type = jsonObject.get("type").getAsString();
        area_id = jsonObject.get("area").getAsJsonObject().get("id").getAsString();
        area_title = jsonObject.get("area").getAsJsonObject().get("title").getAsString();
        area_title_ar = jsonObject.get("area").getAsJsonObject().get("title_ar").getAsString();
        day = jsonObject.get("day").getAsString();
        days = jsonObject.get("days").getAsString();
        shift = jsonObject.get("shift").getAsString();
        workers = jsonObject.get("workers").getAsString();
        price = jsonObject.get("price").getAsString();
        address = jsonObject.get("address").getAsString();
        block = jsonObject.get("block").getAsString();
        street = jsonObject.get("street").getAsString();
        judda = jsonObject.get("judda").getAsString();
        house = jsonObject.get("house").getAsString();
        request = jsonObject.get("request").getAsString();
        status = jsonObject.get("status").getAsString();
        date = jsonObject.get("date").getAsString();
    }
}
