package com.example.sophiya.tbmosystem.AdminInterface;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.sophiya.tbmosystem.HttpSourceRequest;
import com.example.sophiya.tbmosystem.MainActivity;
import com.example.sophiya.tbmosystem.OtherUserInterface.OtherUserMain;
import com.example.sophiya.tbmosystem.R;

public class AdminInterface_User_Login extends AppCompatActivity{
    EditText userName,userPassword;
    TextView heading;
    boolean isLoginIn=false;
    String sharedPrefData = "EMenu";
    int uType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admininterface_user_login);
        isLoginIn = isLogged();
        if(isLoginIn){
            if(uType == 1){
                AdminPage();
            }
            else{
                OtherPage();
            }
        }
        getSupportActionBar().hide();
        heading=(TextView)findViewById(R.id.heading);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/ITCAvantGardePro-BoldOblique.otf");
        SpannableString content = new SpannableString("User Login");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        heading.setText(content);
        heading.setTypeface(font);
        userName=(EditText)findViewById(R.id.All_UserName);
        userPassword=(EditText)findViewById(R.id.All_Password);
    }
    public void LoginBtn(View view){
        try{
            final ProgressBar loginProgressReport = (ProgressBar)findViewById(R.id.loginProgressReport);
            loginProgressReport.setVisibility(View.VISIBLE);
            new HttpSourceRequest(new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    if(msg !=null){
                        String responseCode = msg.obj.toString().trim();
                        try {
                            if(responseCode.equals("1")){
                                AdminPage();
                            }
                            else if(responseCode.equals("2")){
                                OtherPage();
                            }
                            else{
                                View barSnack= findViewById(R.id.barSnack);
                                Snackbar snackbar = Snackbar.make(barSnack, "Invalid UserName and Password", Snackbar.LENGTH_SHORT);
                                snackbar.show();
                            }
                            loginProgressReport.setVisibility(View.INVISIBLE);
                            Log.d("-->",responseCode);

                        }
                        catch (Exception ex){
                            Log.e("Error", ex.toString());
                        }
                    }
                }
            }, MainActivity.ServerIP + "userDetail.php?userName="+userName.getText().toString().trim()+"&userPassword="+userPassword.getText().toString().trim());
        }
        catch (Exception ex){
            Log.e("Error",ex.toString());
        }
    }
    private void storeSharedPref(int uType){
        try {
            if(!isLoginIn) {
                SharedPreferences.Editor editor = getSharedPreferences(sharedPrefData, Context.MODE_PRIVATE).edit();
                editor.putString("username", userName.getText().toString().trim());
                editor.putString("password", userName.getText().toString().trim());
                editor.putInt("uType", uType);
                editor.putBoolean("isLoged", true);
                editor.apply();
            }
        }
        catch (Exception ex){

        }
    }
    private boolean isLogged(){
        try{
            SharedPreferences s = getSharedPreferences(sharedPrefData,Context.MODE_PRIVATE);
            if(s.getBoolean("isLoged",false)){
                uType = s.getInt("uType",0);
                return true;
            }
        }
        catch (Exception ex){

        }
        return false;
    }
    private void AdminPage(){
        storeSharedPref(1);
        isLoginIn = true;
        Intent i=new Intent(AdminInterface_User_Login.this,Admin_Interface.class);
        startActivity(i);
    }
    private void OtherPage(){
        storeSharedPref(2);
        isLoginIn = true;
        Intent i=new Intent(AdminInterface_User_Login.this,OtherUserMain.class);
        startActivity(i);
    }
    @Override
    protected void onStop() {
        super.onStop();
        if(isLoginIn){
            finish();
        }
    }
}