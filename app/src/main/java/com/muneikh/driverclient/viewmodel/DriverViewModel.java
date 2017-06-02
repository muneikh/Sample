package com.muneikh.driverclient.viewmodel;

import android.app.Application;

import com.muneikh.driverclient.DriverApp;
import com.muneikh.driverclient.model.PhoneRequestModel;
import com.muneikh.driverclient.model.RequestTokenModel;
import com.muneikh.driverclient.model.ResponseModel;
import com.muneikh.driverclient.model.RepositoryModel;
import com.muneikh.driverclient.service.GithubApi;

import java.util.Observable;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.Retrofit;
import timber.log.Timber;

public class DriverViewModel extends Observable {

    @Inject
    Retrofit retrofit;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Response<ResponseModel> response;

    public DriverViewModel(Application application) {
        ((DriverApp) application).getNetComponent().inject(this);
    }

    public void requestPhoneVerficiation(String phoneNumber) {
        PhoneRequestModel phoneRequestModel = new PhoneRequestModel(phoneNumber);
        retrofit.create(GithubApi.class)
                .requestPhoneVerification(phoneRequestModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    processResponse(response);
                }, throwable -> {
                    Timber.e("executeApi: " + throwable.getLocalizedMessage());
                }, () -> {
                    Timber.d("executeApi: operation completed");
                });
    }

    private void processResponse(Response<ResponseModel> response) {
        Timber.d("executeApi: " + response.code());
        Timber.d("executeApi: " + response.body().toString());

        if (response.code() == 200 || response.code() == 201) {
            this.response = response;
            notifyChange();
        }
    }

    public void reset() {
        unSubscribeFromObservable();
        compositeDisposable = null;
    }

    private void notifyChange() {
        setChanged();
        notifyObservers();
    }

    public Response<ResponseModel> getReponseModel() {
        return response;
    }

    private void unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public void requestPhoneAuthentication(String pinCode, String phoneNumber) {
        Timber.d("pin code %s , phone # %s", pinCode, phoneNumber);

        RequestTokenModel requestTokenModel = new RequestTokenModel(phoneNumber, pinCode);
        retrofit.create(GithubApi.class)
                .requestPhoneAuthentication(requestTokenModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    processResponse(response);
                }, throwable -> {
                    Timber.e("executeApi: " + throwable.getLocalizedMessage());
                }, () -> {
                    Timber.d("executeApi: operation completed");
                });

    }
}
