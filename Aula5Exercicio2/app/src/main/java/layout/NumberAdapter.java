package layout;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sala01.aula5exercicio2.R;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sala01 on 16/09/2016.
 */
public class NumberAdapter extends BaseAdapter implements Serializable {

    private List<Integer> integerList;
    private Activity activity;

    public NumberAdapter(List<Integer> integerList, Activity activity) {
        this.integerList = integerList;
        this.activity = activity;
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return integerList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View rownumberView = layoutInflater.inflate(R.layout.numberrow_layout, viewGroup, false);
        TextView textViewNumberrow = (TextView) rownumberView.findViewById(R.id.textview_numberrow);

        Integer numero = (Integer) getItem(i);

        String conteudo = Integer.toString(numero);

        if (numero % 2 == 0) {
            conteudo += " é par";
        } else {
            conteudo += " é impar";
        }

        if (ehPrimo(numero)) {
            conteudo += "e primo";
        }

        conteudo += ".";

        textViewNumberrow.setText(conteudo);

        return textViewNumberrow;
    }

    private boolean ehPrimo(Integer numero) {
        int divisor = 2;

        while (divisor < numero) {
            if (numero % (divisor++) == 0) {
                return false;
            }
        }

        return true;
    }
}
