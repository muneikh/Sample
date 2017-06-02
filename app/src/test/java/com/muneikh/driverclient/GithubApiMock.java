package com.muneikh.driverclient;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.muneikh.driverclient.model.PhoneRequestModel;
import com.muneikh.driverclient.model.RepositoryModel;
import com.muneikh.driverclient.model.RequestTokenModel;
import com.muneikh.driverclient.model.ResponseModel;
import com.muneikh.driverclient.service.GithubApi;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.mock.BehaviorDelegate;

public class GithubApiMock implements GithubApi {

    private final BehaviorDelegate<GithubApi> delegate;


    public GithubApiMock(BehaviorDelegate<GithubApi> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Observable<Response<List<RepositoryModel.Repository>>> getRepositoryList(
            @Path("username") String username, @Query("page") int pageNumber,
            @Query("per_page") int repoPerPage) {

        return delegate.returningResponse("dummy").getRepositoryList(username, pageNumber,
                repoPerPage);
    }

    @Override
    public Observable<Response<ResponseModel>> requestPhoneVerification(PhoneRequestModel phoneRequestModel) {
        ResponseModel responseModel = ResponseModel.createSample();
        return delegate.returningResponse(responseModel).requestPhoneVerification(phoneRequestModel);
    }

    @Override
    public Observable<Response<ResponseModel>> requestPhoneAuthentication(RequestTokenModel requestTokenModel) {
        return null;
    }
}
