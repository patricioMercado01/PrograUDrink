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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class ListaDefecto extends Fragment {
    private FirebaseDatabase database;

    private String COCTEL_CHILD = "Jsonstring";
    private String cocteles = "";
    private ArrayList<Coctel> listaCocteles;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_fragmen_list,container,false);
        database = FirebaseDatabase.getInstance();
        final DatabaseReference coctelReference = database.getReference().child(COCTEL_CHILD);
        listaCocteles = new ArrayList<>();
        final ListView listView = (ListView)view.findViewById(R.id.listView);
        coctelReference.addChildEventListener(new ChildEventListener() {
            @Override

            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Coctel newCoctel = dataSnapshot.getValue(Coctel.class);
                String nombre = newCoctel.getNombre();
                listaCocteles.add(newCoctel);
                cocteles += (nombre +",");

                ArrayAdapter<String> lisAdapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_expandable_list_item_1,cocteles.split(","));


                listView.setAdapter(lisAdapter);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            FragmentManager fragmentManager = getFragmentManager();
            DetalleCoctel detalleCoctel = new DetalleCoctel();
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i>=0 && i<=16){
                    Object object = listView.getAdapter().getItem(i);
                    Coctel coctelData;
                    Bundle args = new Bundle();

                    String valor = object.toString();
                    coctelData =  buscarCocteles(valor);
                    System.out.println(coctelData.getNombre());

                    args.putString("nombre", coctelData.getNombre());
                    args.putString("desc",coctelData.getDescripcion());
                    args.putString("prep",coctelData.getPreparacion());
                    args.putStringArrayList("licores",coctelData.getLicores());
                    args.putStringArrayList("ingredientes",coctelData.getIngredientes());


                    detalleCoctel.setArguments(args);
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.Contenedor,detalleCoctel).commit();

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

    public Coctel buscarCocteles(String nombre){
        Coctel findendCoctel = null;

        for (int i=0;i<listaCocteles.size();i++){
            String nombreBusqueda = listaCocteles.get(i).getNombre();
            if (nombre.equals(nombreBusqueda)){

                findendCoctel = listaCocteles.get(i);
            }


        }
        return findendCoctel;
    }


}
