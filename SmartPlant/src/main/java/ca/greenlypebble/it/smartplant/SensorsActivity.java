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

    Button exitSensor, readBtn;
    DatabaseReference tempDatabase, humidDatabase, motionDatabase, lightDatabase, waterDatabase;
    TextView tempVal, humidVal, motionVal, lightVal, waterVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);

        exitSensor = (Button) findViewById(R.id.exitSensor);
        readBtn = (Button) findViewById(R.id.readBtn);
        tempVal = (TextView) findViewById(R.id.tempVal);
        humidVal = (TextView) findViewById(R.id.humidVal);
        motionVal = (TextView) findViewById(R.id.motionVal);
        lightVal = (TextView) findViewById(R.id.lightVal);
        waterVal = (TextView) findViewById(R.id.waterVal);

        tempDatabase = FirebaseDatabase.getInstance().getReference().child("DHT22").child("Temperature").child("Degree Celsius");
        humidDatabase = FirebaseDatabase.getInstance().getReference().child("DHT22").child("Humidity").child("Percentage");
        motionDatabase = FirebaseDatabase.getInstance().getReference().child("User Info").child("GuOK1xzu0SPiutMh2yzG7TVVQnA3").child("Motion Sensor");
        lightDatabase = FirebaseDatabase.getInstance().getReference().child("User Info").child("GuOK1xzu0SPiutMh2yzG7TVVQnA3").child("Light Sensor");
        waterDatabase = FirebaseDatabase.getInstance().getReference().child("User Info").child("GuOK1xzu0SPiutMh2yzG7TVVQnA3").child("Water level");

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
                            tempVal.setText(data+ " °C");
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
                            humidVal.setText(data + " %");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                motionDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists())
                        {
                            String data=snapshot.getValue().toString();
                            motionVal.setText(data);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                lightDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists())
                        {
                            String data=snapshot.getValue().toString();
                            lightVal.setText(data);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                waterDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists())
                        {
                            String data=snapshot.getValue().toString();
                            waterVal.setText(data);
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