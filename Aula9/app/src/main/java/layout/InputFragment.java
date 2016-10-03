package layout;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sala01.aula9.R;
import com.example.sala01.aula9.RepositoryInfo;
import com.example.sala01.aula9.util.GitHubUtil;
import com.example.sala01.aula9.util.GitHubUtilException;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnInputFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InputFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InputFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnInputFragmentInteractionListener mListener;

    public InputFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InputFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InputFragment newInstance(String param1, String param2) {
        InputFragment fragment = new InputFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewFragment = inflater.inflate(R.layout.fragment_input, container, false);

        final EditText editTextUsuario = (EditText) viewFragment.findViewById(R.id.edittext_github_username);
        final Button buttonObterInformacoes = (Button) viewFragment.findViewById(R.id.button_fetch_repositories);

        buttonObterInformacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gitHubUser = editTextUsuario.getText().toString();
                if (!gitHubUser.isEmpty()) {
                    ProgressDialog progressDialog = ProgressDialog.show(getContext(), "Please wait", "Fetching " + gitHubUser + "'s repositories...");
                    ArrayList<RepositoryInfo> repositoryInfoArrayList = null;
                    try {
                        repositoryInfoArrayList = GitHubUtil.getGitHubRepositoriesInfoFromUser(gitHubUser);
                    } catch (GitHubUtilException exception) {
                        Toast.makeText(getContext(), "Failed to fetch user informations.", Toast.LENGTH_LONG);
                    } finally {
                        progressDialog.dismiss();
                    }
                    mListener.onInputFragmentInteraction(repositoryInfoArrayList);
                }
            }
        });
        return viewFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnInputFragmentInteractionListener) {
            mListener = (OnInputFragmentInteractionListener) context;
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
    public interface OnInputFragmentInteractionListener {
        void onInputFragmentInteraction(ArrayList<RepositoryInfo> repositoryInfoArrayList);
    }
}
