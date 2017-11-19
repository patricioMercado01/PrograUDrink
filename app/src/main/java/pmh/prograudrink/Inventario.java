package pmh.prograudrink;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Inventario extends AppCompatActivity {
    private SectionsPageAdapter mSectionPageAdapter;
    private ViewPager mViewPager;
    private static final  String TAG = "MainActivity";
    private FloatingActionButton agregar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser() == null){
            Intent siguiente = new Intent(Inventario.this, LoginFB.class);
            startActivity(siguiente);
            Inventario.this.overridePendingTransition(1,1);
            finish();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);
        Log.d(TAG,"onCreate: Starting.");



        mSectionPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager)findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
         agregar = (FloatingActionButton)findViewById(R.id.flotAgregar);

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cambio = new Intent(Inventario.this,IngresoNuevoCoctel.class);
                startActivity(cambio);
                finish();
            }
        });
    }

    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Licores());
        adapter.addFragment(new Ingredientes());
        viewPager.setAdapter(adapter);
    }
}
