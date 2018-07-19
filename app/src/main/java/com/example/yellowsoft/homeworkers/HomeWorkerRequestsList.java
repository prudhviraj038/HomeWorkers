package com.example.yellowsoft.homeworkers;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.JsonArray;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

/**
 * Created by info on 14-07-2018.
 */

public class HomeWorkerRequestsList extends Activity {
    ImageView back_btn;
    ListView listView;
    ArrayList<HomeWorkersList> homeWorkerRequestsLists;
    HomeWorkerRequestListAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homeworkers_request_layout);
        homeWorkerRequestsLists = new ArrayList<>();
        back_btn = (ImageView) findViewById(R.id.back_btn);
        listView = (ListView) findViewById(R.id.hwrequests_list);
        adapter = new HomeWorkerRequestListAdapter(this,homeWorkerRequestsLists);
        listView.setAdapter(adapter);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeWorkerRequestsList.this.onBackPressed();
            }
        });

        get_home_worker_requests_list();
    }


    public void get_home_worker_requests_list(){
        final KProgressHUD hud = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setLabel("Please wait").setCancellable(true).setMaxProgress(100).show();
        Ion.with(this)
                .load(Session.SERVER_URL+"home_workers_reqs.php")
                .setBodyParameter("member_id",Session.GetUserId(this))
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        try {
                            Log.e("hwresponse", result.toString());
                            hud.dismiss();
                            for (int i = 0; i < result.size(); i++) {
                                HomeWorkersList homeWorkersList = new HomeWorkersList(result.get(i).getAsJsonObject(), HomeWorkerRequestsList.this);
                                homeWorkerRequestsLists.add(homeWorkersList);
                            }
                            adapter.notifyDataSetChanged();
                        }catch (Exception e1){
                            e1.printStackTrace();
                        }
                    }
                });
    }
}
