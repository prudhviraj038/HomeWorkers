package com.example.yellowsoft.homeworkers;

import android.content.Context;

import com.google.gson.JsonObject;

import java.io.Serializable;

/**
 * Created by info on 17-04-2018.
 */

public class Settings implements Serializable {
    public String logo,title,title_ar,email,phone,avail_amount,about,about_ar,contact,contact_ar,privacy,privacy_ar,terms,terms_ar;
    public Settings(JsonObject jsonObject, Context context){
        logo = jsonObject.get("logo").getAsString();
        title = jsonObject.get("title").getAsString();
        title_ar = jsonObject.get("title_ar").getAsString();
        email = jsonObject.get("email").getAsString();
        phone = jsonObject.get("phone").getAsString();
        avail_amount = jsonObject.get("avail_amount").getAsString();
        about = jsonObject.get("about").getAsString();
        about_ar = jsonObject.get("about_ar").getAsString();
        contact = jsonObject.get("contact").getAsString();
        contact_ar = jsonObject.get("contact_ar").getAsString();
        privacy = jsonObject.get("privacy").getAsString();
        privacy_ar = jsonObject.get("privacy_ar").getAsString();
        terms = jsonObject.get("terms").getAsString();
        terms_ar = jsonObject.get("terms_ar").getAsString();
    }
}
