package pmh.prograudrink;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class Splash_Screen extends AppCompatActivity {
    private final int DURACION_SPLASH = 2000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash__screen);

        new Handler().postDelayed(new Runnable(){
            public void run(){
                Intent intent = new Intent(Splash_Screen.this, VerificacionEdad.class);
                startActivity(intent);
                finish();
            };
        }, DURACION_SPLASH);
    }
}
