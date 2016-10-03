package com.example.sala01.aula9;

import android.graphics.Bitmap;

import java.net.URL;

/**
 * Created by sala01 on 26/09/2016.
 */

public class RepositoryInfo {

    private String name;
    private URL urlAvatar;

    public RepositoryInfo(String name, URL urlAvatar) {
        this.name = name;
        this.urlAvatar = urlAvatar;
    }

    public String getName() {
        return name;
    }

    public URL getUrlAvatar() {
        return urlAvatar;
    }
}
