package com.example.sophiya.tbmosystem.CustomerInterface;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.sophiya.tbmosystem.HttpSourceRequest;
import com.example.sophiya.tbmosystem.MainActivity;
import com.example.sophiya.tbmosystem.R;

import org.json.JSONArray;
import java.io.InputStream;
import java.util.ArrayList;

public class Customer_ItemList extends Fragment implements AdapterView.OnItemClickListener{
    String CID;
    ArrayList<Items> itemsArrayList = new ArrayList<Items>();
    GridView gridView;
    ProgressBar pBar,pBar2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.customer_itemlist, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView = (GridView)view.findViewById(R.id.grid);
        gridView.setOnItemClickListener(this);
        CID = getArguments().getString("CID");
        pBar =(ProgressBar)view.findViewById(R.id.pBar);
        pBar.setIndeterminate(true);
        //----Select Items by id
        SelectItemsByID();

    }
    private void SelectItemsByID() {
        try{
            pBar.setVisibility(View.VISIBLE);
            itemsArrayList.clear();
            new HttpSourceRequest(new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    if(msg !=null){
                        final String jsonString = msg.obj.toString();
                        try {
                            AsyncTask<Void,Void,Void> buffering = new AsyncTask<Void,Void,Void>(){
                                @Override
                                protected Void doInBackground(Void... params) {
                                    try {
                                        JSONArray jsonArray = new JSONArray(jsonString);
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            Bitmap bitmap = null;
                                            try {
                                                String URLPath = MainActivity.URLGallery + (jsonArray.getJSONObject(i).getString("photo")).replace("\\","");
                                                InputStream iStream = new java.net.URL(URLPath).openStream();
                                                BitmapFactory.Options options = new BitmapFactory.Options();
                                                options.inSampleSize = 6;
                                                bitmap = BitmapFactory.decodeStream(iStream,null,options);
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            itemsArrayList.add(new Items(
                                                            jsonArray.getJSONObject(i).getInt("foodID"),
                                                            jsonArray.getJSONObject(i).getInt("galleryID"),
                                                            bitmap,
                                                            jsonArray.getJSONObject(i).getString("fName"),
                                                            jsonArray.getJSONObject(i).getInt("fPrice"),
                                                            jsonArray.getJSONObject(i).getString("fDescription")
                                                    )
                                            );
                                        }
                                    }
                                    catch (Exception ex){
                                    }
                                    return null;
                                }
                                @Override
                                protected void onPostExecute(Void aVoid) {
                                    super.onPostExecute(aVoid);
                                    gridView.setAdapter(new ItemsAdapter());
                                    pBar.setVisibility(View.INVISIBLE);
                                }
                            };
                            buffering.execute();
                        }
                        catch (Exception ex){
                            Log.e("Error",ex.toString());
                        }
                    }
                }
            }, MainActivity.ServerIP + "foodItem.php?SCID=" + CID);
        }
        catch (Exception ex){
            Log.e("Error",ex.toString());
        }
    }
    private class Items{
        public Integer ItemID;
        public Integer GalleryID;
        public Bitmap icon;
        public String Title;
        public Integer Price;
        public String Description;

        public Items(Integer ItemID,Integer GalleryID,Bitmap icon,String Title,Integer Price,String Description){
            this.ItemID = ItemID;
            this.GalleryID = GalleryID;
            this.icon = icon;
            this.Title = Title;
            this.Price= Price;
            this.Description=Description;
        }
    }
    private class ItemsAdapter extends ArrayAdapter<Items>{
        public ItemsAdapter() {
            super(Customer_ItemList.this.getActivity(), R.layout.customer_itemlistview,itemsArrayList);
        }
        @Override
        public int getCount() {
            return itemsArrayList.size();
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.customer_itemlistview,null,true);
            ImageView icon = (ImageView)view.findViewById(R.id.itemicon);
            TextView price = (TextView)view.findViewById(R.id.itemprice);
            TextView title = (TextView)view.findViewById(R.id.itemtitle);
            if(itemsArrayList.get(position).icon !=null){icon.setImageBitmap(itemsArrayList.get(position).icon);}
            price.setText("Rs." + itemsArrayList.get(position).Price + "/-");
            Typeface font=Typeface.createFromAsset(getActivity().getAssets(),"fonts/ITCAvantGardePro-MediumOblique.otf");
            price.setTypeface(font);
            title.setText(itemsArrayList.get(position).Title);
            title.setTypeface(font);
            return view;
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i=new Intent(getActivity(),Customer_ItemView.class);
        i.putExtra("Name",itemsArrayList.get(position).Title);
        i.putExtra("ID",itemsArrayList.get(position).ItemID+ "");
        i.putExtra("Price",itemsArrayList.get(position).Price + "");
        i.putExtra("Description", itemsArrayList.get(position).Description);
        i.putExtra("GalleryID", itemsArrayList.get(position).GalleryID+"");
        i.putExtra("bmp",itemsArrayList.get(position).icon);
        startActivity(i);
    }
}
