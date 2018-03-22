package com.example.gregsquibbs.paws.Register.DogInformation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.gregsquibbs.paws.R;
import com.example.gregsquibbs.paws.Register.UserInformation.RegisterFragment;

/**
 * Created by George PC on 20/03/2018.
 */

public class RegisterDogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.contentFrameContainer);

        if (fragment == null){
            RegisterDogFragment dogFragment = new RegisterDogFragment();
            fm.beginTransaction()
                    .add(R.id.contentFrameContainer, dogFragment)
                    .commit();
        }

    }








}
