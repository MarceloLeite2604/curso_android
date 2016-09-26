package com.example.sala01.aula4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final String NOME_EXTRA_ELEMENTO = "elemento";
    public static final String VALOR_EXTRA_ELEMENTO_ACTIVITY = "activity";
    public static final String VALOR_EXTRA_ELEMENTO_FRAGMENT = "fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button buttonActivity = (Button) findViewById(R.id.button_activity);
        final Button buttonFragment = (Button) findViewById(R.id.button_fragment);

        buttonActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exibirActivityDetalhe(VALOR_EXTRA_ELEMENTO_ACTIVITY);
            }
        });

        buttonFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exibirActivityDetalhe(VALOR_EXTRA_ELEMENTO_FRAGMENT);

            }
        });
    }

    private void exibirActivityDetalhe(String valorExtraElemento) {
        Intent intentExibirActivityDetalhe = new Intent(MainActivity.this, DetalheActivity.class);
        intentExibirActivityDetalhe.putExtra(NOME_EXTRA_ELEMENTO, valorExtraElemento);
        startActivity(intentExibirActivityDetalhe);
    }
}

