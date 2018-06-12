package com.stsdev.votingbox.data.network;

import com.stsdev.votingbox.data.Model.User;
import com.stsdev.votingbox.data.Model.Vote;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by stavros on 26/4/2018.
 */

public interface UserAPI {

    //@PUT("/user/{email}.json")
   // Call<User> createUser(@Path("email") String email,@Body User user);


//   @GET("/user/{email}.json")
//    Call<User> getUser(@Path("email")String email );

    @GET("user/login.php")
    Call<User> getUser(@Query("email")String email ,
                       @Query("pass")String pass);

//    @PUT("user/register.php")
//    Call<String> createUser(@Body User user);

    @PUT("user/register.php")
    Observable<String> createUser(@Body User user);

}
