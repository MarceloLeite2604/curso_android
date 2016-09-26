package com.example.sala01.aula8exercicio2;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by vinicius on 9/22/16.
 */

public class StorePrivate {

    private static final String FILE_NAME = "data";

    public static ArrayList<Uri> readFromFile(Context context) {
        Log.d(MainActivity.LOG_TAG, "Reading from file.");
        ArrayList<Uri> uriList = new ArrayList<>();

        try {
            InputStream inputStream = context.openFileInput(FILE_NAME);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString;

                while ((receiveString = bufferedReader.readLine()) != null) {
                    Log.d(MainActivity.LOG_TAG, "Readed from file: " + receiveString);
                    Uri uri = Uri.parse(receiveString);
                    Log.d(MainActivity.LOG_TAG, "Parsed URI: " + uri);
                    uriList.add(uri);
                }

                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return uriList;
    }

    public static void writeToFile(Uri imageUri, Context context) {
        Log.d(MainActivity.LOG_TAG, "Writing to file.");
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(FILE_NAME, Context.MODE_APPEND));
            outputStreamWriter.write(imageUri.toString() + "\n");
            outputStreamWriter.close();
            Log.d(MainActivity.LOG_TAG, "Uri saved: " + imageUri.toString());
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
