package com.stsdev.votingbox.ui.Base;

import android.support.annotation.StringRes;

/**
 * Created by stavros on 30/4/2018.
 */

public interface BaseView {

    public void ShowLoading();
    public void HideLoading();
    public void onError(@StringRes int resId);
    public  void onError(String message);
    public boolean isNetworkConnected();
}
