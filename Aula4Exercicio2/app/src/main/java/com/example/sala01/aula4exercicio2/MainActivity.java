package com.example.sala01.aula4exercicio2;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements RedFragment.OnFragmentInteractionListener, GreenFragment.OnFragmentInteractionListener, BlueFragment.OnFragmentInteractionListener {

    private static final String TAG_DE_LOG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction fragmentTransaction = createFragmentTransaction();
        fragmentTransaction.replace(R.id.framelayout_main, RedFragment.newInstance(null, null), null);
        fragmentTransaction.commit();
    }

    private FragmentTransaction createFragmentTransaction() {

        return getSupportFragmentManager().beginTransaction();
    }

    @Override
    public void onRedFragmentInteraction(Uri uri) {
        Log.d(TAG_DE_LOG, "Red fragment interaction");
        FragmentTransaction fragmentTransaction = createFragmentTransaction();
        fragmentTransaction.replace(R.id.framelayout_red, GreenFragment.newInstance(null, null), null);

        /**
         * Coloca o fragment "Red" no backstack. Assim, quando o fragment "Green" for exibido e o
         * usuário pressionar o botão "back", o fragment "Red" voltará a ser exibido.
         */
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onGreenFragmentInteraction(Uri uri) {
        Log.d(TAG_DE_LOG, "Green fragment interaction");
        FragmentTransaction fragmentTransaction = createFragmentTransaction();
        fragmentTransaction.replace(R.id.framelayout_green, BlueFragment.newInstance(null, null), null);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    @Override
    public void onBlueFragmentInteraction(Uri uri) {
        Log.d(TAG_DE_LOG, "Blue fragment interaction");
    }
}
