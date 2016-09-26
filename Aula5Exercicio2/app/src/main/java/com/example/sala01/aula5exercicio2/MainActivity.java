package com.example.sala01.aula5exercicio2;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

import layout.InputFragment;
import layout.ListFragment;

public class MainActivity extends AppCompatActivity implements InputFragment.OnFragmentInteractionListener, ListFragment.OnListFragmentInteractionListener {

    public static final String LOG_TAG = "LOG_TAG";

    private ArrayList<Integer> listNumeros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listNumeros = new ArrayList<>();

        FragmentTransaction fragmentTransaction = createFragmentTransaction();
        fragmentTransaction.add(R.id.framelayout_main, InputFragment.newInstance(null, null), null);
        fragmentTransaction.commit();

    }

    private FragmentTransaction createFragmentTransaction() {
        return getSupportFragmentManager().beginTransaction();
    }

    @Override
    public void onListFragmentInteraction(Uri uri) {
        finish();
    }

    @Override
    public void onInputFragmentInteraction(Integer numero) {
        Log.d(LOG_TAG, "onInputFragmentInteraction: " + numero);
        if (numero != null) {
            listNumeros.add(numero);
        } else {
            FragmentTransaction fragmentTransaction = createFragmentTransaction();
            fragmentTransaction.replace(R.id.framelayout_input, ListFragment.newInstance(listNumeros));
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
}
