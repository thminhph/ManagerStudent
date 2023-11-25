package com.example.managerstudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.managerstudent.Linh.L_BUS_Diem;
import com.example.managerstudent.Linh.L_BUS_ThongTin;
import com.example.managerstudent.Minh.KhoaActivity;
import com.example.managerstudent.fragment.Fragment_Home;
import com.google.android.material.navigation.NavigationView;

public class Activity_Main extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    ActionBarDrawerToggle toggle;

    //giup phat hien fragment nao dang mo
    private static final int FRAGMENT_HOME = 0;

    //FRAGMENT DANG CHAY
    //gán nó mặc định là Fragment Home
    private int nowFragment = FRAGMENT_HOME;

    //---------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_gui_nav_action_bar);

        setControls();
        setEvents();
    }

    private void setControls() {
        drawerLayout = findViewById(R.id.drawer_Layout);
        toolbar = findViewById(R.id.nav_top_toobar);
        navigationView = findViewById(R.id.nav_view);
    }

    private void setEvents() {
        //dua toolbar vao
        setSupportActionBar(toolbar);

        //click open/close nav
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //tao su kien khi nhan menu item: home, info, score
        navigationView.setNavigationItemSelectedListener(this);

        //Mở APP mặc định vào Home Fragment
        replaceFragment(new Fragment_Home());
        navigationView.getMenu().findItem(R.id.menu_nav_Home).setChecked(true);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_nav_Home) {
            if (nowFragment != FRAGMENT_HOME) {
                replaceFragment(new Fragment_Home());
                nowFragment = FRAGMENT_HOME;
                navigationView.getMenu().findItem(R.id.menu_nav_Home).setChecked(true);
            }
        }

        if (id == R.id.menu_nav_Info) {
            Intent intent = new Intent(this, L_BUS_ThongTin.class);
            startActivity(intent);
        }

        if (id == R.id.menu_nav_Score) {
            Intent intent = new Intent(this, L_BUS_Diem.class);
            startActivity(intent);
        }

        if (id == R.id.menu_nav_Khoa) {
            Intent intent = new Intent(this, KhoaActivity.class);
            startActivity(intent);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_layout_replace, fragment);
        fragmentTransaction.commit();

    }

//    private void defaultMenuSelect() {
//        navigationView.getMenu().findItem(R.id.menu_nav_home).setChecked(false);
//        navigationView.getMenu().findItem(R.id.menu_nav_info).setChecked(false);
//        navigationView.getMenu().findItem(R.id.menu_nav_score).setChecked(false);
//    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
            //neu nav dang mo, nhan nut 'back' se dong no lai, roi thoat app
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (toggle.onOptionsItemSelected(item))
//            return true;
//        return super.onOptionsItemSelected(item);
//    }
}