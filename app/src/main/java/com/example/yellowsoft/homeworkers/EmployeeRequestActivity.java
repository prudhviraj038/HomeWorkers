package com.example.yellowsoft.homeworkers;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by info on 16-07-2018.
 */

public class EmployeeRequestActivity extends Activity {
    ImageView back_btn;
    EditText name,middle_name,place_of_birth,article_number,address,telephone_number,mobile_number,email,start,expected_sal;
    TextView nationality_option,expirydate_option,post_option,marital_status_option,next_btn,dateofbirth_option;
    LinearLayout nationality_popup,post_popup,marital_status_popup,nationality_layout,post_layout,status_layout;
    ImageView nationality_close_btn,status_close_btn,post_close_btn;
    ListView nationaloty_list,marital_status_list,post_list;
    NationalityAdapter adapter;
    ArrayList<Nationality> nationalitiesfrom_api;
    ArrayList<String> status;
    MartialStatusAdapter martialStatusAdapter;
    ArrayList<JobTypes> jobTypesfrom_api;
    JobTypesAdapter jobTypesAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_request_page);
        status = new ArrayList<>();
        nationalitiesfrom_api = new ArrayList<>();
        jobTypesfrom_api = new ArrayList<>();
        back_btn = (ImageView) findViewById(R.id.back_btn);
        next_btn = (TextView) findViewById(R.id.next_btn);
        post_popup = (LinearLayout) findViewById(R.id.post_popup);
        marital_status_popup = (LinearLayout) findViewById(R.id.marital_status_popup);
        status_close_btn = (ImageView) findViewById(R.id.status_close_btn);
        post_close_btn = (ImageView) findViewById(R.id.post_close_btn);
        marital_status_list = (ListView) findViewById(R.id.marital_status_list);
        post_list  = (ListView) findViewById(R.id.post_list);
        nationality_layout = (LinearLayout) findViewById(R.id.nationality_layout);
        post_layout = (LinearLayout) findViewById(R.id.post_layout);
        status_layout = (LinearLayout) findViewById(R.id.status_layout);

        status.add("Married");
        status.add("Single");
        status.add("Seperated");
        status.add("Divorced");
        status.add("Dependents");

        martialStatusAdapter = new MartialStatusAdapter(this,status);
        marital_status_list.setAdapter(martialStatusAdapter);

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeRequestActivity.this,StepTwoActivity.class);
                startActivity(intent);
            }
        });

        name = (EditText) findViewById(R.id.name);
        middle_name = (EditText) findViewById(R.id.middle_name);
        dateofbirth_option = (TextView) findViewById(R.id.dateofbirth_option);
        place_of_birth = (EditText) findViewById(R.id.place_of_birth);
        article_number = (EditText) findViewById(R.id.article_number);
        address = (EditText) findViewById(R.id.address);
        telephone_number = (EditText) findViewById(R.id.telephone_number);
        mobile_number = (EditText) findViewById(R.id.mobile_number);
        email = (EditText) findViewById(R.id.email);
        start = (EditText) findViewById(R.id.start);
        nationality_option = (TextView) findViewById(R.id.nationality_option);
        expirydate_option = (TextView) findViewById(R.id.expirydate_option);
        post_option = (TextView) findViewById(R.id.post_option);
        marital_status_option = (TextView) findViewById(R.id.marital_status_option);
        nationality_popup = (LinearLayout) findViewById(R.id.nationality_popup);
        nationality_close_btn = (ImageView) findViewById(R.id.nationality_close_btn);
        nationaloty_list = (ListView) findViewById(R.id.nationality_list);
        adapter = new NationalityAdapter(this,nationalitiesfrom_api);
        nationaloty_list.setAdapter(adapter);
        jobTypesAdapter = new JobTypesAdapter(this,jobTypesfrom_api);
        post_list.setAdapter(jobTypesAdapter);

        dateofbirth_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  datePicker();
            }
        });

        expirydate_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker2();
            }
        });

        nationality_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nationality_popup.setVisibility(View.VISIBLE);
            }
        });

        nationality_close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nationality_popup.setVisibility(View.GONE);
            }
        });

        nationaloty_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                nationality_popup.setVisibility(View.GONE);
                nationality_option.setText(nationalitiesfrom_api.get(i).title);
            }
        });

        post_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                post_popup.setVisibility(View.VISIBLE);
            }
        });

        post_close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                post_popup.setVisibility(View.GONE);
            }
        });

        post_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                post_popup.setVisibility(View.GONE);
                post_option.setText(jobTypesfrom_api.get(i).title);
            }
        });

        status_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                marital_status_popup.setVisibility(View.VISIBLE);
            }
        });

        status_close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                marital_status_popup.setVisibility(View.GONE);
            }
        });

        marital_status_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                marital_status_popup.setVisibility(View.GONE);
                marital_status_option.setText(status.get(i));
            }
        });
        get_nationalities();
        get_jobtypes();
    }


    private void datePicker(){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,int monthOfYear, int dayOfMonth) {

                        String date_time = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        dateofbirth_option.setText(date_time);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void datePicker2(){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,int monthOfYear, int dayOfMonth) {

                        String date_time = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        expirydate_option.setText(date_time);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }



    public void get_nationalities(){
        Ion.with(this)
                .load(Session.SERVER_URL+"nationality.php")
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        try {
                            for (int i = 0; i < result.size(); i++) {
                                Nationality nationality = new Nationality(result.get(i).getAsJsonObject(), EmployeeRequestActivity.this);
                                nationalitiesfrom_api.add(nationality);
                            }
                        }catch (Exception e1){
                            e1.printStackTrace();
                        }
                    }
                });
    }

    public void get_jobtypes(){
        Ion.with(this)
                .load(Session.SERVER_URL+"job_types.php")
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        try {
                            for (int i = 0; i < result.size(); i++) {
                                JobTypes jobTypes = new JobTypes( EmployeeRequestActivity.this,result.get(i).getAsJsonObject());
                                jobTypesfrom_api.add(jobTypes);
                            }
                        }catch (Exception e1){
                            e1.printStackTrace();
                        }
                    }
                });
    }
}
