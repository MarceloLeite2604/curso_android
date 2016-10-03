package com.example.sala01.aula8exercicio2;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by sala01 on 23/09/2016.
 */

public class UriAdapter extends BaseAdapter {

    private ArrayList<Uri> uriList;
    private Activity activity;

    public UriAdapter(ArrayList<Uri> uriList, Activity activity) {
        Log.d(MainActivity.LOG_TAG, "Creating UriAdapter. List size: " + uriList.size());
        this.uriList = uriList;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return uriList.size();
    }

    @Override
    public Object getItem(int i) {
        return uriList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log.d(MainActivity.LOG_TAG, "Getting UriAdapter view " + i);
        Uri imageUri = (Uri) getItem(i);
        Bitmap bitmap = null;
        try {
            Log.d(MainActivity.LOG_TAG, "Getting Bitmap.");
            bitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), imageUri);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d(MainActivity.LOG_TAG, "Inflating layout.");
        LayoutInflater inflater = (activity).getLayoutInflater();
        View row = inflater.inflate(R.layout.image_layout, viewGroup, false);

        if (bitmap != null) {
            Log.d(MainActivity.LOG_TAG, "Adding image.");
            ImageView imageView = (ImageView) row.findViewById(R.id.imageview_image);
            imageView.setImageBitmap(bitmap);
        }

        Log.d(MainActivity.LOG_TAG, "Concluding getView.");
        return row;
    }


}
