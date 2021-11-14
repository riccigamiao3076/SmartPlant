package ca.greenlypebble.it.smartplant;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import ca.greenlypebble.it.smartplant.ui.GoogleLoginActivity;

public class LogInActivity extends Activity {

    Button signIn;
    ImageView googleLogIn;
    EditText emailAdd, passWord;
    CheckBox remember;
    CheckBox mCheckBoxRemember;
    SharedPreferences mPrefs;
    TextView signUp;
    final String PREFS_NAME = "PrefsFile";

    FirebaseAuth fBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);

        mPrefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        signIn = (Button) findViewById(R.id.signInbutton);
        signUp = (TextView) findViewById(R.id.tViewSignUp);
        emailAdd = (EditText) findViewById(R.id.editTextTextEmailAddress);
        passWord = (EditText) findViewById(R.id.editTextTextPassword);
        googleLogIn = (ImageView) findViewById(R.id.googleLogin);
        mCheckBoxRemember = (CheckBox) findViewById(R.id.checkBoxRemember);
        fBase = FirebaseAuth.getInstance();

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
            String email = emailAdd.getText().toString();
            String password = passWord.getText().toString();

            if (TextUtils.isEmpty(email)) {
                emailAdd.setError("Please enter your email");
                emailAdd.requestFocus();

            } else if (TextUtils.isEmpty(password)) {
                passWord.setError("Please enter your password");
                passWord.requestFocus();

            } else {
                fBase.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LogInActivity.this, "Signed in successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LogInActivity.this, MainActivity.class));
                        } else {
                            Toast.makeText(LogInActivity.this, "Sign in error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogInActivity.this, RegisterActivity.class));
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
