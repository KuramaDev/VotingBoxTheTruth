package com.stsdev.votingbox.ui.CreateVote.OptionsRecycler;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.stsdev.votingbox.R;
import com.stsdev.votingbox.data.Model.Option;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by stavros on 14/4/2018.
 */

public class OptionsAdapter extends RecyclerView.Adapter<OptionsAdapter.OptionViewHolder> {


    private final OptionListPresenter presenter;
    OptionsHandlingListener listener;

    public OptionsAdapter(OptionListPresenter presenter) {
        this.presenter = presenter;

    }

    @Override
    public OptionViewHolder onCreateViewHolder (ViewGroup parent , int viewType){
        Log.d("BOOM BOOM TUM TUM" , "ADAPTER CONSTRUCTOR");
        return new OptionViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.options_row, parent, false));
    }

    @Override
    public void onBindViewHolder(OptionViewHolder holder, int position) {
        presenter.onBindOptionsRowViewAtPosition(position,holder);
    }

    @Override
    public int getItemCount() {
        return presenter.getRepositoriesRowsCount() +1 ;

    }

    public void setOnItemClickListener(OptionsHandlingListener listener){
        this.listener = listener ;
    }

    public interface OptionsHandlingListener{
        public void addNewOption(View view , int position);
        public void deleteOption(View view , int position);
        public void setOptionTitle(String title , int position);

    }

    public class OptionViewHolder extends RecyclerView.ViewHolder implements OptionRowViewContract {

        @BindView(R.id.addNewOption)
        ImageView createNewOption;

//        @BindView(R.id.txtOptionNumber)
//        TextView optionNumber;


        @BindView(R.id.relAdd)
        RelativeLayout relAdd;

        @BindView(R.id.imgDeleteOption)
        ImageView imgDelete;

        @BindView(R.id.edtOptionTitle)
        EditText edtOptionTitle;

        @BindView(R.id.relNormal)
        RelativeLayout relNormal;

        int position = 0;
        private optionEditTextListener optionEditTextListener;
        private Option option;


        OptionViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);

        }



        @Override
        public void setOptionTitle(String title){

        }

        @Override
        public void onDeleteOptionButtonPressed(){

        }

        @Override
        public void onCreateNewOptionPressed(){

        }

        @Override
        public void setLayout(int viewType,int position, Option option){
            this.position = position;
            this.option = option;
            if (viewType == OptionListPresenter.VIEW_TYPE_ADD_OPTION) {
                Log.d("ALERT" , "TRYING TO CREATE ADD OPTION");
                relNormal.setVisibility(View.INVISIBLE);
                relAdd.setVisibility(View.VISIBLE);
                imgDelete.setVisibility(View.GONE);
                edtOptionTitle.setVisibility(View.GONE);

            } else if (viewType == OptionListPresenter.VIEW_TYPE_NORMAL_OPTION) {
                Log.d("ALERT" , "TRYING TO CREATE NORMAL OPTION");
                relNormal.setVisibility(View.VISIBLE);
                relAdd.setVisibility(View.INVISIBLE);
                imgDelete.setVisibility(View.VISIBLE);
               // optionNumber.setText(Integer.toString(getAdapterPosition() + 1));
                edtOptionTitle.setVisibility(View.VISIBLE);
            edtOptionTitle.removeTextChangedListener(optionEditTextListener);
            edtOptionTitle.setText(option.getTitle());
            if (optionEditTextListener == null) {
                optionEditTextListener = new optionEditTextListener();
            }
            edtOptionTitle.addTextChangedListener(optionEditTextListener);


            }
        }

        @OnClick(R.id.addNewOption)
        public void addOption(){

            if (listener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Log.d("POSITION CLICKED", String.valueOf(position));
                    listener.addNewOption(itemView, position);
                }
            }

        }

        @OnClick(R.id.imgDeleteOption)
        public void deleteOptionClicked(){
            if (listener != null) {
                int position = getAdapterPosition();
               if (position != RecyclerView.NO_POSITION) {
                    Log.d("POSITION CLICKED", String.valueOf(position));
                    edtOptionTitle.setText(null);
                    listener.deleteOption(itemView, position);

                }
            }
        }


        private final class optionEditTextListener implements TextWatcher {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d("BEFORE TEXT CHANGE", s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//            EventBus.getDefault().post(new EventBusManager
//                    .OptionControlEvent(option.getId(), s.toString()
//                    , EventBusManager.OptionControlEvent.OPTION_INPUT_TEXT, option.getCode()));
                Log.d("ON TEXT CHANGE", s.toString() + position);


            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("AFTER TEXT CHANGE", s.toString());
                listener.setOptionTitle(s.toString(),position);
            }

//            public void setPositionClicked(int position){
//                this.positionClicked = position;
//
//            }

        }


    }


}
