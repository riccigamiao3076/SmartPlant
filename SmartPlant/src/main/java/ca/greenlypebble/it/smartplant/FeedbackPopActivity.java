package ca.greenlypebble.it.smartplant;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FeedbackPopActivity extends Activity {

    Button sendDatabtn;

    EditText userName, userPhone, userEAdd;
    EditText feedback;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fback_pop);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .9), (int) (height * .8));

        WindowManager.LayoutParams par = getWindow().getAttributes();
        par.gravity = Gravity.CENTER;
        par.x = 0;
        par.y = 0;

        getWindow().setAttributes(par);

        sendDatabtn = findViewById(R.id.fBackButton);

        userName = findViewById(R.id.nameRev);
        userPhone = findViewById(R.id.numRev);
        userEAdd = findViewById(R.id.emailRev);
        feedback = findViewById(R.id.feedbackRev);

        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("Reviews");

        sendDatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitReview();
            }
        });
    }

    private void submitReview() {
        databaseReference = firebaseDatabase.getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (TextUtils.isEmpty(userName.getText()) || userName.getText().toString().length() < 4) {
                    Toast.makeText(FeedbackPopActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(feedback.getText()) || feedback.getText().toString().length() < 10) {
                    Toast.makeText(FeedbackPopActivity.this, "Please enter meaningful feedback", Toast.LENGTH_SHORT).show();
                } else {

                    databaseReference.child("User Feedbacks").child(userID.toString()).child("Phone").setValue(userPhone.getText().toString());
                    databaseReference.child("User Feedbacks").child(userID.toString()).child("Name").setValue(userName.getText().toString());
                    databaseReference.child("User Feedbacks").child(userID.toString()).child("Email").setValue(userEAdd.getText().toString());
                    databaseReference.child("User Feedbacks").child(userID.toString()).child("Feedback").setValue(feedback.getText().toString());

                    finish();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}