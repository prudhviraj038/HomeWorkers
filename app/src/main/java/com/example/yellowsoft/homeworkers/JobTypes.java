package com.example.yellowsoft.homeworkers;

import android.content.Context;

import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by info on 18-07-2018.
 */

public class JobTypes implements Serializable {
    public String id,title,title_ar;
    ArrayList<Questions> questions;
    public JobTypes(Context context, JsonObject jsonObject){
        id = jsonObject.get("id").getAsString();
        title = jsonObject.get("title").getAsString();
        title_ar = jsonObject.get("title_ar").getAsString();

        questions = new ArrayList<>();
        for (int i=0;i<jsonObject.get("questions_list").getAsJsonArray().size();i++){
            Questions question = new Questions(jsonObject.get("questions_list").getAsJsonArray().get(i).getAsJsonObject(),context);
            questions.add(question);
        }
    }

    public class Questions implements Serializable{
        public String id,title,title_ar,type;
        public Questions(JsonObject jsonObject,Context context){
            id = jsonObject.get("id").getAsString();
            title = jsonObject.get("title").getAsString();
            title_ar = jsonObject.get("title_ar").getAsString();
            type = jsonObject.get("type").getAsString();

        }
    }
}
