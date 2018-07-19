package com.example.yellowsoft.homeworkers;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by info on 11-04-2018.
 */

public class HomeWorkersDetailPage extends Activity {
    ImageView back_btn;
    RecyclerView recyclerView;
    HomeWorkersDetailPageAdapter adapter;
    HomeWorkers homeWorkers;
    TextView hw_app_id, hw_age, hw_nationality, hw_religion, hw_sal, hw_amt, hw_experience, full_amt, applicant_id, call_btn, email_btn;
    ImageView hw_image;
    int MY_PERMISSIONS_REQUEST_CALL_PHONE;
    LinearLayout comments_popup,submit_btn;
    ImageView close_btn;
    TextView addcomments_btn,comments_btn;
    EditText comments_edit,rating;
    ArrayList<WorkerComments> workerCommentsfrom_api;
    RatingBar ratingBar;
    TextView book_btn,part_amt;
    String amount;
    String msg;
    TextView st_exp,st_pd,st_full_amt,st_rem_amt,st_partial;
    TextView remaining_amt;
    String full_amt_kd,part_amt_kd,full_amount,part_amount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homeworkers_detailpage);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        workerCommentsfrom_api = new ArrayList<>();
        homeWorkers = (HomeWorkers) getIntent().getSerializableExtra("hw");
        back_btn = (ImageView) findViewById(R.id.back_btn);
        hw_image = (ImageView) findViewById(R.id.hw_image);
        hw_app_id = (TextView) findViewById(R.id.hw_app_id);
        hw_age = (TextView) findViewById(R.id.hw_age);
        hw_nationality = (TextView) findViewById(R.id.hw_nationality);
        hw_religion = (TextView) findViewById(R.id.hw_religion);
        hw_sal = (TextView) findViewById(R.id.hw_sal);
        hw_amt = (TextView) findViewById(R.id.hw_amount);
        hw_experience = (TextView) findViewById(R.id.hw_experience);
        full_amt = (TextView) findViewById(R.id.full_amt);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        applicant_id = (TextView) findViewById(R.id.applicant_id);
        call_btn = (TextView) findViewById(R.id.call_btn);
        email_btn = (TextView) findViewById(R.id.email_btn);
        comments_popup = (LinearLayout) findViewById(R.id.comments_popup);
        close_btn = (ImageView) findViewById(R.id.close_btn);
        submit_btn = (LinearLayout) findViewById(R.id.submit_btn);
        addcomments_btn = (TextView) findViewById(R.id.addcomments_btn);
        comments_btn = (TextView) findViewById(R.id.comments_btn);
        comments_edit = (EditText) findViewById(R.id.comments_edit);
        part_amt =(TextView) findViewById(R.id.part_amt);
        remaining_amt = (TextView) findViewById(R.id.remaining_amt);
        TextView st_id = (TextView) findViewById(R.id.st_id);
        TextView st_age = (TextView) findViewById(R.id.st_age);
        TextView st_nationality = (TextView) findViewById(R.id.st_nationality);
        TextView st_religion = (TextView) findViewById(R.id.st_religion);
        TextView st_salary = (TextView) findViewById(R.id.st_salary);
        TextView st_amount = (TextView) findViewById(R.id.st_amount);
        st_exp = (TextView) findViewById(R.id.st_exp);
        st_pd = (TextView) findViewById(R.id.st_pd);
        st_full_amt = (TextView) findViewById(R.id.st_full_amt);
        //st_rem_amt = (TextView) findViewById(R.id.st_rem_amt);
        st_partial = (TextView) findViewById(R.id.st_partial);
        book_btn = (TextView) findViewById(R.id.pay_btn);
         ratingBar = (RatingBar) findViewById(R.id.rating);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,  boolean fromUser) {
                Log.e("message",String.valueOf(rating));
                Log.e("mess", String.valueOf(ratingBar.getRating()));

            }
        });




        comments_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comments_btn.setTextColor(getResources().getColor(R.color.registerdeselect));
                addcomments_btn.setTextColor(getResources().getColor(R.color.white));
                recyclerView.setVisibility(View.VISIBLE);
            }
        });
        adapter = new HomeWorkersDetailPageAdapter(this,workerCommentsfrom_api);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, 0, false));
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeWorkersDetailPage.this.onBackPressed();
            }
        });

        hw_app_id.setText(homeWorkers.applicant_id);
        hw_age.setText(homeWorkers.age);
        hw_nationality.setText(homeWorkers.nationality_title);
        hw_religion.setText(homeWorkers.religion_title);
        hw_sal.setText(homeWorkers.salary + " KD ");
        hw_amt.setText(homeWorkers.amount + " KD ");
        hw_experience.setText(homeWorkers.experience);
       // full_amt.setText(homeWorkers.amount);
        amount = homeWorkers.part_amount;
        part_amt.setText(amount);
        Picasso.with(this).load(homeWorkers.image).placeholder(R.drawable.layer2).into(hw_image);
        applicant_id.setText("Applicant ID." + " " + homeWorkers.applicant_id);

         full_amount = homeWorkers.amount;
         full_amt.setText(full_amount);
         full_amt_kd = full_amt.getText().toString() + " KD ";
         part_amt_kd = part_amt.getText().toString() + " KD ";

        part_amt_kd = part_amt.getText().toString() + " KD ";
        String   charge = String.valueOf(Integer.parseInt(full_amt.getText().toString()) - Integer.parseInt(part_amt.getText().toString()));
        remaining_amt.setText(charge);

        st_id.setText(Session.GetWord(this,"Applicant ID"));
        st_age.setText(Session.GetWord(this,"Age"));
        st_nationality.setText(Session.GetWord(this,"Nationality"));
        st_religion.setText(Session.GetWord(this,"Religion"));
        st_salary.setText(Session.GetWord(this,"Salary"));
        st_amount.setText(Session.GetWord(this,"Amount"));
        call_btn.setText(Session.GetWord(this,"Call"));
        email_btn.setText(Session.GetWord(this,"Email"));
        st_exp.setText(Session.GetWord(this,"Experience"));
        st_pd.setText(Session.GetWord(this,"Payment Details"));
        st_amount.setText(Session.GetWord(this,"Full Amount"));
//        st_rem_amt.setText(Session.GetWord(this,"Remaining Amount"));
        st_partial.setText(Session.GetWord(this,"Partial amount to be paid"));
        comments_btn.setText(Session.GetWord(this,"Comments"));
        addcomments_btn.setText(Session.GetWord(this,"Add Your Comments"));
        book_btn.setText(Session.GetWord(this,"Request and Pay"));


        call_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                JsonParser jsonParser = new JsonParser();
                if(!Session.GetSettings(HomeWorkersDetailPage.this).equals("-1")) {
                    JsonObject parse = (JsonObject) jsonParser.parse(Session.GetSettings(HomeWorkersDetailPage.this));
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + parse.get("phone").getAsString()));
                    callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    if (ActivityCompat.checkSelfPermission(HomeWorkersDetailPage.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE},
                                MY_PERMISSIONS_REQUEST_CALL_PHONE);
                        return;
                    }

                    startActivity(callIntent);
                }
            }
        });

        book_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeWorkersDetailPage.this, PaymentPage.class);
                intent.putExtra("amount",amount);
                HomeWorkersDetailPage.this.startActivityForResult(intent, 1);
            }
        });

        email_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JsonParser jsonParser = new JsonParser();
                if(!Session.GetSettings(HomeWorkersDetailPage.this).equals("-1")) {
                    JsonObject parse = (JsonObject) jsonParser.parse(Session.GetSettings(HomeWorkersDetailPage.this));
                    Intent Email = new Intent(Intent.ACTION_SEND);
                    Email.setType("text/email");
                    Email.putExtra(Intent.EXTRA_EMAIL, new String[]{parse.get("email").getAsString()});
                    Email.putExtra(Intent.EXTRA_SUBJECT, "Add your Subject");
                    Email.putExtra(Intent.EXTRA_TEXT, "message");
                    startActivity(Intent.createChooser(Email, "Send Feedback:"));
                }
            }
        });

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_worker_comments();
            }
        });

        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comments_popup.setVisibility(View.GONE);
            }
        });

        addcomments_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addcomments_btn.setTextColor(getResources().getColor(R.color.registerdeselect));
                comments_btn.setTextColor(getResources().getColor(R.color.white));
                recyclerView.setVisibility(View.GONE);
                comments_popup.setVisibility(View.VISIBLE);

            }
        });

        worker_comments();


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != 1) {
            return;
        }
        if (resultCode == -1) {
            if (data != null && data.hasExtra("message")) {
                msg = data.getExtras().getString("message");
                Log.e("toast", msg);
                if (this.msg.equals("success")) {
                    Toast.makeText(this, "Payment done Successfully", Toast.LENGTH_SHORT).show();
                    get_home_booking();

                } else if (this.msg.equals("failure")) {
                    Toast.makeText(this, "Please Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (resultCode != 0) {
        }
    }

    public void add_worker_comments(){

        String comments_string = comments_edit.getText().toString();
        String rating_string = String.valueOf(ratingBar.getRating());
        Log.e("ratingcheck",String.valueOf(ratingBar.getRating()));
        if (comments_string.equals("")){
            Toast.makeText(HomeWorkersDetailPage.this,"Please Enter Comments",Toast.LENGTH_SHORT).show();
            comments_edit.requestFocus();
        }else if (rating_string.equals("")){
            Toast.makeText(HomeWorkersDetailPage.this,"Please Select Rating",Toast.LENGTH_SHORT).show();
            rating.requestFocus();
        }else {
            final KProgressHUD hud = KProgressHUD.create(HomeWorkersDetailPage.this)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setLabel("Please wait")
                    .setCancellable(true)
                    .setMaxProgress(100)
                    .show();
            Ion.with(this)
                    .load(Session.SERVER_URL + "add-comments.php")
                    .setBodyParameter("member_id", Session.GetUserId(this))
                    .setBodyParameter("comments", comments_string)
                    .setBodyParameter("worker_id",Session.GetWorkerId(this))
                    .setBodyParameter("rating",rating_string)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            try {
                                hud.dismiss();
                                Log.e("ratingresponse", result.toString());
                                if (result.get("status").getAsString().equals("Success")) {
                                    Toast.makeText(HomeWorkersDetailPage.this, result.get("message").getAsString(), Toast.LENGTH_SHORT).show();
                                    comments_popup.setVisibility(View.GONE);
                                } else {
                                    Toast.makeText(HomeWorkersDetailPage.this, result.get("message").getAsString(), Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }
                    });
        }
    }

    public void worker_comments(){
        final KProgressHUD hud = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setLabel("Please wait").setCancellable(true).setMaxProgress(100).show();
        Ion.with(this)
                .load(Session.SERVER_URL+"worker_comments.php")
                .setBodyParameter("worker_id",Session.GetWorkerId(HomeWorkersDetailPage.this))
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        try {
                            hud.dismiss();
                            Log.e("wcresponse", result.toString());
                            Log.e("id",Session.GetWorkerId(HomeWorkersDetailPage.this));
                            for (int i = 0; i < result.size(); i++) {
                                WorkerComments workerComments = new WorkerComments(result.get(i).getAsJsonObject(), HomeWorkersDetailPage.this);
                                workerCommentsfrom_api.add(workerComments);
                            }
                            adapter.notifyDataSetChanged();
                        }catch (Exception e1){
                            e1.printStackTrace();
                        }
                    }
                });
    }

    public void get_home_booking(){
        final KProgressHUD hud = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setLabel("Please wait").setCancellable(true).setMaxProgress(100).show();
        Ion.with(this)
                .load(Session.SERVER_URL+"home_booking.php")
                .setBodyParameter("member_id",Session.GetUserId(this))
                .setBodyParameter("worker_id",Session.GetWorkerId(HomeWorkersDetailPage.this))
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        try {
                            hud.dismiss();
                            Log.e("homebooking", result.toString());
                            Log.e("id",Session.GetWorkerId(HomeWorkersDetailPage.this));
                            if (result.get("status").getAsString().equals("Success")){
                                Log.e("member_id",result.get("member_id").getAsString());
                                Toast.makeText(HomeWorkersDetailPage.this,result.get("message").getAsString(),Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(HomeWorkersDetailPage.this,result.get("message").getAsString(),Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e1){
                            e1.printStackTrace();
                        }
                    }
                });
    }
}
