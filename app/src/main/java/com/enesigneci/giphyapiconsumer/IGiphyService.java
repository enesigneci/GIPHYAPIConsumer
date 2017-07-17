package com.enesigneci.giphyapiconsumer;

import com.enesigneci.giphyapiconsumer.Giphy.Datum;
import com.enesigneci.giphyapiconsumer.Giphy.GiphyService;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by enesignecirdc on 17/07/17.
 */

public interface IGiphyService {
    @GET()
    public List<Datum> getData(@Query("api_key") String apikey, @Query("q") String q);
    @GET("search")
    Call<GiphyService> getGifs(@Query("api_key") String apiKey, @Query("q") String searchTerm,@Query("limit") int limit);
}
