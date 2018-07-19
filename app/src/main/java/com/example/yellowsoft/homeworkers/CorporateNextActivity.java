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

import com.google.gson.JsonArray;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

/**
 * Created by info on 16-07-2018.
 */

public class CorporateNextActivity extends Activity {
    ImageView back_btn;
    TextView next_btn;
    String company_name,number,address,contact_person;
    LinearLayout category_layout,workers_layout,category_popup,worker_popup;
    TextView category_option,workers_option;
    EditText salary,benefits;
    ImageView category_close_btn,worker_close_btn;
    ListView categories_list,workers_list;
    ArrayList<JobTypes> jobTypesfrom_api;
    JobTypesAdapter adapter;
    ArrayList<String> numbers;
    WorkersAdapter workersAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.corporateform_request_page2);
        jobTypesfrom_api = new ArrayList<>();
        numbers = new ArrayList<>();
        numbers.add("1");
        numbers.add("2");
        numbers.add("3");
        numbers.add("4");
        numbers.add("5");
        numbers.add("6");
        numbers.add("7");
        numbers.add("8");
        back_btn = (ImageView) findViewById(R.id.back_btn);
        next_btn = (TextView) findViewById(R.id.next_btn);
        category_layout = (LinearLayout) findViewById(R.id.category_layout);
        workers_layout = (LinearLayout) findViewById(R.id.workers_layout);
        category_option = (TextView) findViewById(R.id.category_option);
        workers_option = (TextView) findViewById(R.id.workers_option);
        category_popup=(LinearLayout) findViewById(R.id.category_popup);
        category_close_btn = (ImageView) findViewById(R.id.category_close_btn);
        categories_list = (ListView) findViewById(R.id.categories_list);
        worker_popup=(LinearLayout) findViewById(R.id.workers_popup);
        worker_close_btn = (ImageView) findViewById(R.id.workers_close_btn);
        workers_list = (ListView) findViewById(R.id.workers_list);
        salary = (EditText) findViewById(R.id.salary);
        benefits = (EditText) findViewById(R.id.benefits);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CorporateNextActivity.this.onBackPressed();
            }
        });

        adapter = new JobTypesAdapter(this,jobTypesfrom_api);
        categories_list.setAdapter(adapter);

        workersAdapter = new WorkersAdapter(this,numbers);
        workers_list.setAdapter(workersAdapter);


        category_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category_popup.setVisibility(View.VISIBLE);
            }
        });

        category_close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category_popup.setVisibility(View.GONE);
            }
        });

        categories_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                category_popup.setVisibility(View.GONE);
                category_option.setText(jobTypesfrom_api.get(i).title);
            }
        });

        workers_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                worker_popup.setVisibility(View.VISIBLE);
            }
        });

        worker_close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                worker_popup.setVisibility(View.GONE);
            }
        });

        workers_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                worker_popup.setVisibility(View.GONE);
                workers_option.setText(numbers.get(i));
            }
        });

        company_name  = getIntent().getStringExtra("company_name");
        number = getIntent().getStringExtra("number");
        address = getIntent().getStringExtra("address");
        contact_person = getIntent().getStringExtra("contact_person");

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CorporateNextActivity.this,CorporatethreeActivity.class);
                intent.putExtra("category_option",category_option.getText().toString());
                intent.putExtra("worker_option",workers_option.getText().toString());
                intent.putExtra("salary",salary.getText().toString());
                intent.putExtra("benefits",benefits.getText().toString());
                startActivity(intent);
            }
        });

        get_jobtypes();
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
                                JobTypes jobTypes = new JobTypes( CorporateNextActivity.this,result.get(i).getAsJsonObject());
                                jobTypesfrom_api.add(jobTypes);
                            }
                        }catch (Exception e1){
                            e1.printStackTrace();
                        }
                    }
                });
    }
}
