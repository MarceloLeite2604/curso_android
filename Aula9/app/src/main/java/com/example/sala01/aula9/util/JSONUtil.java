package com.example.sala01.aula9.util;

import com.example.sala01.aula9.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by sala01 on 26/09/2016.
 */

public class JSONUtil {



    public static JSONArray getJSONArrayFromUrl(URL url) {
        LogUtil.d(MainActivity.LOG_TAG, "{getJSONArrayFromUrl, 34} ");
        HttpURLConnection httpURLConnection;
        JSONArray jsonArray = null;
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            LogUtil.d(MainActivity.LOG_TAG, "{getJSONArrayFromUrl, 40} ");
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                LogUtil.d(MainActivity.LOG_TAG, "{getJSONArrayFromUrl, 42} ");
                String serverResponse = readStream(httpURLConnection.getInputStream());
                jsonArray = new JSONArray(serverResponse);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LogUtil.d(MainActivity.LOG_TAG, "{getJSONArrayFromUrl, 52} Result: " + jsonArray);
        return jsonArray;
    }

    private static String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }
}
