package ca.greenlypebble.it.smartplant;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HealthActivity extends Activity {

    Button addPlant, exitHealth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        addPlant = (Button) findViewById(R.id.addPlant);
        exitHealth = (Button) findViewById(R.id.exitHealth);

        exitHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}