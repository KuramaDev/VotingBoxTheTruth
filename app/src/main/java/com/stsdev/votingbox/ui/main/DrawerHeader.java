package com.stsdev.votingbox.ui.main;

import android.widget.ImageView;
import android.widget.TextView;

import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.stsdev.votingbox.R;
import com.stsdev.votingbox.data.Model.User;

/**
 * Created by stavros on 5/5/2018.
 */

@NonReusable
@Layout(R.layout.drawer_header)
public class DrawerHeader {

    private User user;

    public DrawerHeader(User user) {
        this.user = user;
    }

    @View(R.id.profileImageView)
    private ImageView profileImage;

    @View(R.id.nameTxt)
    private TextView nameTxt;

    @View(R.id.emailTxt)
    private TextView emailTxt;

    @Resolve
    private void onResolved() {
        if(user!=null){
            nameTxt.setText(user.getName());
            emailTxt.setText(user.getEmail());
        }
        else {
            nameTxt.setText("It is ame Mario");
            emailTxt.setText("saveThePrincess@gmail.com");
        }
    }
}
