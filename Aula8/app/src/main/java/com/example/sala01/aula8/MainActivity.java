package com.example.sala01.aula8;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sala01.aula8.sql.FeedReaderPersistence;

public class MainActivity extends AppCompatActivity {

    private static final String DB_PROPERTY = "entry";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSaveInternal = (Button) findViewById(R.id.btn_saveinternal);
        final EditText edtText = (EditText) findViewById(R.id.edt_texttosave);
        final TextView txtText = (TextView) findViewById(R.id.txv_text);

        btnSaveInternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StorePrivate.writeToFile(edtText.getText().toString(), getApplicationContext());
                txtText.setText(StorePrivate.readFromFile(getApplicationContext()));
            }
        });

        Button btnSaveExternal = (Button) findViewById(R.id.btn_saveexternal);
        btnSaveExternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StorePublic.writeToSDFile(edtText.getText().toString());
                txtText.setText(StorePublic.readRaw(getApplicationContext()));
            }
        });

        Button btnSaveSql = (Button) findViewById(R.id.btn_savesql);
        btnSaveSql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeedReaderPersistence feedReaderPersistence = new FeedReaderPersistence(MainActivity.this);

                feedReaderPersistence.delete(DB_PROPERTY);
                feedReaderPersistence.insert(DB_PROPERTY, edtText.getText().toString());
                String value = feedReaderPersistence.select(DB_PROPERTY);

                txtText.setText(value);
            }
        });


    }
}
