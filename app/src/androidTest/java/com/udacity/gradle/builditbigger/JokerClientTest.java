package com.udacity.gradle.builditbigger;

import android.text.TextUtils;
import android.util.Log;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class JokerClientTest
{
    JokerClient client = new JokerClient(new JokerClient.OnTaskCompletedListener() {
        @Override
        public void onTaskCompleted(String result) {
            Log.d("result_from_task",result);
        }
    });
    @Test
    public void jokerClientTest() throws Exception
    {

        String result = client.execute().get(60, TimeUnit.SECONDS);
        assertNotNull(result);
        assertTrue("Test Failed",!TextUtils.isEmpty(result));
    }
    @org.junit.Test
    public void testDoInBackground() throws Exception {
    }

    @org.junit.Test
    public void testOnPostExecute() throws Exception {
    }

}
