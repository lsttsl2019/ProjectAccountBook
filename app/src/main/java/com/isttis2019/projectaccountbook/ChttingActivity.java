package com.isttis2019.projectaccountbook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChttingActivity extends AppCompatActivity {

    EditText etName;
    CircleImageView ivProfile;

    Uri imgUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chtting);
        etName=findViewById(R.id.et_name);
        ivProfile=findViewById(R.id.iv_profile);

        loadData();
        if(G.nickName!=null){
            Intent intent=new Intent(this, ChatActivity.class);
            startActivity(intent);
            finish();
        }


    }

    public void clickImage(View view) {
            Intent intent=new Intent(Intent.ACTION_PICK);
            intent.setType("imge/*");
            startActivityForResult(intent, 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            switch (requestCode){
                case 10:
                    if (resultCode==RESULT_OK){
                        imgUri=data.getData();
                        Picasso.with(this).load(imgUri).into(ivProfile);

                    }
                    break;
            }
    }

    public void clickBtn(View view) {

    G.nickName=etName.getText().toString();
        saveDate();
   }

   void  saveDate(){
        if (imgUri==null){return;}
       FirebaseStorage firebaseStorage=FirebaseStorage.getInstance();

       SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMddhhmmss");
        String fileName=sdf.format(new Date())+".png";



        final StorageReference imgRef=firebaseStorage.getReference("profileImages/"+fileName);
       UploadTask uploadTask=imgRef. putFile(imgUri);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
            imgRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    G.profileUrl=uri.toString();
                    Toast.makeText(ChttingActivity.this, "프로필 적용완료", Toast.LENGTH_SHORT).show();


                    //DB 저장
                    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();

                    DatabaseReference profileRef= firebaseDatabase.getReference("profiles");
                    profileRef.child(G.nickName).setValue(G.profileUrl);


                    SharedPreferences pref=getSharedPreferences("account", MODE_PRIVATE);
                    SharedPreferences.Editor  editor=pref.edit();

                    editor.putString("nickName", G.nickName);
                    editor.putString("profileUrl", G.profileUrl);
                    editor.commit();


                    Intent intent=new Intent(ChttingActivity.this, ChatActivity.class);
                    startActivity(intent);
                    finish();

                }
            });


            }
        });


   }

    void loadData(){
        SharedPreferences pref= getSharedPreferences("account", MODE_PRIVATE);
        G.nickName= pref.getString("nickName", null);
        G.profileUrl= pref.getString("profileUrl", null);
    }

}































