package com.example.sala01.aula6;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "LOG_TAG";

    private static final int ACTIVITY_SELECIONAR_FOTO = 2236;

    private ImageView imageViewImagem;

    private Button buttonAnexarImagem;

    private Button buttonEnviar;

    private EditText editTextDestinatario;

    private EditText editTextMensagem;

    private Uri uriImagem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAnexarImagem = (Button) findViewById(R.id.button_anexar_imagem);
        imageViewImagem = (ImageView) findViewById(R.id.imageview_imagem);
        editTextDestinatario = (EditText) findViewById(R.id.edittext_destinatario);
        editTextMensagem = (EditText) findViewById(R.id.edittext_mensagem);
        buttonEnviar = (Button) findViewById(R.id.button_enviar);

        buttonAnexarImagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSelecionarImagem = new Intent(Intent.ACTION_PICK);
                intentSelecionarImagem.setType("image/*");
                startActivityForResult(intentSelecionarImagem, ACTIVITY_SELECIONAR_FOTO);
            }
        });

        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String destinatario = editTextDestinatario.getText().toString();
                if (destinatario.isEmpty()) {
                    exibirMensagem("Informe o destinatário da mensagem.");
                    return;
                }


                String mensagem = editTextMensagem.getText().toString();
                if (mensagem.isEmpty()) {
                    exibirMensagem("Informe o conteúdo da mensagem.");
                    return;
                }


                if (imageViewImagem.getDrawable() == null) {
                    exibirMensagem("Anexe uma imagem na mensagem.");
                    return;
                }

                if (verificarEmail(destinatario)) {
                    enviarViaEmail(destinatario, mensagem);
                } else {
                    enviarViaMMS(destinatario, mensagem);
                }
            }
        });
    }

    private void exibirMensagem(String mensagem) {
        Toast.makeText(MainActivity.this, mensagem, Toast.LENGTH_SHORT);
    }

    private void enviarViaMMS(String numeroDeDestino, String mensagem) {
        Uri uri = Uri.parse("smsto:" + numeroDeDestino);
        Intent intentEnviarMMS = new Intent(Intent.ACTION_SENDTO, uri);
        intentEnviarMMS.putExtra("sms_body", mensagem);
        startActivity(intentEnviarMMS);
    }

    private void enviarViaEmail(String destinatario, String mensagem) {
        Intent intentEnviarEmail = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", destinatario, null));
        intentEnviarEmail.putExtra(Intent.EXTRA_SUBJECT, mensagem);
        intentEnviarEmail.putExtra(Intent.EXTRA_TEXT, mensagem);
        intentEnviarEmail.putExtra(Intent.EXTRA_STREAM, uriImagem);

        startActivity(Intent.createChooser(intentEnviarEmail, "Enviar email"));
    }

    private boolean verificarEmail(String destino) {
        return (destino.indexOf("@") >= 0);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ACTIVITY_SELECIONAR_FOTO: {
                if (resultCode == RESULT_OK) {
                    uriImagem = data.getData();
                    try {
                        InputStream inputStreamImagem = getContentResolver().openInputStream(uriImagem);
                        Bitmap bitmapImagem = BitmapFactory.decodeStream(inputStreamImagem);

                        imageViewImagem.setImageBitmap(bitmapImagem);
                        buttonAnexarImagem.setText("Alterar imagem");

                    } catch (FileNotFoundException excecao) {
                        Toast.makeText(this, excecao.getMessage(), Toast.LENGTH_LONG);
                        excecao.printStackTrace();
                    }
                }
                break;
            }
        }
    }
}
