package com.stsdev.votingbox.ui.Voting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.stsdev.votingbox.R;
import com.stsdev.votingbox.data.Model.Vote;
import com.stsdev.votingbox.ui.Base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VoteDetails extends BaseActivity implements VoteDetailsContract {

    VotingPresenterImp<VoteDetailsContract> presenter;
    VoteDetailsContract view;

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

        presenter = new VotingPresenterImp<>((Vote)getIntent().getExtras().getParcelable("Vote"));
        presenter.onAttach(VoteDetails.this);

        setUp();


        optionArea.setAdapter(presenter.getAdapter());
    }

    @Override
    public  void setUp(){
        presenter.InitInfo();
        presenter.InitRecView();
    }

    @Override
    public void setLayout(Vote vote){

        author.setText(vote.getAuthorName());
        endingDate.setText(vote.getEndingDate());
        question.setText(vote.getQuestion());
        numOfVotes.setText(String.valueOf(vote.getPollCount()));
    }


}
