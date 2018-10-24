package com.stsdev.votingbox.ui.Box.Created;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.stsdev.votingbox.R;
import com.stsdev.votingbox.data.Model.Vote;
import com.stsdev.votingbox.ui.Base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by stavros on 23/5/2018.
 */

public class CreatedAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Vote> votes;



    public CreatedAdapter(List<Vote> votes) {
        Log.d("CREATED ADAPTER", String.valueOf(votes.size()));
        this.votes = votes;

    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.d("CREATED ADAPTER2", String.valueOf(votes.size()));
        if(votes.size()>0 || !votes.isEmpty()) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_vote_details, parent, false);
            return new CreatedViewHolder(view);
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


    public class CreatedViewHolder extends BaseViewHolder {

        @BindView(R.id.txtAuthorName)
        TextView authorName;

        @BindView(R.id.txtPubTime)
        TextView endingTime;

        @BindView(R.id.vote_title)
        TextView voteTitle;

        @BindView(R.id.txtBarPollCount)
        TextView numOfVotes;

        @BindView(R.id.imgBarFavorite)
        ImageView fav;

        @BindView(R.id.votedIdentifier)
        TextView votedIdent;

        @BindView(R.id.category_title)
        TextView category;

        public CreatedViewHolder(View viewitem){
            super(viewitem);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void clear(){

        }

        public void onBind(int position){
            super.onBind(position);
            Vote vote = votes.get(position);
            authorName.setText(vote.getAuthorName());
            endingTime.setText(vote.getEndingDate());
            voteTitle.setText(vote.getQuestion());
            numOfVotes.setText(String.valueOf(vote.getPollCount()));
            votedIdent.setVisibility(View.VISIBLE);
            category.setText(vote.getCategory());
            if(vote.isFav()==1){
                Log.d("IsFav", String.valueOf(vote.isFav()));
                fav.setImageResource(R.drawable.heart);
            }
            else{
                Log.d("IsFav", String.valueOf(vote.isFav()));
                fav.setImageResource(R.drawable.favorite_icon);
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
