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
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AlertDialog;
import android.util.Log;
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

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
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
    Spinner spinnerPlace;
    String[] spinnerArray;
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

    ArrayList<ParcelableExpned> parcelableExpneds=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_page1, container,false);


       // mainActivity = (MainActivity) getActivity();

        btnSave=view.findViewById(R.id.btn_Save);
        ed=view.findViewById(R.id.ed_expenditure);
        tvsave=view.findViewById(R.id.tv_Save);
        ivAdd=view.findViewById(R.id.iv_add);
        result=view.findViewById(R.id.tv_result);


        listView=view.findViewById(R.id.page01_listview);
        listViewAdapter=new Page1_ListView_Adapter(page1Items,getContext());
        listView.setAdapter(listViewAdapter);
        //parcelableExpneds=mainActivity.parcelableExpneds;

        listView.setSelection(page1Items.size()-1);

        listViewAdapter.notifyDataSetChanged();
        result.setText(resutlts+"");


        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
        parcelableExpneds=mainActivity.parcelableExpneds;


        expnedItem();
        setText();
    }
int resutlts;

    public void expnedItem(){
        for (int i=0; i<parcelableExpneds.size();i++){
            Page1Item page1Item=new Page1Item(parcelableExpneds.get(i).getToday(),parcelableExpneds.get(i).getPlace(),parcelableExpneds.get(i).getTime(),parcelableExpneds.get(i).getMoney(),parcelableExpneds.get(i).getPath());
            page1Items.add(page1Item);
            mainActivity.addItem(page1Item);
        }



    }

    public void setText(){
        for (int i=0; i<parcelableExpneds.size();i++){
            String day= parcelableExpneds.get(i).today;
            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
            try {
                Date date=sdf.parse(day);
                Calendar calendar=Calendar.getInstance();
                calendar.setTime(date);
                int ns=calendar.get(Calendar.MONTH);
                if (ns==Calendar.getInstance().get(Calendar.MONTH)){
                    String s=parcelableExpneds.get(i).money;
                    int exMoney=Integer.parseInt(s);
                    resutlts+=exMoney;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }


        }

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
                    spinnerPlace=layout.findViewById(R.id.spinner_dialog1);
                    edMoney=layout.findViewById(R.id.ed_money);
                    imgBill=layout.findViewById(R.id.img_addbill);
                    imgbtnAddBill=layout.findViewById(R.id.btn_addbill);

                    ArrayAdapter arrayAdapter=ArrayAdapter.createFromResource(getContext(),R.array.incomesle,android.R.layout.simple_spinner_item);
                    spinnerPlace.setAdapter(arrayAdapter);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerArray=getResources().getStringArray(R.array.incomesle);
                    spinnerPlace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Toast.makeText(getContext(), spinnerArray[position], Toast.LENGTH_SHORT).show();
                            place=spinnerArray[position];
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


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


                          money= edMoney.getText().toString();

                           long now= System.currentTimeMillis();
                                Date  date=new Date(now);
                                SimpleDateFormat sdfform=new SimpleDateFormat("HH:mm:ss");
                                dateTime=sdfform.format(date);

                                int moneyAdd=Integer.parseInt(money);

                                SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
                            try {
                                Date date1= sdf.parse(day);
                                Calendar calendar=Calendar.getInstance();
                                calendar.setTime(date1);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            if (Calendar.getInstance().get(Calendar.MONTH)==calendar.get(Calendar.MONTH)){
                                resutlts+=moneyAdd;
                            }

                            ///////////지출누적금액표시
                                result.setText(resutlts+"");


                                /////////////////////////////////////////////////작업한내용물외부 서버에 보내기
                                serveradd();

                                Page1Item item = new Page1Item( day_year_month_day ,place, dateTime,money,  path);

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
                  // finalPath=getRealPathFromUri(uri); //절대경로얻어옴.
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


    }
    String getRealPathFromUri(Uri uri){
        String[] proj= {MediaStore.Images.Media.DATA};
        CursorLoader loader= new CursorLoader(getContext(), uri, proj, null, null, null);
        Cursor cursor= loader.loadInBackground();
        int column_index= cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result= cursor.getString(column_index);
        cursor.close();
        return  result;
    }




    private void serveradd(){
        String serverUrl="http://dlamtd123.dothome.co.kr/ProjectAccountBook/insertDB.php";

        SimpleMultiPartRequest multiPartRequest=new SimpleMultiPartRequest(Request.Method.POST, serverUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getContext(), "완료", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        multiPartRequest.setCacheEntry(new Cache.Entry());
        multiPartRequest.addStringParam("today",day_year_month_day);
        multiPartRequest.addStringParam("place",place);
        multiPartRequest.addStringParam("time", dateTime);
        multiPartRequest.addStringParam("money", money);
        multiPartRequest.addStringParam("path",path);

        RequestQueue requestQueue= Volley.newRequestQueue(getContext());

        requestQueue.add(multiPartRequest);


    }

}



















