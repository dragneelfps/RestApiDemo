package com.nooblabs.srawa.restapidemo.view.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.nooblabs.srawa.restapidemo.R;
import com.nooblabs.srawa.restapidemo.service.model.ResponseAPI;

public class ResultActivity extends AppCompatActivity {

    private TextView rawResponse;
    private TextView success;
    private TextView error;
    private TextView status;
    private TextView message;
    private TextView phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        init();

        ResponseAPI response = (ResponseAPI) getIntent().getSerializableExtra("response");

        rawResponse.setText(response.toString());
        success.setText(response.success);
        error.setText(response.error);
        if (response.message != null && response.message.size() > 0) {

            final ResponseAPI.ResponseMessage responseMessage = response.message.get(0);
            status.setText(responseMessage.status);
            message.setText(responseMessage.message);
            phone.setText(responseMessage.phone);
        }

    }

    private void init() {
        rawResponse = findViewById(R.id.raw_response);
        success = findViewById(R.id.success);
        error = findViewById(R.id.error);
        status = findViewById(R.id.status);
        message = findViewById(R.id.message);
        phone = findViewById(R.id.phone);
    }
}
