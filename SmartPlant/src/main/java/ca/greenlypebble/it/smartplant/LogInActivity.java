package ca.greenlypebble.it.smartplant;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import ca.greenlypebble.it.smartplant.ui.GoogleLoginActivity;

public class LogInActivity extends Activity {

    Button signIn;
    ImageView googleLogIn;
    EditText emailAdd, passWord;
    CheckBox remember;
    CheckBox mCheckBoxRemember;
    SharedPreferences mPrefs;
    final String PREFS_NAME = "PrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);

        mPrefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        signIn = (Button) findViewById(R.id.signInbutton);
        emailAdd = (EditText) findViewById(R.id.editTextTextEmailAddress);
        passWord = (EditText) findViewById(R.id.editTextTextPassword);
        googleLogIn = (ImageView) findViewById(R.id.googleLogin);
        mCheckBoxRemember = (CheckBox) findViewById(R.id.checkBoxRemember);

        getPreferencesData();


        //Google Sign In
        googleLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this, GoogleLoginActivity.class);
                startActivity(intent);

                emailAdd.getText().clear();
                passWord.getText().clear();

            }
        });


        signIn.setOnClickListener(v -> {
            final String
                    checkEA = emailAdd.getText().toString(),
                    checkPW = passWord.getText().toString();

            if (checkEA.matches("admin") && checkPW.matches("admin")) {

                Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                Toast success = Toast.makeText(getApplicationContext(), "Sucessfully Signed in.", Toast.LENGTH_SHORT);
                success.show();
            }

            else if (checkEA.matches("")) {
                Toast errorM = Toast.makeText(getApplicationContext(), "Please Valid Username.", Toast.LENGTH_SHORT);
                errorM.show();
            }

            else if (checkPW.matches("")) {
                Toast errorM = Toast.makeText(getApplicationContext(), "Please Enter Password.", Toast.LENGTH_SHORT);
                errorM.show();
            }

            else {
                errorM();
            }
            if (mCheckBoxRemember.isChecked()) {
                Boolean boolIsChecked = mCheckBoxRemember.isChecked();
                SharedPreferences.Editor editor = mPrefs.edit();
                editor.putString("pref_name", checkEA);
                editor.putString("pref_pass", checkPW);
                editor.putBoolean("pref_check", boolIsChecked);
                editor.apply();
                Toast.makeText(getApplicationContext(),"Settings have been saved",
                        Toast.LENGTH_SHORT).show();
            }else{
                mPrefs.edit().clear().apply();
            }

        });
    }

    private void getPreferencesData() {
        SharedPreferences sp = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        if (sp.contains("pref_name")) {
            String u = sp.getString("pref_name", "not found");
            emailAdd.setText(u.toString());
        }
        if (sp.contains("pref_pass")) {
            String p = sp.getString("pref_pass", "not found");
            passWord.setText(p.toString());
        }
        if (sp.contains("pref_check"));
            Boolean b = sp.getBoolean("pref_check", false);
            mCheckBoxRemember.setChecked(b);


        }

    private void errorM() {

        new androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("Sign In Failed!")
            .setMessage("Invalid Username or Password.\nEnter ''admin'' for both fields to login.")
            .setIcon(R.drawable.ic_baseline_error_24)

            .setCancelable(true)
            .setPositiveButton("Try Again", null)
            .show();
    }
}
