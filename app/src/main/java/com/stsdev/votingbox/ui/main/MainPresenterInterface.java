package com.stsdev.votingbox.ui.main;

import com.stsdev.votingbox.ui.Base.BasePresenter;

/**
 * Created by stavros on 16/5/2018.
 */

public interface MainPresenterInterface<V extends MainViewContract> extends BasePresenter<V> {
    void RetrieveVotesFromServer();
    MainAdapter getAdapter();
    void initListenerInAdapter();

}
