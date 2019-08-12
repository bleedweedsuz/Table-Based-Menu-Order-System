package com.example.sophiya.tbmosystem;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.QwertyKeyListener;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sophiya.tbmosystem.CustomerInterface.Customer_ItemList;

import org.json.JSONArray;
import java.io.InputStream;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity{
    /**SERVER IP LISTS************************************************/
    public static String URLGallery ="http://suzworkshop.com/adminpage/admin/";
    public static String ServerIP ="http://suzworkshop.com/adminpage/";
    public static String AdminIP ="http://suzworkshop.com/adminpage/admin/";
   //public static String URLGallery ="http://10.0.3.2/adminpage/admin/";
  // public static String ServerIP ="http://10.0.3.2/adminpage/";
  // public static String AdminIP ="http://10.0.3.2/adminpage/admin/";
    /*****************************************************************/
    public static String DBName ="emenu";
    public static String TokenNo = "";
    public static String TableNo ="";
    public static int DBVersion =1;
    public int selectedItem = -1;
    ArrayList<CategoryAdapter> categoryAdapterArrayList = new ArrayList<CategoryAdapter>();
    LinearLayout container;
    ProgressBar pBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        All_User_Login.mainActivity = this;

        getSupportActionBar().hide();
        pBar = (ProgressBar)findViewById(R.id.pBar);
        pBar.setIndeterminate(true);
        pBar.setVisibility(View.VISIBLE);
        container = (LinearLayout)findViewById(R.id.container);
        select_ItemCategory();
        FloatingBtnContainer(findViewById(R.id.FloatingBtnDraggable));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    private void childTileLoader() {
        try {
            container.removeViews(0, container.getChildCount());
        }
        catch (Exception e)
        {
            Log.e("Error",e.toString());
        }
        for(int i=0; i<categoryAdapterArrayList.size();i++){
            container.addView(categoryAdapterArrayList.get(i).GetView());
        }
        ClearSelected();
    }
    class Category {
        Integer CID;
        Bitmap icon;
        String Title;
        public Category(Integer CID,Bitmap icon,String Title){
            this.CID = CID;
            this.icon = icon;
            this.Title = Title;
        }
    }
    class CategoryAdapter {
        Category category;
        public Integer ID;
        public RelativeLayout rBackTile;
        public CategoryAdapter(Category category,Integer ID){
            this.category = category;
            this.ID =ID;
        }
        public View GetView(){
            View view = getLayoutInflater().inflate(R.layout.customer_categorylist,null,true);
            ImageView categoryBtn = (ImageView)view.findViewById(R.id.imgBtn);
            if(category.icon!=null){
                categoryBtn.setImageBitmap(category.icon);
            }
            categoryBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    Fragment fragment = new Customer_ItemList();
                    Bundle bundle = new Bundle();
                    bundle.putString("CID", category.CID + "");
                    fragment.setArguments(bundle);
                    fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                    //   fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    fragmentTransaction.add(R.id.maincontainer, fragment);
                    fragmentTransaction.commit();
                    selectedItem = ID;
                    ClearSelected();

                }
            });
            TextView title = (TextView)view.findViewById(R.id.textName);
            title.setText(category.Title);
            Typeface font=Typeface.createFromAsset(getAssets(),"fonts/ITCAvantGardePro-BoldOblique.otf");
            title.setTypeface(font);

            ProgressBar pCBar = (ProgressBar)view.findViewById(R.id.cPBar);
            pCBar.setVisibility(View.GONE);

            rBackTile = (RelativeLayout)view.findViewById(R.id.rViewTile);
            return view;
        }
    }
    public void select_ItemCategory(){
        new HttpSourceRequest(new Handler(){
            @Override
            public void handleMessage(Message msg) {
                //Run the Code Here
                if(msg != null){
                    final String Source = msg.obj.toString();
                    categoryAdapterArrayList.clear();
                    //----------->
                    try {
                        AsyncTask<Void,Void,Void> buffering = new AsyncTask<Void,Void,Void>(){
                            @Override
                            protected Void doInBackground(Void... params) {
                                try {
                                    JSONArray jsonArray = new JSONArray(Source);
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        Bitmap bitmap = null;
                                        try {
                                            String URLPath = MainActivity.URLGallery + (jsonArray.getJSONObject(i).getString("photo")).replace("\\","");
                                            InputStream iStream = new java.net.URL(URLPath).openStream();
                                            BitmapFactory.Options options = new BitmapFactory.Options();
                                            options.inSampleSize = 2;
                                            bitmap = BitmapFactory.decodeStream(iStream, null, options);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        Category c = new Category(jsonArray.getJSONObject(i).getInt("CID"), bitmap,jsonArray.getJSONObject(i).getString("cName"));
                                        categoryAdapterArrayList.add(new CategoryAdapter(c,i));
                                    }
                                }
                                catch (Exception ex){
                                    //Error
                                }
                                return null;
                            }
                            @Override
                            protected void onPostExecute(Void aVoid) {
                                super.onPostExecute(aVoid);
                                childTileLoader();
                                pBar.setVisibility(View.INVISIBLE);
                            }
                        };
                        buffering.execute();
                    }
                    catch (Exception ex){
                        Log.e("Error", ex.toString());
                    }
                }
            }
        },MainActivity.ServerIP + "itemCategory.php");
    }
    public void FloatingBtnContainer(final View view){
        try{
            final int width=getWidth(this),height=getHeight(this);
            view.setX(width/2);view.setY(height / 4);
            view.setOnTouchListener(new View.OnTouchListener() {
                boolean isDragging = false;
                float X, Y;
                float deltaX, deltaY;
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                            isDragging = false;
                            deltaX = v.getX() - event.getRawX();
                            deltaY = v.getY() - event.getRawY();
                            return true;
                        }
                        case MotionEvent.ACTION_UP: {
                            if (!isDragging) {
                                //OnClick Event Trigger
                                Intent intent = new Intent(MainActivity.this,TableList.class);
                                startActivity(intent);
                            }
                            isDragging = false;
                            return true;
                        }
                        case MotionEvent.ACTION_MOVE: {
                            X = event.getRawX();
                            Y = event.getRawY();
                            //------------->
                            //if (X > v.getWidth() && Y > v.getHeight() && X < (width - v.getWidth()) && Y < (height - v.getHeight())) {
                            if (X > v.getWidth() && X < (width - v.getWidth())) {
                                v.animate().x(event.getRawX() + deltaX).setDuration(0).start();
                            }
                            if(Y > v.getHeight() && Y < (height - v.getHeight())){
                                v.animate().y(event.getRawY() + deltaY).setDuration(0).start();
                            }
                            isDragging = true;
                            return true;
                        }
                        default:
                            return false;
                    }
                }
            });
        }
        catch (Exception Ex){
            Ex.printStackTrace();
        }
    }
    public static int getWidth(Context context){
        int width=0;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        return width;
    }
    public static int getHeight(Context context){
        int height=0;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        height = size.y;
        return height;
    }
    public void ClearSelected(){
        for(int i=0;i<categoryAdapterArrayList.size();i++){
            if(i==selectedItem) {
                categoryAdapterArrayList.get(i).rBackTile.setBackgroundColor(Color.rgb(209,254,253));
            }
            else{
                categoryAdapterArrayList.get(i).rBackTile.setBackgroundColor(Color.WHITE);
            }
        }
    }
}
