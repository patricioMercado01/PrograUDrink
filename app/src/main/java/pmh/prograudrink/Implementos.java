package pmh.prograudrink;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class Implementos extends Fragment {
    private static final String TAG = "Implementos";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_implementos,container,false);

        String imple[] = new String[] {"Implemento para trago 'x' ",
                "Implemento para trago 'x' ","Implemento para trago 'x' ",
                "Implemento para trago 'x' ",
                "Implemento para trago 'x' ",
                "Implemento para trago 'x' ",
                "Implemento para trago 'x' ",
                "Implemento para trago 'x' ",};

        ListView listView = (ListView)view.findViewById(R.id.implementos);

        ArrayAdapter<String> lisAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_expandable_list_item_1,imple);
        listView.setAdapter(lisAdapter);

        return view;
    }
}
