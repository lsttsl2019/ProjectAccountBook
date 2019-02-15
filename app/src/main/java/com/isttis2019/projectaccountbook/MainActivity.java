package com.isttis2019.projectaccountbook;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.isttis2019.projectaccountbook.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


  ActivityMainBinding binding;

  MyAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    binding= DataBindingUtil.setContentView(this,R.layout.activity_main);

    setSupportActionBar(binding.toolbar);


    myAdapter=new MyAdapter(getSupportFragmentManager());
    binding.viewPager.setAdapter(myAdapter);
    binding.layoutTab.setupWithViewPager(binding.viewPager);




    binding.layoutTab.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
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









    }



}
