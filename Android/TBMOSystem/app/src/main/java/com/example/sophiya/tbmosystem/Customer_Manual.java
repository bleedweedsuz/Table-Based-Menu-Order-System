package com.example.sophiya.tbmosystem;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

/**
 * Created by Sophiya on 2/26/2016.
 */
public class Customer_Manual extends AppCompatActivity {
    TextView t0,t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_manual);
        getSupportActionBar().hide();
        t0=(TextView)findViewById(R.id.t0);
        Typeface font=Typeface.createFromAsset(getAssets(),"fonts/ITCAvantGardePro-BoldOblique.otf");
        t0.setTypeface(font);
        SpannableString content1 = new SpannableString("Manual To Place the Food Order");
        content1.setSpan(new UnderlineSpan(), 0, content1.length(), 0);
        t0.setText(content1);

        t1=(TextView)findViewById(R.id.t1);
        Typeface font2=Typeface.createFromAsset(getAssets(),"fonts/ITCAvantGardePro-BookOblique.otf");
        t1.setTypeface(font2);
        t2=(TextView)findViewById(R.id.t2);
        t2.setTypeface(font2);
        t3=(TextView)findViewById(R.id.t3);
        t3.setTypeface(font2);
        t4=(TextView)findViewById(R.id.t4);
        t4.setTypeface(font2);
        t5=(TextView)findViewById(R.id.t5);
        t5.setTypeface(font2);
        t6=(TextView)findViewById(R.id.t6);
        t6.setTypeface(font2);
        t7=(TextView)findViewById(R.id.t7);
        t7.setTypeface(font2);
        t8=(TextView)findViewById(R.id.t8);
        t8.setTypeface(font2);
        t9=(TextView)findViewById(R.id.t9);
        t9.setTypeface(font2);
        t10=(TextView)findViewById(R.id.t10);
        t10.setTypeface(font2);
        t11=(TextView)findViewById(R.id.t11);
        t11.setTypeface(font2);
        t12=(TextView)findViewById(R.id.t12);
        t12.setTypeface(font2);
        t13=(TextView)findViewById(R.id.t13);
        t13.setTypeface(font2);
        t14=(TextView)findViewById(R.id.t14);
        t14.setTypeface(font2);
        t15=(TextView)findViewById(R.id.t15);
        t15.setTypeface(font2);

    }
}
