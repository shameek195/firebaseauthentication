package com.shameekm.firebaseauthentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class welcome extends AppCompatActivity {
private TextView txtWelcome;
private Button btnSignout;
private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
 firebaseAuth=FirebaseAuth.getInstance();
if(firebaseAuth.getCurrentUser()==null)
{
    Intent mainActivityIntent=new Intent(welcome.this,MainActivity.class);
    startActivity(mainActivityIntent);
    finish();
}
if (firebaseAuth.getCurrentUser()!=null)
    txtWelcome.setText("how are you" + firebaseAuth.getCurrentUser().getDisplayName());
        txtWelcome=(TextView)findViewById(R.id.txtWelcome);
        btnSignout=(Button)findViewById(R.id.btnSignout);
        btnSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                Intent mainActivityIntent=new Intent(welcome.this,MainActivity.class);
                startActivity(mainActivityIntent);
                finish();

            }
        });
    }
}
