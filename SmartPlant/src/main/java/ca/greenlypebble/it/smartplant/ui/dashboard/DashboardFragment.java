//Deo Anthony Madrid (N01361264)
//Patrick Gomulka (N01347564)
//Erni Banaag (N01221990)
//Ricci Gamiao (N01363076)
package ca.greenlypebble.it.smartplant.ui.dashboard;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import ca.greenlypebble.it.smartplant.FeedbackPopActivity;
import ca.greenlypebble.it.smartplant.HealthActivity;
import ca.greenlypebble.it.smartplant.HistoryActivity;
import ca.greenlypebble.it.smartplant.R;
import ca.greenlypebble.it.smartplant.SensorsActivity;

public class DashboardFragment extends Fragment {

    private Page2 page2;
    FloatingActionButton fabBtn,fadfeedbackBtn;

    ImageButton sensorButton, historyButton, healthButton, learnButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        page2 = new ViewModelProvider(this).get(Page2.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);


        sensorButton = (ImageButton) root.findViewById(R.id.sensorsButton);
        historyButton = (ImageButton) root.findViewById(R.id.historyButton);
        healthButton =  (ImageButton) root.findViewById(R.id.healthButton);
        learnButton = (ImageButton) root.findViewById(R.id.learnButton);
        fabBtn = (FloatingActionButton) root.findViewById(R.id.fabBtn);
        fadfeedbackBtn = (FloatingActionButton) root.findViewById(R.id.fabfeedbackBtn);



        //FAB Button
        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Health Plant 100%", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //FAB FeedBack Button
        fadfeedbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent feedbackPop = new Intent(getContext(), FeedbackPopActivity.class);
                startActivity(feedbackPop);
            }
        });


        sensorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sensorScreen = new Intent(getContext(), SensorsActivity.class);
                startActivity(sensorScreen);
            }
        });

        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historyScreen = new Intent(getContext(), HistoryActivity.class);
                startActivity(historyScreen);
            }
        });

        healthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent healthScreen = new Intent(getContext(), HealthActivity.class);
                startActivity(healthScreen);
            }
        });

        learnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent learnIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://can-plant.ca/"));
                startActivity(learnIntent);
            }
        });

        return root;


    }
}