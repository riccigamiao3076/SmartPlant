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
        String email = emailReg.getText().toString();
        String password1 = pass1Reg.getText().toString();
        String fName = nameReg.getText().toString();
        String password2 = pass2Reg.getText().toString();
        String num = "+"+numReg.getText().toString();

        String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&=])(?=\\S+$).{4,}$";

        if (TextUtils.isEmpty(fName) || fName.length() < 6) {
            nameReg.setError("Please enter your full name and at least 6 characters");
            nameReg.requestFocus();
        } else if (TextUtils.isEmpty(email)) {
            emailReg.setError(getString(R.string.enterEmail));
            emailReg.requestFocus();
        } else if (TextUtils.isEmpty(password1) || password1.length() < 8 ) {
            pass1Reg.setError("Password must be at least 8 characters");
            pass1Reg.requestFocus();
        }  else if (TextUtils.isEmpty(password2)) {
            pass2Reg.setError("Please confirm your password");
            pass2Reg.requestFocus();
        } else if (TextUtils.isEmpty(num) || num.length() < 10) {
            numReg.setError("Please enter valid number");
            numReg.requestFocus();
        } else if (!password1.matches(PASSWORD_PATTERN)) {
            pass1Reg.setError("Password must contain at least one uppercase, one lowercase and a special character.\n" +
                    "Except + and ~");
            pass1Reg.requestFocus();
        } else if (!password2.matches(password1)) {
            pass2Reg.setError("Password do not match!");
            pass2Reg.requestFocus();
        }

        else {
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
}

