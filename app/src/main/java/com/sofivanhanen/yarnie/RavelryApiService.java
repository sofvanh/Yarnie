package com.sofivanhanen.yarnie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by sofvanh on 24/01/18.
 */

public interface RavelryApiService {

    // TODO: What we really want is the /patterns/search.json query.
    // The call below can be used on specific patterns, whose id's we know.
    @GET("/patterns.json")
    Call<Object> getPatterns(@Query("ids") Integer id, @Header("Authorization") String authHeader);

}
