package com.stsdev.votingbox.ui.Base;

/**
 * Created by stavros on 30/4/2018.
 */

public interface BasePresenter<V extends BaseView> {

    public void onAttach(V view );
    public void onDetach() ;




}
