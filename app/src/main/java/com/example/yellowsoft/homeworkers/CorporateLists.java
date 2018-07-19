package com.example.yellowsoft.homeworkers;

import android.content.Context;

import com.google.gson.JsonObject;

import java.io.Serializable;

/**
 * Created by info on 18-07-2018.
 */

public class CorporateLists implements Serializable {
    public String id,company_name,phone_number,address,contact_person,selected_categories,number_of_workers,salary,
            other_benefits,email_address,contact_number,company_address,image1,image2,image3,status,date;
    public CorporateLists(JsonObject jsonObject, Context context){
        id = jsonObject.get("id").getAsString();
        company_name = jsonObject.get("company_name").getAsString();
        phone_number = jsonObject.get("phone_number").getAsString();
        address = jsonObject.get("address").getAsString();
        contact_person = jsonObject.get("contact_person").getAsString();
        selected_categories = jsonObject.get("selected_categories").getAsString();
        number_of_workers = jsonObject.get("number_of_workers").getAsString();
        salary = jsonObject.get("salary").getAsString();
        other_benefits = jsonObject.get("other_benefits").getAsString();
        email_address = jsonObject.get("email_address").getAsString();
        contact_number = jsonObject.get("contact_number").getAsString();
        company_address = jsonObject.get("company_address").getAsString();
        image1 = jsonObject.get("image1").getAsString();
        image2 = jsonObject.get("image2").getAsString();
        image3 = jsonObject.get("image3").getAsString();
        status = jsonObject.get("status").getAsString();
        date = jsonObject.get("date").getAsString();

    }
}
