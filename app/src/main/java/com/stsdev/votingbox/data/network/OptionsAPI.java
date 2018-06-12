package com.stsdev.votingbox.data.network;

import com.stsdev.votingbox.data.Model.Option;
import com.stsdev.votingbox.data.Model.Vote;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by stavros on 21/5/2018.
 */

public interface OptionsAPI {

    @GET("option/getOptions.php")
    Observable<List<Option>> getAllOptions(@Query("votecode") int votecode);
}
