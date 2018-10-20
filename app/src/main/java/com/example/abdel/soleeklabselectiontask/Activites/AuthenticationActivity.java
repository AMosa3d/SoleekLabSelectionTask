package com.example.abdel.soleeklabselectiontask.Activites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.abdel.soleeklabselectiontask.Fragments.LoginFragment;
import com.example.abdel.soleeklabselectiontask.R;

public class AuthenticationActivity extends AppCompatActivity {

    LoginFragment loginFragment = new LoginFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //reset apptheme after the splash screen

        setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);


        if (savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.login_fragment_container, loginFragment)
                    .commit();
        }

    }


}
