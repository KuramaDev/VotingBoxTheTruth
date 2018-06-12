package com.stsdev.votingbox.ui.CreateVote.OptionsRecycler;

import com.stsdev.votingbox.data.Model.Option;

/**
 * Created by stavros on 14/4/2018.
 */

public interface OptionRowViewContract {

    void setOptionTitle(String title) ;

    void onDeleteOptionButtonPressed();

    void onCreateNewOptionPressed() ;

    void setLayout(int viewType,int position, Option option);


}
