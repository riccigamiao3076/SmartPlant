package ca.greenlypebble.it.smartplant;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HistoryActivity extends Activity {

    Button exitHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        exitHistory = (Button) findViewById(R.id.exitHistory);

        exitHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}