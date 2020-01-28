package com.example.mymvvm.remote;

import com.example.mymvvm.remote.user.UsersList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {

    @GET("/mvvm.php")
    Call<UsersList> getUsersList(
            @Query("name") String name
    );
    @FormUrlEncoded
    @POST("/mvvm.php")
    Call<UsersList> getUsersListByPostRequest(
            @Field("name") String name
    );
}
