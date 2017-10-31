package pmh.prograudrink;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Registro extends AppCompatActivity {


    Button btnCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        btnCrear = (Button)findViewById(R.id.btnRegistrar);
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(), "Ingresa tus datos", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}
