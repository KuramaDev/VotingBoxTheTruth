package com.stsdev.votingbox.ui.main;


import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;


import com.mindorks.placeholderview.PlaceHolderView;
import com.stsdev.votingbox.R;
import com.stsdev.votingbox.data.Model.User;
import com.stsdev.votingbox.data.Model.Vote;
import com.stsdev.votingbox.data.SharedPrefManager;
import com.stsdev.votingbox.ui.Account.AccountDetails;
import com.stsdev.votingbox.ui.Base.BaseActivity;
import com.stsdev.votingbox.ui.Box.MyBox;
import com.stsdev.votingbox.ui.CreateVote.AddNewVoteActivity;
import com.stsdev.votingbox.ui.Voting.VoteDetails;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements DrawerMenuItem.DrawerCallBack, MainViewContract {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawerView)
    PlaceHolderView mDrawerView;

    @BindView(R.id.drawer_view)
    DrawerLayout mDrawer;

    @BindView(R.id.vote_showoff)
    RecyclerView voteArea;

//    List<Vote> votes ;
//    MainAdapter adapter ;

    DrawerMenuItem item1;
    DrawerMenuItem item2;
    DrawerMenuItem item3;
    DrawerMenuItem item4;
    DrawerMenuItem item5;

    DrawerHeader header;

    private MainPresenterImp<MainViewContract> presenter;


    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenterImp();
        presenter.onAttach(MainActivity.this);
        setUnBinder(ButterKnife.bind(this));
        setUp();


    }


    @Override
    public void setUp(){

        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);
        presenter.InitInfo();
        presenter.RetrieveVotesFromServer();
        voteArea.setAdapter(presenter.getAdapter());
        presenter.initListenerInAdapter();

    }

    @Override
    public void onFragmentDetached(){

    }

    public void onFragmentAttached(){


    }

    @Override
    public void setupDrawer(User user){
        item1 = new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_ITEM_HOME);
        item2 = new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_ITEM_CREATE_VOTE);
        item3 = new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_ITEM_VOTINGBOX);
        item4 = new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_ITEM_SEARCH);
        item5 = new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_ITEM_ACCOUNT);
        mDrawerView
                .addView(new DrawerHeader(user))
                .addView(item1)
                .addView(item2)
                .addView(item3)
                .addView(item4)
                .addView(item5);


        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.open_drawer, R.string.close_drawer){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };


        mDrawer.addDrawerListener(drawerToggle);
        item1.setDrawerCallBack(this);
        item2.setDrawerCallBack(this);
        item3.setDrawerCallBack(this);
        item4.setDrawerCallBack(this);
        item5.setDrawerCallBack(this);

        drawerToggle.syncState();
    }

    @Override
    public  void onHomeMenuSelected(){
        String token = SharedPrefManager.getInstance(this).getDeviceToken();
        Log.d("DEVICE TOKEN", token);

    }
    @Override
    public void onCreateVoteMenuSelected(){
        Log.d("Create vote", "trying to do");
        Intent intent= AddNewVoteActivity.getStartIntent(MainActivity.this);
        intent.putExtra("CurrentUser", getExtras());
        startActivity(intent);
        //inish();
    }
    @Override
    public void onBoxMenuSelected(){
        Log.d("Create vote", "trying to do");
        Intent intent= MyBox.getStartIntent(MainActivity.this);
        intent.putExtra("CurrentUser", getExtras());
        startActivity(intent);
        //finish();
    }
    @Override
    public void onSearchMenuSelected(){

    }
    @Override
    public void onAccountMenuSelected(){
        Intent intent= AccountDetails.getStartIntent(MainActivity.this);
        intent.putExtra("CurrentUser", getExtras());

        startActivity(intent);
    }

    @Override
    public void setLayout(User user){

    }

    @Override
    public void OpenVotingActivity(Vote vote){

        Intent intent = VoteDetails.getStartIntent(MainActivity.this);
        intent.putExtra("Vote" , vote);
        intent.putExtra("CurrentUser", getExtras());
        startActivity(intent);

    }

    @Override
    public User getExtras(){
        return ((User)getIntent().getExtras().getParcelable("CurrentUser"));
    }

}
