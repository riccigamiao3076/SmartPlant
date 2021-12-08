package ca.greenlypebble.it.smartplant;

import androidx.annotation.NonNull;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends Activity {

    EditText emailReg, pass1Reg, nameReg, pass2Reg, numReg;
    TextView tvLoginHere;
    Button bRegister;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailReg = findViewById(R.id.eTextEmailReg);
        pass1Reg = findViewById(R.id.eTextPassReg);
        nameReg = findViewById(R.id.eTextFullName);
        pass2Reg = findViewById(R.id.eTextConfirmPass);
        numReg = findViewById(R.id.eTextPhoneNum);
        tvLoginHere = findViewById(R.id.tvLoginHere);
        bRegister = findViewById(R.id.bRegister);

        mAuth = FirebaseAuth.getInstance();

        bRegister.setOnClickListener(view -> {
            registerProcess();
        });

        tvLoginHere.setOnClickListener(view -> {
            startActivity(new Intent(RegisterActivity.this, LogInActivity.class));
        });
    }

    private void registerProcess() {
        String fName = nameReg.getText().toString();
        String email = emailReg.getText().toString();
        String password1 = pass1Reg.getText().toString();
        String password2 = pass2Reg.getText().toString();
        String num = numReg.getText().toString();

        boolean isValidName = RegisterActivity.checkfNameForValidity(fName);
        boolean isValidEmail = RegisterActivity.checkEmailForValidity(email);
        boolean isValidpWord1 = RegisterActivity.checkpWord1ForValidity(password1);

        if (fName.isEmpty() || fName.length() < 6) {
            Toast.makeText(getApplicationContext(), "Name must be at least 6 characters", Toast.LENGTH_LONG).show();
        } else if (!isValidName) {
            Toast.makeText(getApplicationContext(), "Please enter valid name", Toast.LENGTH_LONG).show();
        } else if (!isValidEmail) {
            Toast.makeText(getApplicationContext(), "Please enter valid email address", Toast.LENGTH_LONG).show();
        } else if (password2.isEmpty()) {
            pass2Reg.setError("Please confirm your password");
            pass2Reg.requestFocus();
        } else if (!isValidpWord1) {
            Toast.makeText(getApplicationContext(), "Password must contain at least one uppercase, one lowercase and a special character.\n" +
                    "Except + and ~", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(password1) || password1.length() < 8 ) {
            pass1Reg.setError("Password must be at least 8 characters");
            pass1Reg.requestFocus();
        } else if (!password2.matches(password1)) {
            pass2Reg.setError("Passwords do not match!");
            pass2Reg.requestFocus();
        } else if(num.isEmpty() || num.length() < 10) {
            Toast.makeText(getApplicationContext(), "Please enter valid number", Toast.LENGTH_LONG).show();
        } else {

            mAuth.createUserWithEmailAndPassword(email, password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, R.string.registerSuccess, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LogInActivity.class));
                    } else {
                        Toast.makeText(RegisterActivity.this, "Registration Unsuccessful: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public static boolean checkfNameForValidity(String fName) {

        Pattern fNameRegex =
                Pattern.compile("^[a-zA-z ]*$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = fNameRegex.matcher(fName);
        return matcher.find();
    }

    public static boolean checkEmailForValidity(String email) {

        Pattern eAddRegex =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        email = email.trim();
        Matcher matcher = eAddRegex.matcher(email);
        return matcher.find();

    }

    public static boolean checkpWord1ForValidity(String pWord1) {
        Pattern pWord1Regex =
                Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&=])(?=\\S+$).{4,}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pWord1Regex.matcher(pWord1);
        return matcher.find();
    }
}

