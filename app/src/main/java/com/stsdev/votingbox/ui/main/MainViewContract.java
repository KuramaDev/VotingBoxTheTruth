package com.stsdev.votingbox.ui.main;

import com.stsdev.votingbox.data.Model.User;
import com.stsdev.votingbox.data.Model.Vote;
import com.stsdev.votingbox.ui.Base.BaseView;

import java.util.List;

/**
 * Created by stavros on 16/5/2018.
 */

public interface MainViewContract extends BaseView {

    void setLayout(User user);
    void OpenVotingActivity(Vote transfer);
    User getExtras();
    void setupDrawer(User user);

}
