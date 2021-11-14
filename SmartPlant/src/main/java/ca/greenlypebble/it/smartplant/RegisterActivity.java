package ca.greenlypebble.it.smartplant;

import androidx.annotation.NonNull;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends Activity {

    EditText emailReg, passReg;
    TextView tvLoginHere;
    Button bRegister;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailReg = findViewById(R.id.eTextEmailReg);
        passReg = findViewById(R.id.eTextPassReg);
        tvLoginHere = findViewById(R.id.tvLoginHere);
        bRegister = findViewById(R.id.bRegister);

        mAuth = FirebaseAuth.getInstance();

        bRegister.setOnClickListener(view ->{
            registerProcess();
        });

        tvLoginHere.setOnClickListener(view ->{
            startActivity(new Intent(RegisterActivity.this, LogInActivity.class));
        });
    }

    private void registerProcess(){
        String email = emailReg.getText().toString();
        String password = passReg.getText().toString();

        if (TextUtils.isEmpty(email)){
            emailReg.setError("Please enter your Email");
            emailReg.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            passReg.setError("Please enter your Password");
            passReg.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LogInActivity.class));
                    }else{
                        Toast.makeText(RegisterActivity.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}