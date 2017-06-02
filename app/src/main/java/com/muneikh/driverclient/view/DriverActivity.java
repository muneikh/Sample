package com.muneikh.driverclient.view;

import android.view.View;
import android.widget.EditText;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.muneikh.driverclient.R;
import com.muneikh.driverclient.model.ResponseModel;
import com.muneikh.driverclient.viewmodel.DriverViewModel;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;
import timber.log.Timber;

public class DriverActivity extends AppCompatActivity implements Observer {

    @BindView(R.id.editTextDriverPhoneNumber)
    EditText phoneEditBox;

    private DriverViewModel viewModel;
    //49160299039

    private boolean isPinCodeVerificationState = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        viewModel = new DriverViewModel(getApplication());
        setupObserver(viewModel);

//        viewModel.executeApi();
    }

    public void submitRequest(View view) {
        if (isPinCodeVerificationState == false) {
            String phoneNumber = phoneEditBox.getText().toString();
            Timber.d(phoneNumber);
            viewModel.requestPhoneVerficiation(phoneNumber);
        } else {
            String pinCode = phoneEditBox.getText().toString();
            String phoneNumber = viewModel.getReponseModel().body().data.phone_number;
            viewModel.requestPhoneAuthentication(pinCode, phoneNumber);
        }
    }

    private void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    protected void onDestroy() {
        viewModel.reset();
        super.onDestroy();
    }

    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof DriverViewModel) {
            DriverViewModel viewModel = (DriverViewModel) observable;
            Timber.d("response: %s", viewModel.getReponseModel());

            Response<ResponseModel> response = viewModel.getReponseModel();
            if (response.body().data.type.equalsIgnoreCase("driver_phone_verification_request")) {
                // switch to pin code ui
                phoneEditBox.setHint(R.string.enter_pin_code);
                phoneEditBox.setText("");
                isPinCodeVerificationState = true;



            } else {
                // request auth token
            }

            //textView.setText("response : " + viewModel.getReponseModel().toJson());
        }
    }
}
