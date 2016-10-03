package com.example.sala01.aula5exercicio2;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.sala01.aula5exercicio2.util.LogUtil;

import java.util.ArrayList;

import layout.InputFragment;
import layout.ListFragment;

public class MainActivity extends AppCompatActivity implements InputFragment.OnFragmentInteractionListener, ListFragment.OnListFragmentInteractionListener {

    public static final String LOG_TAG = "LOG_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogUtil.startDebug();

        FragmentTransaction fragmentTransaction = createFragmentTransaction();
        fragmentTransaction.replace(R.id.framelayout_main, InputFragment.newInstance(), null);
        fragmentTransaction.commit();

    }

    @Override
    public void onListFragmentInteraction() {

        LogUtil.d(MainActivity.LOG_TAG, "{onListFragmentInteraction, 32} ");
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onInputFragmentInteraction(ArrayList<Integer> integerArrayList) {
        LogUtil.d(MainActivity.LOG_TAG, "{onInputFragmentInteraction, 42} List size: " + integerArrayList.size());
        FragmentTransaction fragmentTransaction = createFragmentTransaction();
        fragmentTransaction.replace(R.id.framelayout_input, ListFragment.newInstance(integerArrayList));
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private FragmentTransaction createFragmentTransaction() {
        return getSupportFragmentManager().beginTransaction();
    }
}
