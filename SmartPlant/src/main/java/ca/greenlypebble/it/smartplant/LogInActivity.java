package ca.greenlypebble.it.smartplant;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class LogInActivity extends Activity {

    Button signIn;
    EditText emailAdd, passWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);

        signIn = (Button) findViewById(R.id.signInbutton);
        emailAdd = (EditText) findViewById(R.id.editTextTextEmailAddress);
        passWord = (EditText) findViewById(R.id.editTextTextPassword);

        signIn.setOnClickListener(v -> {
            final String
                    checkEA = emailAdd.getText().toString(),
                    checkPW = passWord.getText().toString();

            if (checkEA.matches("admin") && checkPW.matches("admin")) {

                Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            else if (checkEA.matches("")) {
                Toast errorM = Toast.makeText(getApplicationContext(), "Please Enter Your Username.", Toast.LENGTH_SHORT);
                errorM.show();
            }

            else if (checkPW.matches("")) {
                Toast errorM = Toast.makeText(getApplicationContext(), "Please Enter Your Password.", Toast.LENGTH_SHORT);
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
