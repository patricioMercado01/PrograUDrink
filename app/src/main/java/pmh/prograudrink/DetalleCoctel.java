package pmh.prograudrink;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


public class DetalleCoctel extends android.support.v4.app.Fragment {

    private Coctel actualCoctel;
    private TextView descripcion, ingredientes, preparacion, nombre, licores;
    private ImageView imagen;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle data;
        data = getArguments();
        setCoctel(data);
        onResume();
        return inflater.inflate(R.layout.fragment_detalle_coctel, container, false);

    }

    @Override
    public void onStart() {
        super.onStart();
        definirLayout();
        test();
        mostrar();
    }


    public void onResume() {
        super.onResume();

        // Set title bar
        ((MenuLateral) getActivity())
                .setActionBarTitle("Detalle Coctel");

    }

    public void mostrar() {

        this.nombre.setText(actualCoctel.getNombre());
        this.descripcion.setText(actualCoctel.getDescripcion());
        this.preparacion.setText(actualCoctel.getPreparacion());
        this.licores.setText(obtenerLicores());
        this.ingredientes.setText(obtenerIngredientes());
    }

    public void definirLayout() {
        this.imagen = getView().findViewById(R.id.imageCoctelImagen);
        this.descripcion = getView().findViewById(R.id.textCoctelDescripcion);
        this.preparacion = getView().findViewById(R.id.textCoctelPreparacion);
        this.ingredientes = getView().findViewById(R.id.textIngredientesCoctel);
        this.licores = getView().findViewById(R.id.textCoctelLicores);
        this.nombre = getView().findViewById(R.id.textCoctelNombre);


    }

    public String obtenerLicores() {
        String stringLicores = "";
        ArrayList<String> licoresArray = actualCoctel.getLicores();

        for (int i = 0; i < licoresArray.size(); i++) {
            String nuevosLicores = licoresArray.get(i);
            stringLicores += (nuevosLicores + " \n ");
        }

        return stringLicores;
    }

    public String obtenerIngredientes() {
        String stringIngredientes = "";
        if (actualCoctel.getIngredientes()!= null) {
            ArrayList<String> IngredientesArray = actualCoctel.getIngredientes();

            for (int i = 0; i < IngredientesArray.size(); i++) {
                String nuevosIngredientes = IngredientesArray.get(i);
                stringIngredientes += (nuevosIngredientes + " \n ");
            }

            return stringIngredientes;
        }else{

            throw new NullPointerException();
        }
    }

    public void setCoctel(Bundle data) {
        if (data != null) {
            this.actualCoctel = new Coctel();
            this.actualCoctel.setNombre(data.getString("nombre"));
            this.actualCoctel.setDescripcion(data.getString("desc"));
            this.actualCoctel.setPreparacion(data.getString("prep"));
            this.actualCoctel.setLicores(data.getStringArrayList("licores"));
            this.actualCoctel.setIngredientes(data.getStringArrayList("ingredientes"));
        } else {
            throw new NullPointerException();
        }
    }

    public void test(){
        System.out.println("Prueba 1");
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference pathReference = storageRef.child("gs://udrink-5f800.appspot.com/Pictures/"+actualCoctel.getNombre()+".jpg");
        final long ONE_MEGABYTE = 1024 * 1024;
        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeFile("gs://udrink-5f800.appspot.com/Pictures/"+actualCoctel.getNombre()+".jpg");
                imagen.setImageBitmap(bitmap);
                System.out.println("Prueba intermedia del éxito");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
        System.out.println("Prueba2");
    }

}





