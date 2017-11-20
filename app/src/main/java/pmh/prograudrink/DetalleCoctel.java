package pmh.prograudrink;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class DetalleCoctel extends Fragment {


    private Coctel actualCoctel;
    private TextView descripcion,ingredientes,preparacion,nombre,implementos,licores;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        onResume();
        this.actualCoctel = new Coctel("CervezaRon");
         test();


        return inflater.inflate(R.layout.fragment_detalle_coctel, container, false);


    }

    @Override
    public void onStart() {
        super.onStart();
        definirLayout();
        mostrar();
    }

    private void test(){

        this.actualCoctel.definirCoctel("New Coctel","Esto no se prepara aun :v","Es un coctel test nigga from hell");
        this.actualCoctel.test();
    }

    public void onResume(){
        super.onResume();

        // Set title bar
        ((MenuLateral) getActivity())
                .setActionBarTitle("Detalle Coctel");

    }
    public void mostrar(){

        this.nombre.setText(actualCoctel.getNombre());
        this.descripcion.setText(actualCoctel.getDescripcion());
        this.preparacion.setText(actualCoctel.getPreparacion());
        this.licores.setText(obtenerLicores());
    }
    public void definirLayout(){

        this.descripcion = getView().findViewById(R.id.textCoctelDescripcion);
        this.preparacion = getView().findViewById(R.id.textCoctelPreparacion);
        this.implementos = getView().findViewById(R.id.textCoctelUtensillios);
        this.ingredientes = getView().findViewById(R.id.textIngredientesCoctel);
        this.licores = getView().findViewById(R.id.textCoctelLicores);
        this.nombre = getView().findViewById(R.id.textCoctelNombre);


    }
    public String obtenerLicores() {
        String stringLicores = null;
        ArrayList<String> licoresArray = actualCoctel.getLicores();

        for (int i=0;i<licoresArray.size();i++){
            String nuevosLicores = licoresArray.get(i);
            stringLicores += (nuevosLicores+ " /n ");
        }

        return stringLicores;
    }
}

