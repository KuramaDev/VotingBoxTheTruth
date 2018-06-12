package com.stsdev.votingbox.ui.Box;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.stsdev.votingbox.R;
import com.stsdev.votingbox.data.Model.User;
import com.stsdev.votingbox.ui.Base.BaseActivity;
import com.stsdev.votingbox.ui.Box.Created.ParticipateFragment;
import com.stsdev.votingbox.ui.Box.Favorite.FavoriteFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyBox extends BaseActivity implements MyBoxView {

    MyBoxPresenterImpImp<MyBoxView> presenter;

    @BindView(R.id.vpMain)
    ViewPager mainVP;

    @BindView(R.id.tabLayoutPersonal)
    TabLayout tabLayout;

    @BindView(R.id.toolbarSub)
    Toolbar toolbar;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MyBox.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_box);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        mainVP.setAdapter(new MyBox.TabsAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(mainVP);

        presenter = new MyBoxPresenterImpImp<>();
        presenter.onAttach(this);

    }

    class TabsAdapter extends FragmentPagerAdapter {
        private Context mcontext;

        public TabsAdapter ( FragmentManager fm){
            super(fm);

        }

        @Override
        public Fragment getItem (int position){
            switch (position){
                case 0:
                    User user = (User)getIntent().getExtras().getParcelable("CurrentUser");
                    Bundle bundle = new Bundle();
                    bundle.putInt("usercode", user.getUsercode());

                    FavoriteFragment fragobj = FavoriteFragment.newInstance();
                    fragobj.setArguments(bundle);
                    return   fragobj;
                case 1:
                    return ParticipateFragment.newInstance();
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
                    return "Participate";
                case 1:
                    return "Favorite";
            }
            return "";
        }
    }

    @Override
    public void setUp() {

    }
}
