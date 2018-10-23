package com.stsdev.votingbox.data.network;

import com.stsdev.votingbox.data.Model.Promotion;
import com.stsdev.votingbox.data.Model.User;
import com.stsdev.votingbox.data.Model.Vote;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    @GET("vote/getVotes.php")
    Observable<List<Vote>> getAllListVotes(@Query("usercode" )int usercode);

    @GET("vote/getFavorites.php")
    Observable<List<Vote>> getAllFavs(@Query("usercode")int usercode);

    @PUT("vote/addVote.php")
    Observable<String> addVewVote(@Body Vote newvote);

    @GET("vote/getParticipated.php")
    Observable<List<Vote>> getAllParticipated(@Query("usercode")int usercode);

    @POST("vote/setParticipated.php")
    Observable<String> addParticipated(@Body Promotion participatedInfo);

    @GET("vote/isparticipated.php")
    Observable<String> isParticipated(@Query("usercode")int usercode ,
                       @Query("votecode")int votecode);

    @GET("vote/isfavorite.php")
    Observable<String> isFavourite(@Query("usercode")int usercode ,
                                      @Query("votecode")int votecode);

    @GET("vote/updateFav.php")
    Observable<String> updateFavs(@Query("usercode")int usercode ,
                                  @Query("votecode")int votecode);


}
