package com.example.sala01.aula5exercicio3;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.sala01.aula5exercicio3.util.LogUtil;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = "LOG_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogUtil.startDebug();

        final Button buttonViewImage = (Button) findViewById(R.id.button_view_image);

        buttonViewImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtil.d(MainActivity.LOG_TAG, "{onClick, 32} ");
                final EditText editTextURL = (EditText) findViewById(R.id.edittext_url);

                String content = editTextURL.getText().toString();
                LogUtil.d(MainActivity.LOG_TAG, "{onClick, 32} Content: " + content);
                if (!content.isEmpty()) {
                    LogUtil.d(MainActivity.LOG_TAG, "{onClick, 38} Creating download task.");
                    final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);


                    AsyncTask<String, Integer, Bitmap> dowloadImageAsyncTask = new AsyncTask<String, Integer, Bitmap>() {

                        @Override
                        protected void onPreExecute() {
                            progressDialog.setTitle(R.string.progressbar_download_title);
                            progressDialog.setMessage(getString(R.string.progressbar_download_message));
                            progressDialog.setCancelable(false);
                            progressDialog.show();
                        }

                        @Override
                        protected void onPostExecute(Bitmap bitmap) {
                            LogUtil.d(MainActivity.LOG_TAG, "{onPostExecute, 54} ");
                            final ImageView imageViewImage = (ImageView) findViewById(R.id.imageview_image);
                            imageViewImage.setImageBitmap(bitmap);
                            progressDialog.dismiss();
                        }

                        @Override
                        protected Bitmap doInBackground(String... strings) {
                            LogUtil.d(MainActivity.LOG_TAG, "{doInBackground, 62} Downloading image from address: " + strings[0]);
                            return DownloadUtil.downloadBitmap(strings[0]);
                        }
                    };

                    dowloadImageAsyncTask.execute(content);
                }
            }
        });
    }
}
