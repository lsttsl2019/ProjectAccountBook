package com.isttis2019.projectaccountbook;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

public class Page1Fragment extends Fragment {

    TextView tvsave;

    Button btnSave;
    EditText ed;
    String s;

    ImageView ivAdd;

    final int PICK_IMAGE_REQUEST = 1; //앨범에서 가져오려는 값

    ////////다이얼로그
    TextView tvDay;
    Button  btnDay;
    EditText edPlace;
    EditText edMoney;
    TextView result;

    ImageView imgBill;
    ImageView imgbtnAddBill;
        ////////////////////

    Calendar calendar; //달력
    int cyear;
    int cmonth;
    int cday;
    String day_year_month_day;
    DatePicker datePicker;

    ///////////////////////////날짜

    ///////////////////////////데이터를 관리하고 리스트 뷰에 보일녀석들
    ArrayList<Page1Item> page1Items=new ArrayList<>();

    ListView listView;
    Page1_ListView_Adapter listViewAdapter;
    String path;
    String finalPath;

    String dateTime;
    String day;
    String place;
    String money;
    Uri uri;

    int moneyAdd2;
    MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_page1, container,false);


        mainActivity = (MainActivity) getActivity();

        btnSave=view.findViewById(R.id.btn_Save);
        ed=view.findViewById(R.id.ed_expenditure);
        tvsave=view.findViewById(R.id.tv_Save);
        ivAdd=view.findViewById(R.id.iv_add);
        result=view.findViewById(R.id.tv_result);


        listView=view.findViewById(R.id.page01_listview);
        listViewAdapter=new Page1_ListView_Adapter(page1Items,getContext());
        listView.setAdapter(listViewAdapter);

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

                    AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                    builder.setTitle("지출내역 추가");



                    LayoutInflater inflater= getLayoutInflater();
                    View layout =inflater.inflate(R.layout.dialog_fragment1,null);

                    tvDay=layout.findViewById(R.id.ed_inputDay);
                      btnDay=layout.findViewById(R.id.dayBtn);
                    edPlace=layout.findViewById(R.id.ed_inputplace);
                    edMoney=layout.findViewById(R.id.ed_money);
                    imgBill=layout.findViewById(R.id.img_addbill);
                    imgbtnAddBill=layout.findViewById(R.id.btn_addbill);

                    //다이얼로그 추가 버튼과 취소번튼!!
                    builder.setView(layout);
                    builder.setCancelable(true);
                    uri =null;
                    path=null;


                    builder.setPositiveButton("추가", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        if (edMoney.getText().toString().equals("")){
                            Toast.makeText(getActivity(), "금액을 입력해주세요", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }else {

                          day= tvDay.getText().toString();
                          place= edPlace.getText().toString();
                          money= edMoney.getText().toString();

                           long now= System.currentTimeMillis();
                                Date  date=new Date(now);
                                SimpleDateFormat sdfform=new SimpleDateFormat("HH:mm:ss");
                                dateTime=sdfform.format(date);

                                int moneyAdd=Integer.parseInt(money);

                                moneyAdd2+=moneyAdd;


                                result.setText(moneyAdd2+"");
                                /////////////////////////////////////////////////작업한내용물외부 서버에 보내기


                                Page1Item item = new Page1Item(calendar, day_year_month_day ,place, dateTime,money, path);

                                page1Items.add(item);
                                mainActivity.addItem(item);



                            listViewAdapter.notifyDataSetChanged();

                        }



                        }//데이터 추가작업
                    });


                    builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity(), "취소되었습니다다", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                       }
                    });


                    builder.create().show();



                    btnDay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            calendar = Calendar.getInstance();
                            cyear= calendar.get(Calendar.YEAR);
                            cmonth=calendar.get(Calendar.MONDAY);
                            cday=calendar.get(Calendar.DAY_OF_MONTH);



                            final DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                    day_year_month_day=(year+"")+(month+1)+dayOfMonth+"";

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

                                    tvDay.setText(day_year_month_day);
                                }
                            };
                            DatePickerDialog dialog=new DatePickerDialog(getActivity(),dateSetListener,cyear,cmonth,cday);
                            dialog.show();


                        }
                    });//날짜 받아오는 버튼

                    imgbtnAddBill.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent= new Intent(Intent.ACTION_PICK);
                            intent.setType("image/*");
                            startActivityForResult(intent,  PICK_IMAGE_REQUEST);
                        }
                    });
                }
            });//Add리스너



    }//onViewCreated


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PICK_IMAGE_REQUEST:
                if (resultCode == RESULT_OK) {
                   uri=data.getData();
//                finalPath=getRealPathFromUri(uri); //절대경로얻어옴.
                    path = uri.toString();
//                    Picasso.with(getContext()).load(uri).into(imgBill);
                    if (uri != null) {
                        Glide.with(getContext()).load(uri).into(imgBill);
                    } else {
                        Picasso.with(getContext()).load(R.drawable.img_back01).into(imgBill);
                    }

                    break;
                }

        }
        ///////////////절대경로
//       String getRealPathFromUri(Uri uri){
//       String[] proj= {MediaStore.Images.Media.DATA};
//        CursorLoader loader= new CursorLoader(getContext(), uri, proj, null, null, null);
//        Cursor cursor= loader.loadInBackground();
//        int column_index= cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        cursor.moveToFirst();
//        String result= cursor.getString(column_index);
//        cursor.close();
//        return  result;
//    }

    }
}



















