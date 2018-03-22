package com.example.gregsquibbs.paws.UserArea;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.gregsquibbs.paws.IntentConstants;
import com.example.gregsquibbs.paws.R;

public class UserAreaActivity extends AppCompatActivity {


    private UserAreaFragment userAreaFragment;
    private String userID;
    private String name;
    private String username;
    private String email;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);

        Intent intent = getIntent();

        userID = intent.getStringExtra("userID");
        name = intent.getStringExtra("name");
        username = intent.getStringExtra("username");
        email = intent.getStringExtra("email");
        password = intent.getStringExtra("password");

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.contentFrameContainer);

        if (fragment == null){
            userAreaFragment = new UserAreaFragment();
            fm.beginTransaction()
                    .add(R.id.contentFrameContainer, userAreaFragment)
                    .commit();
        }

        Bundle bundle = new Bundle();
        bundle.putString("userID", userID);
        bundle.putString("name", name);
        bundle.putString("username", username);
        bundle.putString("email", email);
        bundle.putString("password", password);
        userAreaFragment.setArguments(bundle);
    }


}
