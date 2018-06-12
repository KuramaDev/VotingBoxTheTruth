package com.stsdev.votingbox.ui.CreateVote.fragments;


import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stsdev.votingbox.R;
import com.stsdev.votingbox.ui.Base.BaseFragment;
import com.stsdev.votingbox.ui.CreateVote.CreateVotePresenterImp;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateVoteSettingsFragment extends BaseFragment implements CreateSettingsView {

    private View rootView;
    private CreateSettingsPresenterImp presenter;
    Unbinder unbinder;
    Calendar endDate;


    @BindView(R.id.txtEndTimeDetail)
    TextView endingDate;

    public CreateVoteSettingsFragment() {
        // Required empty public constructor
    }

    public CreateVoteSettingsFragment newSettingsFragment(CreateSettingsPresenterImp presenter){

        return new CreateVoteSettingsFragment();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_create_vote_settings, container, false);
        unbinder = ButterKnife.bind(this,rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        presenter = new CreateSettingsPresenterImp();
        presenter.onAttach(this);




    }

    @OnClick(R.id.imgEndTime)
    public void EditDate(){
        Log.d("DATE BUTTON", "Pressed" );
        presenter.onEditDateClick(this.getContext());

    }

    @Override
    protected  void setUp(View view){

    }

    @Override
    public void setDate(Calendar date){
        //endingDate.setText(date);
        //String.valueOf(dayOfMonth) +"/"+ String.valueOf(monthOfYear+1)+"/"+String.valueOf(year)
        this.endDate = date;
        endingDate.setText(String.valueOf(date.get(Calendar.DAY_OF_MONTH)) +"/"
                            + String.valueOf(date.get(Calendar.MONTH)+1)+"/"
                            +String.valueOf(date.get(Calendar.YEAR)));


    }

    public Calendar getDate(){
        return this.endDate;
    }
}
