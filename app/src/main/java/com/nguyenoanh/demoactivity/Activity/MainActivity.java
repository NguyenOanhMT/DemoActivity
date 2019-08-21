package com.nguyenoanh.demoactivity.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.nguyenoanh.demoactivity.Fragment.MessageFragment;
import com.nguyenoanh.demoactivity.Fragment.NewFragment;
import com.nguyenoanh.demoactivity.Model.ItemUser;
import com.nguyenoanh.demoactivity.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView imv_home, imv_mess, imv_plus, imv_ring, imv_profile;
    ImageView line_home, line_mess, line_plus, line_ring, line_profile;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView (R.layout.activity_main);

//        try {
//            Intent intent = getIntent();
//            ArrayList<ItemUser> list = intent.getParcelableExtra ("listUser");
//
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
        initLayout ();
        fragmentManager = getSupportFragmentManager ();
        getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, new NewFragment(),"Home").commit();

        imv_home.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                selectIcon (view);
            }
        });
        imv_mess.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                selectIcon (view);
            }
        });
        imv_plus.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                selectIcon (view);
            }
        });
    }

    public void initLayout(){
        imv_home = findViewById (R.id.imv_home);
        imv_mess = findViewById (R.id.imv_message);
        imv_plus = findViewById (R.id.imv_plus);
        imv_ring = findViewById (R.id.imv_notification);
        imv_profile = findViewById (R.id.imv_profile);

        line_home = findViewById (R.id.line_home);
        line_mess = findViewById (R.id.line_mess);
        line_plus = findViewById (R.id.line_plus);
        line_ring = findViewById (R.id.line_ring);
        line_profile = findViewById (R.id.line_profile);
    }

    public void selectIcon(View view){
        switch (view.getId ()){
            case R.id.imv_home:
                colorIcon ();
                line_home.setVisibility (View.VISIBLE);
                imv_home.setImageDrawable (getDrawable (R.drawable.ic_home_black_24dp));
                removeFragment ("Message");
                removeFragment ("Home");
                addFragment ("Home");
                break;
            case R.id.imv_message:
                colorIcon ();
                line_mess.setVisibility (View.VISIBLE);
                imv_mess.setImageDrawable (getDrawable (R.drawable.ic_group8_black));
                removeFragment ("Home");
                removeFragment ("Message");
                addFragment ("Message");
                break;
            case R.id.imv_plus:
                colorIcon ();
                line_plus.setVisibility (View.VISIBLE);
                break;
        }
    }

    public void addFragment(String nameFragment){
        Fragment selection = new Fragment ();
        FragmentTransaction ft_add = fragmentManager.beginTransaction ();
        switch (nameFragment){
            case "Home":
                selection = new NewFragment ();
                ft_add.add (R.id.frame_layout, selection ,nameFragment);
                ft_add.commit ();
                break;
            case "Message":
                selection = new MessageFragment ();
                ft_add.add (R.id.frame_layout, selection ,nameFragment);
                ft_add.commit ();
                break;
            case "Ring":
                selection = new NewFragment ();
                ft_add.add (R.id.frame_layout, selection ,nameFragment);
                ft_add.commit ();
                break;
            case "Profile":
                selection = new MessageFragment ();
                ft_add.add (R.id.frame_layout, selection ,nameFragment);
                ft_add.commit ();
                break;
        }
    }
    public void removeFragment(String nameFragment){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction ();
        switch (nameFragment){
            case "Home":
                NewFragment newFragment = (NewFragment) getSupportFragmentManager ().findFragmentByTag ("Home");
                if(newFragment != null){
                    fragmentTransaction.remove (newFragment);
                    fragmentTransaction.commit ();
                }else {}
                break;
            case "Message":
                MessageFragment messageFragment = (MessageFragment) getSupportFragmentManager ().findFragmentByTag ("Message");
                if(messageFragment != null) {
                    fragmentTransaction.remove (messageFragment);
                    fragmentTransaction.commit ();
                }else {}
                break;
            case "Ring":
//                MessageFragment messageFragment = (MessageFragment) getSupportFragmentManager ().findFragmentByTag ("Message");
//                if(messageFragment != null) {
//                    fragmentTransaction.remove (messageFragment);
//                    fragmentTransaction.commit ();
//                }else {}
                break;
            case "Profile":
//                MessageFragment messageFragment = (MessageFragment) getSupportFragmentManager ().findFragmentByTag ("Message");
//                if(messageFragment != null) {
//                    fragmentTransaction.remove (messageFragment);
//                    fragmentTransaction.commit ();
//                }else {}
                break;
        }

    }
    public void colorIcon(){
        line_home.setVisibility (View.INVISIBLE);
        line_mess.setVisibility (View.INVISIBLE);
        line_plus.setVisibility (View.INVISIBLE);
        line_ring.setVisibility (View.INVISIBLE);
        line_profile.setVisibility (View.INVISIBLE);

        imv_home.setImageDrawable (getDrawable (R.drawable.ic_group_7));
        imv_mess.setImageDrawable (getDrawable (R.drawable.ic_group_8));
        imv_ring.setImageDrawable (getDrawable (R.drawable.ic_group_9));
    }
}
