package com.dur4n.ticketsea.ui.signIn;

import com.dur4n.ticketsea.data.model.user.User;

public class SignInPresenter implements SignInContract.Presenter {

    SignInInteractor callback;
    SignInContract.View view;

    public SignInPresenter(SignInContract.View view) {
        this.view = view;
        this.callback = new SignInInteractor(this);
    }

    @Override
    public void onFailure(String message) {
        view.onFailure(message);
    }

    @Override
    public void onSuccess(String message) {
        view.onSuccess(message);
    }

    @Override
    public void setUsernameEmpty() {
        view.setUsernameEmpty();
    }

    @Override
    public void setPasswordEmpty() {
        view.setPasswordEmpty();
    }

    @Override
    public void setInvalidCredentials() {
        view.setInvalidCredentials();
    }

    @Override
    public void onDestroy() {
        this.view = null;
        this.callback = null;
    }

    public void signIn(User user) {
        callback.signIn(user);
    }
}
