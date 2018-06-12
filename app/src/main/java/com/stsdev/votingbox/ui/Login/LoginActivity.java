package com.stsdev.votingbox.ui.Login;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.stsdev.votingbox.R;
import com.stsdev.votingbox.data.Model.User;
import com.stsdev.votingbox.ui.Base.BaseActivity;
import com.stsdev.votingbox.ui.Register.RegisterActivity;
import com.stsdev.votingbox.ui.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public  class LoginActivity extends BaseActivity implements LoginView {

    private LoginPresenterImp<LoginView> presenter;

    @BindView( R.id.et_email)
    TextInputEditText email;

    @BindView(R.id.et_password)
    TextInputEditText password;

    @BindView(R.id.btn_server_register)
    Button btn_register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUnBinder(ButterKnife.bind(this));
        btn_register.setVisibility(View.GONE);
        presenter = new LoginPresenterImp();
        presenter.onAttach(LoginActivity.this);

    }

    @OnClick(R.id.btn_server_login)
    public void onLogin(){

            presenter.onServerLogin(email.getText().toString(), password.getText().toString());

    }

    @Override
   public void openMainActivity(User user){
       Intent intent = new Intent(this, MainActivity.class);
       intent.putExtra("CurrentUser", user);
       startActivity(intent);
       finish();
   }

   @Override
   protected void onDestroy(){
       presenter.onDetach();
       super.onDestroy();

   }

   @Override
    public  void setUp(){

   }

   @Override
   public void setRegisterVisible(){
       btn_register.setVisibility(View.VISIBLE);

   }

   @OnClick(R.id.btn_server_register)
    public void registerClicked(){
       openRegisterActivity();

   }

   @Override
    public void openRegisterActivity(){
       Intent intent = new Intent(this, RegisterActivity.class);
       this.finish();
       startActivity(intent);
   }

}
