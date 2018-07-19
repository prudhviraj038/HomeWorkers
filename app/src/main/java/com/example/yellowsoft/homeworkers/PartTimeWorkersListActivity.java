package com.example.yellowsoft.homeworkers;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

/**
 * Created by info on 18-07-2018.
 */

public class PartTimeWorkersListActivity extends Activity {
    ImageView back_btn;
    ListView listView;
    TextView st_name;
    ArrayList<PartTimeWorkersList> partTimeWorkersListsfrom_api;
    PartTimeWorkersListAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homeworkers_request_layout);
        partTimeWorkersListsfrom_api = new ArrayList<>();
        back_btn = (ImageView) findViewById(R.id.back_btn);
        st_name = (TextView) findViewById(R.id.st_name);
        st_name.setText("Part Time Request");
        listView = (ListView) findViewById(R.id.hwrequests_list);
        adapter = new PartTimeWorkersListAdapter(this,partTimeWorkersListsfrom_api);
        listView.setAdapter(adapter);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PartTimeWorkersListActivity.this.onBackPressed();
            }
        });

        get_avail_worker_requests_list();
    }


    public void get_avail_worker_requests_list(){
        final KProgressHUD hud = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setLabel("Please wait").setCancellable(true).setMaxProgress(100).show();
        Ion.with(this)
                .load(Session.SERVER_URL+"parttime_workers_reqs.php")
                .setBodyParameter("member_id",Session.GetUserId(this))
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        try {
                            Log.e("parttimelistresponse", result.toString());
                            hud.dismiss();
                            for (int i = 0; i < result.size(); i++) {
                                PartTimeWorkersList partTimeWorkersList = new PartTimeWorkersList(result.get(i).getAsJsonObject(), PartTimeWorkersListActivity.this);
                                partTimeWorkersListsfrom_api.add(partTimeWorkersList);
                            }
                            adapter.notifyDataSetChanged();
                        }catch (Exception e1){
                            e1.printStackTrace();
                        }
                    }
                });
    }


}

