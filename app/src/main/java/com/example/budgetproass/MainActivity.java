package com.example.budgetproass;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import android.view.Menu;
import android.widget.TextView;

import com.example.budgetproass.dialogThu.LoaiThuDialog;
import com.example.budgetproass.dialogThu.ThuDialog;
import com.example.budgetproass.dialogChi.ChiDialog;
import com.example.budgetproass.dialogChi.LoaiChiDialog;
import com.example.budgetproass.ui.chi.KhoanChiFragment;
import com.example.budgetproass.ui.chi.LoaiChiFragment;
import com.example.budgetproass.ui.thu.KhoanThuFragment;
import com.example.budgetproass.ui.thu.LoaiThuFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.budgetproass.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    Intent intent = null;
    TextView tvUserName;
    String strUsername = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(checkLoginRemember()<0){
            intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
        }
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        final MainActivity currentContext = this;
        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Fragment> fragments = getSupportFragmentManager().getFragments();
                Fragment fragment = null;
                for (int i = 0; i < fragments.size(); i++) {
                    if (fragments.get(i).isVisible()){
                        fragment = fragments.get(i);
                    }
                }

                if (fragment instanceof LoaiThuFragment){
                    LoaiThuDialog dialog = new LoaiThuDialog(currentContext, (LoaiThuFragment) fragment);
                    dialog.show();
                }else if (fragment instanceof KhoanThuFragment){
                    ThuDialog dialog = new ThuDialog(currentContext, (KhoanThuFragment) fragment);
                    dialog.show();
                }

                if (fragment instanceof LoaiChiFragment){
                    LoaiChiDialog dialog = new LoaiChiDialog(currentContext, (LoaiChiFragment) fragment);
                    dialog.show();
                }else if (fragment instanceof KhoanChiFragment){
                    ChiDialog dialog = new ChiDialog(currentContext, (KhoanChiFragment) fragment);
                    dialog.show();
                }
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if (destination.getId()==R.id.nav_thoat){
                    intent = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public int checkLoginRemember(){
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        boolean chk = sharedPreferences.getBoolean("REMEMBER",false);
        if(chk){
            strUsername = sharedPreferences.getString("USERNAME","");
            return 1;
        }
        return -1;
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}