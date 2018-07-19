package com.example.yellowsoft.homeworkers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by info on 16-07-2018.
 */

public class StepThreeActivity extends Activity {
    ImageView back_btn;
    TextView previous_btn,next_btn,excellent_option,arabiclevel_option,otherlevel_option,msofficelevel_option,typing_option;
    EditText school_name,years,graduated_date,certificate_number,schoolname,years_option,date_option,certificate_option,name_option,
            years_val,date_val,number_val,name_val,years_text,date_text,number_text;
    LinearLayout excellent_popup,arabiclevel_popup,otherlevel_popup,mslevel_popup,typing_popup,excellent_layout,other_layout,msoffice_layout,arabic_layout,typing_layout;
    ImageView excellent_close_btn,arabic_close_btn,other_close_btn,mslevel_close_btn,typing_close_btn;
    ListView excellent_list,arabic_list,other_list,mslevel_list,typing_list;
    ArrayList<String> titles;
    EnglishLevelAdapter englishLevelAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_request_page3);
        titles = new ArrayList<>();
        titles.add("Excellent");
        titles.add("Good");
        titles.add("Fair");
        excellent_close_btn = (ImageView) findViewById(R.id.excellent_close_btn);
        arabic_close_btn = (ImageView) findViewById(R.id.arabic_close_btn);
        other_close_btn = (ImageView) findViewById(R.id.other_close_btn);
        mslevel_close_btn = (ImageView) findViewById(R.id.mslevel_close_btn);
        typing_close_btn = (ImageView) findViewById(R.id.typing_close_btn);
        excellent_list = (ListView) findViewById(R.id.excellent_list);
        arabic_list = (ListView) findViewById(R.id.arabic_list);
        other_list = (ListView) findViewById(R.id.other_list);
        mslevel_list = (ListView) findViewById(R.id.mslevel_list);
        typing_list = (ListView) findViewById(R.id.typing_list);
        back_btn = (ImageView) findViewById(R.id.back_btn);
        previous_btn = (TextView) findViewById(R.id.previous_btn);
        next_btn = (TextView) findViewById(R.id.next_btn);
        excellent_option = (TextView) findViewById(R.id.excellent_option);
        arabiclevel_option = (TextView) findViewById(R.id.arabiclevel_option);
        otherlevel_option = (TextView) findViewById(R.id.otherlevel_option);
        msofficelevel_option = (TextView) findViewById(R.id.msofficelevel_option);
        typing_option = (TextView) findViewById(R.id.typing_option);
        school_name = (EditText) findViewById(R.id.school_name);
        years = (EditText) findViewById(R.id.years);
        graduated_date = (EditText) findViewById(R.id.graduated_date);
        certificate_number = (EditText) findViewById(R.id.certificate_number);
        schoolname = (EditText) findViewById(R.id.schoolname);
        years_option = (EditText) findViewById(R.id.years_option);
        date_option = (EditText) findViewById(R.id.date_option);
        certificate_option = (EditText) findViewById(R.id.certificate_option);
        years_val = (EditText) findViewById(R.id.years_val);
        date_val = (EditText) findViewById(R.id.date_val);
        number_val = (EditText) findViewById(R.id.number_val);
        name_val = (EditText) findViewById(R.id.name_val);
        years_text = (EditText) findViewById(R.id.years_text);
        date_text = (EditText) findViewById(R.id.date_text);
        number_text = (EditText) findViewById(R.id.number_text);
        excellent_popup = (LinearLayout) findViewById(R.id.excellent_popup);
        arabiclevel_popup = (LinearLayout) findViewById(R.id.arabiclevel_popup);
        otherlevel_popup = (LinearLayout) findViewById(R.id.otherlevel_popup);
        mslevel_popup = (LinearLayout) findViewById(R.id.mslevel_popup);
        typing_popup = (LinearLayout) findViewById(R.id.typing_popup);
        excellent_layout = (LinearLayout) findViewById(R.id.excellent_layout);
        arabic_layout = (LinearLayout) findViewById(R.id.arabic_layout);
        other_layout = (LinearLayout) findViewById(R.id.other_layout);
        msoffice_layout = (LinearLayout) findViewById(R.id.msoffice_layout);
        typing_layout = (LinearLayout) findViewById(R.id.typing_layout);


        englishLevelAdapter = new EnglishLevelAdapter(this,titles);
        excellent_list.setAdapter(englishLevelAdapter);

        englishLevelAdapter = new EnglishLevelAdapter(this,titles);
        other_list.setAdapter(englishLevelAdapter);

        englishLevelAdapter = new EnglishLevelAdapter(this,titles);
        arabic_list.setAdapter(englishLevelAdapter);

        englishLevelAdapter = new EnglishLevelAdapter(this,titles);
        mslevel_list.setAdapter(englishLevelAdapter);

        englishLevelAdapter = new EnglishLevelAdapter(this,titles);
        typing_list.setAdapter(englishLevelAdapter);

        excellent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                excellent_popup.setVisibility(View.VISIBLE);
            }
        });

        excellent_close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                excellent_popup.setVisibility(View.GONE);
            }
        });

        excellent_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                excellent_popup.setVisibility(View.GONE);
                excellent_option.setText(titles.get(i));
            }
        });


        arabic_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arabiclevel_popup.setVisibility(View.VISIBLE);
            }
        });

        arabic_close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arabiclevel_popup.setVisibility(View.GONE);
            }
        });

        arabic_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                arabiclevel_popup.setVisibility(View.GONE);
                arabiclevel_option.setText(titles.get(i));
            }
        });

        other_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                otherlevel_popup.setVisibility(View.VISIBLE);
            }
        });

        other_close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                otherlevel_popup.setVisibility(View.GONE);
            }
        });

        other_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                otherlevel_popup.setVisibility(View.GONE);
                otherlevel_option.setText(titles.get(i));
            }
        });

        msoffice_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mslevel_popup.setVisibility(View.VISIBLE);
            }
        });

        mslevel_close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mslevel_popup.setVisibility(View.GONE);
            }
        });

        mslevel_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mslevel_popup.setVisibility(View.GONE);
                msofficelevel_option.setText(titles.get(i));
            }
        });

        typing_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                typing_popup.setVisibility(View.VISIBLE);
            }
        });

        typing_close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                typing_popup.setVisibility(View.GONE);
            }
        });

        typing_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                typing_popup.setVisibility(View.GONE);
                typing_option.setText(titles.get(i));
            }
        });



        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StepThreeActivity.this.onBackPressed();
            }
        });

        previous_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StepThreeActivity.this.onBackPressed();
            }
        });

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(StepThreeActivity.this,StepFourActivity.class);
                startActivity(intent);
            }
        });
    }
}
