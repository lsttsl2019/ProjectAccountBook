package com.isttis2019.projectaccountbook;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.isttis2019.projectaccountbook.databinding.FragmentPage1Binding;

import java.util.Calendar;

public class Page1Fragment extends Fragment {

    FragmentPage1Binding b;

    TextView tvsave;

    Button btnSave;
    EditText ed;
    String s;

    ImageView ivAdd;

    ////////다이얼로그
    TextView tvDay;
    Button  btnDay;
    EditText edPlace;
    EditText edMoney;

    ImageView imgBill;
    ImageView imgbtnAddBill;
        ////////////////////

    Calendar calendar; //달력
    int cyear;
    int cmonth;
    int cday;
    String day_year_month_day;

    ///////////////////////////날짜

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_page1, container,false);

        b = DataBindingUtil.inflate(inflater, R.layout.fragment_page1, container, false);

        btnSave=view.findViewById(R.id.btn_Save);
        ed=view.findViewById(R.id.ed_expenditure);
        tvsave=view.findViewById(R.id.tv_Save);
        ivAdd=view.findViewById(R.id.iv_add);
        calendar=Calendar.getInstance();




        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);





            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    s=ed.getText().toString();
                    if (s==null||s.equals("")){
                        Toast.makeText(getActivity(), "금액을 설정하세요!!", Toast.LENGTH_SHORT).show();
                    }else {
                        if (btnSave.getText().toString().equals("저장")){
                            tvsave.setText(s+"원");
                            tvsave.setVisibility(View.VISIBLE);
                            ed.setVisibility(View.INVISIBLE);
                            btnSave.setText("수정");

                        }else if (btnSave.getText().toString().equals("수정")){
                            btnSave.setText("저장");
                            ed.setVisibility(View.VISIBLE);
                            tvsave.setVisibility(View.INVISIBLE);
                            tvsave.setText("");
                        }

                    }


                }
            });
            //지출내역 아이템 추가시켜주는 리스너
            ivAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                    builder.setTitle("지출내역 추가");

                    LayoutInflater inflater= getLayoutInflater();
                    View layout =inflater.inflate(R.layout.dialog_fragment1,null);

                    tvDay=layout.findViewById(R.id.ed_inputDay);
                      btnDay=layout.findViewById(R.id.dayBtn);
                    edPlace=layout.findViewById(R.id.ed_inputplace);
                    edMoney=layout.findViewById(R.id.ed_money);
                    imgBill=layout.findViewById(R.id.img_addbill);
                    imgbtnAddBill=layout.findViewById(R.id.btn_addbill);


                    builder.setView(layout);
                    builder.setPositiveButton("추가", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity(), "확인", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                        }
                    });


                    builder.create().show();


                    btnDay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            cyear= calendar.get(Calendar.YEAR);
                            cmonth=calendar.get(Calendar.MONDAY);
                            cday=calendar.get(Calendar.DAY_OF_MONTH);

                            final DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                       day_year_month_day=(year+"")+(month+1)+dayOfMonth+"";
                                       tvDay.setText(day_year_month_day);
                                }
                            };
                            DatePickerDialog dialog=new DatePickerDialog(getActivity(),dateSetListener,cyear,cmonth,cday);
                            dialog.show();


                        }
                    });



                }
            });//Add리스너






    }//onViewCreated


}



















