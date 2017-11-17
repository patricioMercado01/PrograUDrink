package pmh.prograudrink;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Implementos extends Fragment {
    private static final String TAG = "Implementos";

    private FirebaseDatabase database;
    private String IMPLEMENTO_CHILD = "implementos";

    private String implementos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_implementos,container,false);

        database = FirebaseDatabase.getInstance();

        final DatabaseReference ingredienteReference = database.getReference().child(IMPLEMENTO_CHILD);


        final ListView listView = (ListView)view.findViewById(R.id.implementos);

        ingredienteReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG,"implemento listo"+dataSnapshot.getValue().toString());
                implementos += (dataSnapshot.getValue().toString()+",");

                ArrayAdapter<String> lisAdapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_expandable_list_item_1,implementos.split(","));
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
