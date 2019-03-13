package com.isttis2019.projectaccountbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        iv=findViewById(R.id.iv);

        Animation ani= AnimationUtils.loadAnimation(this,R.anim.appear_logo);
        iv.startAnimation(ani);

         loadServerExpend();
        loadServerInocome();



        timer.schedule(task,4000 );


    }

    TimerTask task=new TimerTask() {
        @Override
        public void run() {
            Intent intent=new Intent(IntroActivity.this, MainActivity.class);

            startActivity(intent);


            finish();

        }
    };

String today;
String place;
String time;
String money;
String path;

String income;


    private void loadServerInocome(){
        String serverURL="http://dlamtd123.dothome.co.kr/ProjectAccountBook/loadDtoJsonIncome.php";
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(serverURL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                    try {
                        for (int i=0; i<response.length();i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            today=jsonObject.getString("today");
                            income=jsonObject.getString("income");
                            time=jsonObject.getString("time");
                            money=jsonObject.getString("money");

                            page2Items.add(new Page2_item(today,time,income,money));

                        }
                        Toast.makeText(IntroActivity.this, ""+page2Items.size(), Toast.LENGTH_SHORT).show();

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

    }


    private void loadServerExpend(){
        String serverURL="http://dlamtd123.dothome.co.kr/ProjectAccountBook/loadDtoJson.php";

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(serverURL, new Response.Listener<JSONArray>() {
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
                    path="http://dlamtd123.dothome.co.kr/ProjectAccountBook"+path;

                    page1Items.add(new Page1Item(today,place,time,money,path));


                    }
               // Toast.makeText(IntroActivity.this, ""+response.length(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(IntroActivity.this, ""+page1Items.size(), Toast.LENGTH_SHORT).show();
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
    }



}













