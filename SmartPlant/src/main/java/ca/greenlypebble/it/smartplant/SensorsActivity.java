package ca.greenlypebble.it.smartplant;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SensorsActivity extends Activity {

    Button addSensor, exitSensor, readBtn;
    DatabaseReference tempDatabase, humidDatabase;
    TextView tempVal, humidVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);
        exitSensor = (Button) findViewById(R.id.exitSensor);
        readBtn = (Button) findViewById(R.id.readBtn);
        tempVal = (TextView) findViewById(R.id.tempVal);
        humidVal = (TextView) findViewById(R.id.humidVal);

        tempDatabase = FirebaseDatabase.getInstance().getReference().child("Temparature");
        humidDatabase = FirebaseDatabase.getInstance().getReference().child("Humidity");

        exitSensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists())
                        {
                            String data=snapshot.getValue().toString();
                            tempVal.setText(data);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                humidDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists())
                        {
                            String data=snapshot.getValue().toString();
                            humidVal.setText(data);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}