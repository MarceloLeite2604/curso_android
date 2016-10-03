package layout;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sala01.aula5exercicio2.MainActivity;
import com.example.sala01.aula5exercicio2.R;
import com.example.sala01.aula5exercicio2.util.LogUtil;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sala01 on 16/09/2016.
 */
public class IntegerAdapter extends BaseAdapter implements Serializable {

    private ArrayList<Integer> integerArrayList;
    private Activity activity;

    public IntegerAdapter(ArrayList<Integer> integerArrayList, Activity activity) {
        LogUtil.d(MainActivity.LOG_TAG, "{IntegerAdapter, 24} List size: " + integerArrayList.size());
        this.integerArrayList = integerArrayList;
        this.activity = activity;
    }


    @Override
    public int getCount() {
        return integerArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return integerArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LogUtil.d(MainActivity.LOG_TAG, "{getView, 49} ");
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View rownumberView = layoutInflater.inflate(R.layout.row_integer_layout, viewGroup, false);
        TextView textViewRowInteger = (TextView) rownumberView.findViewById(R.id.textview_row_integer);

        Integer numero = (Integer) getItem(i);

        String conteudo = Integer.toString(numero);

        if (numero % 2 == 0) {
            conteudo += " is even";
        } else {
            conteudo += " is odd";
        }

        if (isPrime(numero)) {
            conteudo += " and prime";
        }

        conteudo += ".";

        textViewRowInteger.setText(conteudo);

        return textViewRowInteger;
    }

    private boolean isPrime(Integer integer) {
        int divisor = 2;

        while (divisor < integer) {
            if (integer % (divisor++) == 0) {
                return false;
            }
        }

        return true;
    }
}
