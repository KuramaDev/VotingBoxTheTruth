package com.stsdev.votingbox.ui.Account;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.stsdev.votingbox.R;
import com.stsdev.votingbox.data.Model.User;
import com.stsdev.votingbox.ui.Base.BaseActivity;
import com.stsdev.votingbox.ui.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountDetails extends BaseActivity implements AccountDetailsView {

    private AccountDetailsPresenterImp<AccountDetailsView> presenter;

    @BindView(R.id.username)
    TextView username;

    @BindView(R.id.email)
    TextView email;

    @BindView(R.id.registered)
    TextView registered;

    @BindView(R.id.toolbarSub2)
    Toolbar toolbar ;

    @BindView(R.id.txtUserName)
    TextView toolbarUsername;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, AccountDetails.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);
        ButterKnife.bind(this);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        presenter = new AccountDetailsPresenterImp<>();
        presenter.onAttach(this);

        setUp();


    }

    @Override
    public void setUp(){
        User currentUser ;

        currentUser =  getCurrentIntent().getParcelableExtra("CurrentUser");
        username.setText(currentUser.getName());
        email.setText(currentUser.getEmail());
        registered.setText(currentUser.getRegisteredDate());
        toolbarUsername.setText(currentUser.getName());

    }

    @Override
    public void onBackPressed()
    {
        // code here to show dialog
        super.onBackPressed();  // optional depending on your needs
    }

    @Override
    public Intent getCurrentIntent(){
        return getIntent();

    }

}
