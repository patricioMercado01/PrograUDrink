package pmh.prograudrink;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    Button btnRegistro;
    Button btnEntrar;
    EditText loginPass,loginName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.loginName = (EditText)findViewById(R.id.loginNameEdit);
        this.loginPass = (EditText)findViewById(R.id.loginPassEdit);
        this.btnRegistro = (Button)findViewById(R.id.btnRegistrar);
        this.btnEntrar = (Button)findViewById(R.id.btnIngresarLog);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, MenuLateral.class);
                startActivity(intent);

            }
        });
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Registro.class);
                startActivity(intent);

            }
        });

       this.loginName.setOnClickListener(new View.OnClickListener(){

           @Override
           public void onClick(View view) {
               loginName.setText("");
           }
       });
       this.loginPass.setOnClickListener(new View.OnClickListener(){

           @Override
           public void onClick(View view) {
               loginPass.setText("");
           }
       });

    }


}
