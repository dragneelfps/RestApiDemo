package com.nooblabs.srawa.restapidemo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.nooblabs.srawa.restapidemo.service.model.RequestAPI;
import com.nooblabs.srawa.restapidemo.service.model.ResponseAPI;
import com.nooblabs.srawa.restapidemo.service.repository.APIRepository;

public class MainViewModel extends AndroidViewModel {

    public MainViewModel(Application application) {
        super(application);
    }

    public LiveData<ResponseAPI> sumbitRequest(RequestAPI requestData) {
        LiveData<ResponseAPI> responseObservable = APIRepository.getRepository().submitRequestData(requestData);
        return responseObservable;
    }
}
