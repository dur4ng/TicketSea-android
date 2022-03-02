package com.dur4n.ticketsea.ui.SignUp;

import android.view.View;

import com.dur4n.ticketsea.data.model.user.UserToSignUp;

public class SignUpPresenter implements SignUpContract.Presenter{

    private SignUpContract.View view;
    private SignUpInteractor interactor;

    public SignUpPresenter(SignUpContract.View view) {
        this.view = view;
        this.interactor = new SignUpInteractor(this);
    }



    public void signUp(UserToSignUp user){
        interactor.signUp(user, this);
    }

    //region callbacks interactor
    @Override
    public void setUsernameEmpty() {
        view.setUsernameEmpty();
    }

    @Override
    public void setUsernameNoAvailable() {
        view.setUsernameNoAvailable();
    }

    @Override
    public void setPasswordEmpty() {
        view.setPasswordEmpty();
    }

    @Override
    public void setPasswordsNotMatch() {
        view.setPasswordsNotMatch();
    }

    @Override
    public void onFailure(String message) {
        view.onFailure(message);
    }

    @Override
    public void onSuccess(String message) {
        view.onSuccess(message);
    }
    //endregion
}
