package com.isttis2019.projectaccountbook;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;



public class MainActivity extends AppCompatActivity {



  Toolbar toolbar;
  fragmentsAdapter fragmentsAdapter;
  ViewPager viewPager;
  TabLayout tabLayout;

  DrawerLayout drawerLayout;
  NavigationView navigationView;
  ActionBarDrawerToggle drawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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



}//class 마지막
