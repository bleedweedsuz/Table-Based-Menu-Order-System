package com.example.sophiya.tbmosystem.AdminInterface;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sophiya.tbmosystem.All_User_Login;
import com.example.sophiya.tbmosystem.MainActivity;
import com.example.sophiya.tbmosystem.R;

public class Admin_Interface extends AppCompatActivity implements AdapterView.OnItemClickListener{
    String sharedPrefData = "EMenu";
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    WebView webView;
    WebChromeClient webChromeClient;
    String AdminURL;
    ListView listView;
    public ValueCallback<Uri[]> uploadFile;
    public static final int REQ_SELECT_FILE = 1001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admininterface_admin_interface);
        AdminURL = MainActivity.AdminIP;
        getSupportActionBar().setTitle("Admin Panel");
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.descriptionOpen,R.string.descriptionClose){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle("Admin Panel");
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Personal");
            }
        };
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        drawerLayout.setClickable(true);
        listView =(ListView)findViewById(R.id.lView);
        listView.setOnItemClickListener(this);
        TextView uName = (TextView)findViewById(R.id.uName);
        uName.setText("Hi,"+getUserName());
        webView = (WebView)findViewById(R.id.webView);
        webChromeClient = new WebChromeClient(){
            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                if(uploadFile != null){
                    uploadFile.onReceiveValue(null);
                    uploadFile = null;
                }
                uploadFile = filePathCallback;
                Intent intent = fileChooserParams.createIntent();
                try{
                    Admin_Interface.this.startActivityForResult(intent,Admin_Interface.REQ_SELECT_FILE);
                }
                catch (ActivityNotFoundException e){
                    uploadFile = null;
                    Toast.makeText(Admin_Interface.this,e.toString(),Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        };
        webView.setWebChromeClient(webChromeClient);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(AdminURL);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQ_SELECT_FILE){
            if(uploadFile == null){return;}
            else{
                uploadFile.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode,data));
                uploadFile = null;
            }
        }
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:{
                webView.loadUrl(AdminURL);
                drawerLayout.closeDrawer(Gravity.LEFT);
            }break;
            case 1:{
                LogOut();
                drawerLayout.closeDrawer(Gravity.LEFT);
            }break;
            default:break;
        }
    }
    private void LogOut(){
        SharedPreferences.Editor editor = getSharedPreferences(sharedPrefData, Context.MODE_PRIVATE).edit();
        editor.putString("username", "");
        editor.putString("password","");
        editor.putInt("uType", 0);
        editor.putBoolean("isLoged", false);
        editor.apply();
        startActivity(new Intent(this, All_User_Login.class));
    }
    private String getUserName(){
        return getSharedPreferences(sharedPrefData, Context.MODE_PRIVATE).getString("username","--");
    }
}
