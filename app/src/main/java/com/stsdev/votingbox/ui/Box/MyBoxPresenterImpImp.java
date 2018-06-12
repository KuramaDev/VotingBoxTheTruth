package com.stsdev.votingbox.ui.Box;

import android.support.annotation.NonNull;
import android.util.Log;

import com.stsdev.votingbox.data.DataManagerImp;
import com.stsdev.votingbox.data.Model.Vote;
import com.stsdev.votingbox.ui.Base.BasePresenterImp;
import com.stsdev.votingbox.ui.Box.Favorite.FavoriteAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by stavros on 22/5/2018.
 */

public class MyBoxPresenterImpImp<V extends MyBoxView> extends BasePresenterImp<V> implements MyBoxPresnter<V> {

    private List<Vote> votes;
    DataManagerImp datamanager;
    FavoriteAdapter favAdapter;


    public MyBoxPresenterImpImp() {
        datamanager = new DataManagerImp();
        votes = new ArrayList<>();
        favAdapter = new FavoriteAdapter(votes);
    }



//    public void RetrieveFavsFromServer(){
//        getView().ShowLoading();
//        datamanager.getObservable().subscribeWith(getObserver());
//    }
//
//    private DisposableObserver<Map<String, Vote>> getObserver(){
//        return new DisposableObserver<Map<String, Vote>>() {
//
//            @Override
//            public void onNext(@NonNull Map<String, Vote> movieResponse) {
//                //votes = new ArrayList<>(movieResponse.values());
//                votes.addAll(movieResponse.values());
//                Log.d("TEST OF RXJAVA","OnNext"+votes.size());
//                // mvi.displayMovies(movieResponse);
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                Log.d("ERROR","Error"+e);
//                e.printStackTrace();
//                //mvi.displayError("Error fetching Movie Data");
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d("TEST OF RXJAVA","Completed");
//                favAdapter.notifyDataSetChanged();
//                getView().HideLoading();
//                //mvi.hideProgressBar();
//            }
//        };
//    }


    public FavoriteAdapter getAdapter(){

        return this.favAdapter;
    }
}
