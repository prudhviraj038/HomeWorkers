package com.example.yellowsoft.homeworkers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;

import org.json.JSONObject;

import java.util.Locale;

/**
 * Created by info on 17-04-2018.
 */

public class Session {
    public static String PAYMENT_URL = "http://products.yellowsoft.in/homeworkers/api/Tapa.php?";
    public static String UPDATE_PAYMENT_URL = "http://products.yellowsoft.in/homeworkers/api/update_part_payment.php?";
    public static final String SERVER_URL = "http://products.yellowsoft.in/homeworkers/api/";
    public static final String Words_ar = "ar";
    public static final String Words_en = "en";
    public static final String mem_id = "mem_id";
    public static final String worker_id = "worker_id";
    public static final String lang = "lan";
    public static  final String setting = "settings";

    public static void SetUserId(Context context, String id) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(mem_id, id);
        editor.commit();
    }

    public static String GetUserId(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(mem_id, "-1");
    }

    public static void SetWorkerId(Context context, String id) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(worker_id, id);
        editor.commit();
    }

    public static String GetWorkerId(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(worker_id, "-1");
    }

    public static void forceRTLIfSupported(Activity activity) {
        Log.e(lang, PreferenceManager.getDefaultSharedPreferences(activity).getString(lang, "-1"));
        Resources res;
        DisplayMetrics dm;
        Configuration conf;
        if (GetLang(activity).equals(Words_en)) {
            res = activity.getResources();
            dm = res.getDisplayMetrics();
            conf = res.getConfiguration();
            conf.locale = new Locale(Words_en.toLowerCase());
            res.updateConfiguration(conf, dm);
            if (Build.VERSION.SDK_INT >= 17) {
                activity.getWindow().getDecorView().setLayoutDirection(0);
            }
        } else if (GetLang(activity).equals(Words_ar)) {
            res = activity.getResources();
            dm = res.getDisplayMetrics();
            conf = res.getConfiguration();
            conf.locale = new Locale(Words_ar.toLowerCase());
            res.updateConfiguration(conf, dm);
            if (Build.VERSION.SDK_INT >= 17) {
                activity.getWindow().getDecorView().setLayoutDirection(1);
            }
        } else {
            res = activity.getResources();
            dm = res.getDisplayMetrics();
            conf = res.getConfiguration();
            conf.locale = new Locale(Words_en.toLowerCase());
            res.updateConfiguration(conf, dm);
            if (Build.VERSION.SDK_INT >= 17) {
                activity.getWindow().getDecorView().setLayoutDirection(0);
            }
        }
    }

    public static void SetLang(Context context, String ar) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(lang, ar);
        editor.commit();
    }

    public static String GetLang(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(lang, Words_en);
    }

    public static void SetEnWords(Context context, String en) {
        Log.e("engres", en);
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(Words_en, en);
        editor.commit();
    }

    public static String GetEnWords(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(Words_en, "-1");
    }

    public static void SetArWords(Context context, String ar) {
        Log.e("arabicres", ar);
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(Words_ar, ar);
        editor.commit();
    }

    public static String GetArWords(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(Words_ar, "-1");
    }

    public static String GetWord(Context context, String word) {
        if (GetLang(context).equals(Words_ar)) {
            try {
                Log.e("ar_words", GetArWords(context));
                word = new JSONObject(GetArWords(context)).getString(word);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                word = new JSONObject(GetEnWords(context)).getString(word);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return word;
    }

    public static void SetSettings(Context context,String settings){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(setting,settings);
        editor.commit();
    }

    public static String GetSettings(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(setting,"-1");
    }
}
