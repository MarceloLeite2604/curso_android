package com.example.sala01.aula9.util;

import android.os.AsyncTask;

import com.example.sala01.aula9.MainActivity;
import com.example.sala01.aula9.util.JSONUtil;
import com.example.sala01.aula9.util.LogUtil;

import org.json.JSONArray;

import java.net.URL;

/**
 * Created by marcelo on 01/10/16.
 */

public class GetJsonArrayFromUrlAsyncTask extends AsyncTask<URL, Void, JSONArray>{

    @Override
    protected JSONArray doInBackground(URL... urls) {
        LogUtil.d(MainActivity.LOG_TAG, "{doInBackground, 35} Downloading from URL: " + urls[0]);
        return JSONUtil.getJSONArrayFromUrl(urls[0]);
    }
}
