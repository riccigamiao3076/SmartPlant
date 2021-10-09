package ca.greenlypebble.it.smartplant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class LogInActivity extends Activity {

    Button signIn;
    EditText emailAdd, passWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);

        // BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.


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
            }

            else {


            }

        });
    }
}
