package pmh.prograudrink;

import java.util.ArrayList;

/**
 * Created by Jess on 20-11-2017.
 */

public class Coctel {

    private int id;
    private ArrayList<String> licores;
    private ArrayList<String> ingredientes;
    private String nombre;
    private String descripcion;
    private String preparacion;

    public Coctel(ArrayList<String> licores, ArrayList<String> ingredientes, String nombre, String descripcion, String preparacion) {
        this.licores = licores;
        this.ingredientes = ingredientes;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.preparacion = preparacion;
    }

    public Coctel() {

        this.licores = new ArrayList<>();
        this.ingredientes = new ArrayList<>();

    }

    public void definirCoctel(String n, String prep, String desc) {
        this.nombre = n;
        this.preparacion = prep;
        this.descripcion = desc;

    }

    public void test() {
        licores.add("Ron Blanco");
        ingredientes.add("Azúcar flor");
        ingredientes.add("Menta");
        ingredientes.add("Jugo de limón");
        ingredientes.add("Hielo");
    }

    public ArrayList<String> getLicores() {
        return this.licores;
    }

    public ArrayList<String> getIngredientes() {
        return this.ingredientes;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public String getPreparacion() {
        return this.preparacion;
    }

    public int getId() {
        return id;
    }
}