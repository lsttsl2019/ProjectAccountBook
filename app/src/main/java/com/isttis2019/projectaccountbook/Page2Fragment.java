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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Page2Fragment extends Fragment {


    TextView tvMoneyAdd;
    ImageView lvadd;

    ListView listView;
    /////////////////////////////////////
    Spinner spinner;
    TextView tvdate;
    TextView tvMoney;
    Button btndate;
    String[] spinnerdata;
    String spinnerAdd;

    /////////////////////////다이얼로그
  Calendar calendar;
   int cyear;
   int cmonth;
   int cday;

   String day_year_month_day;
    ////////////////////////날짜 와 시간


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view=inflater.inflate(R.layout.fragment_page2, container,false);

        tvMoneyAdd=view.findViewById(R.id.fg2_tv_result);
        lvadd=view.findViewById(R.id.fg2_btn_addLsitview);
        calendar=Calendar.getInstance();


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


//        spinnerdata=getResources().getStringArray(R.array.income);
//      ArrayAdapter arrayAdapter=ArrayAdapter.createFromResource(getContext(),R.array.income,android.R.layout.simple_spinner_item);
//        Toast.makeText(getContext(), ""+arrayAdapter, Toast.LENGTH_SHORT).show();
        //arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner.setAdapter(arrayAdapter);
        //spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();
//
//            }
//        });

        lvadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                builder.setTitle("수입내역 추가");
                LayoutInflater inflater=getLayoutInflater();
                View layout =inflater.inflate(R.layout.dialog_fragment2,null);
                tvdate=layout.findViewById(R.id.fg2_dialog_day);
                btndate=layout.findViewById(R.id.fg2_dialog_btnday);
                spinner=layout.findViewById(R.id.fg2_dialog_spinner);
                tvMoney=layout.findViewById(R.id.fg2_dialog_money);


                final ArrayAdapter arrayAdapter=ArrayAdapter.createFromResource(getContext(),R.array.income,android.R.layout.simple_spinner_item);
                spinner.setAdapter(arrayAdapter);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



                builder.setView(layout);
                //////////////////////////////다이얼로그 저장하는곳
                builder.setPositiveButton("저장", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


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

                        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {


                                day_year_month_day=(year+"")+((month+1)+"")+dayOfMonth+"";

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










































