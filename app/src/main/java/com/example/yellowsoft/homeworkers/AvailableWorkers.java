package com.example.yellowsoft.homeworkers;

import android.content.Context;

import com.google.gson.JsonObject;

import java.io.Serializable;

/**
 * Created by info on 17-04-2018.
 */

public class AvailableWorkers implements Serializable {
    public String id,name,name_ar,applicant_id,age,salary,image,amount,experience,nationality_id,nationality_title,nationality_title_ar,
    religion_id,religion_title,religion_title_ar,member_id,firstname,lastname,email,phone;
    public AvailableWorkers(JsonObject jsonObject, Context context){
        id = jsonObject.get("id").getAsString();
        name = jsonObject.get("name").getAsString();
        name_ar = jsonObject.get("name_ar").getAsString();
        applicant_id = jsonObject.get("applicant_id").getAsString();
        age = jsonObject.get("age").getAsString();
        salary = jsonObject.get("salary").getAsString();
        image = jsonObject.get("image").getAsString();
        amount = jsonObject.get("amount").getAsString();
        experience = jsonObject.get("experience").getAsString();
        nationality_id = jsonObject.get("nationality").getAsJsonObject().get("id").getAsString();
        nationality_title = jsonObject.get("nationality").getAsJsonObject().get("title").getAsString();
        nationality_title_ar = jsonObject.get("nationality").getAsJsonObject().get("title_ar").getAsString();
        religion_id = jsonObject.get("religion").getAsJsonObject().get("id").getAsString();
        religion_title = jsonObject.get("religion").getAsJsonObject().get("title").getAsString();
        religion_title_ar =jsonObject.get("nationality").getAsJsonObject().get("title_ar").getAsString();
        member_id = jsonObject.get("member").getAsJsonObject().get("id").getAsString();
        firstname = jsonObject.get("member").getAsJsonObject().get("firstname").getAsString();
        lastname = jsonObject.get("member").getAsJsonObject().get("lastname").getAsString();
        email = jsonObject.get("member").getAsJsonObject().get("email").getAsString();
        phone = jsonObject.get("member").getAsJsonObject().get("phone").getAsString();
    }
}
