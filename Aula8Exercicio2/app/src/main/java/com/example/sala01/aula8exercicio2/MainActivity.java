package com.example.sala01.aula8exercicio2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = "LOG_TAG";
    private static final int CAMERA_REQUEST = 1888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateListView();
        Button photoButton = (Button) this.findViewById(R.id.btn_takephoto);
        photoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
                Log.d(LOG_TAG, "cameraIntent sent");
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Log.d(LOG_TAG, "cameraIntent received");
            Uri uri = data.getData();
            Log.d(LOG_TAG, "URI received: " + uri);
            StorePrivate.writeToFile(uri, this);
            updateListView();
        }
    }

    private void updateListView() {
        ListView lstNumbers = (ListView) findViewById(R.id.listview_images);
        lstNumbers.setAdapter(new UriAdapter(StorePrivate.readFromFile(this), this));
    }

}
