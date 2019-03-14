package com.isttis2019.projectaccountbook;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class IntroActivity extends AppCompatActivity {


  ImageView iv;


Timer timer=new Timer();


ArrayList<Page1Item> page1Items=new ArrayList<>();
ArrayList<Page2_item>page2Items=new ArrayList<>();

ArrayList<ParcelableExpned> parcelables=new ArrayList<>();
ArrayList<ParcelableIncome> parcelableIncomes=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        iv=findViewById(R.id.iv);

        Animation ani= AnimationUtils.loadAnimation(this,R.anim.appear_logo);
        iv.startAnimation(ani);

         loadServer();



        timer.schedule(task,5000 );


    }

    TimerTask task=new TimerTask() {
        @Override
        public void run() {
            Intent intent=new Intent(IntroActivity.this, MainActivity.class);
            intent.putParcelableArrayListExtra("itemEx", parcelables);
            intent.putParcelableArrayListExtra("itemIn", parcelableIncomes);

            startActivity(intent);


            finish();

        }
    };

String today;
String place;
String time;
String money;
String path;





    private void loadServer(){
        String serverURL="http://dlamtd123.dothome.co.kr/ProjectAccountBook/loadDtoJson.php";
        String serverIcomeURL="http://dlamtd123.dothome.co.kr/ProjectAccountBook/loadDtoJsonIncome.php";
        JsonArrayRequest jsonArrayRequesticome=new JsonArrayRequest(Request.Method.POST,serverIcomeURL, null,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
            page2Items.clear();
                try {
                    for (int i=0; i< response.length(); i++){
                        JSONObject jsonObjects=response.getJSONObject(i);
                        String inToday= jsonObjects.getString("today");
                        String income=jsonObjects.getString("income");
                        String inTime=jsonObjects.getString("time");
                        String inMoney=jsonObjects.getString("money");

                        page2Items.add(new Page2_item(inToday,inTime, income,inMoney));
                        parcelableIncomes.add(new ParcelableIncome(inToday,income, inTime,inMoney));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.POST,serverURL, null,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                page1Items.clear();
                for (int i=0; i< response.length(); i++){
                    JSONObject jsonObject=response.getJSONObject(i);

                    today=jsonObject.getString("today");
                    place=jsonObject.getString("place");
                    time=jsonObject.getString("time");
                    money=jsonObject.getString("money");
                    path=jsonObject.getString("path");
                    //path="http://dlamtd123.dothome.co.kr/ProjectAccountBook"+path;

                    page1Items.add(new Page1Item(today,place,time,money,path));



                }
                for (int i=0; i< page1Items.size(); i++){
                    parcelables.add(new ParcelableExpned(page1Items.get(i).toDay,page1Items.get(i).placeData,page1Items.get(i).timeData,page1Items.get(i).moneyData,page1Items.get(i).path));
                }
                 //   Toast.makeText(IntroActivity.this, ""+parcelables.size(), Toast.LENGTH_SHORT).show();

               // Toast.makeText(IntroActivity.this, ""+response.length(), Toast.LENGTH_SHORT).show();
               //     Toast.makeText(IntroActivity.this, ""+page1Items.size(), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(IntroActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(jsonArrayRequest);
        queue.add(jsonArrayRequesticome);

    }



}













