package com.example.sophiya.tbmosystem;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

public class TableList extends AppCompatActivity{
    RecyclerView RMainViewList;
    RecyclerView.Adapter vAdapter;
    RecyclerView.LayoutManager lManager;
    ArrayList<String> tempItemList = new ArrayList<String>();
    ArrayList<Items> tempItemsArrayList = new ArrayList<Items>();
    ProgressBar pBar;
    int TimerPlus =0;
    int tCounter =10;
    boolean isTransactionCountDown = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_list);

        All_User_Login.tableList = this;


        getSupportActionBar().hide();
        pBar =(ProgressBar)findViewById(R.id.pBar);
        RMainViewList = (RecyclerView)findViewById(R.id.RMainViewList);
        RMainViewList.setHasFixedSize(true);
        lManager = new LinearLayoutManager(this);
        RMainViewList.setLayoutManager(lManager);
        ReLoadingItemList();
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
            title.setText(tempItemList.get(position));
            CardView MainBtn = (CardView) holder.getView().findViewById(R.id.mainBack);
            MainBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(tempItemsArrayList.get(position).State == 0){
                        AlertDialog.Builder builder = new AlertDialog.Builder(TableList.this);
                        builder.setTitle("Wait!!");
                        builder.setMessage("are you sure cancel this item?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SQLiteOpenHelper dbHelper = new DBConnectionHelper(TableList.this, MainActivity.DBName, null, MainActivity.DBVersion);
                                SQLiteDatabase db = dbHelper.getWritableDatabase();
                                db.execSQL("delete from ItemList where SN="+ tempItemsArrayList.get(position).SN);
                                db.close();
                                Toast.makeText(TableList.this,"Your Item Deleted",Toast.LENGTH_SHORT).show();
                                ReLoadingItemList();
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
                    else {
                        final EditText commentBox = new EditText(TableList.this);
                        commentBox.setHint("Enter Your Note Here... \n eg. Pack this Item, \n add more spice etc.");
                        commentBox.setText(tempItemsArrayList.get(position).Note.toString().trim());
                        AlertDialog.Builder builder = new AlertDialog.Builder(TableList.this);
                        builder.setTitle("Put Note Here");
                        builder.setView(commentBox);
                        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                tempItemsArrayList.get(position).Note = commentBox.getText().toString().trim();
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
                }
            });
            ImageView icon = (ImageView)holder.getView().findViewById(R.id.cIcon);
            if(tempItemsArrayList.get(position).State == 1){
                icon.setImageDrawable(getDrawable(R.drawable.save));
            }
            else{
                icon.setImageDrawable(null);
            }
        }
        @Override
        public int getItemCount() {
            return tempItemList.size();
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
    private void ReLoadingItemList(){
        SQLiteDatabase db = null;
        try{
            int count =0;
            tempItemList.clear();
            tempItemsArrayList.clear();
            SQLiteOpenHelper dbHelper = new DBConnectionHelper(this, MainActivity.DBName, null, MainActivity.DBVersion);
            db = dbHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery("select *  from ItemList order by SN asc",null);
            for(cursor.moveToLast();!cursor.isBeforeFirst();cursor.moveToPrevious()) {
                count ++;
                tempItemList.add(count + ". "+ cursor.getString(cursor.getColumnIndex("NAME")));
                tempItemsArrayList.add(new Items(
                                        cursor.getInt(cursor.getColumnIndex("SN")),
                                        cursor.getInt(cursor.getColumnIndex("ID")),
                                        cursor.getString(cursor.getColumnIndex("NAME")),
                                        cursor.getInt(cursor.getColumnIndex("ISORDER")),
                                        cursor.getString(cursor.getColumnIndex("COMMENT")),
                                        cursor.getDouble(cursor.getColumnIndex("PRICE"))

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
    public void Confirm_OnClick(final View view){
        if(tempItemList.size()==0){
            View barSnack= findViewById(R.id.tableListSnack);
            Snackbar snackbar = Snackbar.make(barSnack, "No Food Item selected " +
                    "in the Order List", Snackbar.LENGTH_SHORT);
            snackbar.show();
            //Toast.makeText(TableList.this,"No Food Item in the Order List",Toast.LENGTH_SHORT).show();
        }
        else {
            try {
                final EditText tBox = new EditText(this);
                tBox.setHint("Enter Table No.");
                tBox.setText(MainActivity.TableNo);
                new AlertDialog
                        .Builder(this)
                        .setTitle("Please Enter Your Table No.")
                        .setView(tBox)
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                TimerPlus = 0;
                                isTransactionCountDown = true;
                                MainActivity.TableNo = tBox.getText().toString().trim();
                                CountDownBegin(view);
                            }
                        }).create().show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
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
    public void CountDownBegin(final View view){
        View v = getLayoutInflater().inflate(R.layout.table_counter_buffering,null,true);
        final TextView timerText = (TextView)v.findViewById(R.id.timer);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this).
                setView(v).
                setTitle("you can cancel within 10sec..").
                setPositiveButton("CANCEL NOW", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        isTransactionCountDown = false;
                        TimerPlus = 0;
                    }
                }).
                setNegativeButton("Please Proceed", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TimerPlus =0;
                        isTransactionCountDown = false;
                        ConfirmTransaction(view);
                    }
                })
                .setCancelable(false);
        final AlertDialog alertDialog  = builder.create();
        alertDialog.show();
        new AsyncTask<Void,Integer,Void>(){
            boolean isOk =false;
            @Override
            protected Void doInBackground(Void... params) {
                while (isTransactionCountDown){
                    try {
                        TimerPlus++;
                        publishProgress(TimerPlus);
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(TimerPlus >= (tCounter *2)){
                        isOk =true;
                        return null;
                    }
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                timerText.setText(values[0]/2 +"");
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if(isOk){
                    alertDialog.cancel();
                    ConfirmTransaction(view);
                }
            }
        }.execute();
    }
    public void ConfirmTransaction(final View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(TableList.this);
        builder.setTitle("Are you sure to confirm this order?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                pBar.setVisibility(View.VISIBLE);
                view.setEnabled(false);
                JSONArray jsonArray = new JSONArray();
                for (int i = 0; i < tempItemsArrayList.size(); i++) {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("TableNo", MainActivity.TableNo + "");
                        jsonObject.put("FoodID", tempItemsArrayList.get(i).ItemID + "");
                        jsonObject.put("Note", tempItemsArrayList.get(i).Note);
                        jsonObject.put("Token", tempItemsArrayList.get(i).UniqueID);
                        jsonObject.put("Price", tempItemsArrayList.get(i).Price);
                        jsonArray.put(jsonObject);
                        SQLiteOpenHelper dbHelper = new DBConnectionHelper(TableList.this, MainActivity.DBName, null, MainActivity.DBVersion);
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        db.execSQL("Update ItemList set ISORDER='1',COMMENT='" + tempItemsArrayList.get(i).Note
                                + "' where SN='" + tempItemsArrayList.get(i).SN + "'");
                        db.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }


                String jString = jsonArray.toString().trim().replace(" ", "%20");
                Log.d("-->",jString);
                String HTTPRequestString = MainActivity.ServerIP + "order.php?jString=" + jString;
                new HttpSourceRequest(new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        if (msg != null) {
                            String ack = msg.obj.toString();
                            Log.d("-->",ack);
                            view.setEnabled(true);
                            if (ack.contains("1") || ack.contains("2")) {
                                Toast.makeText(TableList.this, "your order is confirm.", Toast.LENGTH_SHORT).show();
                                ReLoadingItemList();
                            } else {
                                Toast.makeText(TableList.this, "something goes wrong!", Toast.LENGTH_SHORT).show();
                            }
                            pBar.setVisibility(View.GONE);
                        }
                    }
                }, HTTPRequestString);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }
    public void BillPlease(View view){
        startActivity(new Intent(this,Bill.class));
    }
}
