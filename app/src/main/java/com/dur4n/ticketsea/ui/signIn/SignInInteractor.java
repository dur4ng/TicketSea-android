package com.dur4n.ticketsea.ui.signIn;

import com.dur4n.ticketsea.data.model.user.User;
import com.dur4n.ticketsea.data.repository.user.UserLocalRepository;
import com.dur4n.ticketsea.data.room.LocalDB;

public class SignInInteractor implements SignInContract.OnInteractorCallback {

    SignInContract.OnInteractorCallback callback;

    public SignInInteractor(SignInContract.OnInteractorCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onFailure(String message) {
        callback.onFailure(message);
    }

    @Override
    public void onSuccess(String message) {
        callback.onSuccess(message);
    }

    @Override
    public void setUsernameEmpty() {
        callback.setUsernameEmpty();
    }

    @Override
    public void setPasswordEmpty() {
        callback.setPasswordEmpty();
    }

    @Override
    public void setInvalidCredentials() {
        callback.setInvalidCredentials();
    }

    public void signIn(User user) {
        UserLocalRepository.getInstance().signIn(user, callback);
    }
}
