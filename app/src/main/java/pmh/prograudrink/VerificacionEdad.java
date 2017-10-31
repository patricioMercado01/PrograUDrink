package pmh.prograudrink;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class VerificacionEdad extends AppCompatActivity {

    Button siguiente;
    Button no;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificacion_edad);

        siguiente = (Button)findViewById(R.id.btnSi);
        no = (Button)findViewById(R.id.btnNo);

        siguiente.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View w){

                Intent siguiente = new Intent(VerificacionEdad.this, MenuLateral.class);
                startActivity(siguiente);

            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast toast = Toast.makeText(getApplicationContext(), "Solo mayores de edad", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }
}
