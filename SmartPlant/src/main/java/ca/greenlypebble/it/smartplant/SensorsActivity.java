package ca.greenlypebble.it.smartplant;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SensorsActivity extends Activity {

    Button addSensor, exitSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);

        addSensor = (Button) findViewById(R.id.addSensor);
        exitSensor = (Button) findViewById(R.id.exitSensor);

        exitSensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}