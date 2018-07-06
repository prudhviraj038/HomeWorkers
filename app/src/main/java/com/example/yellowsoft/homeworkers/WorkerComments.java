package com.example.yellowsoft.homeworkers;

import android.content.Context;

import com.google.gson.JsonObject;

import java.io.Serializable;

/**
 * Created by info on 18-04-2018.
 */

public class WorkerComments implements Serializable {
    public String id,comments,rating,member_id,firstname,lastname,email,phone;
    public WorkerComments(JsonObject jsonObject, Context context){
        id = jsonObject.get("id").getAsString();
        comments = jsonObject.get("comments").getAsString();
        rating = jsonObject.get("rating").getAsString();
        member_id = jsonObject.get("member").getAsJsonObject().get("id").getAsString();
        firstname = jsonObject.get("member").getAsJsonObject().get("firstname").getAsString();
        lastname = jsonObject.get("member").getAsJsonObject().get("lastname").getAsString();
        email = jsonObject.get("member").getAsJsonObject().get("email").getAsString();
        phone = jsonObject.get("member").getAsJsonObject().get("phone").getAsString();
    }
}
