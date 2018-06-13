package com.stsdev.votingbox.ui.main;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stsdev.votingbox.R;
import com.stsdev.votingbox.data.Model.Vote;
import com.stsdev.votingbox.ui.Base.BasePresenter;
import com.stsdev.votingbox.ui.Base.BaseView;
import com.stsdev.votingbox.ui.Base.BaseViewHolder;
import com.stsdev.votingbox.ui.Voting.VotingAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by stavros on 6/5/2018.
 */

public class MainAdapter extends RecyclerView.Adapter<BaseViewHolder>{

    private List<Vote> votes ;
    OnItemSelectedListener listener;

    public MainAdapter(List<Vote> vote){
        this.votes = vote;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(votes.size()>0 || !votes.isEmpty()) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_vote_details, parent, false);
            return new MainViewHolder(view);
        }
        else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_empty_results, parent, false);
            return new EmptyResultsViewHolder(view);
        }


    }

    @Override
    public int getItemCount() {

            return votes.size();

    }

    public void setOnItemClickListener(OnItemSelectedListener listener){
        this.listener = listener;
    }

    public interface OnItemSelectedListener  {
        void itemSelected(int position);
    }

    public class MainViewHolder extends BaseViewHolder {

        @BindView(R.id.txtAuthorName)
        TextView authorName;

        @BindView(R.id.txtPubTime)
        TextView endingTime;

        @BindView(R.id.vote_title)
        TextView voteTitle;

        @BindView(R.id.txtBarPollCount)
        TextView numOfVotes;


        public   MainViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        protected void clear(){

        }

        public void onBind(int position) {
            super.onBind(position);
            Vote vote = votes.get(position);
            authorName.setText(vote.getAuthorName());
            endingTime.setText(vote.getEndingDate());
            voteTitle.setText(vote.getQuestion());
            numOfVotes.setText(String.valueOf(vote.getPollCount()));
        }


        @OnClick({R.id.author_bar, R.id.vote_title})
        public void onItemCklick(){
            if (listener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Log.d("POSITION CLICKED", String.valueOf(position));
                    listener.itemSelected(position);
                }
            }
        }



    }

    public class EmptyResultsViewHolder extends BaseViewHolder{

        public  EmptyResultsViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        protected void clear(){

        }

        public void onBind(int position) {
            super.onBind(position);


        }

    }
}
