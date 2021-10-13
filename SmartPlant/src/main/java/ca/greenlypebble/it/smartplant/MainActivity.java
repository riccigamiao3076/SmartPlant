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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import ca.greenlypebble.it.smartplant.ui.dashboard.DashboardFragment;
import ca.greenlypebble.it.smartplant.ui.home.Page1;

public class MainActivity extends AppCompatActivity {

    private int openCamOne = 1;

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
        switch (item.getItemId()){
            case R.id.cameraMenu:
                requestStoragePermission();
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

    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)) {

            new AlertDialog.Builder(this)
                    .setMessage("Allow Smart Plant to open Camera?")
                    .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, openCamOne);
                        }
                    })
                    .setNegativeButton("Deny", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, openCamOne);
        }
    }

    @Override
    public void onRequestPermissionsResult(int theCode, @NonNull String[] thePer, @NonNull int[] theResult) {
        super.onRequestPermissionsResult(theCode, thePer, theResult);
        if (theCode == openCamOne) {
            if (theResult.length > 0 && theResult[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
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