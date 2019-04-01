package com.isttis2019.projectaccountbook;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;

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


  ArrayList<Parcelable> parcelables=new ArrayList<>();
  ArrayList<Parcelable2> parcelable2s=new ArrayList<>();

   CalendarView calendarViews;


   ArrayList<ParcelableExpned> parcelableExpneds=new ArrayList<>();
   ArrayList<ParcelableIncome> parcelableIncomes=new ArrayList<>();

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

    toolbar.setTitleTextColor(Color.WHITE);
    viewPager=findViewById(R.id.viewPager);
    tabLayout=findViewById(R.id.layout_tab);

    fragmentsAdapter =new fragmentsAdapter(getSupportFragmentManager());
    viewPager.setAdapter(fragmentsAdapter);
    tabLayout.setupWithViewPager(viewPager);
    //테이블 레이아웃

        drawerLayout= findViewById(R.id.draweerlayout);
        navigationView=findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menu_aa:
                        Intent intent=new Intent(MainActivity.this, ExpenditureActivity.class );
                        intent.putExtra("Item",parcelables);
                        startActivity(intent);

                        break;
                    case R.id.menu_bb:
                        Intent intent2=new Intent(MainActivity.this,IncomeActivity.class);
                        intent2.putExtra("items",parcelable2s);
                        startActivity(intent2);

                        break;
                    case R.id.chtting:
                        Intent intent3=new Intent(MainActivity.this, ChttingActivity.class);
                        startActivity(intent3);
                        break;
                }
                    drawerLayout.closeDrawer(navigationView,true);

                return false;
            }
        });

        //드로우어 조절용 토글버튼
        drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.app_name,R.string.app_name);

        //드러우어 토글버튼아이콘이 보이도록 액션바에게 요청
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //삼선아이콘 모양으로 보이도록 토글버튼의 동기 맞추기..!
        drawerToggle.syncState();

        //삼선아이콘과 화살표 아이콘이 자동 변환되도록...
        drawerLayout.addDrawerListener(drawerToggle);




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


    Intent intent=getIntent();
    parcelableExpneds=intent.getParcelableArrayListExtra("itemEx");
    parcelableIncomes=intent.getParcelableArrayListExtra("itemIn");



         page3Fragment= (Page3Fragment) fragmentsAdapter.getItem(2);
    }//onCreate
    Page3Fragment page3Fragment;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        drawerToggle.onOptionsItemSelected(item);

        return super.onOptionsItemSelected(item);
    }


    public void addCalendarView(CalendarView calendarView){
        calendarViews =calendarView;
    }

 public  Page3Fragment  getPage3Fragment() {
     return page3Fragment;
     }



///////////////////////////////////////////날짜를 분류하기 위한 캘리더 뷰
    public void addItem(Page1Item item){
        itemsPage1.add(item);
        parcelables.add(new Parcelable(item.getToDay(),item.getMoneyData()));

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

    public void addItem2(Page2_item item2){
        itemsPage2.add(item2);
        parcelable2s.add(new Parcelable2(item2.getToDay(),item2.getMoney()));

    }

    public ArrayList<Page2_item> getItem2s(){
        return itemsPage2;
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
