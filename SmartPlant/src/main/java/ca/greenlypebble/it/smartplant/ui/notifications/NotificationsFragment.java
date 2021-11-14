//Deo Anthony Madrid (N01361264)
//Patrick Gomulka (N01347564)
//Erni Banaag (N01221990)
//Ricci Gamiao (N01363076)
package ca.greenlypebble.it.smartplant.ui.notifications;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;

import ca.greenlypebble.it.smartplant.LogInActivity;
import ca.greenlypebble.it.smartplant.R;
import ca.greenlypebble.it.smartplant.RatePopActivity;

public class NotificationsFragment extends Fragment {


    Button  myProf,
            idNumber,
            prefer,
            contactUs,
            about,
            rateApp,
            signOut,
            portraitLock;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        new ViewModelProvider(this).get(Page3.class);
        View root = inflater.inflate(R.layout.fragment_page3, container, false);

        myProf = (Button) root.findViewById(R.id.mpBtn);
        idNumber = (Button) root.findViewById(R.id.idBtn);
        prefer = (Button) root.findViewById(R.id.prefBtn);
        contactUs = (Button) root.findViewById(R.id.cntBtn);
        about = (Button) root.findViewById(R.id.abtBtn);
        rateApp = (Button) root.findViewById(R.id.rateBtn);
        signOut = (Button) root.findViewById(R.id.sOBtn);
        portraitLock = (Button) root.findViewById(R.id.portraitButton);

        idNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                alertDialogBuilder.setMessage(userID);

                alertDialogBuilder.show();
            }
        });

        rateApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ratePopup = new Intent(getContext(), RatePopActivity.class);
                startActivity(ratePopup);
            }
        });

        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactOptions();
            }

            private void contactOptions() {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                alertDialogBuilder.setTitle("Contact Us");
                alertDialogBuilder.setMessage("You can call or email us now.");

                alertDialogBuilder.setPositiveButton(Html.fromHtml("<font color='#0000FF'>Call</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intentCall=new Intent(Intent.ACTION_DIAL);
                        intentCall.setData(Uri.parse("tel:6475718154"));
                        startActivity(intentCall);

                    }
                });

                alertDialogBuilder.setNegativeButton(Html.fromHtml("<font color='#0000FF'>Email</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intentMail = new Intent(Intent.ACTION_SENDTO);
                        intentMail.setData(Uri.parse("mailto:"));
                        intentMail.putExtra(Intent.EXTRA_EMAIL, new String[]{"smartplant@gmail.com"});
                        startActivity(intentMail);                  }
                });

                alertDialogBuilder.setNeutralButton(Html.fromHtml("<font color='#FF0000'>Cancel</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialogBuilder.show();
            }
        });

        portraitLock.setOnClickListener(v -> getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT));


        signOut.setOnClickListener(v -> {

            alertSignOut();

        });
        return root;

    }
            private void alertSignOut() {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                alertDialogBuilder.setTitle("Are you sure you want to sign out?");

                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(getActivity(), LogInActivity.class);
                                        startActivity(intent);
                                    }
                                });

                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                        alertDialogBuilder.show();
            }
}
