package com.isttis2019.projectaccountbook;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Page2Fragment extends Fragment {


    TextView tvMoneyAdd;
    int mnresult;

    ImageView lvadd;

    ListView listView;
    Page2_ListView_Adapter page2ListViewAdapter;
    ArrayList<Page2_item> page2Items=new ArrayList<>();
    /////////////////////////////////////
    Spinner spinner;
    TextView tvdate;
    EditText tvMoney;
    Button btndate;
    String[] spinnerdata;


    /////////////////////////다이얼로그
  Calendar calendar;
   int cyear;
   int cmonth;
   int cday;

   String day_year_month_day;
    ////////////////////////날짜 와 시간
    String time;
    String data;
    String money;
    String item;
      Calendar calendarAdd;

      MainActivity mainActivity;
    Page3Fragment page3Fragment;

      boolean isPless=true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view=inflater.inflate(R.layout.fragment_page2, container,false);

        tvMoneyAdd=view.findViewById(R.id.fg2_tv_result);
        lvadd=view.findViewById(R.id.fg2_btn_addLsitview);
        calendar=Calendar.getInstance();
        listView=view.findViewById(R.id.fg2_listview);
        page2ListViewAdapter= new Page2_ListView_Adapter(page2Items, getContext());
        listView.setAdapter(page2ListViewAdapter);
        mainActivity= (MainActivity) getActivity();


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        lvadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                builder.setTitle("수입내역 추가");

                LayoutInflater inflater=getLayoutInflater();
                View layout =inflater.inflate(R.layout.dialog_fragment2,null);
                tvdate=layout.findViewById(R.id.fg2_dilog_Day);
                btndate=layout.findViewById(R.id.fg2_dilog_dayBtn);
                spinner=layout.findViewById(R.id.fg2_dilog_spinner);
                tvMoney=layout.findViewById(R.id.fg2_dilog_money);


                final ArrayAdapter arrayAdapter=ArrayAdapter.createFromResource(getContext(),R.array.income,android.R.layout.simple_spinner_item);
                spinner.setAdapter(arrayAdapter);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerdata=getResources().getStringArray(R.array.income);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getContext(), spinnerdata[position], Toast.LENGTH_SHORT).show();
                        item=spinnerdata[position];
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {


                    }
                });




                builder.setView(layout);
                //////////////////////////////다이얼로그 저장하는곳
                builder.setPositiveButton("저장", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        if (tvMoney.getText().toString().equals("")){
                            dialog.cancel();
                            Toast.makeText(getContext(), "금액을 입력해주세요", Toast.LENGTH_SHORT).show();
                        }else {


                        long now= System.currentTimeMillis();
                        Date date=new Date(now);
                        SimpleDateFormat sdfform=new SimpleDateFormat("HH:mm:ss");
                        time=sdfform.format(date);
                        /////////////////////////////////시간
                        money=tvMoney.getText().toString();
                        int moneyadd=Integer.parseInt(money);

                        mnresult+=moneyadd;
                        tvMoneyAdd.setText(mnresult+"");
                        ////누적시킨하루 금액

                        data=day_year_month_day;
                        ///날자저장
                        if (data==null){
                            cyear= calendar.get(Calendar.YEAR);
                            cmonth=calendar.get(Calendar.MONDAY);
                            cday=calendar.get(Calendar.DAY_OF_MONTH);
                           data=cyear+""+(cmonth+1)+""+cday+"";
                        }

                        Page2_item page2Item=new Page2_item(calendarAdd,day_year_month_day,time, item,money);
                        page2Items.add(0,page2Item);

                        mainActivity.addItem2(page2Item);


                        while (isPless){
                            page3Fragment=(Page3Fragment) getFragmentManager().getFragments().get(2); // 플래그먼트 추가
                            isPless=false;
                            }
                        page3Fragment.getItemfg2(page2Item);



                        page2ListViewAdapter.notifyDataSetChanged();
                        Toast.makeText(getContext(), "저장되었습니다", Toast.LENGTH_SHORT).show();
                    }

                    }
                });

                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "취소되었습니다.", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
///////////////////////////////////날짜 작업
                btndate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cyear= calendar.get(Calendar.YEAR);
                        cmonth=calendar.get(Calendar.MONDAY);
                        cday=calendar.get(Calendar.DAY_OF_MONTH);

                        calendarAdd= Calendar.getInstance();
                        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {


                                day_year_month_day=(year+"")+((month+1)+"")+dayOfMonth+"";

                                G.year=view.getYear()+"";

                                if (view.getDayOfMonth()>=10){
                                    G.dayOfMonth=view.getDayOfMonth()+"";
                                }else {
                                    G.dayOfMonth="0"+view.getDayOfMonth()+"";
                                }


                                if (view.getMonth()>=9){
                                    G.month=(view.getMonth()+1)+"";
                                }else {
                                    G.month="0"+(view.getMonth()+1)+"";
                                }

                                day_year_month_day=G.year+G.month+G.dayOfMonth;


                                tvdate.setText(day_year_month_day);
                            }
                        };
                        DatePickerDialog dialog=new DatePickerDialog(getActivity(),dateSetListener,cyear,cmonth,cday);
                        dialog.show();
                    }
                });

                builder.create().show();

            }
        });
        //////////////////////////////////다이얼로그 작업







    }




}










































