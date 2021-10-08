// Deo Anthony Madrid (N01361264)
// Patrick Gomulka (N01347564)
// Erni Banaag (N01221990)
// Ricci Gamiao (N01363076)
package ca.greenlypebble.it.smartplant;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import ca.greenlypebble.it.smartplant.ui.dashboard.DashboardFragment;
import ca.greenlypebble.it.smartplant.ui.home.Page1;

public class MainActivity extends AppCompatActivity {

    Button signIn;
    EditText emailAdd, passWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        signIn = (Button) findViewById(R.id.signInbutton);
        emailAdd = (EditText) findViewById(R.id.editTextTextEmailAddress);
        passWord = (EditText) findViewById(R.id.editTextTextPassword);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String
                        checkEA = emailAdd.getText().toString(),
                        checkPW = passWord.getText().toString();

                if (checkEA.matches("admin") && checkPW.matches("admin") )
                    setContentView(R.layout.fragment_page2);

                else {

                }
                // NavigationUI.setupWithNavController(navView, navController);
            }
        });
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Smart Plant")
                .setMessage("Are you sure you want to close Smart Plant?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}