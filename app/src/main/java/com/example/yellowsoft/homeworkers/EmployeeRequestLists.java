package com.example.yellowsoft.homeworkers;

import android.content.Context;

import com.google.gson.JsonObject;

import java.io.Serializable;

/**
 * Created by info on 18-07-2018.
 */

public class EmployeeRequestLists implements Serializable {
    public String id,first_name,middle_name,family_name,date_of_birth,place_of_birth,nationality,
            residency_expiredate,article_number,address,telephone,mobile,email,position_id,position_title,position_title_ar,
            do_you_work_now,when_can_youstart,expected_salary,marital_status,are_you_employed,companyName,
            maywe_contact_yourcurrent_employer,fullname_first,occupation_first,company_first,
            contactdetails_first,fullname_second,occupation_second,company_second,contactdetails_second,
            fullname_third,occupation_third,company_third,contactdetails_third,applied_before,when_details,relatives_incompany,name_relationship,school_first,
            years_first,graduated_date_first,certifate_first,school_second,years_second,graduated_date_second,certifate_second,
            school_third,years_third,graduated_date_third,certifate_third,school_fourth,years_fourth,graduated_date_fourth,certifate_fourth,english,
            arabic,other,msoffice,typing,status,date;
    public EmployeeRequestLists(JsonObject jsonObject, Context context){
        id = jsonObject.get("id").getAsString();
        status = jsonObject.get("status").getAsString();
        date = jsonObject.get("date").getAsString();

    }
}
