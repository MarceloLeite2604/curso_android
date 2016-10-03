package layout;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.sala01.aula5exercicio2.MainActivity;
import com.example.sala01.aula5exercicio2.R;
import com.example.sala01.aula5exercicio2.util.LogUtil;

import java.util.ArrayList;

public class InputFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private ArrayList<Integer> integersArrayList;


    public InputFragment() {
        integersArrayList = new ArrayList<>();
    }

    public static InputFragment newInstance() {
        InputFragment fragment = new InputFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View viewFragment = inflater.inflate(R.layout.fragment_input, container, false);

        final Button buttonSave = (Button) viewFragment.findViewById(R.id.button_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtil.d(MainActivity.LOG_TAG, "{onClick, 48} Save");
                final EditText editTextNumero = (EditText) viewFragment.findViewById(R.id.edittext_integer);
                Integer integer = new Integer(editTextNumero.getText().toString());
                LogUtil.d(MainActivity.LOG_TAG, "{onClick, 52} Integer: " + integer);
                integersArrayList.add(integer);
                editTextNumero.setText("");
            }
        });

        final Button buttonList = (Button) viewFragment.findViewById(R.id.button_list);
        buttonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtil.d(MainActivity.LOG_TAG, "{onClick, 62}");
                mListener.onInputFragmentInteraction(integersArrayList);
            }
        });

        return viewFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onInputFragmentInteraction(ArrayList<Integer> integerArrayList);
    }
}
