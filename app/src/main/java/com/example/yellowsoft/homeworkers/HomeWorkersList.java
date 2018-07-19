package com.example.yellowsoft.homeworkers;

import android.content.Context;

import com.google.gson.JsonObject;

import java.io.Serializable;

/**
 * Created by info on 14-07-2018.
 */

public class HomeWorkersList implements Serializable {
    public String id,type,age,exp_kuwait,phone,status,nationality_id,nationality_title,nationality_title_ar,service_id,service_title,
    service_title_ar,religion_id,religion_title,religion_title_ar,date;
    public HomeWorkersList(JsonObject jsonObject, Context context){
        id =  jsonObject.get("id").getAsString();
        type = jsonObject.get("type").getAsString();
        age = jsonObject.get("age").getAsString();
        exp_kuwait = jsonObject.get("exp_kuwait").getAsString();
        phone = jsonObject.get("phone").getAsString();
        status = jsonObject.get("status").getAsString();
        date = jsonObject.get("date").getAsString();
        nationality_id = jsonObject.get("nationality").getAsJsonObject().get("id").getAsString();
        nationality_title = jsonObject.get("nationality").getAsJsonObject().get("title").getAsString();
        nationality_title_ar = jsonObject.get("nationality").getAsJsonObject().get("title_ar").getAsString();
        service_id = jsonObject.get("service").getAsJsonObject().get("id").getAsString();
        service_title= jsonObject.get("service").getAsJsonObject().get("title").getAsString();
        service_title_ar = jsonObject.get("service").getAsJsonObject().get("title_ar").getAsString();
        religion_id = jsonObject.get("religion").getAsJsonObject().get("id").getAsString();
        religion_title = jsonObject.get("religion").getAsJsonObject().get("title").getAsString();
        religion_title_ar = jsonObject.get("religion").getAsJsonObject().get("title_ar").getAsString();

    }
}
