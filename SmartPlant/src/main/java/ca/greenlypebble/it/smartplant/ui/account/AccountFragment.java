//Deo Anthony Madrid (N01361264)
//Patrick Gomulka (N01347564)
//Erni Banaag (N01221990)
//Ricci Gamiao (N01363076)
package ca.greenlypebble.it.smartplant.ui.account;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
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

public class AccountFragment extends Fragment {

    Button  myProf, about, prefer, idNumber, rateApp, contactUs, portraitLock, signOut;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        new ViewModelProvider(this).get(Page3.class);
        View root = inflater.inflate(R.layout.fragment_account, container, false);

        myProf = (Button) root.findViewById(R.id.mpBtn);
        about = (Button) root.findViewById(R.id.abtBtn);
        prefer = (Button) root.findViewById(R.id.prefBtn);
        idNumber = (Button) root.findViewById(R.id.idBtn);
        rateApp = (Button) root.findViewById(R.id.rateBtn);
        contactUs = (Button) root.findViewById(R.id.cntBtn);
        portraitLock = (Button) root.findViewById(R.id.portraitButton);
        signOut = (Button) root.findViewById(R.id.sOBtn);

        myProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                alertDialogBuilder.setMessage(userEmail);

                alertDialogBuilder.show();
            }
        });
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
                alertDialogBuilder.setTitle(R.string.contactUS);
                alertDialogBuilder.setMessage(R.string.callOrEmail);

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
                        Intent intentMail = new Intent(Intent.ACTION_VIEW);
                        Uri data = Uri.parse("mailto:smartplant@gmail.com");
                        intentMail.setData(data);
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
                alertDialogBuilder.setTitle(R.string.sureSignOut);

                alertDialogBuilder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(getActivity(), LogInActivity.class);
                                        startActivity(intent);
                                    }
                                });

                alertDialogBuilder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                        alertDialogBuilder.show();
            }
}
