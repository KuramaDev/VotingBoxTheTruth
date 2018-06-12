package com.stsdev.votingbox.data.network;

import com.stsdev.votingbox.data.Model.Option;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by stavros on 27/4/2018.
 */

public class AppApiHelper  {

    //public static  final String BASE_URL = "https://thevotingbox.firebaseio.com/";
    public static  final String BASE_URL = "http://192.168.1.9/api/";
    public static Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(BASE_URL)
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                            .build();

    private static UserAPI userApi = null;
    private static VotesAPI votesApi = null;
    private static OptionsAPI optionsAPI = null;


    public static UserAPI getInstance(){
        if(userApi == null){
            userApi = retrofit.create(UserAPI.class);
        }
        return  userApi ;
    }

    public static VotesAPI getVotesApi(){
        if (votesApi == null){
            votesApi = retrofit.create(VotesAPI.class);
        }
        return votesApi;
    }

    public static OptionsAPI getOptionsApi(){
        if(optionsAPI == null){
            optionsAPI = retrofit.create(OptionsAPI.class);
        }
        return  optionsAPI ;
    }

}
