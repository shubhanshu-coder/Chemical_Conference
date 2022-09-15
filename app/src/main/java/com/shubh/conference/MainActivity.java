package com.shubh.conference;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MaterialToolbar toolbar = findViewById(R.id.topAppbar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                item.setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);
                Fragment fragment=null;
                switch (id) {
                    case R.id.navhome:
                        replacefragment(new HomeFragment());
                        break;
                    case R.id.navabout:
                        replacefragment(new AboutFragment());
                        break;
                    case R.id.navabstract:
                        replacefragment(new AbtractFragment());
                        break;
                    case R.id.navac:
                        replacefragment(new ACFragment());
                        break;
                    case R.id.navbrochure:
                        replacefragment(new BrochureFragment());
                        break;
                    case R.id.navoc:
                        replacefragment(new OCFragment());
                        break;
                    case R.id.navcontact:
                        replacefragment(new ContactFragment());
                        break;
                    default:
                        replacefragment(new HomeFragment());
                        break;

                }
                return true;
            }
        });
    }
    private void replacefragment(Fragment  fragment)
    {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();


    }
}