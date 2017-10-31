package pmh.prograudrink;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class Favoritos extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_favoritos,container,false);

        String cocteles[] = new String[] {
                "Aspirina tequila",
                "Gelatina de salbutamol",
                "mojito africano",
                "Mojito de pollito",
                "Cóctel de granizado tu hermana",
                "Cóctel peruano",
                "Sangría de caviar"};

        ListView listView = (ListView)view.findViewById(R.id.favoritos);

        ArrayAdapter<String> lisAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_expandable_list_item_1,cocteles);
        listView.setAdapter(lisAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            FragmentManager fragmentManager = getFragmentManager();
            DetalleCoctel detalleCoctel = new DetalleCoctel();
            ListaDefecto fragmenList = new ListaDefecto();
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.Contenedor,detalleCoctel).addToBackStack(null).commit();

            }
        });



        return view;
    }


}
