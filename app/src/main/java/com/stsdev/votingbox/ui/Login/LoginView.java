package com.stsdev.votingbox.ui.Login;

import com.stsdev.votingbox.data.Model.User;
import com.stsdev.votingbox.ui.Base.BaseView;

/**
 * Created by stavros on 22/4/2018.
 */

public interface LoginView extends BaseView {

    void  openMainActivity(User user);
    void  setRegisterVisible();
    void  openRegisterActivity();
    String getToken();

}
