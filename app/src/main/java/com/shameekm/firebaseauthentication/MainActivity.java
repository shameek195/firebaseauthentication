package com.shameekm.firebaseauthentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class MainActivity extends AppCompatActivity {
private FirebaseAuth firebaseAuth;
private EditText edtName;
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnSignIn;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       edtName =(EditText) findViewById(R.id.edtName);
        edtName.setText("");
        edtEmail =(EditText) findViewById(R.id.edtEmail);
        edtEmail.setText("");
        edtPassword =(EditText) findViewById(R.id.edtEnterPassword);
        edtPassword.setText("");
        Button btnsignin=(Button)findViewById(R.id.button);
        Button btnsignup=(Button)findViewById(R.id.button2);

        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null) {
            Intent welcomIntent = new Intent(MainActivity.this, welcome.class);
        startActivity(welcomIntent);
        finish();
        }
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail=edtEmail.getText().toString();
                String userPassword=edtPassword.getText().toString();
                signupUserWithEmailAndPassword(userEmail,userPassword);
            }
        });
        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail=edtEmail.getText().toString();
                String userPassword=edtPassword.getText().toString();
                signinTheUserWithEmailAndPassword(userEmail,userPassword);
            }
        });
    }
    private void signupUserWithEmailAndPassword(String userEmail,String userPassword)
    {
        firebaseAuth.createUserWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
              if(!task.isSuccessful())
              {
                  Toast.makeText(MainActivity.this,"There was a problem signining in try again",Toast.LENGTH_SHORT).show();
              }

              else
              {
                  Toast.makeText(MainActivity.this,"you have successfully signed in",Toast.LENGTH_SHORT).show();
                   specifyUserProfile();
              }

            }
        });

    }
    private void specifyUserProfile()
    {
        FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser !=null)
        {
            UserProfileChangeRequest userProfileChangeRequest=new UserProfileChangeRequest.Builder()
                    .setDisplayName(edtName.getText().toString()).build();
            firebaseUser.updateProfile(userProfileChangeRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        //task was succefull
                    }
                    else
                    {
                        //task was not succefull
                    }

                }
            });
        }

    }
    private void signinTheUserWithEmailAndPassword(String userEmail,String userPassword)
    {
        firebaseAuth.signInWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
              if(!task.isSuccessful())
              {
                  Toast.makeText(MainActivity.this,"there was an error ",Toast.LENGTH_SHORT).show();

              }
              else
              {
                  Intent welcomeIntent=new Intent(MainActivity.this,welcome.class);
                  startActivity(welcomeIntent);
                  finish();
              }
            }
        });
    }
}
