package com.stsdev.votingbox.ui.CreateVote;



import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;


import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;


import com.stsdev.votingbox.R;
import com.stsdev.votingbox.data.Model.Option;
import com.stsdev.votingbox.data.Model.SmartFragmentStatePagerAdapter;
import com.stsdev.votingbox.data.Model.User;
import com.stsdev.votingbox.ui.Base.BaseActivity;
import com.stsdev.votingbox.ui.CreateVote.fragments.CreateOptionsFragment;
import com.stsdev.votingbox.ui.CreateVote.fragments.CreateVoteSettingsFragment;

import java.util.Calendar;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNewVoteActivity extends BaseActivity implements CreateVoteView {

    private CreateVotePresenterImp<CreateVoteView> presenter;
    private TabsAdapter tabsAdapter;
    private SmartFragmentStatePagerAdapter adapterViewPager;
    private CreateOptionsFragment frag1;
    private CreateVoteSettingsFragment frag2;
    private User user ;
    @BindView(R.id.editText)
    EditText typeQuestion;



    @BindView(R.id.tabLayoutCreateVote)
    TabLayout tabs;

    @BindView(R.id.vpSubArea)
    ViewPager vpRegion;

    @BindString(R.string.optiontab)
    String optionTab;

    @BindString(R.string.settingstab)
    String settingsTab;

    Toolbar mainToolbar;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, AddNewVoteActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_vote);
        ButterKnife.bind(this);

        user = (User)getIntent().getExtras().getParcelable("CurrentUser");
        mainToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);
        tabsAdapter = new TabsAdapter(getSupportFragmentManager());
        vpRegion.setAdapter(tabsAdapter);
        tabs.setupWithViewPager(vpRegion);


        presenter = new CreateVotePresenterImp<>();
        presenter.onAttach(this);

    }

    class TabsAdapter extends SmartFragmentStatePagerAdapter {
        private Context mcontext;

        public TabsAdapter ( FragmentManager fm){
            super(fm);

        }

        @Override
        public Fragment getItem (int position){
            switch (position){
                case 0:
                    return  CreateOptionsFragment.newTabFragment();
                case 1:
                    return new CreateVoteSettingsFragment();
            }
            return null ;
        }

        @Override
        public int getCount(){
            return 2;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return optionTab;
                case 1:
                    return settingsTab;
            }
            return "";
        }


    }


    @OnClick(R.id.submitVote)
    public void OnSubmit(){

        Log.d("TEST",String.valueOf(vpRegion.getCurrentItem()));
        presenter.createNewVote(typeQuestion.getText().toString(),retrieveOptions(),retrieveDate(),retrieveCategory());

    }

    @Override
    public void setUp(){

    }

    public List<Option> retrieveOptions(){

       frag1 = (CreateOptionsFragment) tabsAdapter.getRegisteredFragment(0);
       return frag1.getOptionList();
    }

    public Calendar retrieveDate(){
        frag2=(CreateVoteSettingsFragment) tabsAdapter.getRegisteredFragment(1);
        return frag2.getDate();
    }

    public String retrieveCategory(){
        frag2=(CreateVoteSettingsFragment) tabsAdapter.getRegisteredFragment(1);
        Log.d("Item Retrieved" ,frag2.getCategoryName());
        return frag2.getCategoryName();
    }


    public User retrieveUserInfo(){
        return  user;
    }

    @Override
    public void closeActivity(){
        finish();
    }
}
