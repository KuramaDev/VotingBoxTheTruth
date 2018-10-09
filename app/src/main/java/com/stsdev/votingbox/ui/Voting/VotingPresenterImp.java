package com.stsdev.votingbox.ui.Voting;

import android.support.annotation.NonNull;
import android.util.Log;

import com.stsdev.votingbox.data.DataManagerImp;
import com.stsdev.votingbox.data.Model.Option;
import com.stsdev.votingbox.data.Model.Promotion;
import com.stsdev.votingbox.data.Model.User;
import com.stsdev.votingbox.data.Model.Vote;
import com.stsdev.votingbox.ui.Base.BasePresenterImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by stavros on 19/5/2018.
 */

public class VotingPresenterImp<V extends VoteDetailsContract> extends BasePresenterImp<V> implements VotingPresenterContract<V>  {

    VotingAdapter adapter;
    List<Option> options;
    Vote transferedVote;
    DataManagerImp datamanager;
    Option selectedOption;

    public VotingPresenterImp(Vote vote) {
        this.transferedVote = vote;
        datamanager = new DataManagerImp();
        options = new ArrayList<>();
        adapter = new VotingAdapter(options,transferedVote.getPollCount());
    }

    public void InitInfo(){
        getView().setLayout(transferedVote);
    }

    public void setAdapter(VotingAdapter adapter) {
        this.adapter = adapter;
    }

    public VotingAdapter getAdapter() {
        return adapter;
    }

    @Override
    public void getOptionsFromServer(){
        getView().ShowLoading();
        int votecode = Integer.valueOf(transferedVote.getVoteCode());
        datamanager.getObservable(votecode).subscribeWith(getObserver());
    }

    private DisposableObserver<List<Option>> getObserver(){
        return new DisposableObserver<List<Option>>() {

            @Override
            public void onNext(@NonNull List<Option> movieResponse) {
                //votes = new ArrayList<>(movieResponse.values());
                options.addAll(movieResponse);
                Log.d("TEST OF RXJAVA","OnNext"+ options.size());
                // mvi.displayMovies(movieResponse);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("ERROR","Error"+e);
                e.printStackTrace();
                //mvi.displayError("Error fetching Movie Data");
            }

            @Override
            public void onComplete() {
                Log.d("TEST OF RXJAVA","Completed");
               adapter.notifyDataSetChanged();
               getView().HideLoading();
                //mvi.hideProgressBar();
            }
        };
    }

    public void InitRecView(){

        getOptionsFromServer();

    }

    @Override
    public void initListenerInAdapter(){
        adapter.setOnItemClickListener(new VotingAdapter.OnItemSelectedListenerVoting(){
            @Override
            public  void itemSelected(int position){

                Option transferOption;
                Log.d("Item Clicked", String.valueOf(position));
                transferOption= options.get(position);
                Log.d("OptionCode", String.valueOf(transferOption.getOptionCode()));
                selectedOption = transferOption;

            }
            @Override
            public  void unselect(int position){

                Log.d("Previous Item", String.valueOf(position));


            }

        });
    }

    public void sendPromotion(){
        Promotion promotion = new Promotion();
        User curUser = getView().retrieveUserInfo();
        promotion.setVoteCode(Integer.valueOf(transferedVote.getVoteCode()));
        promotion.setUserCode(curUser.getUsercode());
        promotion.setOptionCode(selectedOption.getOptionCode());
        Log.d("UserCode to send", String.valueOf(promotion.getUserCode()));
        Log.d("VoteCode to send", String.valueOf(promotion.getVoteCode()));
        Log.d("OptionCode to send", String.valueOf(promotion.getOptionCode()));
        datamanager.addParticipatedObservable(promotion).subscribeWith(getObserverPatricipated());
    }

    private DisposableObserver<String> getObserverPatricipated(){
        return new DisposableObserver<String>() {

            @Override
            public void onNext(@NonNull String movieResponse) {

                Log.d("TEST OF RXJAVA","OnNext"+movieResponse);
                // mvi.displayMovies(movieResponse);
                if(movieResponse.equals("s")){

                }
                else{
                    getView().onError(movieResponse);
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("ERROR","Error"+e);
                e.printStackTrace();
                //mvi.displayError("Error fetching Movie Data");
            }

            @Override
            public void onComplete() {
                Log.d("TEST OF RXJAVA","Completed");
                getView().HideLoading();

                //mvi.hideProgressBar();
            }
        };
    }

    @Override
    public void CheckParticipation(){
        User curUser = getView().retrieveUserInfo();
        datamanager.isParticipatedObservable(curUser.getUsercode(), Integer.valueOf(transferedVote.getVoteCode())).subscribeWith(isObserverPatricipated());
    }

    private DisposableObserver<String> isObserverPatricipated(){
        return new DisposableObserver<String>() {

            @Override
            public void onNext(@NonNull String movieResponse) {

                Log.d("TEST OF RXJAVA","OnNext"+movieResponse);
                // mvi.displayMovies(movieResponse);
                if(movieResponse.equals("t")){
                    Log.d("PARTICIPATED MOTH","FCKING TRUE");
                    getView().SetParticipatedLayout();
                }

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("ERROR","Error"+e);
                e.printStackTrace();
                //mvi.displayError("Error fetching Movie Data");
            }

            @Override
            public void onComplete() {
                Log.d("TEST OF RXJAVA","Completed");
                getView().HideLoading();

                //mvi.hideProgressBar();
            }
        };
    }

}
