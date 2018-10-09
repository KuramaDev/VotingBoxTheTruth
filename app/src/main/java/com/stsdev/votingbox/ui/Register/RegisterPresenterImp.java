package com.stsdev.votingbox.ui.Register;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.stsdev.votingbox.R;
import com.stsdev.votingbox.Utils.CommonUtils;
import com.stsdev.votingbox.data.DataManager;
import com.stsdev.votingbox.data.DataManagerImp;
import com.stsdev.votingbox.data.Model.Subscription;
import com.stsdev.votingbox.data.Model.User;
import com.stsdev.votingbox.data.Model.Vote;
import com.stsdev.votingbox.data.SharedPrefManager;
import com.stsdev.votingbox.ui.Base.BasePresenter;
import com.stsdev.votingbox.ui.Base.BasePresenterImp;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by stavros on 28/4/2018.
 */

public class RegisterPresenterImp<V extends RegisterView> extends BasePresenterImp<V> implements RegisterPresenter<V> {

    private DataManagerImp mdatamanager;
    private DataManager dataManager;




    public RegisterPresenterImp(DataManager datamager){

        this.dataManager = datamager;
    }

    public RegisterPresenterImp() {
        mdatamanager = new DataManagerImp();
    }



    public DataManager getDataManager(){
        return  this.dataManager;
    }

    @Override
    public void onRegisterClicked(String name , String email, String password){



        if (email == null || email.isEmpty()) {
            getView().onError(R.string.empty_email);
            return;
        }
        if (password == null || password.isEmpty()) {
            getView().onError(R.string.empty_password);
            return;
        }
        if (name == null || name.isEmpty()) {
            getView().onError(R.string.empty_name);
            return;
        }
        if(!CommonUtils.isEmailValid(email)){
             getView().onError(R.string.invalid_email);
             return;
        }
        else if(!CommonUtils.isPasswordValid(password) && password.length()<8){
            getView().onError(R.string.invalid_password);
            return;
        }

        registerUser(name,email,password);


    }

    public void registerUser(String name  , String email , String  password){
        User user = new User(name,email,password);
        getView().ShowLoading();
        mdatamanager.getCreateUserObservable(user).subscribeWith(getObserver());
    }

    private DisposableObserver<String> getObserver(){
        return new DisposableObserver<String>() {

            @Override
            public void onNext(@NonNull String movieResponse) {

                Log.d("TEST OF RXJAVA","OnNext"+movieResponse);
                // mvi.displayMovies(movieResponse);
                if(movieResponse.equals("s")){
                    getView().openLoginActivity();
                }
                else{
                    getView().onError(movieResponse);
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("ERROR","Error"+e);
                e.printStackTrace();
                //mvi.displayError("Error fetching Movie Data");
            }

            @Override
            public void onComplete() {
                Log.d("TEST OF RXJAVA","Completed");
                getView().HideLoading();

                //mvi.hideProgressBar();
            }
        };
    }

    public void subscribeUser(String email,Context context){
        final String token = SharedPrefManager.getInstance(context).getDeviceToken();
        Log.d("Token" , token);
        Log.d("EMAIL" , email);
        Subscription subscription = new Subscription();
        subscription.setEmail(email);
        subscription.setToken(token);
        mdatamanager.subscribeUserObservable(subscription).subscribeWith( getSubscribeObserver());
    }

    private DisposableObserver<String> getSubscribeObserver(){
        return new DisposableObserver<String>() {

            @Override
            public void onNext(@NonNull String movieResponse) {

                Log.d("Subscription","OnNext"+movieResponse);
                // mvi.displayMovies(movieResponse);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("Subscription ERROR","Error"+e);
                e.printStackTrace();
                //mvi.displayError("Error fetching Movie Data");
            }

            @Override
            public void onComplete() {
                Log.d("TEST OF RXJAVA","Completed");


                //mvi.hideProgressBar();
            }
        };
    }
}
