package com.stsdev.votingbox.ui.Voting;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.akexorcist.roundcornerprogressbar.TextRoundCornerProgressBar;
import com.stsdev.votingbox.R;
import com.stsdev.votingbox.data.Model.Option;
import com.stsdev.votingbox.data.Model.Vote;
import com.stsdev.votingbox.ui.Base.BaseViewHolder;
import com.stsdev.votingbox.ui.main.MainAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by stavros on 20/5/2018.
 */

public class VotingAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Option> options;
    private int totalVotes;


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





    public class VotingViewHolder extends BaseViewHolder{

        @BindView(R.id.progressOption)
        RoundCornerProgressBar progressBar;

        @BindView(R.id.edtOptionTitle)
        TextView optTitle;

        @BindView(R.id.txtOptionNumber)
        TextView optNum;

        @BindView(R.id.percentage)
        TextView percentage;

        public  void clear(){

        }

        public VotingViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void onBind(int position) {
            super.onBind(position);
            Option option = options.get(position);
            optNum.setText( String.valueOf(position+1));
            optTitle.setText(option.getTitle());
            progressBar.setProgress(computePercent(option.getCount()));
            percentage.setText(String.valueOf(computePercent(option.getCount())));
        }

        private float computePercent(int votesOfOption){
            float percentage;
            percentage = votesOfOption*100 / totalVotes ;
            return percentage;
        }
    }


}
