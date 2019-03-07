package com.isttis2019.projectaccountbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class IncomeActivity extends AppCompatActivity {

    ArrayList<Parcelable2> parcelable2s=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        Intent intent=getIntent();
        parcelable2s=intent.getParcelableArrayListExtra("items");

        Toast.makeText(this, ""+parcelable2s.size(), Toast.LENGTH_SHORT).show();

    }
}
