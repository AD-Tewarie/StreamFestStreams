package com.example.streamfeststreams;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.streamfeststreams.fragments.ProfileFragment;

public class LoginActivity extends AppCompatActivity {
    public static final String KEY_TO_INTENT = "com.example.streamfest.KEY";
    private EditText Name;
    private EditText Password;
    private Button Login;
    SharedPreferences sp;
    ConstraintLayout layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn", false);

        layout = (ConstraintLayout)findViewById(R.id.login_layout_constraint);
        ImageView imageViewLogo = (ImageView)findViewById(R.id.imageView2);

        if (isDarkModeOn){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            layout.setBackgroundResource(R.drawable.login_donker);
            imageViewLogo.setImageResource(R.drawable.streamfest_logo__white);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            layout.setBackgroundResource(R.drawable.login_licht);
            imageViewLogo.setImageResource(R.drawable.streamfest_logo__black);
        }

        Name = (EditText) findViewById(R.id.nameLogin);
        Password = (EditText) findViewById(R.id.passLogin);
        Login = (Button) findViewById(R.id.buttLogin);




        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.getText().toString(), Password.getText().toString());
                
            }
        });
    }

    private void validate(String userName, String userPassword){
        if ((userName.equals( "Streamfest")) && (userPassword.equals("Streamfest"))){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
