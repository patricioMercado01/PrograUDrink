package pmh.prograudrink;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
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

import static android.content.ContentValues.TAG;


public class ListaDefecto extends Fragment {
    private FirebaseDatabase database;
    private String COCTEL_CHILD = "cocteles";

    private String cocteles;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_fragmen_list,container,false);
        database = FirebaseDatabase.getInstance();
        final DatabaseReference coctelReference = database.getReference().child(COCTEL_CHILD);

        final ListView listView = (ListView)view.findViewById(R.id.listView);


        coctelReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG,"coctel listo"+ dataSnapshot.getValue().toString());
                cocteles += (dataSnapshot.getValue().toString()+",");

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
