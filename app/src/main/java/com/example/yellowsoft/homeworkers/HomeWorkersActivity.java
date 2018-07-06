package com.example.yellowsoft.homeworkers;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

/**
 * Created by info on 11-04-2018.
 */

public class HomeWorkersActivity extends Activity {
    ImageView back_btn;
    ListView listView;
    HomeWorkersAdapter adapter;
    ArrayList<Integer> images;
    ArrayList<HomeWorkers> homeWorkersfrom_api;
    ImageView filter_btn,close_btn;
    LinearLayout filter_popup;
    ArrayList<Nationality>nationalitiesfrom_api;
    LinearLayout nationality_btn;
    TextView nationality_option;
    ArrayList<Religions> religionsfrom_api;
    LinearLayout religion_btn;
    TextView religion_option;
    EditText min,max;
    TextView submit_btn;
    TextView st_hw;
    String nationality,religion,sal_from,sal_to,nationality_id,religion_id;
    TextView st_filter,st_max_sal,st_min_sal,age_option,amount_option;
    LinearLayout nationality_popup,religion_popup,age_popup,amount_popup,age_btn,amount_btn;
    ImageView nationality_close_btn,religion_close_btn,age_close_btn,amount_close_btn;
    ListView nationality_list,religion_list,age_list,amount_list;
    NationalityAdapter nationalityAdapter;
    ReligionAdapter religionAdapter;
    AgeAdapter ageAdapter;
    AmountAdapter amountAdapter;
    ArrayList<String> age;
    ArrayList<String> amount;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homeworkers_list);
        Session.forceRTLIfSupported(this);
        homeWorkersfrom_api = new ArrayList<>();
        nationalitiesfrom_api = new ArrayList<>();
        religionsfrom_api = new ArrayList<>();
        images = new ArrayList<>();
        images.add(R.drawable.layer2);
        images.add(R.drawable.layer2);
        images.add(R.drawable.layer2);
        images.add(R.drawable.layer2);
        age = new ArrayList<>();
        for (int i=0;i<100;i++){
            age.add(String.valueOf(i+1));
            Log.e("result",String.valueOf(i+1));
        }
        amount = new ArrayList<>();
        for (int i=99;i<1000;i++){
            amount.add(String.valueOf(i+1));
        }
        age_btn = (LinearLayout) findViewById(R.id.age_btn);
        amount_btn = (LinearLayout) findViewById(R.id.amount_btn);
        age_option = (TextView) findViewById(R.id.age_option);
        amount_option = (TextView) findViewById(R.id.amount_option);
        age_popup = (LinearLayout) findViewById(R.id.age_popup);
        amount_popup = (LinearLayout) findViewById(R.id.amount_popup);
        age_close_btn = (ImageView) findViewById(R.id.age_close_btn);
        amount_close_btn = (ImageView) findViewById(R.id.amount_close_btn);
        age_list = (ListView) findViewById(R.id.age_list);
        amount_list = (ListView) findViewById(R.id.amount_list);
        nationality_popup = (LinearLayout) findViewById(R.id.nationality_popup);
        religion_popup = (LinearLayout) findViewById(R.id.religion_popup);
        nationality_close_btn = (ImageView) findViewById(R.id.nationality_close_btn);
        religion_close_btn = (ImageView) findViewById(R.id.religion_close_btn);
        nationality_list = (ListView) findViewById(R.id.nationality_list);
        religion_list =(ListView) findViewById(R.id.religion_list);
        back_btn = (ImageView) findViewById(R.id.back_btn);
        st_filter = (TextView) findViewById(R.id.st_filter);
        st_max_sal = (TextView) findViewById(R.id.st_max_sal);
        st_min_sal = (TextView) findViewById(R.id.st_min_sal);
        filter_btn = (ImageView) findViewById(R.id.filter_btn);
        close_btn = (ImageView) findViewById(R.id.close_btn);
        listView = (ListView) findViewById(R.id.homeworkers_list);
        filter_btn = (ImageView) findViewById(R.id.filter_btn);
        filter_popup = (LinearLayout) findViewById(R.id.filter_popup);
        nationality_btn = (LinearLayout) findViewById(R.id.nationality_btn);
        nationality_option = (TextView) findViewById(R.id.nationality_option);
        religion_btn = (LinearLayout) findViewById(R.id.religion_btn);
        st_hw = (TextView) findViewById(R.id.st_hw);
        religion_option = (TextView) findViewById(R.id.religion_option);
        min = (EditText) findViewById(R.id.min);
        max = (EditText) findViewById(R.id.max);
        submit_btn = (TextView) findViewById(R.id.submit_btn);
        adapter = new HomeWorkersAdapter(this,homeWorkersfrom_api);
        listView.setAdapter(adapter);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeWorkersActivity.this.onBackPressed();
            }
        });

        st_filter.setText(Session.GetWord(this,"Filter"));
        st_max_sal.setText(Session.GetWord(this,"Maximum Sal"));
        st_min_sal.setText(Session.GetWord(this,"Minimum Sal"));
        nationality_option.setHint(Session.GetWord(this,"Select Nationality"));
        religion_option.setHint(Session.GetWord(this,"Select Religion"));
        max.setHint(Session.GetWord(this,"Max Sal"));
        min.setHint(Session.GetWord(this,"Min Sal"));

        st_hw.setText(Session.GetWord(this,"Home Workers"));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(HomeWorkersActivity.this,HomeWorkersDetailPage.class);
                intent.putExtra("hw",homeWorkersfrom_api.get(i));
                startActivity(intent);
            }
        });

        nationalityAdapter = new NationalityAdapter(this,nationalitiesfrom_api);
        nationality_list.setAdapter(nationalityAdapter);

        religionAdapter = new ReligionAdapter(this,religionsfrom_api);
        religion_list.setAdapter(religionAdapter);

        ageAdapter = new AgeAdapter(this,age);
        age_list.setAdapter(ageAdapter);

        amountAdapter = new AmountAdapter(this,amount);
        amount_list.setAdapter(amountAdapter);

        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filter_popup.setVisibility(View.GONE);
            }
        });

        filter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nationality_option.getText().toString().equals("");
                religion_option.getText().toString().equals("");
                max.getText().toString().equals("");
                min.getText().toString().equals("");
                filter_popup.setVisibility(View.VISIBLE);
            }
        });

        nationality_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nationality_popup.setVisibility(View.VISIBLE);
                Animation anim = AnimationUtils.loadAnimation(HomeWorkersActivity.this, R.anim.myanim);
                nationality_popup.startAnimation(anim);
//                Dialog dialog = onNationalitiesChoice();
//                dialog.show();
            }
        });

        nationality_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                nationality_popup.setVisibility(View.GONE);
                nationality_option.setText(nationalitiesfrom_api.get(i).title);
            }
        });

        religion_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                religion_popup.setVisibility(View.GONE);
                religion_option.setText(religionsfrom_api.get(i).title);
            }
        });

        age_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                age_popup.setVisibility(View.VISIBLE);
                Animation anim = AnimationUtils.loadAnimation(HomeWorkersActivity.this, R.anim.myanim);
                age_popup.startAnimation(anim);
            }
        });

        amount_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount_popup.setVisibility(View.VISIBLE);
                Animation anim = AnimationUtils.loadAnimation(HomeWorkersActivity.this, R.anim.myanim);
                amount_popup.startAnimation(anim);
            }
        });


        age_close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                age_popup.setVisibility(View.GONE);
            }
        });

        amount_close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount_popup.setVisibility(View.GONE);
            }
        });


        age_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                age_popup.setVisibility(View.GONE);
                age_option.setText(age.get(i));
            }
        });

        amount_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                amount_popup.setVisibility(View.GONE);
                amount_option.setText(amount.get(i));
            }
        });


        religion_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                religion_popup.setVisibility(View.VISIBLE);
                Animation anim = AnimationUtils.loadAnimation(HomeWorkersActivity.this, R.anim.myanim);
                religion_popup.startAnimation(anim);
//                Dialog dialog = onReligionsChoice();
//                dialog.show();
            }
        });

        nationality_close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nationality_popup.setVisibility(View.GONE);

            }
        });

        religion_close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                religion_popup.setVisibility(View.GONE);
            }
        });

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filter_popup.setVisibility(View.GONE);
                homeWorkersfrom_api.clear();
                get_home_workers();
            }
        });

        get_home_workers();
        get_nationalities();
        get_religions();

    }


    public void get_nationalities(){
        final KProgressHUD hud = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setLabel("Please wait").setCancellable(true).setMaxProgress(100).show();
        Ion.with(this)
                .load(Session.SERVER_URL+"nationality.php")
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        try {
                            Log.e("nationalityrespinse", result.toString());
                            hud.dismiss();
                            for (int i = 0; i < result.size(); i++) {
                                Nationality nationality = new Nationality(result.get(i).getAsJsonObject(), HomeWorkersActivity.this);
                                nationalitiesfrom_api.add(nationality);
                            }
                        }catch (Exception e1){
                            e1.printStackTrace();
                        }
                    }
                });
    }

    public void get_religions(){
        final KProgressHUD hud = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setLabel("Please wait").setCancellable(true).setMaxProgress(100).show();
        Ion.with(this)
                .load(Session.SERVER_URL+"religions.php")
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        try {
                            Log.e("religionresponse", result.toString());
                            hud.dismiss();
                            for (int i = 0; i < result.size(); i++) {
                                Religions religions = new Religions(result.get(i).getAsJsonObject(), HomeWorkersActivity.this);
                                religionsfrom_api.add(religions);
                            }
                        }catch (Exception e1){
                            e1.printStackTrace();
                        }
                    }
                });
    }


    public Dialog onNationalitiesChoice() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final CharSequence[] array = new CharSequence[nationalitiesfrom_api.size()];
        for(int i=0;i<nationalitiesfrom_api.size();i++){
            array[i] = nationalitiesfrom_api.get(i).title;
            Log.e("message",nationalitiesfrom_api.get(i).title);
        }
        builder.setTitle("Select Nationalities").setSingleChoiceItems(array, 0, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int i) {
                String selectedItem = array[i].toString();
                Log.e("select",selectedItem);
                nationality_option.setText(selectedItem);
                nationality_id = nationalitiesfrom_api.get(i).id;


            }
        })

                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        return builder.create();
    }

    public Dialog onReligionsChoice() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final CharSequence[] array = new CharSequence[religionsfrom_api.size()];
        for(int i=0;i<religionsfrom_api.size();i++){
            array[i] = religionsfrom_api.get(i).title;
            Log.e("message",religionsfrom_api.get(i).title);
        }
        builder.setTitle("Select Nationalities").setSingleChoiceItems(array, 0, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int i) {
                String selectedItem = array[i].toString();
                Log.e("select",selectedItem);
                religion_option.setText(selectedItem);
                religion_id = religionsfrom_api.get(i).id;


            }
        })

                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        return builder.create();
    }

    public void get_home_workers(){
        final KProgressHUD hud = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setLabel("Please wait").setCancellable(true).setMaxProgress(100).show();
        Ion.with(this)
                .load(Session.SERVER_URL+"home_workers.php")
                .setBodyParameter("nationality",nationality_id)
                .setBodyParameter("religion",religion_id)
                .setBodyParameter("salary_from",min.getText().toString())
                .setBodyParameter("salary_to",max.getText().toString())
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        try {
                            Log.e("hwresponse", result.toString());
                            hud.dismiss();
                            for (int i = 0; i < result.size(); i++) {
                                HomeWorkers homeWorkers = new HomeWorkers(result.get(i).getAsJsonObject(), HomeWorkersActivity.this);
                                homeWorkersfrom_api.add(homeWorkers);
                            }
                            adapter.notifyDataSetChanged();
                        }catch (Exception e1){
                            e1.printStackTrace();
                        }
                    }
                });
    }



}
