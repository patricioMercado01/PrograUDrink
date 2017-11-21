package pmh.prograudrink;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class DetalleCoctel extends android.support.v4.app.Fragment{


    private Coctel actualCoctel;
    private TextView descripcion,ingredientes,preparacion,nombre,implementos,licores;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        onResume();
        this.actualCoctel = new Coctel("CervezaRon");
         test();
//Test Push from hell

        return inflater.inflate(R.layout.fragment_detalle_coctel, container, false);


    }

    @Override
    public void onStart() {
        super.onStart();
        definirLayout();
        mostrar();
    }

    private void test(){

        this.actualCoctel.definirCoctel("Mojito","\nColoca todos los ingredientes en una licuadora e inicia con velocidad lenta,  auméntala poco a poco, hasta que ya no se escuche ningún hielo y se logre la textura frozen."+
                "\nPon la mezcla en un vaso previamente refrigerado, para que no se pierda la textura frozen."+
                "\nAdornar con una ramita de menta y una rodaja de lima.","El mojito,\u200B es un popular cóctel originario de Cuba, compuesto de ron, azúcar, limón, menta o hierbabuena y agua mineralizada.");
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
        this.ingredientes.setText(obtenerIngredientes());
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
        String stringLicores = "" ;
        ArrayList<String> licoresArray = actualCoctel.getLicores();

        for (int i=0;i<licoresArray.size();i++){
            String nuevosLicores = licoresArray.get(i);
            stringLicores += (nuevosLicores+ " \n ");
        }

        return stringLicores;
    }

    public String obtenerIngredientes() {
        String stringIngredientes = "" ;
        ArrayList<String> IngredientesArray = actualCoctel.getIngredientes();

        for (int i=0;i<IngredientesArray.size();i++){
            String nuevosIngredientes = IngredientesArray.get(i);
            stringIngredientes += (nuevosIngredientes+ " \n ");
        }

        return stringIngredientes;
    }
}

