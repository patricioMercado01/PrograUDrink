package pmh.prograudrink;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.app.PendingIntent.getActivity;

public class IngresoNuevoCoctel extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 234;

    private Button btnUpImage;
    private ImageView imageView;
    private EditText editDescripcion;
    private EditText editNombre;
    private EditText editPreparacion;
    private Spinner spinLicor;
    private Spinner spinIngrediente;
    private Button btnAddLicor;
    private Button btnLicor1;
    private Button btnLicor2;
    private Button btnLicor3;
    private Button btnLicor4;
    private Button btnAddIngr;
    private Button btnIngrediente1;
    private Button btnIngrediente2;
    private Button btnIngrediente3;
    private Button btnIngrediente4;
    private EditText editLicor;
    private EditText editIngr;
    private Button btnAddCoctel;

    private int cantidadDatos;

    //Descarga de datos
    private FirebaseDatabase database;
    private String LICOR_CHILD = "licores";
    private String INGREDIENTES_CHILD = "ingrediente";
    private String licores = "";
    private String ingredientes = "";

    //subida de datos
    private String MI_COCTEL_CHILD = "misCocteles";


    private StorageReference storageReference;
    private DatabaseReference mDatabase;
    private Uri filePath;
    private static final int GALLERY_INTENT = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_nuevo_coctel);

        imageView = (ImageView) findViewById(R.id.imgUpImage);
        btnUpImage = (Button) findViewById(R.id.btnUpImage);
        editDescripcion = (EditText) findViewById(R.id.editDescripcion);
        editNombre = (EditText) findViewById(R.id.editNombre);
        editPreparacion = (EditText) findViewById(R.id.editPreparacion);
        btnAddCoctel = (Button) findViewById(R.id.btnAddCoctel);

        spinIngrediente = (Spinner) findViewById(R.id.spinIngredientes);
        btnAddIngr = (Button) findViewById(R.id.btnAddIngr);
        btnIngrediente1 = (Button) findViewById(R.id.btnIngrediente1);
        btnIngrediente2 = (Button) findViewById(R.id.btnIngrediente2);
        btnIngrediente3 = (Button) findViewById(R.id.btnIngrediente3);
        btnIngrediente4 = (Button) findViewById(R.id.btnIngrediente4);
        editIngr = (EditText) findViewById(R.id.editIngrediente);

        spinLicor = (Spinner) findViewById(R.id.spinLicores);
        editLicor = (EditText) findViewById(R.id.editLicor);
        btnAddLicor = (Button) findViewById(R.id.btnAddLicor);
        btnLicor1 = (Button) findViewById(R.id.btnLicor1);
        btnLicor2 = (Button) findViewById(R.id.btnLicor2);
        btnLicor3 = (Button) findViewById(R.id.btnLicor3);
        btnLicor4 = (Button) findViewById(R.id.btnLicor4);


        storageReference = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS);


       /* btnUpImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });*/

        database = FirebaseDatabase.getInstance();

        final DatabaseReference ingredienteReference = database.getReference().child(INGREDIENTES_CHILD);
        ingredienteReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ingredientes += (dataSnapshot.getValue().toString() + ",");

                ArrayAdapter<String> adapterLicor = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_spinner_item, ingredientes.split(","));

                spinIngrediente.setAdapter(adapterLicor);
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

        final DatabaseReference coctelReference = database.getReference().child(LICOR_CHILD);
        coctelReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                licores += (dataSnapshot.getValue().toString() + ",");

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_spinner_item, licores.split(","));

                spinLicor.setAdapter(adapter);
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

        btnLicor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnLicor1.setText("");
                btnLicor1.setVisibility(View.INVISIBLE);
            }
        });
        btnLicor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnLicor2.setText("");
                btnLicor2.setVisibility(View.INVISIBLE);
            }
        });
        btnLicor3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnLicor3.setText("");
                btnLicor3.setVisibility(View.INVISIBLE);
            }
        });
        btnLicor4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnLicor4.setText("");
                btnLicor4.setVisibility(View.INVISIBLE);
            }
        });
        btnIngrediente1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnIngrediente1.setText("");
                btnIngrediente1.setVisibility(View.INVISIBLE);
            }
        });
        btnIngrediente2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnIngrediente2.setText("");
                btnIngrediente2.setVisibility(View.INVISIBLE);
            }
        });
        btnIngrediente3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnIngrediente3.setText("");
                btnIngrediente3.setVisibility(View.INVISIBLE);
            }
        });
        btnIngrediente4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnIngrediente4.setText("");
                btnIngrediente4.setVisibility(View.INVISIBLE);
            }
        });


        btnAddLicor.setOnClickListener(new View.OnClickListener() {
            String cantidad;
            String licor;

            @Override
            public void onClick(View view) {
                if (editLicor.getText().toString().length() > 0) {
                    cantidad = editLicor.getText().toString();
                    licor = spinLicor.getSelectedItem().toString();

                    if (btnLicor1.getText().toString() == "") {
                        btnLicor1.setText(licor + " " + cantidad);
                        btnLicor1.setVisibility(View.VISIBLE);
                    } else if (btnLicor2.getText() == "") {
                        btnLicor2.setText(licor + " " + cantidad);
                        btnLicor2.setVisibility(View.VISIBLE);
                    } else if (btnLicor3.getText() == "") {
                        btnLicor3.setText(licor + " " + cantidad);
                        btnLicor3.setVisibility(View.VISIBLE);
                    } else if (btnLicor4.getText() == "") {
                        btnLicor4.setText(licor + " " + cantidad);
                        btnLicor4.setVisibility(View.VISIBLE);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Llena el campo con la cantidad de licor que tiene su coctel", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnAddIngr.setOnClickListener(new View.OnClickListener() {
            String ingrediente;
            String cantidad;

            @Override
            public void onClick(View view) {
                if (editIngr.getText().toString().length() > 0) {
                    ingrediente = spinIngrediente.getSelectedItem().toString();
                    cantidad = editIngr.getText().toString();

                    if (btnIngrediente1.getText().toString() == "") {
                        btnIngrediente1.setText(ingrediente + " " + cantidad);
                        btnIngrediente1.setVisibility(View.VISIBLE);
                    } else if (btnIngrediente2.getText().toString() == "") {
                        btnIngrediente2.setText(ingrediente + " " + cantidad);
                        btnIngrediente2.setVisibility(View.VISIBLE);
                    } else if (btnIngrediente3.getText().toString() == "") {
                        btnIngrediente3.setText(ingrediente + " " + cantidad);
                        btnIngrediente3.setVisibility(View.VISIBLE);
                    } else if (btnIngrediente4.getText().toString() == "") {
                        btnIngrediente4.setText(ingrediente + " " + cantidad);
                        btnIngrediente4.setVisibility(View.VISIBLE);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Llena el campo con la cantidad de ingrediente que tiene su coctel", Toast.LENGTH_SHORT).show();
                }
            }
        });

        numberChild();
        btnAddCoctel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference miCoctelReference = database.getReference().child(MI_COCTEL_CHILD);

                ArrayList<String> listLicores = new ArrayList<String>();
                ArrayList<String> listIngr = new ArrayList<String>();


                if (btnLicor1.getText().toString() != "") {
                    listLicores.add(btnLicor1.getText().toString());
                }
                if (btnLicor2.getText().toString() != "") {
                    listLicores.add(btnLicor2.getText().toString());
                }
                if (btnLicor3.getText().toString() != "") {
                    listLicores.add(btnLicor3.getText().toString());
                }
                if (btnLicor4.getText().toString() != "") {
                    listLicores.add(btnLicor4.getText().toString());
                }
                if (btnIngrediente1.getText().toString() != "") {
                    listIngr.add(btnIngrediente1.getText().toString());
                }
                if (btnIngrediente2.getText().toString() != "") {
                    listIngr.add(btnIngrediente2.getText().toString());
                }
                if (btnIngrediente3.getText().toString() != "") {
                    listIngr.add(btnIngrediente3.getText().toString());
                }
                if (btnIngrediente4.getText().toString() != "") {
                    listIngr.add(btnIngrediente4.getText().toString());
                }
                if (listLicores.size() == 0 || listIngr.size() == 0 || editNombre.equals("") || editDescripcion.equals("")) {
                    Toast.makeText(getApplicationContext(), "Faltan datos por rellenar", Toast.LENGTH_SHORT).show();
                } else {

                    Coctel coctel = new Coctel(cantidadDatos, listLicores, listIngr, editNombre.getText().toString(), editDescripcion.getText().toString(), editDescripcion.getText().toString());
                    miCoctelReference.child(Integer.toString(cantidadDatos)).setValue(coctel);
                    Toast.makeText(getApplicationContext(), "Su coctel " + editNombre.getText().toString() + " ha sido creado", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });


        btnUpImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_INTENT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();
            StorageReference filePath = storageReference.child("Pictures").child(uri.getLastPathSegment());
            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(IngresoNuevoCoctel.this, "Foto Correcta", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void numberChild() {
        mDatabase = database.getReference(MI_COCTEL_CHILD);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cantidadDatos = (int) dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
