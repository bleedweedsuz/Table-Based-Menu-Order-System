package com.example.sophiya.tbmosystem;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;
public class Bill extends AppCompatActivity {
    RecyclerView RMainViewList;
    RecyclerView.Adapter vAdapter;
    RecyclerView.LayoutManager lManager;
    ProgressBar pBar;
    ArrayList<Items> tempItemsArrayList = new ArrayList<Items>();
    boolean isCheckOut=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill);

        All_User_Login.bill = this;


        getSupportActionBar().hide();
        pBar =(ProgressBar)findViewById(R.id.pBar);
        RMainViewList = (RecyclerView)findViewById(R.id.RMainViewList);
        RMainViewList.setHasFixedSize(true);
        lManager = new LinearLayoutManager(this);
        RMainViewList.setLayoutManager(lManager);
        GetBill();
    }
    private void GetBill(){
        pBar.setVisibility(View.VISIBLE);
        new HttpSourceRequest(new Handler(){
            @Override
            public void handleMessage(Message msg) {
                //Run the Code Here
                if(msg != null){
                    String jSource = msg.obj.toString();
                    //------------->
                    try {
                        pBar.setVisibility(View.GONE);
                        Double total = 0.0;
                        tempItemsArrayList.clear();
                        if(!jSource.equals("")) {
                            JSONArray jsonArray = new JSONArray(jSource);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                tempItemsArrayList.add(new Items(
                                                jsonArray.getJSONObject(i).getInt("SN"),
                                                jsonArray.getJSONObject(i).getInt("foodID"),
                                                jsonArray.getJSONObject(i).getString("Name"),
                                                jsonArray.getJSONObject(i).getInt("state"),
                                                jsonArray.getJSONObject(i).getString("note"),
                                                jsonArray.getJSONObject(i).getDouble("Price")
                                        )
                                );
                                total += jsonArray.getJSONObject(i).getDouble("Price");
                            }
                        }
                        tempItemsArrayList.add(new Items(
                                        0,
                                        0,
                                        "TOTAL PRICE",
                                        2,
                                        "",
                                        total
                                )
                        );
                        vAdapter = new RAdapter();
                        RMainViewList.setAdapter(vAdapter);
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, MainActivity.ServerIP + "order.php?TOKEN="+MainActivity.TokenNo);
    }
    class RAdapter extends RecyclerView.Adapter<RViewHolder>{
        public RAdapter() {
            super();
        }
        @Override
        public RViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_list_card,parent,false);
            RViewHolder rViewHolder = new RViewHolder(view);
            return rViewHolder;
        }
        @Override
        public void onBindViewHolder(RViewHolder holder, final int position) {
            TextView title =(TextView) holder.getView().findViewById(R.id.cName);
            String data = tempItemsArrayList.get(position).Title + "  Rs." + tempItemsArrayList.get(position).Price + "/-";
            title.setText(Html.fromHtml(data));

            RelativeLayout rView= (RelativeLayout)holder.getView().findViewById(R.id.rView);
            rView.setBackgroundColor(Color.rgb(255, 255, 255));

            ImageView icon = (ImageView)holder.getView().findViewById(R.id.cIcon);
            if(tempItemsArrayList.get(position).State == 1){
                icon.setImageDrawable(getDrawable(R.drawable.save));
            }
            else if(tempItemsArrayList.get(position).State == 2){
                rView.setBackgroundColor(Color.rgb(206,254,255));
            }
            else{
                icon.setImageDrawable(null);
            }
        }
        @Override
        public int getItemCount() {
            return tempItemsArrayList.size();
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
    private class Items{
        public Integer SN;
        public Integer ItemID;
        public String Title;
        public Integer State;
        public String Note;
        public String UniqueID;
        public Double Price;
        public Items(Integer SN,Integer ItemID,String Title,Integer State,String Note,Double Price){
            this.SN = SN;
            this.ItemID = ItemID;
            this.Title = Title;
            this.State = State;
            this.Note = Note;
            this.UniqueID = MainActivity.TokenNo + "-" + SN;
            this.Price = Price;
        }
    }
    public void CheckOut(final View view){
        try{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Wait!!");
            builder.setMessage("are you sure want to check out!");
            builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    pBar.setVisibility(View.VISIBLE);
                    //First Mark as leave = 1 in server
                    new HttpSourceRequest(new Handler(){
                        @Override
                        public void handleMessage(Message msg) {
                            //Run the Code Here
                            if(msg != null){
                                String ack = msg.obj.toString();
                                if(ack.equals("1")) {

                                    view.setEnabled(false);
                                    SQLiteOpenHelper dbHelper = new DBConnectionHelper(Bill.this, MainActivity.DBName, null, MainActivity.DBVersion);
                                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                                    //Clear All Item Insert
                                    db.execSQL("delete from ItemList");
                                    //Clear All CItem
                                    db.execSQL("delete from CItemList");

                                    db.close();
                                    //Clear SharedPref Token
                                    String tokenSharedPref = "TokenBox";
                                    SharedPreferences sharedPreferences = getSharedPreferences(tokenSharedPref, Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("Token", "");
                                    editor.apply();
                                    //Navigate to Main Page

                                    Toast.makeText(Bill.this, "Thank you for coming!", Toast.LENGTH_SHORT).show();
                                    MainActivity.TokenNo = "";
                                    MainActivity.TableNo = "";
                                    //startActivity(new Intent(Bill.this, All_User_Login.class));
                                    if (All_User_Login.mainActivity != null) {
                                        All_User_Login.mainActivity.finish();
                                    }
                                    if (All_User_Login.tableList != null) {
                                        All_User_Login.tableList.finish();
                                    }
                                    if (All_User_Login.bill != null) {
                                        All_User_Login.bill.finish();
                                    }
                                    finish();
                                }else{
                                    Toast.makeText(Bill.this,ack,Toast.LENGTH_SHORT).show();
                                }
                                pBar.setVisibility(View.GONE);
                                view.setEnabled(true);
                            }
                        }
                    },MainActivity.ServerIP + "order.php?LTOKEN=" + MainActivity.TokenNo);
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
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        if(isCheckOut){
            finish();
        }
    }
}
