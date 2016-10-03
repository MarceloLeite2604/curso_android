package com.example.sala01.aula9;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.sala01.aula9.util.LogUtil;

import java.util.ArrayList;

import layout.InputFragment;
import layout.RepositoriesFragment;

public class MainActivity extends AppCompatActivity implements InputFragment.OnInputFragmentInteractionListener, RepositoriesFragment.OnRepositoriesFragmentInteractionListener {

    public static final String LOG_TAG = "LOG_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogUtil.startDebug();

        // Shows input frame.
        FragmentTransaction fragmentTransaction = beginFragmentTransaction();
        fragmentTransaction.replace(R.id.framelayout_main, InputFragment.newInstance(null, null));
        fragmentTransaction.commit();
    }

    /**
     * Interaction with input frame.
     *
     * @param repositoryInfoArrayList An array list containing GitHub user repositories info.
     */
    @Override
    public void onInputFragmentInteraction(ArrayList<RepositoryInfo> repositoryInfoArrayList) {

        // Switch to repositories frame.
        FragmentTransaction fragmentTransaction = beginFragmentTransaction();
        RepositoriesFragment repositoriesFragment = RepositoriesFragment.newInstance(repositoryInfoArrayList);
        fragmentTransaction.replace(R.id.fragment_input, repositoriesFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onRepositoriesFragmentInteraction(Uri uri) {
    }

    /**
     * Begins a fragment transaction
     *
     * @return The fragment transaction started.
     */
    private FragmentTransaction beginFragmentTransaction() {
        return getSupportFragmentManager().beginTransaction();
    }
}
