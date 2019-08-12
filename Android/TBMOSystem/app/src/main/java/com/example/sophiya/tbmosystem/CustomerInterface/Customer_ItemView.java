package com.example.sophiya.tbmosystem.CustomerInterface;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.InputType;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sophiya.tbmosystem.DBConnectionHelper;
import com.example.sophiya.tbmosystem.HttpSourceRequest;
import com.example.sophiya.tbmosystem.MainActivity;
import com.example.sophiya.tbmosystem.R;

import org.json.JSONArray;
import java.io.InputStream;
import java.util.ArrayList;
public class Customer_ItemView extends AppCompatActivity {
    GridView gridView;
    String GalleryIDStr;
    String ItemID;
    ImageView tileIco;
    String Price;
    TextView RateVal,ItemTitle,ItemDetails,Add;
    ArrayList<Tile> BmpAList = new ArrayList<Tile>();
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_itemview);
        getSupportActionBar().hide();
        gridView =(GridView)findViewById(R.id.grid);
        this.GalleryIDStr = getIntent().getStringExtra("GalleryID");
        this.ItemID = getIntent().getStringExtra("ID");
        /*ItemView Initialize*/
        ItemTitle = (TextView)findViewById(R.id.itemTitle);
        Typeface font=Typeface.createFromAsset(getAssets(),"fonts/ITCAvantGardePro-MediumOblique.otf");
        ItemTitle.setTypeface(font);
        RateVal = (TextView)findViewById(R.id.RateVal);
        Typeface font1=Typeface.createFromAsset(getAssets(),"fonts/ITCAvantGardePro-MediumOblique.otf");
        RateVal.setTypeface(font1);
        ItemDetails = (TextView)findViewById(R.id.itemDescription);
        Typeface font2=Typeface.createFromAsset(getAssets(),"fonts/ITCAvantGardePro-BookOblique.otf");
        ItemDetails.setTypeface(font2);
        Add=(TextView)findViewById(R.id.AddItem);
        Add.setTypeface(font1);
        tileIco =(ImageView)findViewById(R.id.itemIco);

        progressBar = (ProgressBar)findViewById(R.id.pBarProgress);
        SetStringData();
        MakeGallery();
    }
    private void SetStringData(){
        ItemTitle.setText(getIntent().getStringExtra("Name"));
        SpannableString content1 = new SpannableString(getIntent().getStringExtra("Name"));
        content1.setSpan(new UnderlineSpan(), 0, content1.length(), 0);
        ItemTitle.setText(content1);
        RateVal.setText("Rs. "+getIntent().getStringExtra("Price")+"/-");
        Price = getIntent().getStringExtra("Price");
        String detailData = "<h3>Details</h3>" +getIntent().getStringExtra("Description");
        ItemDetails.setText(Html.fromHtml(detailData));
        tileIco.setImageBitmap(((Bitmap) getIntent().getParcelableExtra("bmp")));
    }
    private void MakeGallery(){
        new HttpSourceRequest(new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg !=null){
                    final String jsonString = msg.obj.toString();
                    try {
                        AsyncTask<Void,Bitmap,Void> buffering = new AsyncTask<Void,Bitmap,Void>(){
                            @Override
                            protected void onPreExecute() {
                                super.onPreExecute();
                                BmpAList.clear();
                                progressBar.setVisibility(View.VISIBLE);
                            }
                            @Override
                            protected Void doInBackground(Void... params) {
                                try {
                                    BmpAList.clear();
                                    JSONArray jsonArray = new JSONArray(jsonString);
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        Bitmap bitmap = null;
                                        try {
                                            String URLPath = MainActivity.URLGallery + (jsonArray.getJSONObject(i).getString("image")).replace("\\","");
                                            InputStream iStream = new java.net.URL(URLPath).openStream();
                                            BitmapFactory.Options options = new BitmapFactory.Options();
                                            options.inSampleSize = 6;
                                            bitmap = BitmapFactory.decodeStream(iStream,null,options);
                                            BmpAList.add(new Tile(bitmap));
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                                catch (Exception ex){
                                }
                                return null;
                            }
                            @Override
                            protected void onPostExecute(Void aVoid) {
                                super.onPostExecute(aVoid);
                                gridView.setAdapter(new GalleryTileAdapter());
                                progressBar.setVisibility(View.GONE);
                            }
                        };
                        buffering.execute();
                    }
                    catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        },MainActivity.ServerIP + "galleryInfo.php?gID=" + GalleryIDStr);
    }
    class Tile{
        public Bitmap bmp;
        public Tile(Bitmap bmp){
            this.bmp = bmp;
        }
    }
    private class GalleryTileAdapter extends ArrayAdapter<Tile>{
        public GalleryTileAdapter() {
            super(Customer_ItemView.this,R.layout.customer_gallerylist_item,BmpAList);
        }
        @Override
        public int getCount() {
            return BmpAList.size();
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.customer_gallerylist_item,null,true);
            ImageView imageView = (ImageView)view.findViewById(R.id.iconView);
            imageView.setImageBitmap(BmpAList.get(position).bmp);
            return view;
        }
    }
    public void AddItem(View view){
        final EditText numberBox = new EditText(Customer_ItemView.this);
        numberBox.setText("1");
        numberBox.setInputType(InputType.TYPE_CLASS_NUMBER);

        final View nView =numberBox;
        AlertDialog.Builder builder = new AlertDialog.Builder(Customer_ItemView.this);
        builder.setView(nView);
        builder.setPositiveButton("Add To List", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Integer orderQ;

                orderQ = Integer.valueOf(numberBox.getText().toString().trim());
                //---->add to local database
                try {
                    boolean setFlag = false;
                    SQLiteOpenHelper dbHelper = new DBConnectionHelper(Customer_ItemView.this, MainActivity.DBName, null, MainActivity.DBVersion);
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    //--->Insert
                    for (int i = 0; i < orderQ; i++) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("ID", Integer.valueOf(ItemID));
                        contentValues.put("NAME", ItemTitle.getText().toString());
                        contentValues.put("COMMENT", "");
                        contentValues.put("TOKEN", MainActivity.TokenNo);
                        contentValues.put("ISORDER",0);
                        contentValues.put("PRICE",Double.valueOf(Price));
                        db.insert("ItemList", null, contentValues);
                        setFlag = true;
                    }
                    db.close();
                    if (setFlag) {
                        Toast.makeText(Customer_ItemView.this, "your order is set", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(Customer_ItemView.this, "Oops! something goes wrong", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        builder.create().show();
    }
}
