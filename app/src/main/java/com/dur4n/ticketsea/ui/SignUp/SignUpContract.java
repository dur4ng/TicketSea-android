package com.dur4n.ticketsea.ui.SignUp;

import com.dur4n.ticketsea.data.model.user.User;
import com.dur4n.ticketsea.data.model.user.UserToSignUp;
import com.dur4n.ticketsea.ui.base.user.OnRepositoryUserCallback;

public interface SignUpContract {
    interface View extends OnInteractorCallback{

    }
    interface Presenter extends OnInteractorCallback{

    }
    interface OnInteractorCallback extends OnRepositoryUserCallback {
        void setUsernameEmpty();
        void setUsernameNoAvailable();
        void setPasswordEmpty();
        void setPasswordsNotMatch();
    }

    interface Repository{
        void signUp(UserToSignUp user, OnInteractorCallback callback);
        void createUser(User user, OnInteractorCallback callback);
    }
}
