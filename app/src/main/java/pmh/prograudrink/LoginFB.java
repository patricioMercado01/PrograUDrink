package pmh.prograudrink;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class LoginFB extends AppCompatActivity {
    private static final int RC_SIGN_IN = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_fb);

        FirebaseAuth auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser() != null){
            FirebaseUser firebaseUser = auth.getCurrentUser();
            Toast.makeText(this,"usuario logueado"+ firebaseUser.getDisplayName(),Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,MenuLateral.class));
        }else{
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setIsSmartLockEnabled(false).setAvailableProviders(Arrays.
                    asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build())).build(),RC_SIGN_IN);
        }
    }


    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if(resultCode == RESULT_OK){
                startActivity(new Intent(this, MenuLateral.class));
                finish();
                return;
            }else{
                Toast.makeText(this, "hubo un problema con el inicio de sesión",Toast.LENGTH_SHORT).show();
            }
        }

    }
}
