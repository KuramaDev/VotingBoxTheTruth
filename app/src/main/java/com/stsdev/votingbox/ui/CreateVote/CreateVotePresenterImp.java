package com.stsdev.votingbox.ui.CreateVote;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.DatePicker;

import com.stsdev.votingbox.R;
import com.stsdev.votingbox.Utils.CommonUtils;
import com.stsdev.votingbox.data.DataManagerImp;
import com.stsdev.votingbox.data.Model.Option;
import com.stsdev.votingbox.data.Model.User;
import com.stsdev.votingbox.data.Model.Vote;
import com.stsdev.votingbox.ui.Base.BasePresenter;
import com.stsdev.votingbox.ui.Base.BasePresenterImp;
import com.stsdev.votingbox.ui.CreateVote.OptionsRecycler.OptionListPresenter;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by stavros on 17/4/2018.
 */

public class CreateVotePresenterImp<V extends CreateVoteView> extends BasePresenterImp<V> implements CreateVotePresenter<V> {


    DataManagerImp datamanager;
    Date sendDate;
    String sendingDate;

    private CreateVoteView contract;

    public CreateVotePresenterImp(){
        datamanager = new DataManagerImp();
    }



    public void createNewVote(String question, List<Option> options,Calendar endDate){

        if(question.isEmpty() || question.length()==0){
            getView().onError(R.string.empty_vote_question);
            return ;
        }
        if(options.isEmpty() || options.size()<2){
            getView().onError(R.string.least_option_number);
            return ;
        }
        if(endDate==null){
            getView().onError("You need to set an ending date");
            return;
        }
        else{
             sendDate = new Date(endDate.getTime().getTime());
             sendingDate = CommonUtils.ConvertDateToJSONString(sendDate);
        }
        for(Option item : options){
            if(item.getTitle()==null || item.getTitle().isEmpty() || item.getTitle().length()==0){
                getView().onError("All your options need to have a title");
                return ;
            }
        }

        Vote newVote = new Vote();
        newVote.setQuestion(question);
        newVote.setEndingDate(sendingDate);
        //newVote.set_endingDate(sendDate);
        newVote.setOptions(options);
        User cur_user = getView().retrieveUserInfo();


        newVote.setAuthorName(cur_user.getName());
        newVote.setAuthorCode(cur_user.getUsercode());

        getView().ShowLoading();
        datamanager.getCreateVoteObservable(newVote).subscribeWith(getObserver());
    }

    private DisposableObserver<String> getObserver(){
        return new DisposableObserver<String>() {

            @Override
            public void onNext(@NonNull String movieResponse) {
                //votes = new ArrayList<>(movieResponse.values());
                //votes.addAll(movieResponse.values());

                Log.d("VOTING RESPONSE","OnNext"+ movieResponse);
                // mvi.displayMovies(movieResponse);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("ERROR main","Error"+e);
                e.printStackTrace();
                //mvi.displayError("Error fetching Movie Data");
            }

            @Override
            public void onComplete() {
                Log.d("TEST OF RXJAVA","Completed");

                getView().HideLoading();
                getView().closeActivity();
                //mvi.hideProgressBar();
            }
        };
    }

}
