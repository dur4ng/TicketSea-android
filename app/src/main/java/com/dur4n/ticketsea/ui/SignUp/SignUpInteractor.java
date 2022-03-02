package com.dur4n.ticketsea.ui.SignUp;

import com.dur4n.ticketsea.data.model.user.UserToSignUp;
import com.dur4n.ticketsea.data.repository.user.UserLocalRepository;

public class SignUpInteractor implements SignUpContract.OnInteractorCallback{
    // callback
    private SignUpContract.OnInteractorCallback callback;

    public SignUpInteractor(SignUpContract.OnInteractorCallback callback) {
        this.callback = callback;
    }

    //region request user repository
    public void signUp(UserToSignUp user, SignUpContract.OnInteractorCallback callback){
        UserLocalRepository.getInstance().signUp(user, callback);
    }
    //endregion

    //region callbacks user repository
    @Override
    public void setUsernameEmpty() {
        callback.setUsernameEmpty();
    }

    @Override
    public void setUsernameNoAvailable() {
        callback.setUsernameNoAvailable();
    }

    @Override
    public void setPasswordEmpty() {
        callback.setPasswordEmpty();
    }

    @Override
    public void setPasswordsNotMatch() {
        callback.setPasswordsNotMatch();
    }

    @Override
    public void onFailure(String message) {
        callback.onFailure(message);
    }

    @Override
    public void onSuccess(String message) {
        callback.onSuccess(message);
    }
    //endregion
}
