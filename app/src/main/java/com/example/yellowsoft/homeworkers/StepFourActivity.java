package com.example.yellowsoft.homeworkers;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.koushikdutta.ion.Ion;

import java.util.Calendar;

/**
 * Created by info on 16-07-2018.
 */

public class StepFourActivity extends Activity {
    ImageView back_btn;
    TextView previous_btn,submit_btn,from_date_option,to_date_option,fromdate_val,todate_val,fromdate,todate;
    EditText name,last_position,start,last,employer_option,lastposition,
            start_option,last_option,employer_val,position_val,start_text,last_text;
    LinearLayout from_date_layout,to_date_layout,fromdate_layout,todate_layout,fromdate_,todate_;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_request_page4);
        back_btn = (ImageView) findViewById(R.id.back_btn);
        previous_btn = (TextView) findViewById(R.id.previous_btn);
        submit_btn = (TextView) findViewById(R.id.submit_btn);
        from_date_option = (TextView) findViewById(R.id.from_date_option);
        to_date_option = (TextView) findViewById(R.id.to_date_option);
        fromdate_val = (TextView) findViewById(R.id.fromdate_val);
        todate_val = (TextView) findViewById(R.id.todate_val);
        fromdate = (TextView) findViewById(R.id.fromdate);
        todate = (TextView) findViewById(R.id.todate);
        name = (EditText) findViewById(R.id.name);
        last_position = (EditText) findViewById(R.id.last_position);
        start = (EditText) findViewById(R.id.start);
        last = (EditText) findViewById(R.id.last);
        employer_option = (EditText) findViewById(R.id.employer_option);
        lastposition = (EditText) findViewById(R.id.lastposition);
        start_option = (EditText) findViewById(R.id.start_option);
        last_option = (EditText) findViewById(R.id.last_option);
        employer_val = (EditText) findViewById(R.id.employer_val);
        position_val = (EditText) findViewById(R.id.position_val);
        start_text = (EditText) findViewById(R.id.start_text);
        last_text = (EditText) findViewById(R.id.last_text);
        from_date_layout = (LinearLayout) findViewById(R.id.from_date_layout);
        to_date_layout = (LinearLayout) findViewById(R.id.to_date_layout);
        fromdate_layout = (LinearLayout) findViewById(R.id.fromdate_layout);
        todate_layout = (LinearLayout) findViewById(R.id.todate_layout);
        fromdate_ = (LinearLayout) findViewById(R.id.fromdate_);
        todate_ = (LinearLayout) findViewById(R.id.todate_);

        from_date_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker();
            }
        });

        to_date_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker2();
            }

        });

        fromdate_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker3();
            }
        });

        todate_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker4();
            }
        });

        fromdate_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker5();
            }
        });

        todate_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker6();
            }
        });



        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StepFourActivity.this.onBackPressed();
            }
        });
        previous_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StepFourActivity.this.onBackPressed();
            }
        });
    }


    private void datePicker(){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        String date_time = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        from_date_option.setText(date_time);
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
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        String date_time = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        to_date_option.setText(date_time);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void datePicker3(){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        String date_time = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        fromdate_val.setText(date_time);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void datePicker4(){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        String date_time = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        todate_val.setText(date_time);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void datePicker5(){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        String date_time = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        fromdate.setText(date_time);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void datePicker6(){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        String date_time = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        todate.setText(date_time);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

//    public void get_employee(){
//        Ion.with(this)
//                .load(Session.SERVER_URL+"add-employee.php")
//                .setBodyParameter("member_id",Session.GetUserId(this))
//                .setBodyParameter("content",)
//    }

}
