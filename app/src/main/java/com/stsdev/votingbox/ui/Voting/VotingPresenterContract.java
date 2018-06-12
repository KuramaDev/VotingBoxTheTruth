package com.stsdev.votingbox.ui.Voting;

import com.stsdev.votingbox.ui.Base.BasePresenter;

/**
 * Created by stavros on 19/5/2018.
 */

public interface VotingPresenterContract<V extends VoteDetailsContract> extends BasePresenter<V> {

    void getOptionsFromServer();
}
