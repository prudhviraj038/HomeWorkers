package com.example.yellowsoft.homeworkers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.eminayar.panter.DialogType;
import com.eminayar.panter.PanterDialog;
import com.eminayar.panter.interfaces.OnSingleCallbackConfirmListener;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by info on 11-04-2018.
 */

public class PartTimeWorkersActivity extends Activity {
    ImageView back_btn;
    LinearLayout select_area,select_day,select_workers,select_service;
    TextView area_option,day_option,worker_option,amt_btn,book_btn,service_option;
    EditText block,street,judda,house,message,address;
    ArrayList<Areas> areasfrom_api;
    String area_id;
    AreaAdapter adapter;
    LinearLayout area_popup,service_popup;
    ImageView close_btn,service_close_btn;
    ListView listView,services_list;
    int quantity;
    LinearLayout morning_shift,evening_shift;
    ImageView checkbox,checkbox1;
    String checked,type;
    String quant;
    int dates = 7;
    String msg,amount,booking_id;
    TextView st_area,st_day,st_quantity,st_shift,st_block,st_judda,st_street,st_house,st_sr,st_evening,st_morning,st_address;
    ImageView male_checkbox,female_checkbox;
    TextView male_option,female_option;
    LinearLayout male,female;
    ArrayList<Services> servicesfrom_api;
    ServicesAdapter servicesAdapter;
    String service_id,service_charge;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parttimeworkers_screen);
        Session.forceRTLIfSupported(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        areasfrom_api = new ArrayList<>();
        servicesfrom_api = new ArrayList<>();
        st_area = (TextView) findViewById(R.id.st_area);
        st_day = (TextView) findViewById(R.id.st_day);
        st_quantity = (TextView) findViewById(R.id.st_quantity);
        st_shift = (TextView) findViewById(R.id.st_shift);
        st_block = (TextView) findViewById(R.id.st_block);
        st_judda = (TextView) findViewById(R.id.st_judda);
        st_street = (TextView) findViewById(R.id.st_street);
        st_house = (TextView) findViewById(R.id.st_house);
        st_sr = (TextView) findViewById(R.id.st_sr);
        st_evening = (TextView) findViewById(R.id.st_evening);
        st_morning = (TextView) findViewById(R.id.st_morning);



        st_area.setText(Session.GetWord(this,"Area"));
        st_day.setText(Session.GetWord(this,"Day"));
        st_quantity.setText(Session.GetWord(this,"Quantity"));
        st_shift.setText(Session.GetWord(this,"Choose Shift"));
       // st_address.setText(Session.GetWord(this,"Enter your address"));
        st_block.setText(Session.GetWord(this,"Block"));
        st_street.setText(Session.GetWord(this,"Street"));
        st_judda.setText(Session.GetWord(this,"Judda"));
        st_house.setText(Session.GetWord(this,"House"));
        st_sr.setText(Session.GetWord(this,"Special Request"));
        st_evening.setText(Session.GetWord(this,"Evening Shift"));
        st_morning.setText(Session.GetWord(this,"Morning Shift"));
//        day_option.setHint(Session.GetWord(this,"Select the day"));


        back_btn = (ImageView) findViewById(R.id.back_btn);
        select_area = (LinearLayout) findViewById(R.id.select_area);
        select_day = (LinearLayout) findViewById(R.id.select_day);
        select_workers = (LinearLayout) findViewById(R.id.select_workers);
        area_option = (TextView) findViewById(R.id.area_option);
        day_option = (TextView) findViewById(R.id.day_option);
        worker_option = (TextView) findViewById(R.id.worker_option);
        amt_btn = (TextView) findViewById(R.id.amt_btn);
        book_btn = (TextView) findViewById(R.id.book_btn);
        block = (EditText) findViewById(R.id.block);
        street = (EditText) findViewById(R.id.street);
        judda = (EditText) findViewById(R.id.judda);
        st_address = (TextView) findViewById(R.id.st_address);
        house = (EditText) findViewById(R.id.house);
        message = (EditText) findViewById(R.id.message);
        listView = (ListView) findViewById(R.id.areas_list);
        close_btn = (ImageView) findViewById(R.id.close_btn);
        area_popup = (LinearLayout) findViewById(R.id.area_popup);
        morning_shift = (LinearLayout) findViewById(R.id.morning_shift);
        evening_shift = (LinearLayout) findViewById(R.id.evening_shift);
        select_service = (LinearLayout) findViewById(R.id.select_service);
        service_option = (TextView) findViewById(R.id.service_option);
        male_checkbox = (ImageView) findViewById(R.id.male_checkbox);
        female_checkbox = (ImageView) findViewById(R.id.female_checkbox);
        male_option = (TextView) findViewById(R.id.male_option);
        female_option = (TextView) findViewById(R.id.female_option);
        checkbox = (ImageView) findViewById(R.id.checkbox);
        checkbox1 = (ImageView) findViewById(R.id.checkbox1);
        service_popup = (LinearLayout) findViewById(R.id.service_popup);
        service_close_btn = (ImageView) findViewById(R.id.service_close_btn);
        services_list = (ListView) findViewById(R.id.services_list);
        male = (LinearLayout) findViewById(R.id.male);
        female = (LinearLayout) findViewById(R.id.female);
        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                area_popup.setVisibility(View.GONE);
            }
        });

        service_close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                service_popup.setVisibility(View.GONE);
            }
        });

        services_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                service_popup.setVisibility(View.GONE);
                service_option.setText(servicesfrom_api.get(i).title);
            }
        });





        morning_shift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkbox.isEnabled()){
                    checkbox.setImageResource(R.drawable.checked);
                    checkbox1.setImageResource(R.drawable.unchecked);
                    checked="1";
                    //get_quantity();
                }else {
                    checkbox.setImageResource(R.drawable.unchecked);
                }
            }
        });

        evening_shift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkbox1.isEnabled()){
                    checkbox1.setImageResource(R.drawable.checked);
                    checkbox.setImageResource(R.drawable.unchecked);
                    checked="2";
                    //get_quantity();
                }else {
                    checkbox1.setImageResource(R.drawable.unchecked);

                }
            }
        });

        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (male_checkbox.isEnabled()){
                    male_checkbox.setImageResource(R.drawable.checked);
                    female_checkbox.setImageResource(R.drawable.unchecked);
                    type="male";
                }else {
                    male_checkbox.setImageResource(R.drawable.unchecked);
                }
            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (female_checkbox.isEnabled()){
                    female_checkbox.setImageResource(R.drawable.checked);
                    male_checkbox.setImageResource(R.drawable.unchecked);
                    type="female";
                }else {
                    female_checkbox.setImageResource(R.drawable.unchecked);

                }
            }
        });



        adapter = new AreaAdapter(this,areasfrom_api,this);
        listView.setAdapter(adapter);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PartTimeWorkersActivity.this.onBackPressed();
            }
        });

        select_area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                area_popup.setVisibility(View.VISIBLE);
            }
        });

        select_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                service_popup.setVisibility(View.VISIBLE);
                Animation anim = AnimationUtils.loadAnimation(PartTimeWorkersActivity.this, R.anim.myanim);
                service_popup.startAnimation(anim);
            }
        });

        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                area_popup.setVisibility(View.GONE);
            }
        });

        servicesAdapter = new ServicesAdapter(this,servicesfrom_api,this);
        services_list.setAdapter(servicesAdapter);

        amt_btn.setText("000 KD");



        select_workers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                get_quantity();
            }
        });






        select_day.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
//                PanterDialog panterDialog = new PanterDialog(PartTimeWorkersActivity.this);
//                panterDialog.setHeaderBackground(getResources().getColor(R.color.white));
//                panterDialog.setTitle("Select Date");
//                final String[] array = new String[dates];
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//                for (int i = 0; i < 7; i++) {
//                    Calendar calendar = new GregorianCalendar();
//                    calendar.add(Calendar.DATE, i);
//                    String day = sdf.format(calendar.getTime());
//                    Log.i("tag", day);
//                    array[i] = String.valueOf(day);
//                }
//                panterDialog.setDialogType(DialogType.SINGLECHOICE)
//                        .isCancelable(false)
//                        .items((String[]) array, new OnSingleCallbackConfirmListener() {
//                            @Override
//                            public void onSingleCallbackConfirmed(PanterDialog dialog, int pos, String text) {
//                                String selectedItem = array[pos].toString();
//                                Log.e("select",selectedItem);
//                                day_option.setText(selectedItem);
//                            }
//                        })
//                        .setPositive("Ok", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//
//                            }
//                        })
//                        .setNegative("Cancel", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//
//                            }
//                        })
//                        .show();

                Dialog dialog = onDayChoice();
                dialog.show();
//                final Calendar mcurrentDate=Calendar.getInstance();
//                final int mYear = mcurrentDate.get(Calendar.YEAR);
//                final int mMonth = mcurrentDate.get(Calendar.MONTH);
//                final int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
//
//                DatePickerDialog mDatePicker=new DatePickerDialog(PartTimeWorkersActivity.this, new DatePickerDialog.OnDateSetListener() {
//                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
//                       // day_option.setText(selectedday +"-"+(selectedmonth+1) +"-"+selectedyear);
//                        String month;
//                        if (selectedmonth < 10){
//                            month = "0" + String.valueOf(selectedmonth+1);
//                        }else {
//                            month = String.valueOf(selectedmonth+1);
//                        }
//
//                        day_option.setText(selectedyear +"-"+month+"-"+selectedday);
//                    }
//                },mYear, mMonth, mDay);
//                mDatePicker.setTitle("Select date");
//                mDatePicker.show();
            }
        });




        book_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPartTimeWorkers();
            }
        });

        get_areas();
        get_services();
    }


    public void get_services(){
        Ion.with(this)
                .load(Session.SERVER_URL+"services.php")
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        try {
                            for (int i = 0; i < result.size(); i++) {
                                Services services = new Services(result.get(i).getAsJsonObject(), PartTimeWorkersActivity.this);
                                servicesfrom_api.add(services);
                            }
                        }catch (Exception e1){
                            e1.printStackTrace();
                        }
                    }
                });
    }





    public void get_areas() {
        final KProgressHUD hud = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setLabel("Please wait").setCancellable(true).setMaxProgress(100).show();
        Ion.with(this).load(Session.SERVER_URL+"areas.php").asJsonArray().setCallback(new FutureCallback<JsonArray>() {
            public void onCompleted(Exception e, JsonArray result) {
                try {
                    hud.dismiss();
                    Log.e("country", result.toString());
                    for (int i = 0; i < result.size(); i++) {
                        PartTimeWorkersActivity.this.areasfrom_api.add(new Areas(result.get(i).getAsJsonObject(), PartTimeWorkersActivity.this, "0"));
                        for (int j = 0; j < result.get(i).getAsJsonObject().get("areas").getAsJsonArray().size(); j++) {
                            PartTimeWorkersActivity.this.areasfrom_api.add(new Areas(result.get(i).getAsJsonObject().get("areas").getAsJsonArray().get(j).getAsJsonObject(), PartTimeWorkersActivity.this, "1"));
                        }
                    }
                    adapter.notifyDataSetChanged();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
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
                    update_payment();
                } else if (this.msg.equals("failure")) {
                    Toast.makeText(this, "Please Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (resultCode != 0) {
        }
    }

    public Dialog onDayChoice() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final CharSequence[] array = new CharSequence[dates];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"+" "+ "EEEE");

        for (int i = 0; i < 7; i++) {
            Calendar calendar = new GregorianCalendar();
            calendar.add(Calendar.DATE, i);
            String day = sdf.format(calendar.getTime());
            Log.i("tag", day);
            array[i] = String.valueOf(day);
        }


        builder.setTitle("Select Day").setSingleChoiceItems(array, 0, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int i) {
                String selectedItem = array[i].toString();
                Log.e("select",selectedItem);
                day_option.setText(selectedItem);
                Log.e("day_option",day_option.getText().toString());

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




    public void get_quantity(){
        final KProgressHUD hud = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setLabel("Please wait").setCancellable(true).setMaxProgress(100).show();
        Ion.with(this)
                .load(Session.SERVER_URL+"part_qty.php")
                .setBodyParameter("day",day_option.getText().toString())
                .setBodyParameter("shift",checked)
                .setBodyParameter("service_id",service_id)
                .setBodyParameter("type",type)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        try {
                            hud.dismiss();
                            Log.e("serviceid",service_id);
                            Log.e("quant",result.toString());
                            quantity = Integer.parseInt(result.get("available_workers").getAsString());
                            Dialog dialog = onCreateDialogSingleChoice();
                            dialog.show();
//                            for (int i=0;i<quantity;i++){
//                                worker_option.setText(quantity);
//                                Log.e("quantity", String.valueOf(quantity));
//                            }
                            amount = result.get("price").getAsString();
                            amt_btn.setText(amount);


                        }catch (Exception e1){
                            e1.printStackTrace();
                        }
                    }
                });
    }


    public Dialog onCreateDialogSingleChoice() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final CharSequence[] array = new CharSequence[quantity];
        for(int i=0;i<quantity;i++){
            array[i] = String.valueOf(i+1);
            Log.e("message",String.valueOf(i+1));
        }
        builder.setTitle("Select Quantity").setSingleChoiceItems(array, 0, new DialogInterface.OnClickListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(DialogInterface dialog, int i) {
                String selectedItem = array[i].toString();
                Log.e("select",selectedItem);
                worker_option.setText(selectedItem);
                Log.e("quantity", String.valueOf(quantity));
                service_charge = String.valueOf(Integer.parseInt(worker_option.getText().toString()) *Integer.parseInt(amount));
                amt_btn.setText(service_charge+ " KD ");
                Log.e("servicecharge", amt_btn.getText().toString());

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




    public void addPartTimeWorkers(){
        String area_option_string = area_id;
        String day_string = day_option.getText().toString();
        String shift_string = checked;
        String workers_string = worker_option.getText().toString();
        String block_string = block.getText().toString();
        String street_string = street.getText().toString();
        String judda_string = judda.getText().toString();
        String house_string = house.getText().toString();
        final String service_string = service_option.getText().toString();
        String address_string  = block_string +" "+street_string+" "+house_string+" "+judda_string;
        String type_string = type;
        final String amount_string = amt_btn.getText().toString();
        if (area_option_string==""){
            Toast.makeText(PartTimeWorkersActivity.this,"Please Select Area",Toast.LENGTH_SHORT).show();
        }else if (day_string.equals("")){
            Toast.makeText(PartTimeWorkersActivity.this,"Please Enter Day",Toast.LENGTH_SHORT).show();
            day_option.requestFocus();
        }else if (shift_string.equals("")){
            Toast.makeText(PartTimeWorkersActivity.this,"Please Enter Shift",Toast.LENGTH_SHORT).show();
        }else if (workers_string.equals("")){
            Toast.makeText(PartTimeWorkersActivity.this,"Please Enter Workers",Toast.LENGTH_SHORT).show();
            worker_option.requestFocus();
        }else if (amount_string.equals("")){
            Toast.makeText(PartTimeWorkersActivity.this,"Please Enter Amount",Toast.LENGTH_SHORT).show();
            amt_btn.requestFocus();
        }else if (service_string.equals("")){
            Toast.makeText(PartTimeWorkersActivity.this,"Please Select Service",Toast.LENGTH_SHORT).show();
            service_option.requestFocus();
        }else if (type.equals("")){
            Toast.makeText(PartTimeWorkersActivity.this,"Please Pass Type male/female",Toast.LENGTH_SHORT).show();
        }else if (address_string.equals("")){
            Toast.makeText(PartTimeWorkersActivity.this,"Please Enter Address",Toast.LENGTH_SHORT).show();
            block.requestFocus();
        }else{
            final KProgressHUD hud = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setLabel("Please wait").setCancellable(true).setMaxProgress(100).show();
            Ion.with(this)
                    .load(Session.SERVER_URL+"add-partworkers.php")
                    .setBodyParameter("member_id",Session.GetUserId(this))
                    .setBodyParameter("area",area_option_string)
                    .setBodyParameter("day",day_string)
                    .setBodyParameter("shift",shift_string)
                    .setBodyParameter("workers",workers_string)
                    .setBodyParameter("price",amount_string)
                    .setBodyParameter("address",address_string)
                    .setBodyParameter("service_id",service_id)
                    .setBodyParameter("type",type)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            try {
                                hud.dismiss();
                                Log.e("parttime",result.toString());
                                if (result.get("status").getAsString().equals("Success")){
                                    booking_id = result.get("bookin_id").getAsString();
                                    Log.e("booking",booking_id);
                                    Toast.makeText(PartTimeWorkersActivity.this,result.get("message").getAsString(),Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(PartTimeWorkersActivity.this, PaymentPage.class);
                                    intent.putExtra("amount", service_charge);
                                    PartTimeWorkersActivity.this.startActivityForResult(intent, 1);
                                }else {
                                    Toast.makeText(PartTimeWorkersActivity.this,result.get("message").getAsString(),Toast.LENGTH_SHORT).show();
                                }
                            }catch (Exception e1){
                                e1.printStackTrace();
                            }

                        }
                    });
        }

    }

    public void update_payment(){
        final KProgressHUD hud = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait").setCancellable(true).setMaxProgress(100).show();
        Ion.with(this)
                .load(Session.SERVER_URL+"update_part_payment.php")
                .setBodyParameter("member_id",Session.GetUserId(this))
                .setBodyParameter("booking_id",booking_id)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                         hud.dismiss();
                        PartTimeWorkersActivity.this.onBackPressed();
                    }
                });
    }
















}
