package com.example.sophiya.tbmosystem;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sophiya.tbmosystem.AdminInterface.AdminInterface_User_Login;
import com.example.sophiya.tbmosystem.CustomerInterface.Customer_ItemList;
import com.example.sophiya.tbmosystem.CustomerInterface.Customer_RestaurantDetails;

import org.w3c.dom.Text;

/**
 * Created by Sophiya on 2/21/2016.
 */
public class All_User_Login extends AppCompatActivity implements View.OnClickListener {
    public static All_User_Login all_user_login;
    public static MainActivity mainActivity;
    public static TableList tableList;
    public static Bill bill;
    ImageView admin,customer,restaurant,manual;
    String tokenSharedPref ="TokenBox";
    TextView mainUserLogin,mainUserManual,mainCustomer,mainRestaurantInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_user_login);
        if(all_user_login != null) {
            all_user_login = this;
        }
        getSupportActionBar().hide();
        admin=(ImageView)findViewById(R.id.adminLogin);
        admin.setOnClickListener(this);
        manual=(ImageView)findViewById(R.id.userManual);
        manual.setOnClickListener(this);
        customer=(ImageView)findViewById(R.id.customerLogin);
        customer.setOnClickListener(this);
        restaurant=(ImageView)findViewById(R.id.restaurantInfo);
        restaurant.setOnClickListener(this);
        mainUserLogin=(TextView)findViewById(R.id.mainUserLogin);
        Typeface font=Typeface.createFromAsset(getAssets(),"fonts/ITCAvantGardePro-BoldOblique.otf");
        mainUserLogin.setTypeface(font);
        SpannableString content1 = new SpannableString("User Login");
        content1.setSpan(new UnderlineSpan(), 0, content1.length(), 0);
        mainUserLogin.setText(content1);

        mainUserManual=(TextView)findViewById(R.id.mainUserManual);
        mainUserManual.setTypeface(font);
        SpannableString content2 = new SpannableString("Customer Manual");
        content2.setSpan(new UnderlineSpan(), 0, content2.length(), 0);
        mainUserManual.setText(content2);

        mainCustomer= (TextView)findViewById(R.id.mainCustomer);
        mainCustomer.setTypeface(font);
        SpannableString content3 = new SpannableString("Customer");
        content3.setSpan(new UnderlineSpan(), 0, content3.length(), 0);
        mainCustomer.setText(content3);

        mainRestaurantInfo=(TextView)findViewById(R.id.mainRestaurantInfo);
        mainRestaurantInfo.setTypeface(font);
        SpannableString content4 = new SpannableString("Restaurant Info");
        content4.setSpan(new UnderlineSpan(), 0, content4.length(), 0);
        mainRestaurantInfo.setText(content4);
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.adminLogin){
            Intent i=new Intent(All_User_Login.this,AdminInterface_User_Login.class);
            startActivity(i);
        }
        if (v.getId()==R.id.customerLogin){
            final String Token;
            final SharedPreferences sharedPreferences = getSharedPreferences(tokenSharedPref, Context.MODE_PRIVATE);
            //Check if there is Token Exist or Not in sharedPref

            //first check if token exist or not in shared pref
            Token = sharedPreferences.getString("Token","");
            if(Token.equals("")){
                //GENERATE NEW
                GenerateNew(sharedPreferences);
            }
            else{
                //if exists it is paid or not
                //if yes GENERATE NEW //Already Paid

                //if No
                //Use OLD ONE
                //OR Make New By Checking out
                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Found Older Token.Checking Token If Paid Or Not");
                progressDialog.setCancelable(false);
                progressDialog.show();
                new HttpSourceRequest(new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        //Run the Code Here
                        if(msg != null){
                            String ack = msg.obj.toString();
                            progressDialog.cancel();
                            if(ack.equals("1")){//NOT PAID
                                AlertDialog.Builder builder = new AlertDialog.Builder(All_User_Login.this);
                                builder.setTitle("Token Not Paid?");
                                builder.setMessage("please go to the Bill Section and checkout for new Token!");
                                builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        MainActivity.TokenNo = Token;
                                        Intent i = new Intent(All_User_Login.this, MainActivity.class);
                                        startActivity(i);
                                    }
                                });
                                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                builder.create().show();
                            }
                            else if(ack.equals("0")){//PAID
                                GenerateNew(sharedPreferences);//CREATE NEW
                            }
                        }
                    }
                }, MainActivity.ServerIP + "token.php?TNO=");
            }
        }
        if(v.getId()==R.id.restaurantInfo){
            Intent i=new Intent(All_User_Login.this, Customer_RestaurantDetails.class);
            startActivity(i);
        }
        if(v.getId()==R.id.userManual){
            Intent i=new Intent(All_User_Login.this, Customer_Manual.class);
            startActivity(i);
        }

    }
    void GenerateNew(final SharedPreferences sharedPreferences){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Wait!!");
        builder.setMessage("Do You sure want to create new Token!");
        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final ProgressDialog progressDialog = new ProgressDialog(All_User_Login.this);
                progressDialog.setMessage("Creating New Token");
                progressDialog.setCancelable(false);
                progressDialog.show();
                new HttpSourceRequest(new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        //Run the Code Here
                        if (msg != null) {
                            String Token = msg.obj.toString();
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("Token", Token);
                            editor.apply();
                            MainActivity.TokenNo = Token;
                            progressDialog.cancel();
                            Intent i = new Intent(All_User_Login.this, MainActivity.class);
                            startActivity(i);
                        }
                    }
                }
                        , MainActivity.ServerIP + "token.php?TOKEN");
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("Wait!!");
        b.setMessage("Are you sure want to exit?");
        b.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (mainActivity != null) {
                    mainActivity.finish();
                }
                if (tableList != null) {
                    tableList.finish();
                }
                if (bill != null) {
                    bill.finish();
                }
                if (all_user_login != null){
                    all_user_login.finish();
                }
                finish();
            }
        });
        b.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        b.create().show();
    }
}
