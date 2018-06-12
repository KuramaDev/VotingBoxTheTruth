package com.stsdev.votingbox.ui.Login;

import android.util.Log;

import com.stsdev.votingbox.R;
import com.stsdev.votingbox.Utils.CommonUtils;
import com.stsdev.votingbox.data.DataManagerImp;
import com.stsdev.votingbox.data.Model.User;
import com.stsdev.votingbox.ui.Base.BasePresenterImp;

/**
 * Created by stavros on 22/4/2018.
 */

public class LoginPresenterImp<V extends LoginView> extends BasePresenterImp<V>
        implements LoginPresenter<V>, DataManagerImp.ResponseHandler {

    private static final String TAG = "LoginPresenterImp";
    private DataManagerImp datamanager;


    public LoginPresenterImp() {
        datamanager = new DataManagerImp(this);
    }


    @Override
    public  void onServerLogin(String email , String password){


        if (email == null || email.isEmpty()) {
            getView().onError(R.string.empty_email);
            return;
        }

        if (!CommonUtils.isEmailValid(email)) {
            getView().onError(R.string.invalid_email);
            return;
       }

        if (password == null || password.isEmpty()) {
            getView().onError(R.string.empty_password);
            return;
        }

        getView().ShowLoading();
        if(getView().isNetworkConnected()) {
            datamanager.getUser(email,password);
        }
        else{
            getView().HideLoading();
            getView().onError("There is no Internet connection");
        }
    }

    @Override
    public   void getUserResponse(User user){

        if(user != null) {
           // if (entryEmail.equals(user.getEmail()) && entryPassword.equals(user.getPassword())) {
                getView().openMainActivity(user);
           // } else {
//                getView().HideLoading();
//                getView().setRegisterVisible();
//                getView().onError("email and password doesn't match");
//
//            }
        }
        else{
            getView().HideLoading();
            getView().setRegisterVisible();
            getView().onError("Wrong credentials or account doesn't exist");
        }



    }

}
