package pmh.prograudrink;

/**
 * Created by Jess on 20-11-2017.
 */
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class GestorJson {

    public String coctelToJson(Coctel p) {

        Gson gson = new GsonBuilder().create();
        String salida = gson.toJson(p);
        return salida;
    }

    public Coctel jsonToCoctel(String json){
        Gson objJson = new Gson();
        Coctel jsonCoctel = objJson.fromJson(json, Coctel.class);
        return jsonCoctel;
    }

}
