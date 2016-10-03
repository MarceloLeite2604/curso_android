package layout;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.sala01.aula5exercicio2.MainActivity;
import com.example.sala01.aula5exercicio2.R;
import com.example.sala01.aula5exercicio2.util.LogUtil;

import java.util.ArrayList;

public class ListFragment extends Fragment {
    private static final String ARG_PARAM1 = "integerArrayList";

    public ArrayList<Integer> integerArrayList;

    private OnListFragmentInteractionListener mListener;

    public ListFragment() {
    }

    public static ListFragment newInstance(ArrayList<Integer> integerArrayList) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, integerArrayList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            integerArrayList = (ArrayList<Integer>) getArguments().getSerializable(ARG_PARAM1);
            LogUtil.d(MainActivity.LOG_TAG, "{onCreate, 41} List size: " + integerArrayList.size());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LogUtil.d(MainActivity.LOG_TAG, "{onCreateView, 45} ");
        final View view = inflater.inflate(R.layout.fragment_list, container, false);

        final ListView listViewIntegers = (ListView) view.findViewById(R.id.listview_integers);

        listViewIntegers.setAdapter(new IntegerAdapter(integerArrayList, getActivity()));

        final Button buttonBack = (Button) view.findViewById(R.id.button_back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onListFragmentInteraction();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
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

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction();
    }
}
