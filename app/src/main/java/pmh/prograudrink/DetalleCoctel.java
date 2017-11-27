package pmh.prograudrink;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import java.util.ArrayList;


public class DetalleCoctel extends android.support.v4.app.Fragment {

    private Coctel actualCoctel;
    private TextView descripcion, ingredientes, preparacion, nombre, licores;


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
        ArrayList<String> IngredientesArray = actualCoctel.getIngredientes();

        for (int i = 0; i < IngredientesArray.size(); i++) {
            String nuevosIngredientes = IngredientesArray.get(i);
            stringIngredientes += (nuevosIngredientes + " \n ");
        }

        return stringIngredientes;
    }
        public void setCoctel(Bundle data){
            this.actualCoctel= new Coctel();
            this.actualCoctel.setNombre(data.getString("nombre"));
            this.actualCoctel.setDescripcion(data.getString("desc"));
            this.actualCoctel.setPreparacion(data.getString("prep"));
            this.actualCoctel.setLicores(data.getStringArrayList("licores"));
            this.actualCoctel.setIngredientes(data.getStringArrayList("ingredientes"));
        }

    public void setCoctel(Bundle data){
        this.actualCoctel= new Coctel();
        this.actualCoctel.setNombre(data.getString("nombre"));
        this.actualCoctel.setDescripcion(data.getString("desc"));
        this.actualCoctel.setPreparacion(data.getString("prep"));
        this.actualCoctel.setLicores(data.getStringArrayList("licores"));
        this.actualCoctel.setIngredientes(data.getStringArrayList("ingredientes"));
    }

}



