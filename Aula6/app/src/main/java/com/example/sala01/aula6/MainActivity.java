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

    private static final int ACTIVITY_SELECT_PHOTO = 2236;

    private ImageView imageViewImage;

    private Button buttonAttachImage;

    private Button buttonSend;

    private EditText editTextReceiver;

    private EditText editTextMessage;

    private Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAttachImage = (Button) findViewById(R.id.button_attach_image);
        imageViewImage = (ImageView) findViewById(R.id.imageview_image);
        editTextReceiver = (EditText) findViewById(R.id.edittext_receiver);
        editTextMessage = (EditText) findViewById(R.id.edittext_message);
        buttonSend = (Button) findViewById(R.id.button_send);

        buttonAttachImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSelectImage = new Intent(Intent.ACTION_PICK);
                intentSelectImage.setType("image/*");
                startActivityForResult(intentSelectImage, ACTIVITY_SELECT_PHOTO);
            }
        });

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String receiver = editTextReceiver.getText().toString();
                if (receiver.isEmpty()) {
                    showMessage(getString(R.string.alert_inform_receiver));
                    return;
                }


                String message = editTextMessage.getText().toString();
                if (message.isEmpty()) {
                    showMessage(getString(R.string.alert_inform_message));
                    return;
                }


                if (imageViewImage.getDrawable() == null) {
                    showMessage(getString(R.string.alert_attach_image));
                    return;
                }

                if (isAnEmailAddress(receiver)) {
                    sendEmail(receiver, message);
                } else {
                    sendMMS(receiver, message);
                }
            }
        });
    }

    private void showMessage(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT);
    }

    private void sendMMS(String receiver, String message) {
        Uri uri = Uri.parse("smsto:" + receiver);
        Intent intentEnviarMMS = new Intent(Intent.ACTION_SENDTO, uri);
        intentEnviarMMS.putExtra("sms_body", message);
        startActivity(intentEnviarMMS);
    }

    private void sendEmail(String receiver, String message) {
        Intent intentEnviarEmail = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", receiver, null));
        intentEnviarEmail.putExtra(Intent.EXTRA_SUBJECT, message);
        intentEnviarEmail.putExtra(Intent.EXTRA_TEXT, message);
        intentEnviarEmail.putExtra(Intent.EXTRA_STREAM, imageUri);

        startActivity(Intent.createChooser(intentEnviarEmail, "Send email"));
    }

    private boolean isAnEmailAddress(String destino) {
        return (destino.indexOf("@") >= 0);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ACTIVITY_SELECT_PHOTO: {
                if (resultCode == RESULT_OK) {
                    imageUri = data.getData();
                    try {
                        InputStream inputStreamImagem = getContentResolver().openInputStream(imageUri);
                        Bitmap bitmapImage = BitmapFactory.decodeStream(inputStreamImagem);

                        imageViewImage.setImageBitmap(bitmapImage);
                        buttonAttachImage.setText(getString(R.string.label2_button_attach_image));

                    } catch (FileNotFoundException exception) {
                        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG);
                        exception.printStackTrace();
                    }
                }
                break;
            }
        }
    }
}
