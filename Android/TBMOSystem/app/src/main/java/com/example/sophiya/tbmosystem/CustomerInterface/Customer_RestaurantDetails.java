package com.example.sophiya.tbmosystem.CustomerInterface;

import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.sophiya.tbmosystem.HttpSourceRequest;
import com.example.sophiya.tbmosystem.MainActivity;
import com.example.sophiya.tbmosystem.R;


import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by Sophiya on 2/21/2016.
 */
public class Customer_RestaurantDetails extends AppCompatActivity {
    TextView restaurantName,restaurantInfo,restaurantBestDish,restaurantTutorial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_restaurantdetails);
        getSupportActionBar().hide();
        restaurantName=(TextView)findViewById(R.id.resName);
        Typeface font=Typeface.createFromAsset(getAssets(),"fonts/ITCAvantGardePro-BoldOblique.otf");
        restaurantName.setTypeface(font);

        restaurantInfo=(TextView)findViewById(R.id.resDescription);
        Typeface font2=Typeface.createFromAsset(getAssets(),"fonts/ITCAvantGardePro-BookOblique.otf");
        restaurantInfo.setTypeface(font2);
        restaurantBestDish=(TextView)findViewById(R.id.resDishName);
        Typeface font3=Typeface.createFromAsset(getAssets(),"fonts/ITCAvantGardePro-BoldOblique.otf");
        restaurantBestDish.setTypeface(font3);
        restaurantTutorial=(TextView)findViewById(R.id.resDishTutorial);
        Typeface font4=Typeface.createFromAsset(getAssets(),"fonts/ITCAvantGardePro-BookOblique.otf");
        restaurantTutorial.setTypeface(font4);
        Select_RestaurantDetail();
    }
    void Select_RestaurantDetail() {
        final ProgressBar progressReport = (ProgressBar)findViewById(R.id.progressReport);
        progressReport.setVisibility(View.VISIBLE);
             new HttpSourceRequest(new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    //Run the Code Here
                    if(msg != null){
                        String Source = msg.obj.toString();
                        try {
                            JSONArray jsonArray=new JSONArray(Source);
                            if(jsonArray.length() > 0) {
                                int i=0;
                                        SpannableString content1 = new SpannableString(jsonArray.getJSONObject(i).getString("res_name"));
                                        content1.setSpan(new UnderlineSpan(), 0, content1.length(), 0);
                                        restaurantName.setText(content1);
                                        restaurantInfo.setText(jsonArray.getJSONObject(i).getString("res_info"));
                                        SpannableString content2 = new SpannableString(jsonArray.getJSONObject(i).getString("res_bestdish"));
                                        content2.setSpan(new UnderlineSpan(), 0, content2.length(), 0);
                                        restaurantBestDish.setText(content2);
                                        restaurantTutorial.setText(jsonArray.getJSONObject(i).getString("res_tutorial"));
                            }

                            progressReport.setVisibility(View.GONE);
                        }
                        catch (Exception ex){
                            Log.e("Error",ex.toString());
                        }

                    }
                }
            },MainActivity.ServerIP + "restaurant_detail.php");
        }
}
