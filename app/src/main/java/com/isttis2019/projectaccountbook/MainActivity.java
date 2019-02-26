package com.isttis2019.projectaccountbook;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {



  Toolbar toolbar;
  fragmentsAdapter fragmentsAdapter;
  ViewPager viewPager;
  TabLayout tabLayout;

  DrawerLayout drawerLayout;
  NavigationView navigationView;
  ActionBarDrawerToggle drawerToggle;


  ArrayList<Page1Item> itemsPage1 = new ArrayList<>();
  ArrayList<Page2_item> itemsPage2= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        }

        }

    toolbar=findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    viewPager=findViewById(R.id.viewPager);
    tabLayout=findViewById(R.id.layout_tab);

    fragmentsAdapter =new fragmentsAdapter(getSupportFragmentManager());
    viewPager.setAdapter(fragmentsAdapter);
    tabLayout.setupWithViewPager(viewPager);
    //테이블 레이아웃

        drawerLayout= findViewById(R.id.draweerlayout);
        navigationView=findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);

        drawerToggle =new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();





     tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
        getSupportActionBar().setSubtitle(tab.getText());
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    });




    }//onCreate


    public void addItem(Page1Item item){
        itemsPage1.add(item);
    }

    public ArrayList<Page1Item> getItems(){
        return itemsPage1;
    }

    public ArrayList<Page1Item> getItemsPage1() {
        return itemsPage1;
    }

    public void setItemsPage1(ArrayList<Page1Item> itemsPage1) {
        this.itemsPage1 = itemsPage1;
    }

    public ArrayList<Page2_item> getItemsPage2() {
        return itemsPage2;
    }

    public void setItemsPage2(ArrayList<Page2_item> itemsPage2) {
        this.itemsPage2 = itemsPage2;
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case 100:
                if(grantResults[0]==PackageManager.PERMISSION_DENIED){
                    Toast.makeText(this, "외부저장소 사용불가\n이미지업로드 불가", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

}//class 마지막
