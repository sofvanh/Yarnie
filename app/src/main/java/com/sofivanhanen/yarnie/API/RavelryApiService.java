package com.sofivanhanen.yarnie.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by sofvanh on 24/01/18.
 */

public interface RavelryApiService {

    public static final int MAX_NUMBER_OF_PATTERNS = 50;
    public static final int NUMBER_OF_COLORS = 1;

    // This query "/patterns/search.json" can be used just like the Ravelry pattern search,
    // adding in any parameters. This specific call defines number of colors, number of patterns wanted,
    // and the yarn weight required.
    @GET("/patterns/search.json")
    Call<PatternsSearchResult> getPatterns(@Query("colors") int numberOfColors,
                                           @Query("page_size") int numberOfPatterns,
                                           @Query("weight") String yarnWeight,
                                           @Header("Authorization") String authHeader);

    // This query returns detailed Pattern objects.
    // ids should be in form "1 2 3 4", here it is parsed to "1+2+3+4"
    @GET("/patterns.json")
    Call<FullPatternsResult> getPatternsById(@Query("ids") String ids,
                                             @Header("Authorization") String authHeader);

}
