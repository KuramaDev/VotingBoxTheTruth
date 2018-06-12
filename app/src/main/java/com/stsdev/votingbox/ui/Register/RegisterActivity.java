package com.stsdev.votingbox.ui.Register;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.os.Bundle;

import com.stsdev.votingbox.R;
import com.stsdev.votingbox.ui.Base.BaseActivity;
import com.stsdev.votingbox.ui.Login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements RegisterView {

    private RegisterPresenterImp<RegisterView> mpresenter ;
   // private RegisterPresenter<RegisterView> presenter ;

    @BindView( R.id.reg_name)
    TextInputEditText name ;

    @BindView(R.id.reg_email)
    TextInputEditText email ;

    @BindView(R.id.reg_password)
    TextInputEditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //RegisterPresenterImp<RegisterView> mpresenter = new
        mpresenter = new RegisterPresenterImp();
        ButterKnife.bind(this);
        mpresenter.onAttach(RegisterActivity.this);

    }



    @Override
    public  void setUp(){

    }

    @Override
    public void openLoginActivity(){
       Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_server_register)
    public  void  onRegister(){
        mpresenter.onRegisterClicked(name.getText().toString(),email.getText().toString(),password.getText().toString());
    }


}
