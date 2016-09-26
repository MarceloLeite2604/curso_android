package com.example.sala01.aula4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class DetalheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        final ScrollView scrollViewCicloDeVida = (ScrollView) findViewById(R.id.scrollview_ciclo_de_vida);
        scrollViewCicloDeVida.setVisibility(View.VISIBLE);

        final ScrollView scrollViewDefinicao = (ScrollView) findViewById(R.id.scrollview_definicao);
        scrollViewDefinicao.setVisibility(View.GONE);

        final Button buttonDefinicao = (Button) findViewById(R.id.button_definicao);

        buttonDefinicao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollViewCicloDeVida.setVisibility(View.GONE);
                scrollViewDefinicao.setVisibility(View.VISIBLE);
            }
        });

        final Button buttonVoltarCicloDeVida = (Button) findViewById(R.id.button_voltar_ciclo_de_vida);

        buttonVoltarCicloDeVida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        final Button buttonVoltarDefinicao = (Button) findViewById(R.id.button_voltar_definicao);
        buttonVoltarDefinicao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollViewCicloDeVida.setVisibility(View.VISIBLE);
                scrollViewDefinicao.setVisibility(View.GONE);
            }
        });

        final ImageView imageViewCicloDeVida = (ImageView) findViewById(R.id.imageview_ciclo_de_vida);
        final TextView textViewDefinicao = (TextView) findViewById(R.id.textview_definicao);
        final TextView textViewSubtituloCicloDeVida = (TextView) findViewById(R.id.textview_subtitulo_ciclo_de_vida);
        final TextView textViewSubtituliDefinicao = (TextView) findViewById(R.id.textview_subtitulo_definicao);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String elemento = bundle.getString(MainActivity.NOME_EXTRA_ELEMENTO);
            if (elemento != null && !elemento.isEmpty()) {
                switch (elemento) {
                    case MainActivity.VALOR_EXTRA_ELEMENTO_ACTIVITY: {
                        imageViewCicloDeVida.setImageResource(R.drawable.ciclo_de_vida_activity);
                        textViewDefinicao.setText(R.string.definicao_activity);
                        textViewSubtituloCicloDeVida.setText(R.string.activity);
                        textViewSubtituliDefinicao.setText(R.string.activity);
                        break;
                    }
                    case MainActivity.VALOR_EXTRA_ELEMENTO_FRAGMENT: {
                        imageViewCicloDeVida.setImageResource(R.drawable.ciclo_de_vida_fragment);
                        textViewDefinicao.setText(R.string.definicao_fragment);
                        textViewSubtituloCicloDeVida.setText(R.string.fragment);
                        textViewSubtituliDefinicao.setText(R.string.fragment);
                        break;
                    }

                }
            }
        }


    }
}
