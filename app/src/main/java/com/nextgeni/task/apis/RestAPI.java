package com.nextgeni.task.apis;


import com.nextgeni.task.entities.Categories;
import com.nextgeni.task.entities.GenericResponse;
import com.nextgeni.task.entities.Products;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface RestAPI<T> {


    @GET("apps/jaldpay/api/getCategories")
    Call<GenericResponse<Categories>> getCategories(@Query("access_token") String token, @Query("language") String lang);

    @GET("apps/jaldpay/api/getProduct")
    Call<GenericResponse<Products>> getProduct(@Query("access_token") String token, @Query("categoryID") String id);
}
