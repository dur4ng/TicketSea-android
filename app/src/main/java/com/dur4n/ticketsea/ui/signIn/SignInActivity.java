package com.dur4n.ticketsea.ui.signIn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.dur4n.ticketsea.MainActivity;
import com.dur4n.ticketsea.R;
import com.dur4n.ticketsea.data.model.user.User;
import com.dur4n.ticketsea.databinding.ActivitySignInBinding;
import com.dur4n.ticketsea.ui.SignUp.SignUpActivity;

public class SignInActivity extends AppCompatActivity implements SignInContract.View{

    SignInPresenter presenter;
    ActivitySignInBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in);
        presenter = new SignInPresenter(this);

        binding.btnSignUp.setOnClickListener(listener -> {
            startSignUpActivity();
        });
        binding.btLogin.setOnClickListener(listener -> {
            signIn();
        });
    }

    public void signIn(){
        presenter.signIn(getUser());
    }
    public User getUser(){
        String username = binding.tilUsernameSignIn.getEditText().getText().toString();
        String password = binding.tilPassword.getEditText().getText().toString();
        User user = new User(username, password);

        return user;
    }
    private void startSignUpActivity() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onFailure(String message) {
        binding.tieUsernameSignIn.setError(message);
    }

    @Override
    public void onSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        // navigate main activity
        startMainActivity();
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void setUsernameEmpty() {
        binding.tieUsernameSignIn.setError("Empty");
    }

    @Override
    public void setPasswordEmpty() {
        binding.tiePassword.setError("Empty");
    }

    @Override
    public void setInvalidCredentials() {
        binding.tieUsernameSignIn.setError("Invalid credentials");
    }
}