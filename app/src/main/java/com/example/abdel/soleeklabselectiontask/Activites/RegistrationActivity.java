package com.example.abdel.soleeklabselectiontask.Activites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.abdel.soleeklabselectiontask.Fragments.RegistrationFragment;
import com.example.abdel.soleeklabselectiontask.R;

public class RegistrationActivity extends AppCompatActivity {

    RegistrationFragment registrationFragment = new RegistrationFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        if (savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.registration_fragment_container, registrationFragment)
                    .commit();
        }
    }
}
