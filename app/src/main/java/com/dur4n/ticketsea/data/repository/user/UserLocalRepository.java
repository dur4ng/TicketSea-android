package com.dur4n.ticketsea.data.repository.user;

import com.dur4n.ticketsea.data.dao.UserDAO;
import com.dur4n.ticketsea.data.model.user.User;
import com.dur4n.ticketsea.data.model.user.UserToSignUp;
import com.dur4n.ticketsea.data.room.LocalDB;
import com.dur4n.ticketsea.ui.SignUp.SignUpContract;
import com.dur4n.ticketsea.ui.signIn.SignInContract;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class UserLocalRepository implements SignUpContract.Repository, SignInContract.Repository {

    // instance of the repository
    private static UserLocalRepository userLocalRepository;

    // select DAO
    private final UserDAO userDAO;

    public UserLocalRepository() {
        this.userDAO = LocalDB.getDatabase().userDAO();
    }

    public static UserLocalRepository getInstance(){
        if (userLocalRepository == null){
            userLocalRepository = new UserLocalRepository();
        }
        return userLocalRepository;
    }
//region signUP
    @Override
    public void signUp(
            UserToSignUp user,
            SignUpContract.OnInteractorCallback callback
    ) {

        User userToRegister = new User(user.getUsername(), user.getPassword());

        if (user.getUsername().equals("")){
            callback.setUsernameEmpty();
        } else if(user.getPassword().equals("")){
            callback.setPasswordEmpty();
        }else if(user.getPasswordConfiramtion().equals("")){
            callback.setPasswordEmpty();
        }
        else if(!user.getPassword().equals(user.getPasswordConfiramtion())){
            callback.setPasswordsNotMatch();
        } else if (getUser(userToRegister) != null){
            callback.setUsernameNoAvailable();
        } else {
            createUser(userToRegister, callback);
        }
    }

    @Override
    public void createUser(
            User user,
            SignUpContract.OnInteractorCallback callback
    ) {
        long result = 0;
        try {
            result = LocalDB.databaseWriteExecutor.submit(()->userDAO.insert(user)).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (result == 0)
            callback.onFailure("Error in database");
        else
            callback.onSuccess("User registered");
    }


//endregion
    //region SignIn
    @Override
    public void signIn(
            User user,
            SignInContract.OnInteractorCallback callback
    ) {
        if (user.getUsername().equals("")){
            callback.setUsernameEmpty();
        } else if(user.getPassword().equals("")){
            callback.setPasswordEmpty();
        } else if(validateUser(user) == null){
            callback.setInvalidCredentials();
        }else {
            callback.onSuccess("Success login as " + user.getUsername());
        }
    }
    //endregion
    // Utils
    User getUser(User user){
        User userTMP = null;
        try {
            userTMP = LocalDB.databaseWriteExecutor.submit(() -> userDAO.checkUsername(user.getUsername())).get();
        } catch (ExecutionException e) {
            return null;
        } catch (InterruptedException e) {
            return null;
        }

        return userTMP;
    }

    User validateUser(User user){
        User userTMP = null;
        try {
            userTMP = LocalDB.databaseWriteExecutor.submit(() -> userDAO.validUser(user.getUsername(), user.getPassword())).get();
        } catch (ExecutionException e) {
            return null;
        } catch (InterruptedException e) {
            return null;
        }

        return userTMP;
    }
}
