package pmh.prograudrink;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Ingredientes extends Fragment {
    private FirebaseDatabase database;
    private String INGREDIENTE_CHILD = "ingrediente";

    private String ingredientes = "";

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

        View view =inflater.inflate(R.layout.fragment_ingredientes,container,false);

        final ListView listView = (ListView)view.findViewById(R.id.ingredientes);

        database = FirebaseDatabase.getInstance();
        final DatabaseReference coctelReference = database.getReference().child(INGREDIENTE_CHILD);

        coctelReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ingredientes += (dataSnapshot.getValue().toString()+",");

                ArrayAdapter<String> lisAdapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_expandable_list_item_1,ingredientes.split(","));
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

        return view;
    }

}