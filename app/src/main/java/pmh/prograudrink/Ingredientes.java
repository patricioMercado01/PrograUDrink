package pmh.prograudrink;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class Ingredientes extends Fragment {
    private static final String TAG = "Ingredientes";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_ingredientes,container,false);

        String ingre[] = new String[] {"Ingrediente para trago 'x' ",
                "Ingrediente para trago 'x' ","Ingrediente para trago 'x' ",
                "Ingrediente para trago 'x' ",
                "Ingrediente para trago 'x' ",
                "Ingrediente para trago 'x' ",
                "Ingrediente para trago 'x' ",
                "Ingrediente para trago 'x' ",};

        ListView listView = (ListView)view.findViewById(R.id.ingredientes);

        ArrayAdapter<String> lisAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_expandable_list_item_1,ingre);
        listView.setAdapter(lisAdapter);


        return view;
    }

}