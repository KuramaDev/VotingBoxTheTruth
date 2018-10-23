package com.stsdev.votingbox.ui.Voting;

import com.stsdev.votingbox.data.Model.User;
import com.stsdev.votingbox.data.Model.Vote;
import com.stsdev.votingbox.ui.Base.BaseView;

/**
 * Created by stavros on 19/5/2018.
 */

public interface VoteDetailsContract extends BaseView {
   void setLayout(Vote vote);
   User retrieveUserInfo();
   void SetParticipatedLayout();
   void  SetFavouriteLayout();
}
