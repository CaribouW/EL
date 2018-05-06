package Activitys;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.lenovo.elapp.R;

import Fragments.Fragment_Lib;
import Fragments.MainActivityLeftFragment;
import Tmp_lib.BottomNavigationView_Lib;
import Tmp_lib.Music_lib;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationTask();
        NavigationViewTask();

    }

    private void NavigationViewTask() {
        NavigationView navigationView = findViewById(R.id.navigation_view_left);
        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {

            }
            return false;
        });
    }

    private void BottomNavigationTask() {
        Fragment_Lib.replaceFragment(this, new MainActivityLeftFragment(), R.id.root_Frame_layout);
        BottomNavigationView navigation = findViewById(R.id.MainActivityNavigation);
        navigation.setOnNavigationItemSelectedListener(
                BottomNavigationView_Lib.Get_OnNavigationItemselectedListener(this));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Music_lib.playExternal(new MediaPlayer(), "bgm1.mp3");//还未确定
                } else {
                    Toast.makeText(this, "拒绝权限无法访问", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    private boolean IsPermitted() {
        return ContextCompat.checkSelfPermission(MainActivity.this
                , Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(MainActivity.this
                , new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                }, 1);
    }
}
