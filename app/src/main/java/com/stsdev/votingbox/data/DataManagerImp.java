package com.stsdev.votingbox.data;

import android.util.Log;

import com.stsdev.votingbox.data.Model.Option;
import com.stsdev.votingbox.data.Model.User;
import com.stsdev.votingbox.data.Model.Vote;
import com.stsdev.votingbox.data.network.AppApiHelper;
import com.stsdev.votingbox.data.network.OptionsAPI;
import com.stsdev.votingbox.data.network.UserAPI;
import com.stsdev.votingbox.data.network.VotesAPI;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by stavros on 28/4/2018.
 */

public class DataManagerImp implements DataManager {

    public static final String TAG = DataManagerImp.class.getSimpleName();
    private ResponseHandler handler;

    public DataManagerImp() {
    }

    private final UserAPI service = AppApiHelper.getInstance();
    private final VotesAPI votesService = AppApiHelper.getVotesApi();
    private final OptionsAPI optionsService = AppApiHelper.getOptionsApi();

    public Observable<String> getCreateUserObservable(User user){
        return service.createUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<String> getCreateVoteObservable(Vote vote){
        return votesService.addVewVote(vote)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public void getUser( final String email , final String pass){

        Call<User> call  =  service.getUser(email,pass);
        call.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
               // Log.d (TAG , "Found the user with email" + response.body().getName());
                handler.getUserResponse(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d (TAG , "User with email" + email + "not found");

            }
        });

    }

//    public Observable<Map<String, Vote>> getObservable(){
//        return votesService.getAllVotes()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
    public Observable<List<Vote>> getListObservable(){
        return votesService.getAllListVotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<Option>> getObservable(int votecode){
        return optionsService.getAllOptions(votecode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<Vote>> getFavsObservable(int usercode){
        return votesService.getAllFavs(usercode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

//    public Observable<Map<String, Vote>> getCreatedObservable(){
//        return votesService.getAllCreated()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }

    public interface ResponseHandler {

        public void getUserResponse(User user);


    }

    public DataManagerImp(ResponseHandler handler) {
        this.handler = handler;
    }
}
