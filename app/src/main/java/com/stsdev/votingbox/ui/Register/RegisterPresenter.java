package com.stsdev.votingbox.ui.Register;

import com.stsdev.votingbox.ui.Base.BasePresenter;
import com.stsdev.votingbox.ui.Base.BaseView;

/**
 * Created by stavros on 28/4/2018.
 */

public interface RegisterPresenter<V extends RegisterView> extends BasePresenter<V> {
    void onRegisterClicked(String name , String email , String password);
    void onAttach(V view) ;
}
