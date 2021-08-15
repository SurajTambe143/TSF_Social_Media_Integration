package com.example.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    Button signout;
    TextView username,userEmail;
    ImageView profile_pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        profile_pic=findViewById(R.id.imageView6);
        username=findViewById(R.id.username);
        userEmail=findViewById(R.id.userEmail);
        signout=findViewById(R.id.signOut);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(HomeActivity.this,MainActivity.class));
            }
        });

        if (mUser!=null){
            if(mUser.getDisplayName()!=null) {
                username.setText(mUser.getDisplayName());
            }else{
                username.setText("No User Name");
            }
            userEmail.setText(mUser.getEmail());
            if (mUser.getPhotoUrl()!=null){
                String photoUrl=mUser.getPhotoUrl().toString();
                photoUrl = photoUrl+"?type=large";
                Glide.with(this).load(photoUrl).into(profile_pic);
            }else{
                profile_pic.setImageResource(R.drawable.ic_baseline_person_24);
            }
        }else {
            username.setText("No Name");
            userEmail.setText("No Email");
            profile_pic.setImageResource(R.drawable.ic_baseline_person_24);
        }
    }
}