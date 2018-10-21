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
import android.util.Log;
import android.view.View;

import com.stsdev.votingbox.R;
import com.stsdev.votingbox.data.Model.User;
import com.stsdev.votingbox.ui.Base.BaseActivity;
import com.stsdev.votingbox.ui.Box.Created.ParticipateFragment;
import com.stsdev.votingbox.ui.Box.Favorite.FavoriteFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        mainVP.setAdapter(new MyBox.TabsAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(mainVP);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

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
                    Log.d("Created" , "Participated");
                    User user = (User)getIntent().getExtras().getParcelable("CurrentUser");
                    Bundle bundle = new Bundle();
                    bundle.putInt("usercode", user.getUsercode());

                    FavoriteFragment fragobj = FavoriteFragment.newInstance();
                    fragobj.setArguments(bundle);
                    return   fragobj;
                case 1:
                    Log.d("Created" , "Favs");
                    User user2 = (User)getIntent().getExtras().getParcelable("CurrentUser");
                    Bundle bundle2 = new Bundle();
                    bundle2.putInt("usercode", user2.getUsercode());

                    ParticipateFragment fragobj2 = ParticipateFragment.newInstance();
                    fragobj2.setArguments(bundle2);
                    return   fragobj2;

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
                case 1:
                    Log.d("Created" , "Participated");
                    return "Participate";
                case 0:
                    Log.d("Created" , "Favs");
                    return "Favorite";
            }
            return "";
        }
    }

    @Override
    public void setUp() {

    }




    @Override
    public void onBackPressed()
    {
        // code here to show dialog
        super.onBackPressed();  // optional depending on your needs
    }


}
