package pmh.prograudrink;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;

public class IngresoNuevoCoctel extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 234;

    private Button btnUpImage;
    private ImageView imageView;
    private EditText editDescripcion;
    private EditText editNombre;
    private EditText editPreparacion;
    private Spinner spinLicor;
    private Spinner spinIngrediente;
    private Button  btnLicor1;
    private Button  btnLicor2;
    private Button  btnLicor3;
    private Button  btnLicor4;
    private Button  btnIngrediente1;
    private Button  btnIngrediente2;
    private Button  btnIngrediente3;
    private Button  btnIngrediente4;





    private StorageReference storageReference;
    private DatabaseReference mDatabase;
    private Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_nuevo_coctel);

        
        imageView = (ImageView) findViewById(R.id.imgUpImage);
        btnUpImage = (Button) findViewById(R.id.btnUpImage);
        editDescripcion =(EditText) findViewById(R.id.editDescripcion);
        editNombre = (EditText)findViewById(R.id.editNombre);
        editPreparacion =(EditText)findViewById(R.id.editPreparacion);


        storageReference = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS);

        btnUpImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });

    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
