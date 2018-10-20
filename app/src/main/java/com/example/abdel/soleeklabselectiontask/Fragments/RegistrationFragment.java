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
import com.example.abdel.soleeklabselectiontask.R;
import com.example.abdel.soleeklabselectiontask.Utilites.SharedPreferencesUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationFragment extends Fragment implements View.OnClickListener {

    TextView loginTextView;
    EditText emailEditText,passwordEditText,confirmPasswordEditText;
    Button registrationBtn;
    FirebaseAuth mAuth;

    final String EMAIL_BUNDLE_ID = "email_text";
    final String PASSWORD_BUNDLE_ID = "password_text";
    final String CONFIRM_PASSWORD_BUNDLE_ID = "confirm_password_text";


    public RegistrationFragment() {
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(EMAIL_BUNDLE_ID,emailEditText.getText().toString());
        outState.putString(PASSWORD_BUNDLE_ID,passwordEditText.getText().toString());
        outState.putString(CONFIRM_PASSWORD_BUNDLE_ID,confirmPasswordEditText.getText().toString());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.registration_fragment,container,false);

        emailEditText = (EditText) view.findViewById(R.id.email_edit_text);
        passwordEditText = (EditText) view.findViewById(R.id.password_edit_text);
        confirmPasswordEditText = (EditText) view.findViewById(R.id.confirm_password_edit_text);
        registrationBtn = (Button) view.findViewById(R.id.registration_btn);
        loginTextView = (TextView) view.findViewById(R.id.login_text_view);

        mAuth = FirebaseAuth.getInstance();

        if (savedInstanceState != null)
        {
            emailEditText.setText(savedInstanceState.getString(EMAIL_BUNDLE_ID));
            passwordEditText.setText(savedInstanceState.getString(PASSWORD_BUNDLE_ID));
            confirmPasswordEditText.setText(savedInstanceState.getString(CONFIRM_PASSWORD_BUNDLE_ID));
        }

        registrationBtn.setOnClickListener(this);
        loginTextView.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id)
        {
            case R.id.registration_btn:
                final String email = emailEditText.getText().toString();
                final String password = passwordEditText.getText().toString();
                final String confirmPassword = confirmPasswordEditText.getText().toString();

                if (email.equals(""))
                {
                    Toast.makeText(getContext(),getString(R.string.email_required_text),Toast.LENGTH_LONG).show();
                }
                else if (password.equals(""))
                {
                    Toast.makeText(getContext(),getString(R.string.password_required_text),Toast.LENGTH_LONG).show();
                }
                else if (password.equals(""))
                {
                    Toast.makeText(getContext(),getString(R.string.confirm_password_required_text),Toast.LENGTH_LONG).show();
                }
                else if (!password.equals(confirmPassword))
                {
                    Toast.makeText(getContext(),getString(R.string.unmatched_passwords_text),Toast.LENGTH_LONG).show();
                }
                else
                {
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                SharedPreferencesUtils.SaveLoginCred(getActivity(),email);
                                Toast.makeText(getContext(),getString(R.string.registration_completed_text),Toast.LENGTH_LONG).show();

                                //Kill the stack and start application activities stack with the home fragment as the root
                                startActivity(
                                        new Intent(getActivity(),HomeActivity.class)
                                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK)
                                );
                            }
                            else
                            {
                                Toast.makeText(getContext(),getString(R.string.registration_failed_text) + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                break;

            case R.id.login_text_view:
                getActivity().finish();
                break;

            default:
                break;
        }
    }
}
