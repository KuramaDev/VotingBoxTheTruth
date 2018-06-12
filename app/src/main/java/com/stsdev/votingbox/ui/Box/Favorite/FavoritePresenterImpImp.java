package com.stsdev.votingbox.ui.Box.Favorite;

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

public class FavoritePresenterImpImp<V extends FavoriteView> extends BasePresenterImp<V> implements FavoritePresenter<V> {

    private List<Vote> votes;
    DataManagerImp datamanager;
    FavoriteAdapter favAdapter;


    public FavoritePresenterImpImp() {
        datamanager = new DataManagerImp();
        votes = new ArrayList<>();
        favAdapter = new FavoriteAdapter(votes);
    }



    public void RetrieveFavsFromServer(int usercode){
        getView().ShowLoading();
        datamanager.getFavsObservable(usercode).subscribeWith(getObserver());
    }

    private DisposableObserver<List<Vote>> getObserver(){
        return new DisposableObserver<List<Vote>>() {

            @Override
            public void onNext(@NonNull List<Vote> movieResponse) {
                //votes = new ArrayList<>(movieResponse.values());
                votes.addAll(movieResponse);
                Log.d("TEST OF RXJAVA fragment","OnNext"+votes.size());
                // mvi.displayMovies(movieResponse);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("ERROR fragment","Error"+e);
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


    public FavoriteAdapter getAdapter(){

        return this.favAdapter;
    }
}
