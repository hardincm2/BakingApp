package com.brassbeluga.bakingapp.task;

import android.os.AsyncTask;

import com.brassbeluga.bakingapp.util.NetworkUtil;

import java.net.URL;

import javax.inject.Inject;

/**
 * Async task to fetch the body response for an HTTP request.
 */
public class GetHttpResponseTask extends AsyncTask<URL, Void, String> {
    private HttpResponseListener listener;

    @Inject
    public GetHttpResponseTask(HttpResponseListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(URL... params) {
        URL requestUrl = params[0];
        return NetworkUtil.getResponseFromHttpUrl(requestUrl);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        listener.onHttpResponseFinished(result);
    }

    public interface HttpResponseListener {
        void onHttpResponseFinished(String response);
    }
}
