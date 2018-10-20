package com.example.abdel.soleeklabselectiontask.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.abdel.soleeklabselectiontask.AsyncTasks.CountryAsyncTask;
import com.example.abdel.soleeklabselectiontask.Fragments.HomeFragment;
import com.example.abdel.soleeklabselectiontask.Interfaces.HomeCountriesInterface;
import com.example.abdel.soleeklabselectiontask.Models.Country;
import com.example.abdel.soleeklabselectiontask.R;
import com.example.abdel.soleeklabselectiontask.Utilites.SharedPreferencesUtils;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomeCountriesInterface {

    List<Country> countries;
    HomeFragment homeFragment = new HomeFragment();
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferencesUtils.SetAppTheme(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        progressBar = (ProgressBar) findViewById(R.id.country_loading_progressBar);

        if (savedInstanceState == null)
        {
            new CountryAsyncTask(this).execute();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.home_fragment_container, homeFragment)
                    .commit();
        }
    }

    @Override
    public void sendCountriesData(List<Country> countries) {
        homeFragment.setCountries(countries);
    }

    @Override
    public void handleProgressBar(int visibility) {
        progressBar.setVisibility(visibility);
    }

    @Override
    public void toastErrorMessage(String ERROR_MESSAGE) {
        Toast.makeText(this,ERROR_MESSAGE,Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        SharedPreferencesUtils.SetMenuViewModeIcon(this,menu.findItem(R.id.view_mode_menu_item));

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onCountryClick(Country country) {
        String toastText = "Capital : " + country.getCapital() + "\n" +
                "Region : " + country.getRegion();

        Toast.makeText(this,toastText,Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //switch cases can be used here
        if (id == R.id.logout_menu_item)
        {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this,AuthenticationActivity.class));
            finish();
        }
        else if (id == R.id.view_mode_menu_item)
        {
            SharedPreferencesUtils.SwitchViewMode(this, item);
            recreate();
        }
        return super.onOptionsItemSelected(item);
    }
}
