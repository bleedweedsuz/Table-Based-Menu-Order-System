package com.example.sophiya.tbmosystem.OtherUserInterface;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sophiya.tbmosystem.All_User_Login;
import com.example.sophiya.tbmosystem.DBConnectionHelper;
import com.example.sophiya.tbmosystem.HttpSourceRequest;
import com.example.sophiya.tbmosystem.MainActivity;
import com.example.sophiya.tbmosystem.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class OtherUserMain extends AppCompatActivity implements AdapterView.OnItemClickListener{
    String sharedPrefData = "EMenu";
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    ListView listView;
    int TimeToSleep = 5000;
    boolean stopThisSession =false;
    RecyclerView RMainViewList;
    RecyclerView.LayoutManager lManager;
    RecyclerView.Adapter vAdapter;
    ArrayList<CItemList> cItemLists = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_user_main);
        getSupportActionBar().setTitle("User Panel");
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.descriptionOpen,R.string.descriptionClose){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle("User Panel");
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
        listView =(ListView)findViewById(R.id.olView);
        listView.setOnItemClickListener(this);
        TextView uName = (TextView)findViewById(R.id.uName);
        uName.setText("Hi," + getUserName());
        RMainViewList = (RecyclerView)findViewById(R.id.RMainViewList);
        RMainViewList.setHasFixedSize(true);
        lManager = new LinearLayoutManager(this);
        RMainViewList.setLayoutManager(lManager);
        //-------------------------------------------->
        RunTask();
        LoadData();
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
                drawerLayout.closeDrawer(Gravity.LEFT);
                LoadData();
            }break;
            case 1:{
                drawerLayout.closeDrawer(Gravity.LEFT);
                LogOut();
            }break;
            default:break;
        }
    }
    private void LogOut(){
        stopThisSession =true;
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
    public void RunTask(){
        try{
            new HttpSourceRequest(new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    if(msg != null){
                        //first get data
                        final String jSource = msg.obj.toString();
                        new AsyncTask<Void,Void,Void>(){
                            boolean ringNow =false;
                            @Override
                            protected Void doInBackground(Void... params) {
                                //SQLITEHELPER-->
                                SQLiteOpenHelper dbHelper = new DBConnectionHelper(OtherUserMain.this, MainActivity.DBName, null, MainActivity.DBVersion);
                                //second convert to jsonArray
                                try {
                                    JSONArray dataArray = new JSONArray(jSource);
                                    for(int i=0;i<dataArray.length();i++){
                                        boolean isOK =false;
                                        //Checking TOKEN HERE
                                        String TOKEN = dataArray.getJSONObject(i).getString("token");
                                        String NOTE = dataArray.getJSONObject(i).getString("note");
                                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                                        try{
                                            Cursor c = db.rawQuery("select * from CItemList where TOKEN=?",new String[]{TOKEN});
                                            if(c.getCount()> 0){
                                                isOK = true;
                                            }
                                            else{
                                                isOK =false;
                                            }
                                        }
                                        catch (Exception ex){
                                            ex.printStackTrace();
                                        }
                                        db.close();
                                        //Insert
                                        if(!isOK){
                                            SQLiteDatabase dbI = dbHelper.getWritableDatabase();
                                            try{
                                                ContentValues contentValues = new ContentValues();
                                                contentValues.put("ITEMID", dataArray.getJSONObject(i).getString("foodID"));
                                                contentValues.put("ITEMNAME", dataArray.getJSONObject(i).getString("Name"));
                                                contentValues.put("NOTE", dataArray.getJSONObject(i).getString("note"));
                                                contentValues.put("TOKEN",dataArray.getJSONObject(i).getString("token"));
                                                contentValues.put("TIME",dataArray.getJSONObject(i).getString("time"));
                                                contentValues.put("TABLENO",dataArray.getJSONObject(i).getString("tableNO"));
                                                dbI.insert("CItemList", null, contentValues);
                                                ringNow =true;
                                            }
                                            catch (Exception ex){
                                                ex.printStackTrace();
                                            }
                                            dbI.close();
                                        }
                                        //Update
                                        else{
                                            boolean isChange = false;
                                            try {
                                                SQLiteDatabase dbCS = dbHelper.getWritableDatabase();
                                                try {
                                                    Cursor c = dbCS.rawQuery("select * from CItemList where NOTE=?", new String[]{NOTE});
                                                    if (c.getCount() > 0) {
                                                        isChange = false;
                                                    } else {
                                                        isChange = true;
                                                    }
                                                } catch (Exception ex) {
                                                    ex.printStackTrace();
                                                }
                                                dbCS.close();
                                            }
                                            catch (Exception ex){
                                                ex.printStackTrace();
                                            }
                                            SQLiteDatabase dbU = dbHelper.getWritableDatabase();
                                            try{
                                                //check if note is changes or not if not then just leave it
                                                if(isChange) {
                                                    ContentValues contentValues = new ContentValues();
                                                    contentValues.put("NOTE", dataArray.getJSONObject(i).getString("note"));
                                                    dbU.update("CItemList", contentValues, "TOKEN=?", new String[]{dataArray.getJSONObject(i).getString("token")});
                                                    ringNow=true;
                                                }
                                            }
                                            catch (Exception ex){
                                                ex.printStackTrace();
                                            }
                                            dbU.close();
                                        }
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                if(ringNow) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Ring();
                                            LoadData();
                                        }
                                    });
                                }
                                try {
                                    Thread.sleep(TimeToSleep);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                return null;
                            }

                            @Override
                            protected void onPostExecute(Void aVoid) {
                                super.onPostExecute(aVoid);
                                if(!stopThisSession) {
                                    RunTask();
                                }
                            }
                        }.execute();
                    }
                }
            }, MainActivity.ServerIP + "order.php");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    void Ring(){
        try {
            SoundPool soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
            soundPool.load(this, R.raw.buzzer, 0);
            soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                @Override
                public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                    soundPool.play(sampleId, 1, 1, 0, 0, 1f);
                }
            });
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    void LoadData(){
        SQLiteDatabase db = null;
        try{
            cItemLists.clear();
            int count =0;
            SQLiteOpenHelper dbHelper = new DBConnectionHelper(this, MainActivity.DBName, null, MainActivity.DBVersion);
            db = dbHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery("select *  from CItemList order by SN asc",null);
            for(cursor.moveToLast();!cursor.isBeforeFirst();cursor.moveToPrevious()) {
                count ++;
                cItemLists.add(new CItemList(count,
                                cursor.getInt(cursor.getColumnIndex("SN")),
                                cursor.getInt(cursor.getColumnIndex("ITEMID")),
                                cursor.getString(cursor.getColumnIndex("ITEMNAME")),
                                cursor.getString(cursor.getColumnIndex("NOTE")),
                                cursor.getString(cursor.getColumnIndex("TOKEN")),
                                cursor.getString(cursor.getColumnIndex("TIME")),
                                cursor.getString(cursor.getColumnIndex("TABLENO"))
                        )
                );
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            if(db!=null){db.close();}
            vAdapter = new RAdapter();
            RMainViewList.setAdapter(vAdapter);
        }
    }
    class RAdapter extends RecyclerView.Adapter<RViewHolder>{
        public RAdapter() {
            super();
        }
        @Override
        public RViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.o_user_list,parent,false);
            RViewHolder rViewHolder = new RViewHolder(view);
            return rViewHolder;
        }
        @Override
        public void onBindViewHolder(RViewHolder holder, final int position) {
            TextView title =(TextView) holder.getView().findViewById(R.id.cName);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(cItemLists.get(position).POSITION + ". " +cItemLists.get(position).ITEMNAME);
            stringBuilder.append("<br/>NOTE:<br/>"+ cItemLists.get(position).NOTE);
            stringBuilder.append("<br/>Table [" + cItemLists.get(position).TABLENO + "]");
            stringBuilder.append("<br/>Time:"+ cItemLists.get(position).TIME +" Token No." + cItemLists.get(position).TOKEN);

            title.setText(Html.fromHtml(stringBuilder.toString()));
        }
        @Override
        public int getItemCount() {
            return cItemLists.size();
        }
    }
    public class RViewHolder extends RecyclerView.ViewHolder {
        View view;
        public RViewHolder(View view) {
            super(view);
            this.view = view;
        }
        public View getView(){
            return this.view;
        }
    }
    public class CItemList{
        public Integer POSITION;
        public Integer SN;
        public Integer ITEMID;
        public String ITEMNAME;
        public String NOTE;
        public String TOKEN;
        public String TIME;
        public String TABLENO;
        public CItemList(Integer POSITION,Integer SN, Integer ITEMID, String ITEMNAME, String NOTE, String TOKEN, String TIME,String TABLENO) {
            this.POSITION = POSITION;
            this.SN = SN;
            this.ITEMID = ITEMID;
            this.ITEMNAME = ITEMNAME;
            this.NOTE = NOTE;
            this.TOKEN = TOKEN;
            this.TIME = TIME;
            this.TABLENO =TABLENO;
        }
    }
}