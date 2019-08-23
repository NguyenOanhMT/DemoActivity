package com.nguyenoanh.demoactivity.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.nguyenoanh.demoactivity.Adapter.ItemMessageAdapter;
import com.nguyenoanh.demoactivity.Adapter.ItemUserAdapter;
import com.nguyenoanh.demoactivity.Model.ItemChat;
import com.nguyenoanh.demoactivity.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageActivity extends AppCompatActivity {

    CircleImageView profile;
    TextView tvName;
    ImageButton btnSend;
    EditText edtSend;

    RecyclerView recyclerView;
    ItemMessageAdapter messageAdapter;
    ArrayList<ItemChat> listChat = new ArrayList<> ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_message);

        profile = (CircleImageView) findViewById (R.id.imv_profile);
        tvName = (TextView) findViewById (R.id.tv_name);
        btnSend = (ImageButton) findViewById (R.id.btn_send);
        edtSend = (EditText) findViewById (R.id.edt_message);

        recyclerView = (RecyclerView) findViewById (R.id.recycler_view_message);
        recyclerView.setHasFixedSize (true);
        recyclerView.setItemAnimator(new DefaultItemAnimator ());
        LinearLayoutManager layoutManager = new LinearLayoutManager ( getApplicationContext ());
        recyclerView.setLayoutManager (layoutManager);

        Intent intent = getIntent ();
        int id = 0;
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra ("bundle");
            if (bundle != null) {
                profile.setImageResource (bundle.getInt ("avatar"));
                tvName.setText (bundle.getString ("name"));
                id = bundle.getInt ("id");
            } else {
            }
        }


        // handling event on click button Send
        btnSend.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                String message = edtSend.getText ().toString ();

                if(!message.equals ("")){
                    listChat.add ( new ItemChat ( 1, 0, message));
                    messageAdapter = new ItemMessageAdapter (MessageActivity.this, listChat);
                    recyclerView.setAdapter (messageAdapter);

                }else {
                    Toast.makeText (MessageActivity.this, "You can't send empty message", Toast.LENGTH_SHORT).show ();
                }
                edtSend.setText ("");
            }
        });
    }
}
