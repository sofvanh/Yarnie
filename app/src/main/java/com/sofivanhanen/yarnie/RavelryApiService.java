package com.sofivanhanen.yarnie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by sofvanh on 24/01/18.
 */

public interface RavelryApiService {

    // This query "/patterns/search.json" can be used just like the Ravelry pattern search,
    // adding in any parameters. This specific call only uses the search word.
    @GET("/patterns/search.json")
    Call<PatternsSearchResult> getPatterns(@Query("query") String searchWord, @Header("Authorization") String authHeader);

    // TODO: Add calls for queries with different parameters

}
