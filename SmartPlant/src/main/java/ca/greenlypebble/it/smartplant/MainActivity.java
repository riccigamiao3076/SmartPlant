// Deo Anthony Madrid (N01361264)
// Patrick Gomulka (N01347564)
// Erni Banaag (N01221990)
// Ricci Gamiao (N01363076)

package ca.greenlypebble.it.smartplant;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
//The two design patterns used are Structural and Behavioral design patterns.
    //The two design principles used are Keep it simple, Stupid!, and Don't Repeat Yourself.
    private int openCamOne = 1;
    Button lightBtn;
    private DatabaseReference rootDatabaseRef;
    private DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    private Button readName;
    private Button updateBtn;
    private TextView plantName;
    private TextView status;
    private EditText updateName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseDatabase = FirebaseDatabase.getInstance();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("Notification", "Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        //Notifications
        lightBtn = findViewById(R.id.lightButton);

        lightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this,"Notification");
                builder.setContentTitle("Light Notification");
                builder.setContentText("Lights turned on");
                builder.setSmallIcon(R.drawable.ic_launcher_foreground);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainActivity.this);
                managerCompat.notify(1,builder.build());
            }
        });

        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        databaseReference = firebaseDatabase.getReference();
        databaseReference.child("User Info").child(userID).child("Account").setValue("Active");
        databaseReference.child("User Info").child(userID.toString()).child("Humidity").setValue("55%");
        databaseReference.child("User Info").child(userID.toString()).child("Light Levels").setValue("500 Lumens");
        databaseReference.child("User Info").child(userID.toString()).child("Motion Sensor").setValue("Active");
        databaseReference.child("User Info").child(userID.toString()).child("Temperature").setValue("75 Degrees");
        databaseReference.child("User Info").child(userID.toString()).child("Login").setValue("Succesful");

        //Update Plant Name Database:
        plantName = findViewById(R.id.smartPlant);
        updateBtn = findViewById(R.id.updatePlant);
        updateName = findViewById(R.id.plantNameUpdate);
        status = findViewById(R.id.statusText);
        rootDatabaseRef = databaseReference.child("User Info").child(userID).child(getString(R.string.plantName));

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick (View v){
                rootDatabaseRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists())
                        {
                            String data = snapshot.getValue().toString();
                            plantName.setText(data);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                String data = updateName.getText().toString();
                rootDatabaseRef.setValue(data);
            }
            });
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
//                Toast.makeText(this, R.string.feedbackSelected, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, FeedbackPopActivity.class));
//                feedbackDialog();
            break;
            case R.id.statusMenu:
                databaseReference = FirebaseDatabase.getInstance().getReference().child("Status");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists())
                        {
                            String data = snapshot.getValue().toString();
                            status.setText(data);
                            new Handler().postDelayed(new Runnable() {

                                @Override
                                public void run() {
                                    status.setText("");
                                }
                            }, 4000 );//time in millisecond minimizes
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        System.out.println(getString(R.string.databaseFailed));
                    }
                });

            break;
            case R.id.settingMenu:
                setContentView(R.layout.fragment_account);
                Toast.makeText(this, R.string.settingSelected, Toast.LENGTH_SHORT).show();
            default:
                return super.onOptionsItemSelected(item);
        }

        return false;
    }

    private void feedbackDialog() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.feedbackImportant)
                .setMessage(R.string.accountRateFeedback)
                .setPositiveButton(R.string.ok, (dialog, which) -> dialog.cancel())
                .show();
    }

    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)) {

            new AlertDialog.Builder(this)
                    .setMessage(R.string.smartPlantOpenCam)
                    .setPositiveButton(R.string.allow, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, openCamOne);
                        }
                    })
                    .setNegativeButton(R.string.deny, new DialogInterface.OnClickListener() {
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
                Toast.makeText(this, R.string.permGrant, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
            } else {
                Toast.makeText(this, R.string.permDeny, Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Exit Message.
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(R.string.smart_plant)
                .setMessage(R.string.exitMsg)
                .setPositiveButton(R.string.yes, (dialog, which) -> finish())
                .setNegativeButton(R.string.no, null)
                .show();
    }
}