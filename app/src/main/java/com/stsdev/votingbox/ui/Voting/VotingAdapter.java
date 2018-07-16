package com.stsdev.votingbox.ui.Voting;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.akexorcist.roundcornerprogressbar.TextRoundCornerProgressBar;
import com.stsdev.votingbox.R;
import com.stsdev.votingbox.data.Model.Option;
import com.stsdev.votingbox.data.Model.Vote;
import com.stsdev.votingbox.ui.Base.BaseViewHolder;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by stavros on 20/5/2018.
 */

public class VotingAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Option> options;
    private int totalVotes;
    OnItemSelectedListenerVoting listener;


    public VotingAdapter(List<Option> options,int totalVotes) {

        this.options = options;
        this.totalVotes = totalVotes;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_option, parent, false);
        return new VotingViewHolder(view);


    }

    @Override
    public int getItemCount() {

        return options.size();

    }


    public void setOnItemClickListener(OnItemSelectedListenerVoting listener){
        this.listener = listener;
    }

    public interface OnItemSelectedListenerVoting  {
        void itemSelected(int position);
        void unselect(int position);
    }


    public class VotingViewHolder extends BaseViewHolder {

        @BindView(R.id.progressOption)
        RoundCornerProgressBar progressBar;

        @BindView(R.id.edtOptionTitle)
        TextView optTitle;

        @BindView(R.id.txtOptionNumber)
        TextView optNum;

        @BindView(R.id.percentage)
        TextView percentage;

        @BindView(R.id.votedImg)
        ImageView  voted;

        int prevPosition ;

        public  void clear(){

        }

        public VotingViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            int prev_position = getOldPosition() ;
        }

        public void onBind(int position) {
            super.onBind(position);
            Option option = options.get(position);
            optNum.setText( String.valueOf(position+1));
            optTitle.setText(option.getTitle());
            progressBar.setProgress(computePercent(option.getCount()));
            percentage.setText(String.valueOf(computePercent(option.getCount())));
            percentage.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            voted.setVisibility(View.GONE);
        }

        private float computePercent(int votesOfOption){
            float percentage=0;
            if(totalVotes!=0){
                percentage = votesOfOption*100 / totalVotes ;
            }
            return percentage;
        }

        @OnClick(R.id.option)
        public void onItemCklick(){
            if (listener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Log.d("POSITION OPTION", String.valueOf(position));

                    voted.setVisibility(View.VISIBLE);
                    listener.itemSelected(position);
                }
            }
        }
    }


}
