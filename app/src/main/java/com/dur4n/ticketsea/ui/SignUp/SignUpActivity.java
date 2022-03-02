package com.dur4n.ticketsea.ui.SignUp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dur4n.ticketsea.R;
import com.dur4n.ticketsea.data.model.user.UserToSignUp;

import com.dur4n.ticketsea.databinding.ActivitySignupBinding;
import com.dur4n.ticketsea.ui.signIn.SignInActivity;

public class SignUpActivity extends AppCompatActivity implements SignUpContract.View {

    UserToSignUp userView = new UserToSignUp("","", "");
    ActivitySignupBinding binding;
    SignUpPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        presenter = new SignUpPresenter(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup);

        binding.btRegister.setOnClickListener(listener -> {
            signUp();
        });
    }


    public void signUp(){
        UserToSignUp user =  getUser();
        presenter.signUp(user);
    }

    public UserToSignUp getUser(){
        String username = binding.tilUsernameSignUp.getEditText().getText().toString();
        String password = binding.tilPassword.getEditText().getText().toString();
        String passwordRepeat = binding.tilPasswordRepeat.getEditText().getText().toString();

        return new UserToSignUp(username,password,passwordRepeat);
    }

    @Override
    public void setUsernameEmpty() {
        binding.tieUsernameSignUp.setError("Empty");
    }

    @Override
    public void setUsernameNoAvailable() {
        binding.tieUsernameSignUp.setError("Not available");
    }

    @Override
    public void setPasswordEmpty() {
        binding.tiePassword.setError("Empty");
    }

    @Override
    public void setPasswordsNotMatch() {
        binding.tiePasswordRepeat.setError("Not match");
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        // navega al login
        startActivitySignIn();
    }

    private void startActivitySignIn() {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }
}