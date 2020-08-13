package com.example.kitab_ghar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText txt_pNumber,txt_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_message = (EditText)findViewById(R.id.txt_message);
        txt_pNumber = (EditText)findViewById(R.id.txt_phonenumber);

    }
    public void btn_send(View view){
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
            if(permissionCheck==PackageManager.PERMISSION_GRANTED)
            {
                MyMessage();
            }
            else {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS}, 0);
            }

    }

    private void MyMessage() {
        String phoneNumber = txt_pNumber.getText().toString().trim();
        String Message ="Thanks For Ordering Form KitabGhar.Your Tracking ID is :"+ txt_message.getText().toString().trim()+":visit our Website to Track kitabghar.github.io/Kitab_Ghar/track ";

        if( ! phoneNumber.equals("") || ! Message.equals("")) {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber,null, Message,null,null );
        Toast.makeText(this,"Sent",Toast.LENGTH_SHORT).show();


    }
        else {
            Toast.makeText(this,"Fail",Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case 0:
                if(grantResults.length>=0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    MyMessage();
                }
                else
                    Toast.makeText(this,"permission Denined",Toast.LENGTH_SHORT).show();

        }
    }
}