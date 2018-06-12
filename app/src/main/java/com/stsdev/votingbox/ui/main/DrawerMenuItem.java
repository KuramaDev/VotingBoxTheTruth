package com.stsdev.votingbox.ui.main;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.stsdev.votingbox.R;

/**
 * Created by stavros on 5/5/2018.
 */

@Layout(R.layout.drawer_item)
public class DrawerMenuItem {

    public static final int DRAWER_MENU_ITEM_HOME = 1;
    public static final int DRAWER_MENU_ITEM_VOTINGBOX = 2;
    public static final int DRAWER_MENU_ITEM_CREATE_VOTE = 3;
    public static final int DRAWER_MENU_ITEM_SEARCH = 4;


    private int mMenuPosition;
    private Context mContext;
    private DrawerCallBack mCallBack;

    @View(R.id.itemNameTxt)
    private TextView itemNameTxt;

    @View(R.id.itemIcon)
    private ImageView itemIcon;

    public DrawerMenuItem(Context context, int menuPosition) {
        mContext = context;
        mMenuPosition = menuPosition;
    }

    @Resolve
    private void onResolved() {
        switch (mMenuPosition){
            case DRAWER_MENU_ITEM_HOME:
                itemNameTxt.setText("HOME");
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.home));
                break;
            case DRAWER_MENU_ITEM_VOTINGBOX:
                itemNameTxt.setText("MyVotingBox");
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.voting_box));
                break;
            case DRAWER_MENU_ITEM_CREATE_VOTE:
                itemNameTxt.setText("Create Vote");
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.create_vote));
                break;
            case DRAWER_MENU_ITEM_SEARCH:
                itemNameTxt.setText("Search");
              //  itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_email_black_18dp));
                break;

        }
    }

    @Click(R.id.mainView)
    private void onMenuItemClick(){
        switch (mMenuPosition){
            case DRAWER_MENU_ITEM_HOME:
                Toast.makeText(mContext, "Home", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onHomeMenuSelected();
                break;
            case DRAWER_MENU_ITEM_CREATE_VOTE:
                Toast.makeText(mContext, "Create Vote", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onCreateVoteMenuSelected();
                break;
            case DRAWER_MENU_ITEM_VOTINGBOX:
                Toast.makeText(mContext, "MyVotingBox", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onBoxMenuSelected();
                break;
            case DRAWER_MENU_ITEM_SEARCH:
                Toast.makeText(mContext, "Search", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onSearchMenuSelected();
                break;

        }
    }

    public DrawerMenuItem() {
    }

    public void setDrawerCallBack(DrawerCallBack callBack) {
        mCallBack = callBack;
    }



    public interface DrawerCallBack{
        void onHomeMenuSelected();
        void onCreateVoteMenuSelected();
        void onBoxMenuSelected();
        void onSearchMenuSelected();

    }
}