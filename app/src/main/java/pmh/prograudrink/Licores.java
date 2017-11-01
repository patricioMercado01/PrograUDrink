package pmh.prograudrink;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class Licores extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_licores,container,false);

        String licores[] = new String[] {"Licor para trago 'x' ",
                "Licor para trago 'x' ","Licor para trago 'x' ",
                "Licor para trago 'x' ",
                "Licor para trago 'x' ",
                "Licor para trago 'x' ",
                "Licor para trago 'x' ",
                "Licor para trago 'x' ",};

        ListView listView = (ListView)view.findViewById(R.id.licores);

        ArrayAdapter<String> lisAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_expandable_list_item_1,licores);
        listView.setAdapter(lisAdapter);


        return view;
    }
}
