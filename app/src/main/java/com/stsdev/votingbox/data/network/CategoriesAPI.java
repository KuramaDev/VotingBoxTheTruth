package com.stsdev.votingbox.data.network;

import com.stsdev.votingbox.data.Model.Category;
import com.stsdev.votingbox.data.Model.Vote;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by stavros on 17/6/2018.
 */

public interface CategoriesAPI {

    @GET("category/read.php")
    Observable<List<Category>> getCategories();

}
