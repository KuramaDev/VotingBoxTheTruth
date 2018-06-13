package com.stsdev.votingbox.ui.Box.Created;

import android.support.annotation.NonNull;
import android.util.Log;

import com.stsdev.votingbox.data.DataManagerImp;
import com.stsdev.votingbox.data.Model.Vote;
import com.stsdev.votingbox.ui.Base.BasePresenterImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by stavros on 23/5/2018.
 */

public class CreateFragmentPresenterImpImp<V extends CreateFragmentView> extends BasePresenterImp<V> implements CreateFragmentPresenter<V> {

    private List<Vote> votes;
    DataManagerImp datamanager;
    CreatedAdapter favAdapter;


    public CreateFragmentPresenterImpImp() {
        datamanager = new DataManagerImp();
        votes = new ArrayList<>();
        favAdapter = new CreatedAdapter(votes);
    }



    public void RetrieveCreatedFromServer(){
        getView().ShowLoading();
        //datamanager.getParticipatedObservable().subscribeWith(getObserver());
        votes.clear();
        favAdapter.notifyDataSetChanged();
        getView().HideLoading();
    }

    private DisposableObserver<Map<String, Vote>> getObserver(){
        return new DisposableObserver<Map<String, Vote>>() {

            @Override
            public void onNext(@NonNull Map<String, Vote> movieResponse) {
                //votes = new ArrayList<>(movieResponse.values());
                votes.addAll(movieResponse.values());
                Log.d("TEST OF RXJAVA","OnNext"+votes.size());
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
                favAdapter.notifyDataSetChanged();
                getView().HideLoading();
                //mvi.hideProgressBar();
            }
        };
    }


    public CreatedAdapter getAdapter(){

        return this.favAdapter;
    }

}
