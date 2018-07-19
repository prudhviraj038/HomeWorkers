package com.example.yellowsoft.homeworkers;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by info on 16-07-2018.
 */

public class StepTwoActivity extends Activity {
    ImageView back_btn;
    TextView previous_btn,next_btn;
    LinearLayout yes,no,yes_btn,no_btn,yes_layout,yes_working,no_working,no_layout;
    TextView yes_option,no_option,yes_value,no_value,yes_val,no_val,when_option,yes_text,no_text;
    EditText company_name,full_name,occupation_val1,company_name_val,contact,full_name_val,occupation_val,company_name_text,contact_val,name_text,occupation_text,
    companyname,contact_details,relationship;
    ImageView yes_checkbox,no_checkbox,yes_check,no_check,yes_imageview,no_imageview,yes_,no_;
    String checked,checked1,checked2,checked3;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_request_page2);
        back_btn = (ImageView) findViewById(R.id.back_btn);
        previous_btn = (TextView) findViewById(R.id.previous_btn);
        next_btn = (TextView) findViewById(R.id.next_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StepTwoActivity.this.onBackPressed();
            }
        });

        yes = (LinearLayout) findViewById(R.id.yes);
        no = (LinearLayout) findViewById(R.id.no);
        yes_btn = (LinearLayout) findViewById(R.id.yes_btn);
        no_btn = (LinearLayout) findViewById(R.id.no_btn);
        yes_layout = (LinearLayout) findViewById(R.id.yes_layout);
        no_layout = (LinearLayout) findViewById(R.id.no_layout);
        yes_working = (LinearLayout) findViewById(R.id.yes_working);
        no_working = (LinearLayout) findViewById(R.id.no_working);
        yes_option = (TextView) findViewById(R.id.yes_option);
        no_option = (TextView) findViewById(R.id.no_option);
        yes_value = (TextView) findViewById(R.id.yes_value);
        no_value = (TextView) findViewById(R.id.no_value);
        yes_val = (TextView) findViewById(R.id.yes_val);
        no_val = (TextView) findViewById(R.id.no_val);
        when_option = (TextView) findViewById(R.id.when_option);
        yes_text = (TextView) findViewById(R.id.yes_text);
        no_text = (TextView) findViewById(R.id.no_text);
        yes_checkbox = (ImageView) findViewById(R.id.yes_checkbox);
        no_checkbox = (ImageView) findViewById(R.id.no_checkbox);
        yes_check = (ImageView) findViewById(R.id.yes_check);
        no_check = (ImageView) findViewById(R.id.no_check);
        yes_imageview = (ImageView) findViewById(R.id.yes_imageview);
        no_imageview = (ImageView) findViewById(R.id.no_imageview);
        yes_ = (ImageView) findViewById(R.id.yes_);
        no_ = (ImageView) findViewById(R.id.no_);


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (yes_checkbox.isEnabled()){
                    yes_checkbox.setImageResource(R.drawable.checked);
                    no_checkbox.setImageResource(R.drawable.unchecked);
                    checked="1";
                    //get_quantity();
                }else {
                    yes_checkbox.setImageResource(R.drawable.unchecked);
                }
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (no_checkbox.isEnabled()){
                    no_checkbox.setImageResource(R.drawable.checked);
                    yes_checkbox.setImageResource(R.drawable.unchecked);
                    checked="2";
                    //get_quantity();
                }else {
                    no_checkbox.setImageResource(R.drawable.unchecked);

                }
            }
        });

        yes_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (yes_check.isEnabled()){
                    yes_check.setImageResource(R.drawable.checked);
                    no_check.setImageResource(R.drawable.unchecked);
                    checked1="1";
                    //get_quantity();
                }else {
                    yes_check.setImageResource(R.drawable.unchecked);
                }
            }
        });

        no_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (no_check.isEnabled()){
                    no_check.setImageResource(R.drawable.checked);
                    yes_check.setImageResource(R.drawable.unchecked);
                    checked1="1";
                    //get_quantity();
                }else {
                    no_check.setImageResource(R.drawable.unchecked);
                }
            }
        });

        yes_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (yes_imageview.isEnabled()){
                    yes_imageview.setImageResource(R.drawable.checked);
                    no_imageview.setImageResource(R.drawable.unchecked);
                    checked2="1";
                    //get_quantity();
                }else {
                    yes_imageview.setImageResource(R.drawable.unchecked);
                }
            }
        });


        no_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (no_imageview.isEnabled()){
                    no_imageview.setImageResource(R.drawable.checked);
                    yes_imageview.setImageResource(R.drawable.unchecked);
                    checked3="1";
                    //get_quantity();
                }else {
                    no_imageview.setImageResource(R.drawable.unchecked);
                }
            }
        });


        yes_working.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (yes_.isEnabled()){
                    yes_.setImageResource(R.drawable.checked);
                    no_.setImageResource(R.drawable.unchecked);
                    checked3="1";
                    //get_quantity();
                }else {
                    yes_.setImageResource(R.drawable.unchecked);
                }
            }
        });

        no_working.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (no_.isEnabled()){
                    no_.setImageResource(R.drawable.checked);
                    yes_.setImageResource(R.drawable.unchecked);
                    checked2="1";
                    //get_quantity();
                }else {
                    no_.setImageResource(R.drawable.unchecked);
                }
            }
        });

        company_name = (EditText) findViewById(R.id.company_name);
        full_name = (EditText) findViewById(R.id.full_name);
        occupation_val1 = (EditText) findViewById(R.id.occupation_val1);
        company_name_val = (EditText) findViewById(R.id.company_name_val);
        contact = (EditText) findViewById(R.id.contact);
        full_name_val = (EditText) findViewById(R.id.full_name_val);
        occupation_val = (EditText) findViewById(R.id.occupation_val);
        company_name_text = (EditText) findViewById(R.id.company_name_text);
        contact_val = (EditText) findViewById(R.id.contact_val);
        name_text = (EditText) findViewById(R.id.name_text);
        occupation_text = (EditText) findViewById(R.id.occupation_text);
        companyname = (EditText) findViewById(R.id.companyname);
        contact_details = (EditText) findViewById(R.id.contact_details);
        relationship = (EditText) findViewById(R.id.relationship);

         when_option.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 datePicker();
             }
         });



        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StepTwoActivity.this,StepThreeActivity.class);
                startActivity(intent);
            }
        });

        previous_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StepTwoActivity.this.onBackPressed();
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
                        when_option.setText(date_time);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}
