package com.stsdev.votingbox.ui.Voting;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.stsdev.votingbox.R;
import com.stsdev.votingbox.data.Model.Promotion;
import com.stsdev.votingbox.data.Model.User;
import com.stsdev.votingbox.data.Model.Vote;
import com.stsdev.votingbox.ui.Base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VoteDetails extends BaseActivity implements VoteDetailsContract {

    VotingPresenterImp<VoteDetailsContract> presenter;
    VoteDetailsContract view;
    User user;
    @BindView(R.id.txtAuthorName)
    TextView author;

    @BindView(R.id.txtPubTime)
    TextView endingDate;

    @BindView(R.id.vote_title_voting)
    TextView question;

    @BindView(R.id.txtBarPollCount)
    TextView numOfVotes;

    @BindView(R.id.optionRec)
    RecyclerView optionArea;







    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, VoteDetails.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_details);
        setUnBinder(ButterKnife.bind(this));
        user = (User)getIntent().getExtras().getParcelable("CurrentUser");
        presenter = new VotingPresenterImp<>((Vote)getIntent().getExtras().getParcelable("Vote"));
        presenter.onAttach(VoteDetails.this);

        setUp();


        optionArea.setAdapter(presenter.getAdapter());
        presenter.initListenerInAdapter();

    }

    @Override
    public  void setUp(){
        presenter.InitInfo();
        presenter.InitRecView();
        presenter.CheckParticipation();
    }

    @Override
    public void setLayout(Vote vote){

        author.setText(vote.getAuthorName());
        endingDate.setText(vote.getEndingDate());
        question.setText(vote.getQuestion());
        numOfVotes.setText(String.valueOf(vote.getPollCount()));
    }

    @OnClick(R.id.floatingActionButton)
    public void submit(){
        presenter.sendPromotion();
    }

    public User retrieveUserInfo(){
        return  user;
    }

    @Override
    public void SetParticipatedLayout(){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
       // progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
    }

}