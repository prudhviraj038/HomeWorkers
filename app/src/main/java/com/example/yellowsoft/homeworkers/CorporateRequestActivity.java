package com.example.yellowsoft.homeworkers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by info on 16-07-2018.
 */

public class CorporateRequestActivity extends Activity {
    ImageView back_btn;
    TextView next_btn;
    EditText company_name,number,address,contact_person;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.corporateform_request_page);
        back_btn = (ImageView) findViewById(R.id.back_btn);
        company_name = (EditText) findViewById(R.id.company_name);
        number = (EditText) findViewById(R.id.number);
        address = (EditText) findViewById(R.id.address);
        contact_person = (EditText) findViewById(R.id.contact_person);
        next_btn = (TextView) findViewById(R.id.next_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CorporateRequestActivity.this.onBackPressed();
            }
        });

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CorporateRequestActivity.this,CorporateNextActivity.class);
                intent.putExtra("company_name",company_name.getText().toString());
                intent.putExtra("number",number.getText().toString());
                intent.putExtra("address",address.getText().toString());
                intent.putExtra("contact_person",contact_person.getText().toString());
                startActivity(intent);
            }
        });
    }



}
