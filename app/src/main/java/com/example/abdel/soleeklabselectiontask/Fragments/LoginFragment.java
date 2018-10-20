package com.example.abdel.soleeklabselectiontask.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abdel.soleeklabselectiontask.Activites.HomeActivity;
import com.example.abdel.soleeklabselectiontask.Activites.RegistrationActivity;
import com.example.abdel.soleeklabselectiontask.R;
import com.example.abdel.soleeklabselectiontask.Utilites.SharedPreferencesUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment implements View.OnClickListener {

    EditText emailEditText,passwordEditText;
    Button loginBtn;
    TextView registrationTextView;
    FirebaseAuth mAuth;
    final String EMAIL_BUNDLE_ID = "email_text";
    final String PASSWORD_BUNDLE_ID = "password_text";

    public LoginFragment() {
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(EMAIL_BUNDLE_ID,emailEditText.getText().toString());
        outState.putString(PASSWORD_BUNDLE_ID,passwordEditText.getText().toString());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null)
        {
            startActivity(new Intent(getActivity(),HomeActivity.class));
            getActivity().finish();
        }

        View view = inflater.inflate(R.layout.login_fragment,container,false);

        emailEditText = (EditText) view.findViewById(R.id.email_edit_text);
        passwordEditText = (EditText) view.findViewById(R.id.password_edit_text);
        loginBtn = (Button) view.findViewById(R.id.login_btn);
        registrationTextView = (TextView) view.findViewById(R.id.registration_text_view);
        String savedEmail = SharedPreferencesUtils.getSavedEmail(getActivity());

        if (!savedEmail.equals(""))
            emailEditText.setText(savedEmail);

        if (savedInstanceState != null)
        {
            emailEditText.setText(savedInstanceState.getString(EMAIL_BUNDLE_ID));
            passwordEditText.setText(savedInstanceState.getString(PASSWORD_BUNDLE_ID));
        }

        loginBtn.setOnClickListener(this);
        registrationTextView.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id)
        {
            case R.id.login_btn:
                final String email = emailEditText.getText().toString();
                final String password = passwordEditText.getText().toString();

                if (email.equals(""))
                {
                    Toast.makeText(getContext(),getString(R.string.email_required_text),Toast.LENGTH_LONG).show();
                }
                else if (password.equals(""))
                {
                    Toast.makeText(getContext(),getString(R.string.password_required_text),Toast.LENGTH_LONG).show();
                }
                else
                {
                    mAuth.signInWithEmailAndPassword(email,password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful())
                                    {
                                        SharedPreferencesUtils.SaveLoginCred(getActivity(),email);

                                        startActivity(new Intent(getActivity(),HomeActivity.class));

                                        //Should be killed since the user is verified to login
                                        getActivity().finish();
                                    }
                                    else
                                    {
                                        Toast.makeText(getContext(),getString(R.string.login_failed_text) + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
                break;

            case R.id.registration_text_view:
                startActivity(new Intent(getActivity(),RegistrationActivity.class));
                break;

            default:
                break;
        }
    }

}
