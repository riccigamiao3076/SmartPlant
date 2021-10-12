//Deo Anthony Madrid (N01361264)
//Patrick Gomulka (N01347564)
//Erni Banaag (N01221990)
//Ricci Gamiao (N01363076)
package ca.greenlypebble.it.smartplant.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import ca.greenlypebble.it.smartplant.R;

public class NotificationsFragment extends Fragment {


    Button myProf,
            idNumber,
            prefer,
            cntctUs,
            about,
            rateApp;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        new ViewModelProvider(this).get(Page3.class);
        View root = inflater.inflate(R.layout.fragment_page3, container, false);

        myProf = (Button) root.findViewById(R.id.mpBtn);
        idNumber = (Button) root.findViewById(R.id.idBtn);
        prefer = (Button) root.findViewById(R.id.prefBtn);
        cntctUs = (Button) root.findViewById(R.id.cntBtn);
        about = (Button) root.findViewById(R.id.abtBtn);
        rateApp = (Button) root.findViewById(R.id.rateBtn);

        return root;
    }
}
