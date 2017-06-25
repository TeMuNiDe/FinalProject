package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.temunide.jokeserver.myApi.MyApi;

import java.io.IOException;


/**
 * Created by varma on 24-06-2017.
 */

 class JokerClient extends AsyncTask<Void,Void,String>
{
    private OnTaskCompletedListener onTaskCompletedListener;
    private static MyApi myApiService = null;
    private Context context;



    JokerClient(OnTaskCompletedListener listener,Context context) {
        this.onTaskCompletedListener = listener;
        this.context  = context;
    }

    @Override
    protected String doInBackground(Void... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://"+context.getResources().getString(R.string.server_ip)+":8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }



        try {
            return myApiService.sayHi().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        onTaskCompletedListener.onTaskCompleted(s);

    }

    interface OnTaskCompletedListener{
        void onTaskCompleted(String result);
    }
}
