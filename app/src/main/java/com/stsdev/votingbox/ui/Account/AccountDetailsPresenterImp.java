package com.stsdev.votingbox.ui.Account;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Adapter;

import com.stsdev.votingbox.data.DataManagerImp;
import com.stsdev.votingbox.data.Model.Category;
import com.stsdev.votingbox.data.Model.Preferences;
import com.stsdev.votingbox.data.Model.User;
import com.stsdev.votingbox.data.Model.Vote;
import com.stsdev.votingbox.ui.Base.BasePresenter;
import com.stsdev.votingbox.ui.Base.BasePresenterImp;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by stavros on 14/6/2018.
 */

public class AccountDetailsPresenterImp<V extends AccountDetailsView> extends BasePresenterImp<V> implements AccountDetailsPresenter<V>{

    User currentUser;
    AccountAdapter adapter;
    private List<Category> catList;
    private DataManagerImp datamanager;

    public AccountAdapter getAdapter() {
        return adapter;
    }


    public AccountDetailsPresenterImp() {
        catList = new ArrayList<>();
        adapter = new AccountAdapter(catList);
        adapter.setItemCklickListener(new AccountAdapter.onCklickListener() {
            @Override
            public void updateChecked(int position) {
                String category =  catList.get(position).getCategory();
                Log.d("Clicked", catList.get(position).getCategory());
                currentUser = getView().getCurrentIntent().getParcelableExtra("CurrentUser");
                updatePreference(category,currentUser.getEmail());
            }
        });
        datamanager = new DataManagerImp();
    }

    public User checkExtras(){


        if(getView().getCurrentIntent().getParcelableExtra("CurrentUser") != null){
            currentUser = getView().getCurrentIntent().getParcelableExtra("CurrentUser");
            return currentUser;
        }
        else{
            getView().onError("sOMETHING UNEXPECTED OCCURED");
            return null;
        }

    }

    public void RetrieveCategoriesFromServer(){
        getView().ShowLoading();
        currentUser = getView().getCurrentIntent().getParcelableExtra("CurrentUser");
        datamanager.getCategoriesObservable(currentUser.getEmail()).subscribeWith(getObserver());
    }

    private DisposableObserver<List<Category>> getObserver(){
        return new DisposableObserver<List<Category>>() {

            @Override
            public void onNext(@NonNull List<Category> movieResponse) {
                //votes = new ArrayList<>(movieResponse.values());
                catList.addAll(movieResponse);
                Log.d("TEST OF RXJAVA fragment","OnNext"+catList.size());
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
                for (final Category category: catList
                     ) {

                    Log.d("CHECKED WORKED",String.valueOf(category.getChecked()));
                    
                }
                adapter.notifyDataSetChanged();
                getView().HideLoading();
                //mvi.hideProgressBar();
            }
        };
    }

    public void updatePreference(String category, String email){
        Preferences pref = new Preferences();
        pref.setCategory(category);
        pref.setEmail(email);
        datamanager.setPreference(pref).subscribeWith(getPreferencesObserver());
    }

    private DisposableObserver<String> getPreferencesObserver(){
        return new DisposableObserver<String>() {

            @Override
            public void onNext(@NonNull String movieResponse) {
                //votes = new ArrayList<>(movieResponse.values());

                Log.d("TEST OF RXJAVA fragment","OnNext"+ movieResponse);
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
               // adapter.notifyDataSetChanged();
                //getView().HideLoading();
                //mvi.hideProgressBar();
            }
        };
    }
}
