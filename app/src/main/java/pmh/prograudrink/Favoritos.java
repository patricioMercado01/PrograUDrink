package pmh.prograudrink;

import android.content.Intent;
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



        Intent intent = new Intent(getActivity(), Login.class);
        getActivity().startActivity(intent);


        String cocteles[] = new String[] {
                "Terremoto chileno",
                "Piscola",
                "Blue moon",
                "Michelada",
                "Whisky rojo",
                "Martini seco"};

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
