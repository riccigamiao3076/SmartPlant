// Deo Anthony Madrid (N01361264)
// Patrick Gomulka (N01347564)
// Erni Banaag (N01221990)
// Ricci Gamiao (N01363076)
package ca.greenlypebble.it.smartplant;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import ca.greenlypebble.it.smartplant.ui.dashboard.DashboardFragment;
import ca.greenlypebble.it.smartplant.ui.home.Page1;

public class MainActivity extends AppCompatActivity {

    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        //Firebase Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference accountLogin = database.getReference("Account");
        DatabaseReference temp = database.getReference("Temperature");
        DatabaseReference humid = database.getReference("Humidity");
        DatabaseReference soilMoisture = database.getReference("Soil Moisture");
        DatabaseReference lightLevels = database.getReference("Light Levels");
        DatabaseReference motionSensor = database.getReference("Moisture");

        accountLogin.setValue("Login Successful");
        temp.setValue("Temperature: 75 Degrees");
        humid.setValue("Humidity: 55%");
        soilMoisture.setValue("Soil Moisture: 50%");
        lightLevels.setValue("Light levels: 500 Lumens");
        motionSensor.setValue("Motion sensor: Active");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Intent cam;
        switch (item.getItemId()){
            case R.id.cameraMenu:
                Toast.makeText(this, "Opening Camera", Toast.LENGTH_SHORT).show();
                openCamera();
            break;
            case R.id.feedbackMenu:
                Toast.makeText(this, "Feedback Selected", Toast.LENGTH_SHORT).show();
            break;
            case R.id.statusMenu:
                Toast.makeText(this, "Status Selected", Toast.LENGTH_SHORT).show();
            break;
            case R.id.settingMenu:
                Toast.makeText(this, "Settings Selected", Toast.LENGTH_SHORT).show();
            default:
                return super.onOptionsItemSelected(item);
        }

        return false;
    }

    public void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }

    //Exit Message.
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Smart Plant")
                .setMessage(R.string.exitMsg)
                .setPositiveButton("Yes", (dialog, which) -> finish())
                .setNegativeButton("No", null)
                .show();
    }
}