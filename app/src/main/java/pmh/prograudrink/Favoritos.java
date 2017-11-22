package pmh.prograudrink;

import android.app.Activity;
import android.content.Intent;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;


public class Favoritos extends android.support.v4.app.Fragment {


    private FirebaseDatabase database;
    private String FAVORITOS_CHILD = "favoritos";

    private String favoritos = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FirebaseAuth auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser() == null){
            Intent siguiente = new Intent(getActivity(), LoginFB.class);
            startActivity(siguiente);
            ((Activity) getActivity()).overridePendingTransition(0,0);
            getActivity().finish();
        }

        onResume();

        View view =inflater.inflate(R.layout.fragment_favoritos,container,false);


        final ListView listView = (ListView)view.findViewById(R.id.favoritos);

        database = FirebaseDatabase.getInstance();
        final DatabaseReference coctelReference = database.getReference().child(FAVORITOS_CHILD);

        coctelReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG,"licor listo"+ dataSnapshot.getValue().toString());
                favoritos += (dataSnapshot.getValue().toString()+",");

                ArrayAdapter<String> lisAdapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_expandable_list_item_1,favoritos.split(","));
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
            ListaDefecto fragmenList = new ListaDefecto();
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.Contenedor,detalleCoctel).addToBackStack(null).commit();
            }
        });
        return view;
    }

    public void onResume(){
        super.onResume();

        // Set title bar
        ((MenuLateral) getActivity())
                .setActionBarTitle("Favoritos");

    }
}
