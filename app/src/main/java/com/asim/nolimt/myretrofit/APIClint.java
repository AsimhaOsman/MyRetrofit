package com.asim.nolimt.myretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by NO Limt on 24.07.2017.
 */

public interface APIClint {

    @GET("/feeds/flowers.json")
    Call<List<MyFlawers>> getFlowers(@Query("q") String result);

}
