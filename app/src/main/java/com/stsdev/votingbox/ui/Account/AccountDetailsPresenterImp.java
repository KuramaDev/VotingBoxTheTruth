package com.stsdev.votingbox.ui.Account;

import com.stsdev.votingbox.data.Model.User;
import com.stsdev.votingbox.ui.Base.BasePresenter;
import com.stsdev.votingbox.ui.Base.BasePresenterImp;

/**
 * Created by stavros on 14/6/2018.
 */

public class AccountDetailsPresenterImp<V extends AccountDetailsView> extends BasePresenterImp<V> implements AccountDetailsPresenter<V>{

    User currentUser;

    public AccountDetailsPresenterImp() {
    }

    public User checkExtras(){


        if(getView().getCurrentIntent().getParcelableExtra("CurrentUser") != null){
            currentUser = getView().getCurrentIntent().getParcelableExtra("CurrentUser");
            return currentUser;
        }
        else{
            getView().onError("sOMETHING UNEXPECTED OCCURED");
            return null;
        }

    }
}
