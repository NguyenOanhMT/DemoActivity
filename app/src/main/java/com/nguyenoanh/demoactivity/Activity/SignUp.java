package com.nguyenoanh.demoactivity.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.nguyenoanh.demoactivity.Model.ItemUser;
import com.nguyenoanh.demoactivity.R;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignUp extends AppCompatActivity {
    Button btnSignUp;
    TextView tvSignIn;
    CircleImageView profile;
    EditText edtEmail, edtPass, edtUser;

    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    DatabaseReference reference;
    private FirebaseAuth.AuthStateListener authLinear;

    StorageReference storageReference;
    private static  final int IMAGE_REQUEST=1;
    private static  final int RESULT_OK=1;
    private Uri imageUri;
    private StorageTask uploadTask;

    public static final String EMAIL ="email";
    public static final String PASS = "pass";
    public static final String BUNDLE = "bundle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView (R.layout.activity_sign_up);

        profile = (CircleImageView) findViewById (R.id.imv_profile);
        edtEmail = (EditText) findViewById (R.id.edt_email);
        edtPass = (EditText) findViewById (R.id.edt_pass);
        edtUser = (EditText) findViewById (R.id.edt_name);

        storageReference = FirebaseStorage.getInstance ().getReference ("uploads");
        firebaseUser = FirebaseAuth.getInstance ().getCurrentUser ();
        reference = FirebaseDatabase.getInstance ().getReference ("Users").child (firebaseUser.getUid ());

        auth = FirebaseAuth.getInstance ();
        btnSignUp = (Button) findViewById (R.id.btn_sign_up);
        btnSignUp.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                String user = edtUser.getText().toString();
                String email = edtEmail.getText().toString();
                String pass = edtPass.getText().toString();

                if ((TextUtils.isEmpty(user) || TextUtils.isEmpty(email)
                        || TextUtils.isEmpty(pass)) || TextUtils.isEmpty (user) ){
                    Toast.makeText(SignUp.this, "All fill are required", Toast.LENGTH_SHORT).show();
                }else if(edtPass.length() < 8)
                    Toast.makeText(SignUp.this, "Pass least 8 character ", Toast.LENGTH_SHORT).show();
                else if(isValidEmail (email) == false) {
                    Toast.makeText (SignUp.this, "Check address email", Toast.LENGTH_SHORT).show ();
                }else {
                    register(user,email,pass);
                }

                byBundle ();
//                byExtras ();
            }
        });

        tvSignIn = (TextView) findViewById (R.id.tv_sign_in);
        tvSignIn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, SignIn.class);
                intent.addFlags (Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity (intent);
                finish();
            }
        });

//        reference.addValueEventListener (new ValueEventListener () {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                ItemUser user = dataSnapshot.getValue (ItemUser.class);
//                edtUser.setText (user.getUsername ());
//
//                //check image avatar
//                String url =  user.getImvAvatar ();
//
//                if (url != null && url.equals("default")) {
//                    profile.setImageResource (R.drawable.anh);
//                }else {
//                    Glide.with (getApplicationContext ()).load (user.getImvAvatar ())
//                            .into (profile);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
        //upload image avatar
//        profile.setOnClickListener (new View.OnClickListener () {
//            @Override
//            public void onClick(View v) {
//                openIamge();
//            }
//        });
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    //create an account send fire auth
    private void register(final String user, final String email, String password){
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            assert firebaseUser != null;
                            String userid = firebaseUser.getUid();
                            reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("id",userid);
                            hashMap.put("user",user);
                            hashMap.put("imageURL", "default");

                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void> () {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                            Intent intent = new Intent (SignUp.this, MainActivity.class);
                                            intent.addFlags (Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity (intent);
                                            finish ();
                                    }
                                }
                            });
                        }else {
                            Toast.makeText(SignUp.this, "You can't register with this email or pass", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
        auth.addAuthStateListener(authLinear);
    }


//    private void openIamge(){
//        Intent intent = new Intent ();
//        intent.setType ("image/*");
//        intent.setAction (Intent.ACTION_GET_CONTENT);
//        startActivityForResult (intent, IMAGE_REQUEST);
//    }
//
//    private String getFileExtension(Uri uri){
//        ContentResolver contentResolver =  this.getContentResolver ();
//        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton ();
//        return mimeTypeMap.getExtensionFromMimeType (contentResolver.getType (uri));
//    }
//
//    //handling upload image in SignUp
//    private void uploadImage(){
//        final ProgressDialog pd = new ProgressDialog (this );
//        pd.setMessage ("Uploading");
//        pd.show();
//
//        if(imageUri != null){
//            final StorageReference fileReference = storageReference.child (System.currentTimeMillis ()
//                    +"."+ getFileExtension (imageUri));
//
//            uploadTask = fileReference.putFile (imageUri);
//            uploadTask.continueWithTask (new Continuation<UploadTask.TaskSnapshot, Task<Uri>> () {
//                @Override
//                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
//                    if(!task.isSuccessful ()){
//                        throw  task.getException ();
//                    }
//                    return fileReference.getDownloadUrl ();
//                }
//            }).addOnCompleteListener (new OnCompleteListener<Uri> () {
//                @Override
//                public void onComplete(@NonNull Task<Uri> task) {
//                    if(task.isSuccessful ()){
//                        Uri dowloadUri = task.getResult ();
//                        String mUri = dowloadUri.toString ();
//
//                        reference = FirebaseDatabase.getInstance ().getReference ("Users").child (firebaseUser.getUid ());
//                        HashMap<String, Object> hashMap = new HashMap<> ();
//                        hashMap.put("imageURL", mUri);
//                        reference.updateChildren (hashMap);
//                        pd.dismiss ();
//                    }else {
//                        Toast.makeText (SignUp.this, "Failed!", Toast.LENGTH_SHORT).show ();
//                        pd.dismiss ();
//                    }
//                }
//            }).addOnFailureListener (new OnFailureListener () {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText (SignUp.this , e.getMessage (), Toast.LENGTH_SHORT).show ();
//                    pd.dismiss ();
//                }
//            });
//        }else {
//            Toast.makeText (this,"No image selection", Toast.LENGTH_SHORT ).show ();
//        }
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data){
//        super.onActivityResult (requestCode, resultCode,data);
//
//        if(requestCode == IMAGE_REQUEST && requestCode == RESULT_OK
//                && data != null && data.getData () != null){
//            imageUri = data.getData ();
//            if(uploadTask != null && uploadTask.isInProgress ()){
//                Toast.makeText (this, "Upload in progress", Toast.LENGTH_SHORT).show ();
//            }else {
//                uploadImage ();
//            }
//        }
//    }

    //tear up data and send it to activity SignIn
//    public void byExtras() {
//        Intent intent = new Intent(SignUp.this, SignIn.class);
//        intent.putExtra(EMAIL, edtEmail.getText().toString());
//        intent.putExtra(PASS, edtPass.getText().toString());
//        startActivity(intent);
//    }

    //packed data and sent to activity SignIn
    public void byBundle() {
        Intent intent = new Intent(SignUp.this, SignIn.class);
        Bundle bundle = new Bundle();
        bundle.putString(EMAIL, edtEmail.getText().toString());
        bundle.putString(PASS, edtPass.getText().toString());
        intent.putExtra(BUNDLE, bundle);
        startActivity(intent);
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    public void onStop(){
        super.onStop();
        if(authLinear != null){
            auth.removeAuthStateListener(authLinear);
        }
    }
}
