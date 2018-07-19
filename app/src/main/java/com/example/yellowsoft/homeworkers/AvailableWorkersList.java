package com.example.yellowsoft.homeworkers;

import android.content.Context;

import com.google.gson.JsonObject;

import java.io.Serializable;

/**
 * Created by info on 18-07-2018.
 */

public class AvailableWorkersList implements Serializable {
    public String id,applicant_id,name,name_ar,age,nationality,email,phone,religion,salary,amount,
            part_amount,experience,experience_ar,exp_date,status,date;
    public AvailableWorkersList(JsonObject jsonObject, Context context){
        id = jsonObject.get("id").getAsString();
        applicant_id = jsonObject.get("applicant_id").getAsString();
        name = jsonObject.get("name").getAsString();
        name_ar = jsonObject.get("name_ar").getAsString();
        age = jsonObject.get("age").getAsString();
        nationality = jsonObject.get("nationality").getAsString();
        email = jsonObject.get("email").getAsString();
        phone = jsonObject.get("phone").getAsString();
        religion = jsonObject.get("religion").getAsString();
        salary = jsonObject.get("salary").getAsString();
        amount = jsonObject.get("amount").getAsString();
        part_amount = jsonObject.get("part_amount").getAsString();
        experience = jsonObject.get("experience").getAsString();
        experience_ar = jsonObject.get("experience_ar").getAsString();
        exp_date = jsonObject.get("exp_date").getAsString();
        status = jsonObject.get("status").getAsString();
        date = jsonObject.get("date").getAsString();
    }
}
