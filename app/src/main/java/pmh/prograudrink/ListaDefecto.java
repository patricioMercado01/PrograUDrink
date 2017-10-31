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


public class ListaDefecto extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_fragmen_list,container,false);

        String cocteles[] = new String[] {
                "Margarita corona",
                "Gelatina de champaña",
                "Margarita de fresa con tequila",
                "Sorbete de piña al cava",
                "Caipiroska de pepino",
                "El trago de la bandera de colombia",
                "Fresa espumante",
                "Whiskey: Irish Redhead",
                "Clericot de vino blanco",
                "Carajillo de Bailey's",
                "Sorbete de mojito",
                "Trago de durazno",
                "Blueberry Spritzer",
                "Mojito de vodka con maracuyá",
                "Cóctel de granizado de ginebra",
                "Cóctel americano",
                "Sangría de cava"};

        ListView listView = (ListView)view.findViewById(R.id.listView);

        ArrayAdapter<String> lisAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_expandable_list_item_1,cocteles);
        listView.setAdapter(lisAdapter);



    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        FragmentManager fragmentManager = getFragmentManager();
        DetalleCoctel detalleCoctel = new DetalleCoctel();
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
           if (i>=0 && i<=16){
               FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
               fragmentTransaction.replace(R.id.Contenedor,detalleCoctel).addToBackStack(null).commit();
           }
        }
    });

        return view;
    }
    public void onResume(){
        super.onResume();

        // Set title bar
        ((MenuLateral) getActivity())
                .setActionBarTitle("Cocteles");

    }



    private void move(){


    }

}
