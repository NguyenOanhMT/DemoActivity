package com.nguyenoanh.demoactivity.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.nguyenoanh.demoactivity.R;;

public class SignIn extends AppCompatActivity {
    Button btnSignIn;
    TextView tvSignUp, tvForgotPass;
    EditText edtEmail, edtPass;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView (R.layout.activity_sign_in);

        edtEmail = (EditText) findViewById (R.id.edt_email);
        edtPass = (EditText) findViewById (R.id.edt_pass);

        //receive data to activity SignUp
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra(SignUp.BUNDLE);
            if (bundle != null) {
                edtEmail.setText(bundle.getString(SignUp.EMAIL));
                edtPass.setText(bundle.getString(SignUp.PASS));
            } else {
                edtEmail.setText(intent.getStringExtra(SignUp.EMAIL));
                edtPass.setText(intent.getStringExtra(SignUp.PASS));
            }
        }

        //click Forgot Password -> open browser link https://www.google.com/
        tvForgotPass = (TextView) findViewById (R.id.tv_forgot_pass);
        tvForgotPass.setOnClickListener (new View.OnClickListener (){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com/"));
                onResume ();
                startActivity(intent);
            }
        });

        auth = FirebaseAuth.getInstance ();
        btnSignIn = (Button) findViewById (R.id.btn_sign_in);
        btnSignIn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                //check email and pass if true to activity MainActivity, false still at SignIn
                String email = edtEmail.getText().toString();
                String pass = edtPass.getText().toString();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(SignIn.this, "All fill are required", Toast.LENGTH_SHORT).show();
                }else
                    auth.signInWithEmailAndPassword(email,pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult> () {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        Intent intent = new Intent(SignIn.this, MainActivity.class);
                                        intent.addFlags (Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                        startActivity(intent);
                                        finish();
                                    }else
                                        Toast.makeText(SignIn.this, "Login failed", Toast.LENGTH_SHORT).show();
                                }
                            });
            }
        });

        //listener event click button SingUp
        tvSignUp = (TextView) findViewById (R.id.tv_sign_up);
        tvSignUp.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignIn.this, SignUp.class);
                intent.addFlags (Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume ();
    }
}
