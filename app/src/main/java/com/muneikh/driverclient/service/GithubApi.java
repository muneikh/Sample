package com.muneikh.driverclient.service;

import com.muneikh.driverclient.model.PhoneRequestModel;
import com.muneikh.driverclient.model.ResponseModel;
import com.muneikh.driverclient.model.RepositoryModel;
import com.muneikh.driverclient.model.RequestTokenModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubApi {

    @GET("/users/{username}/repos")
    Observable<Response<List<RepositoryModel.Repository>>> getRepositoryList(
            @Path("username") String username,
            @Query("page") int pageNumber,
            @Query("per_page") int repoPerPage);

    @Headers("X-Api-Version: 1")
    @POST("/api/driver/phone_verification_requests")
    Observable<Response<ResponseModel>>
    requestPhoneVerification(@Body PhoneRequestModel phoneRequestModel);

    @Headers("X-Api-Version: 1")
    @POST("/api/driver/request_tokens")
    Observable<Response<ResponseModel>>
    requestPhoneAuthentication(@Body RequestTokenModel requestTokenModel);
}
