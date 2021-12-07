//Deo Anthony Madrid (N01361264)
//Patrick Gomulka (N01347564)
//Erni Banaag (N01221990)
//Ricci Gamiao (N01363076)
package ca.greenlypebble.it.smartplant.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import ca.greenlypebble.it.smartplant.FeedbackPopActivity;
import ca.greenlypebble.it.smartplant.MainActivity;
import ca.greenlypebble.it.smartplant.R;
import ca.greenlypebble.it.smartplant.RatePopActivity;
import ca.greenlypebble.it.smartplant.SensorsActivity;

public class DashboardFragment extends Fragment {

    private Page2 page2;

    ImageButton sensorButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        page2 = new ViewModelProvider(this).get(Page2.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);


        sensorButton = (ImageButton) root.findViewById(R.id.sensorsButton);

        sensorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sensorScreen = new Intent(getContext(), SensorsActivity.class);
                startActivity(sensorScreen);
            }
        });

        return root;


    }
}