package com.muneikh.driverclient;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.muneikh.driverclient.model.PhoneRequestModel;
import com.muneikh.driverclient.service.GithubApi;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.observers.TestObserver;
import retrofit2.Retrofit;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;
import timber.log.Timber;

import static org.junit.Assert.assertEquals;

public class GihubApiTest {

    private final NetworkBehavior behavior = NetworkBehavior.create();
    private final TestObserver testObserver = TestObserver.create();
    private GithubApi githubApi;

    @Before
    public void setUp() throws Exception {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://triebwerk.herokuapp.com").build();

        MockRetrofit mockRetrofit = new MockRetrofit.Builder(retrofit)
                .networkBehavior(behavior).build();

        final BehaviorDelegate<GithubApi> delegate = mockRetrofit.create(GithubApi.class);
        githubApi = new GithubApiMock(delegate);
    }

    @Test
    public void testSuccessResponse() throws Exception {
        givenNetworkFailurePercentIs(0);
        PhoneRequestModel phoneRequestModel = new PhoneRequestModel("12345");
        githubApi.requestPhoneVerification(phoneRequestModel)
                .subscribe(response -> {
                    assertEquals(response.isSuccessful(), true);
                }, throwable -> {
                    System.out.print("error: " + throwable.getLocalizedMessage());
                }, () -> {
                    Timber.d("operation completed");
                });
    }

    @Test
    public void testPhoneVerificationRequest() throws Exception {
        givenNetworkFailurePercentIs(0);
        PhoneRequestModel phoneRequestModel = new PhoneRequestModel("12345");
        githubApi.requestPhoneVerification(phoneRequestModel)
                .subscribe(response -> {
                    assertEquals("driver_phone_verification_request", response.body().data.type);
                }, throwable -> {
                    System.out.print("error: " + throwable.getLocalizedMessage());
                }, () -> {
                    Timber.d("operation completed");
                });
    }

//    @Test
//    public void testFailureResponse() throws Exception {
//        givenNetworkFailurePercentIs(100);
//        githubApi.getRepositoryList("google", 1, 10).subscribe(testObserver);
//        testObserver.assertNoValues();
//        testObserver.assertError(IOException.class);
//    }
//
//    @Test
//    public void testMaxRepoCount() throws Exception {
//        givenNetworkFailurePercentIs(0);
//        githubApi.getRepositoryList("google", 1, 10).subscribe(response ->
//                assertEquals(10, response.body().size()));
//    }

    private void givenNetworkFailurePercentIs(int failurePercent) {
        behavior.setDelay(0, TimeUnit.MILLISECONDS);
        behavior.setVariancePercent(0);
        behavior.setFailurePercent(failurePercent);
    }
}