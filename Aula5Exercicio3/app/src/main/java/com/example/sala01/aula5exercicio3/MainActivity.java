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

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "logTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button buttonVisualizar = (Button) findViewById(R.id.button_visualizar);

        buttonVisualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(LOG_TAG, "Button visualizar");
                final EditText editTextURL = (EditText) findViewById(R.id.edittext_url);

                String conteudo = editTextURL.getText().toString();
                Log.d(LOG_TAG, "Conteúdo: " + conteudo);
                if (!conteudo.isEmpty()) {
                    Log.d(LOG_TAG, "Criando task de download.");
                    AsyncTask<String, Integer, Bitmap> dowloadImageAsyncTask = new AsyncTask<String, Integer, Bitmap>() {

                        private ProgressDialog progressDialog;

                        @Override
                        protected void onPostExecute(Bitmap bitmap) {
                            Log.d(LOG_TAG, "onPostExecute");
                            final ImageView imagemViewImagem = (ImageView) findViewById(R.id.imageview_imagem);
                            imagemViewImagem.setImageBitmap(bitmap);
                            progressDialog.dismiss();
                        }

                        @Override
                        protected Bitmap doInBackground(String... strings) {
                            Log.d(LOG_TAG, "Downloading: " + strings[0]);
                            // TODO: A criação do progressBar está abortando a execução do programa.
                            progressDialog = ProgressDialog.show(getApplicationContext(), "Title",
                                    "Baixando ...");
                            //progressDialog.setCancelable(false);
                            Log.d(LOG_TAG, "Trace 2");
                            return DownloadUtil.downloadBitmap(strings[0]);
                        }

                        @Override
                        protected void onProgressUpdate(Integer... values) {
                            super.onProgressUpdate(values);
                            Log.d(LOG_TAG, "onProgressUpdate");
                            progressDialog.setMessage("Baixando " + values[0]);
                        }
                    };

                    dowloadImageAsyncTask.execute(conteudo);
                }
            }
        });
    }
}
