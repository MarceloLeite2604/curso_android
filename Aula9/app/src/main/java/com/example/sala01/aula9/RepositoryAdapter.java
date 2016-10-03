package com.example.sala01.aula9;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sala01.aula9.util.ImageUtil;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.List;

/**
 * Created by sala01 on 26/09/2016.
 */

public class RepositoryAdapter extends BaseAdapter {

    private List<RepositoryInfo> repositoryInfoList;
    private Activity activity;

    public RepositoryAdapter(List<RepositoryInfo> repositoryInfoList, Activity activity) {
        this.repositoryInfoList = repositoryInfoList;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return repositoryInfoList.size();
    }

    @Override
    public Object getItem(int i) {
        return repositoryInfoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (activity).getLayoutInflater();
        View row = inflater.inflate(R.layout.row_repository, viewGroup, false);

        final ImageView imageViewAvatar = (ImageView) row.findViewById(R.id.imageview_avatar);
        final TextView textViewName = (TextView) row.findViewById(R.id.textview_name);

        RepositoryInfo repositoryInfo = (RepositoryInfo) getItem(i);

        Picasso.with(activity).load(repositoryInfo.getUrlAvatar().toString()).into(imageViewAvatar);
        textViewName.setText(repositoryInfo.getName());

        return row;
    }
}
