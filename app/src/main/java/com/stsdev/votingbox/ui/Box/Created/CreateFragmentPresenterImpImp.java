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



    public void RetrieveCreatedFromServer(int usercode){
        getView().ShowLoading();
        datamanager.getParticipatedObservable(usercode).subscribeWith(getObserver());
        //votes.clear();
        //favAdapter.notifyDataSetChanged();
        //getView().HideLoading();
    }

    private DisposableObserver<List<Vote>> getObserver(){
        return new DisposableObserver<List<Vote>>() {

            @Override
            public void onNext(@NonNull List<Vote> movieResponse) {
                //votes = new ArrayList<>(movieResponse.values());
                votes.addAll(movieResponse);
                Log.d("TEST OF RXJAVA","OnNext"+votes.size());
                // mvi.displayMovies(movieResponse);
                if(votes.size() == 0){
                    getView().onError("No results found");
                }


            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("ERROR","Error"+e);
                e.printStackTrace();
                getView().onError("No results found");
                getView().HideLoading();
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
