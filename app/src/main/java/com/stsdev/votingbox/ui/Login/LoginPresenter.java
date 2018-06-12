package com.stsdev.votingbox.ui.Login;

import com.stsdev.votingbox.ui.Base.BasePresenter;

/**
 * Created by stavros on 22/4/2018.
 */

public interface LoginPresenter<V extends LoginView> extends BasePresenter<V> {
   // void onFBLoginClick();
  //  void onGooglrLoginClick();
    void onServerLogin(String email , String password);
   // void onAttach(V view);
    //void onDetach();
}
