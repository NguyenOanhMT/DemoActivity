package com.nguyenoanh.demoactivity.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.nguyenoanh.demoactivity.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageActivity extends AppCompatActivity {

    CircleImageView avatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_message);

        avatar = (CircleImageView) findViewById (R.id.imv_profile);

//        Intent intent = getIntent ();
//        if( intent != null) {
//            Bundle bundle = intent.getBundleExtra(SignUp.BUNDLE);
//            if (bundle != null) {
//                Toast.makeText (getApplicationContext (), "hello", Toast.LENGTH_SHORT).show ();
//                avatar.setImageResource (intent.getIntExtra ("avatar", avatar));
//
//            } else {

//            }

    }
}
