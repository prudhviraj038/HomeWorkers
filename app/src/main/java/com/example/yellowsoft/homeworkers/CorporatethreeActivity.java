package com.example.yellowsoft.homeworkers;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eminayar.panter.DialogType;
import com.eminayar.panter.PanterDialog;
import com.eminayar.panter.interfaces.OnSingleCallbackConfirmListener;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Created by info on 16-07-2018.
 */

public class CorporatethreeActivity extends Activity {
    ImageView back_btn,trade_image,signatory_image,civil_image;
    LinearLayout trade_layout,signatory_layout,civil_layout;
    TextView submit_btn;
    String corporate_option,worker_option,salary,benefits;
    Integer REQUEST_CAMERA = 1, SELECT_FILE = 0;
    int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.corporateform_request_page_3);
        back_btn = (ImageView) findViewById(R.id.back_btn);
        trade_image = (ImageView) findViewById(R.id.trade_image);
        signatory_image = (ImageView) findViewById(R.id.signatory_image);
        civil_image = (ImageView) findViewById(R.id.civil_image);
        trade_layout = (LinearLayout) findViewById(R.id.trade_layout);
        signatory_layout = (LinearLayout) findViewById(R.id.signatory_layout);
        civil_layout = (LinearLayout) findViewById(R.id.civil_layout);
        submit_btn = (TextView) findViewById(R.id.submit_btn);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CorporatethreeActivity.this.onBackPressed();
            }
        });

        corporate_option = getIntent().getStringExtra("corporate_option");
        worker_option = getIntent().getStringExtra("worker_option");
        salary = getIntent().getStringExtra("salary");
        benefits = getIntent().getStringExtra("benefits");

        trade_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pick_image();
            }
        });

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                JsonObject jsonObject = new JsonObject();
//                jsonObject.addProperty("member_id", Session.GetUserId(this));
//                JsonParser jsonParser = new JsonParser();
//                JsonArray jsonArray = Session.GetCartProducts(this);
//                JsonArray jsonArray1 = new JsonArray();
//                Log.e("products_cart", jsonArray.toString());
//                for (int i = 0; i < jsonArray.size(); i++) {
//                    JsonObject jsonObject2 = new JsonObject();
//                    jsonObject2.addProperty("price", jsonArray.get(i).getAsJsonObject().get("price").getAsString());
//                    jsonObject2.addProperty("product_id", jsonArray.get(i).getAsJsonObject().get("id").getAsString());
//                    jsonObject2.addProperty("quantity", jsonArray.get(i).getAsJsonObject().get("cart_quantity").getAsString());
//                    jsonArray1.add(jsonObject2);
//                    Log.e("productsresponse", jsonArray1.toString());
//                }
//                jsonObject.add("products", jsonArray1);
//                JsonObject jsonObject1;
//                if (Session.GetAddressType(this).equals("House")) {
//                    jsonObject1 = new JsonObject();
//                    jsonObject1.addProperty(Session.phone, Session.GetPhone(this));
//                    jsonObject1.addProperty("area", Session.GetAreaId(this));
//                    jsonObject1.addProperty("street", this.street);
//                    jsonObject1.addProperty("block", this.block);
//                    jsonObject1.addProperty("lastname", Session.GetLname(this));
//                    jsonObject1.addProperty("firstname", Session.GetFname(this));
//                    jsonObject1.addProperty("house", this.house);
//                    jsonObject1.addProperty("flat", "");
//                    jsonObject1.addProperty("email", Session.GetEmail(this));
//                    jsonObject1.addProperty("jaddah", this.jaddah);
//                    jsonObject.add("address", jsonObject1);
//                } else if (Session.GetAddressType(this).equals("Apartment")) {
//                    jsonObject1 = new JsonObject();
//                    jsonObject1.addProperty(Session.phone, Session.GetPhone(this));
//                    jsonObject1.addProperty("area", Session.GetAreaId(this));
//                    jsonObject1.addProperty("street", this.b_street);
//                    jsonObject1.addProperty("block", this.b_block);
//                    jsonObject1.addProperty("lastname", Session.GetLname(this));
//                    jsonObject1.addProperty("firstname", Session.GetFname(this));
//                    jsonObject1.addProperty("house", this.b_house);
//                    jsonObject1.addProperty("floor", this.b_floor);
//                    jsonObject1.addProperty("flat", "");
//                    jsonObject1.addProperty("email", Session.GetEmail(this));
//                    jsonObject1.addProperty("jaddah", this.b_jaddah);
//                    jsonObject.add("address", jsonObject1);
//                    Log.e("apartmentadd", jsonObject1.toString());
//                } else if (Session.GetAddressType(this).equals("Hospital")) {
//                    jsonObject1 = new JsonObject();
//                    jsonObject1.addProperty(Session.phone, Session.GetPhone(this));
//                    jsonObject1.addProperty("area", Session.GetAreaId(this));
//                    jsonObject1.addProperty("street", this.h_street);
//                    jsonObject1.addProperty("block", this.h_block);
//                    jsonObject1.addProperty("lastname", Session.GetLname(this));
//                    jsonObject1.addProperty("firstname", Session.GetFname(this));
//                    jsonObject1.addProperty("house", this.h_house);
//                    jsonObject1.addProperty("floor", this.h_floor);
//                    jsonObject1.addProperty("flat", "");
//                    jsonObject1.addProperty("email", Session.GetEmail(this));
//                    jsonObject1.addProperty("jaddah", this.h_jaddah);
//                    jsonObject.add("address", jsonObject1);
//                }
//                jsonObject.addProperty("coupon_code", this.coupon_text.getText().toString());
//                jsonObject.addProperty("discount_amount", this.discount_value.getText().toString());
//                jsonObject.addProperty("total_price", this.total);
//                Log.e("dc", Session.GetPharmciId(this));
//                jsonObject.addProperty("delivery_charges", Session.GetPharmciDc(this));
//                jsonObject.addProperty("payment_method", this.pay_met);
//                jsonObject.addProperty("deliveryTime", this.time);
//                jsonObject.addProperty("deliveryDate", this.date);
//                jsonObject.addProperty("pharmacy", Session.GetPharmciId(this));
//                jsonObject.addProperty("payment", "0");
//                Log.e("reeeee", jsonObject.toString());
            }
        });
    }

    public void pick_image() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                if (shouldShowRequestPermissionRationale(
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                }
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                return;
            }
        }

        final String[] cases = {"Camera", "Gallery", "Choose from Library"};
        final PanterDialog panterDialog = new PanterDialog(CorporatethreeActivity.this);
        panterDialog.setHeaderBackground(R.color.white);
        panterDialog.setTitle("Select Image");
        panterDialog.setDialogType(DialogType.SINGLECHOICE);
        panterDialog.setPositive("OK");
        panterDialog.setNegative("Cancel", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                panterDialog.dismiss();
            }
        });
        panterDialog.items(cases, new OnSingleCallbackConfirmListener() {
            @Override
            public void onSingleCallbackConfirmed(PanterDialog dialog, int pos, String text) {
                if (cases[pos].equals("Camera")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                } else if (cases[pos].equals("Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, SELECT_FILE);
                }
            }
        });
        panterDialog.isCancelable(false);
        panterDialog.show();
    }
}
