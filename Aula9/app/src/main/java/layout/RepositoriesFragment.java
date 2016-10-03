package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.sala01.aula9.R;
import com.example.sala01.aula9.RepositoryAdapter;
import com.example.sala01.aula9.RepositoryInfo;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnRepositoriesFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RepositoriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RepositoriesFragment extends Fragment {

    private static final String ARG_PARAM1 = "repositoryInfoArrayList";

    private OnRepositoriesFragmentInteractionListener mListener;

    private ArrayList<RepositoryInfo> repositoryInfoArrayList;

    public RepositoriesFragment() {
    }


    public static RepositoriesFragment newInstance(ArrayList<RepositoryInfo> repositoryInfoArrayList) {
        RepositoriesFragment fragment = new RepositoriesFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, repositoryInfoArrayList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            repositoryInfoArrayList = (ArrayList<RepositoryInfo>) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewFragment = inflater.inflate(R.layout.fragment_repositories, container, false);


        ListView listViewRepositores = (ListView) viewFragment.findViewById(R.id.listview_repositories);
        RepositoryAdapter repositoryAdapter = new RepositoryAdapter(repositoryInfoArrayList, getActivity());
        listViewRepositores.setAdapter(repositoryAdapter);

        return viewFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRepositoriesFragmentInteractionListener) {
            mListener = (OnRepositoriesFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnRepositoriesFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnRepositoriesFragmentInteractionListener {
        void onRepositoriesFragmentInteraction(Uri uri);
    }
}
