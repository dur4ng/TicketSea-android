package com.dur4n.ticketsea.ui.signIn;

import com.dur4n.ticketsea.data.model.user.User;
import com.dur4n.ticketsea.ui.base.user.OnRepositoryUserCallback;

public interface SignInContract {

    interface View extends OnInteractorCallback {

    }
    interface Presenter extends OnInteractorCallback {
        void onDestroy();
    }
    interface OnInteractorCallback extends OnRepositoryUserCallback {
        void setUsernameEmpty();
        void setPasswordEmpty();
        void setInvalidCredentials();
    }

    interface Repository{
        void signIn(User user, OnInteractorCallback callback);
    }

}
