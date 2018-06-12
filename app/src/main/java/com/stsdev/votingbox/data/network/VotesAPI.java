package com.stsdev.votingbox.data.network;

import com.stsdev.votingbox.data.Model.User;
import com.stsdev.votingbox.data.Model.Vote;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by stavros on 16/5/2018.
 */

public interface VotesAPI {

//    @PUT("/votes/{votecode}.json")
//    Call<User> createVote(@Path("votecode") String votecode, @Body User user);

//   @GET("/Votes/.json")
//    Observable<Map<String, Vote>> getAllVotes();

//    @GET("/Created/.json")
//    Observable<Map<String, Vote>> getAllCreated();

    @GET("vote/read.php?records")
    Observable<List<Vote>> getAllListVotes();

    @GET("vote/getFavorites.php")
    Observable<List<Vote>> getAllFavs(@Query("usercode")int usercode);

    @PUT("vote/addVote.php")
    Observable<String> addVewVote(@Body Vote newvote);


}
