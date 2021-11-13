package ca.greenlypebble.it.smartplant;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import ca.greenlypebble.it.smartplant.ui.GoogleLoginActivity;

public class LogInActivity extends Activity {

    Button signIn;
    ImageButton googleLogIn;
    EditText emailAdd, passWord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);

        signIn = (Button) findViewById(R.id.signInbutton);
        emailAdd = (EditText) findViewById(R.id.editTextTextEmailAddress);
        passWord = (EditText) findViewById(R.id.editTextTextPassword);
        googleLogIn = (ImageButton) findViewById(R.id.googleLogin);


        //Google Sign In
        googleLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this, GoogleLoginActivity.class);
                startActivity(intent);
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

        });
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
