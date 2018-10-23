package com.stsdev.votingbox.ui.main;

import android.support.annotation.NonNull;
import android.util.Log;

import com.stsdev.votingbox.data.DataManagerImp;
import com.stsdev.votingbox.data.Model.User;
import com.stsdev.votingbox.data.Model.Vote;
import com.stsdev.votingbox.ui.Base.BasePresenterImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by stavros on 6/5/2018.
 */

public class MainPresenterImp<V extends MainViewContract> extends BasePresenterImp<V> implements MainPresenterInterface<V>{


    private DataManagerImp datamanager;
    private MainAdapter adapter;

    private List<Vote> votes;

    // Empty Constructor
    public MainPresenterImp() {
        datamanager = new DataManagerImp();
        votes = new ArrayList<>();
        adapter = new MainAdapter(votes);

    }

    public void RetrieveVotesFromServer(int usercode){
        getView().ShowLoading();
        datamanager.getListObservable(usercode).subscribeWith(getObserver());
    }

    private DisposableObserver<List<Vote>> getObserver(){
        return new DisposableObserver<List<Vote>>() {

            @Override
            public void onNext(@NonNull List<Vote> movieResponse) {
                //votes = new ArrayList<>(movieResponse.values());
                //votes.addAll(movieResponse.values());
                votes.addAll(movieResponse);
                Log.d("TEST OF RXJAVA main","OnNext"+votes.size());
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
                adapter.notifyDataSetChanged();
                getView().HideLoading();
                //mvi.hideProgressBar();
            }
        };
    }

    @Override
    public MainAdapter getAdapter(){

        return this.adapter;
    }

    @Override
    public void initListenerInAdapter(){
        adapter.setOnItemClickListener(new MainAdapter.OnItemSelectedListener(){
            @Override
            public  void itemSelected(int position){
                Vote transferVote;
                Log.d("Item Clicked", String.valueOf(position));
                transferVote=  votes.get(position);
                getView().OpenVotingActivity(transferVote);
            }
        });
    }

    public void InitInfo(){
       User transferedUser = getView().getExtras();

        getView().setupDrawer(transferedUser);
    }



}
