package com.nooblabs.srawa.restapidemo.service.tasks;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.nooblabs.srawa.restapidemo.service.database.RequestDataDbHelper;
import com.nooblabs.srawa.restapidemo.service.database.RequestDataHelper;
import com.nooblabs.srawa.restapidemo.service.model.RequestAPI;

import java.lang.ref.WeakReference;

public class SaveRequestAsyncTask extends AsyncTask<RequestAPI, Void, Void> {


    private WeakReference<Context> app;

    public SaveRequestAsyncTask(Context context) {
        app = new WeakReference<>(context);
    }

    @Override
    protected Void doInBackground(RequestAPI... requestAPIS) {
        if (requestAPIS == null || requestAPIS.length < 1) {
            return null;
        }

        final RequestAPI data = requestAPIS[0];
        if (data == null) {
            return null;
        }

        Context context = app.get();
        if (context != null) {
            RequestDataDbHelper helper = new RequestDataDbHelper(context);
            SQLiteDatabase db = helper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(RequestDataHelper.RequestEntry.COLUMN_FIRST_NAME, data.firstName);
            values.put(RequestDataHelper.RequestEntry.COLUMN_LAST_NAME, data.lastName);
            values.put(RequestDataHelper.RequestEntry.COLUMN_PHONE, data.phoneNumber);
            values.put(RequestDataHelper.RequestEntry.COLUMN_ADDRESS, data.address);
            values.put(RequestDataHelper.RequestEntry.COLUMN_RESTAURANT_NAME, data.restaurantName);
            values.put(RequestDataHelper.RequestEntry.COLUMN_REQUEST_BY, data.requestBy);

            long newRowId = db.insert(RequestDataHelper.RequestEntry.TABLE_NAME, null, values);
            Log.d("xyz", "Request: " + data.toString() + " dbId: " + newRowId);
        }

        return null;
    }
}
