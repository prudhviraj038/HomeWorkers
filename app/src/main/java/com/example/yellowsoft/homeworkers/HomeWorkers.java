package com.example.yellowsoft.homeworkers;

import android.content.Context;

import com.google.gson.JsonObject;

import java.io.Serializable;

/**
 * Created by info on 17-04-2018.
 */

public class HomeWorkers implements Serializable {
    public String id,name,name_ar,applicant_id,age,salary,image,amount,part_amount,experience,nationality_id,nationality_title,
            nationality_title_ar,religion_id,religion_title,religion_title_ar;
    public HomeWorkers(JsonObject jsonObject,Context context){
        id = jsonObject.get("id").getAsString();
        name = jsonObject.get("name").getAsString();
        name_ar = jsonObject.get("name_ar").getAsString();
        applicant_id = jsonObject.get("applicant_id").getAsString();
        age = jsonObject.get("age").getAsString();
        salary = jsonObject.get("salary").getAsString();
        image = jsonObject.get("image").getAsString();
        amount = jsonObject.get("amount").getAsString();
        part_amount = jsonObject.get("part_amount").getAsString();
        experience = jsonObject.get("experience").getAsString();
        nationality_id = jsonObject.get("nationality").getAsJsonObject().get("id").getAsString();
        nationality_title = jsonObject.get("nationality").getAsJsonObject().get("title").getAsString();
        nationality_title_ar = jsonObject.get("nationality").getAsJsonObject().get("title_ar").getAsString();
        religion_id = jsonObject.get("religion").getAsJsonObject().get("id").getAsString();
        religion_title = jsonObject.get("religion").getAsJsonObject().get("title").getAsString();
        religion_title_ar = jsonObject.get("religion").getAsJsonObject().get("title_ar").getAsString();
    }
}
