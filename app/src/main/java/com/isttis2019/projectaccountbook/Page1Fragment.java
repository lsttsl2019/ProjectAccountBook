package com.isttis2019.projectaccountbook;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.isttis2019.projectaccountbook.databinding.FragmentPage1Binding;

public class Page1Fragment extends Fragment {

    FragmentPage1Binding b;

    TextView tvsave;

    Button btnSave;
    EditText ed;
    String s;

    ImageView ivAdd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_page1, container,false);

        b = DataBindingUtil.inflate(inflater, R.layout.fragment_page1, container, false);

        btnSave=view.findViewById(R.id.btn_Save);
        ed=view.findViewById(R.id.ed_expenditure);
        tvsave=view.findViewById(R.id.tv_Save);
        ivAdd=view.findViewById(R.id.iv_add);




        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
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

            ivAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });



    }


}



















