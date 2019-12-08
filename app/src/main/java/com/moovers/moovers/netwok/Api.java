package com.moovers.moovers.netwok;

import com.moovers.moovers.model.ServiceProviders;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("https://sheetsu.com/apis/v1.0su/e5cc1630393f/search")
    Call<List<ServiceProviders>> getServiceProviders(@Query("category") String cat);


}
