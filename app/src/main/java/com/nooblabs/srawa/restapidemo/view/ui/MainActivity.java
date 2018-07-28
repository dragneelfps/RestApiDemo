package com.nooblabs.srawa.restapidemo.view.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.nooblabs.srawa.restapidemo.R;
import com.nooblabs.srawa.restapidemo.service.model.RequestAPI;
import com.nooblabs.srawa.restapidemo.service.model.ResponseAPI;
import com.nooblabs.srawa.restapidemo.service.tasks.SaveRequestAsyncTask;
import com.nooblabs.srawa.restapidemo.viewmodel.MainViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MainViewModel viewModel = null;
    private EditText fnameField;
    private EditText lnameField;
    private EditText phoneField;
    private EditText addressField;
    private EditText restaurantNameField;
    private Spinner requestByField;

    private ArrayList<String> requestByData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        Button submitBtn = findViewById(R.id.submit_btn);
        submitBtn.setOnClickListener(this);

        init();

    }

    void init() {
        fnameField = findViewById(R.id.fname_field);
        lnameField = findViewById(R.id.lname_field);
        phoneField = findViewById(R.id.phone_field);
        addressField = findViewById(R.id.address_field);
        restaurantNameField = findViewById(R.id.restaurant_field);
        requestByField = findViewById(R.id.request_by_field);

        requestByData = new ArrayList<>();
        requestByData.add("Owner");
        requestByData.add("Manager");
        requestByData.add("Other");

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, requestByData);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        requestByField.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        boolean isValid = true;
        String fname = fnameField.getText().toString();
        if (isInvalid(fname)) {
            fnameField.setError("Enter Valid First Name");
            isValid = false;
        }
        String lname = lnameField.getText().toString();
        if (isInvalid(lname)) {
            lnameField.setError("Enter Valid Last Name");
            isValid = false;
        }
        String phone = phoneField.getText().toString();
        if (!isValidPhone(phone)) {
            phoneField.setError("Enter Valid Phone Number");
            isValid = false;
        }
        String address = addressField.getText().toString();
        if (isInvalid(address)) {
            addressField.setError("Enter Valid Address");
            isValid = false;
        }
        String restaurantName = restaurantNameField.getText().toString();
        if (isInvalid(fname)) {
            restaurantNameField.setError("Enter Valid Restaurant Address");
            isValid = false;
        }
        if (!isValid)
            return;
        int requestBy = requestByField.getSelectedItemPosition();
        final RequestAPI requestData = new RequestAPI(fname, lname, phone, address, restaurantName, requestBy);
        viewModel.sumbitRequest(requestData).observe(this, new Observer<ResponseAPI>() {
            @Override
            public void onChanged(@Nullable ResponseAPI responseAPI) {
                if (responseAPI != null) {

                    SaveRequestAsyncTask saveTask = new SaveRequestAsyncTask(getBaseContext());
                    saveTask.execute(requestData);

                    displayResponse(responseAPI);

                    Intent intent = new Intent(getBaseContext(), ResultActivity.class);
                    intent.putExtra("response", responseAPI);
                    Toast.makeText(getBaseContext(), "Submitting Request", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                } else {
                    Toast.makeText(getBaseContext(), "ERROR", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private boolean isInvalid(String value) {
        return value == null || value.length() <= 0;
    }

    private boolean isValidPhone(String phoneValue) {
        return phoneValue != null && phoneValue.length() == 10;
    }

    private void displayResponse(ResponseAPI responseData) {
        Log.d("xyz", responseData.toString());
    }
}
