package ca.greenlypebble.it.smartplant;

import android.app.Activity;
import android.os.Bundle;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RatePopActivity extends Activity {

    RatingBar rateBar;
    Button sendDatabtn;

//    EditText userName, userPhone, userEAdd;
    TextView ratingInfotv;
//    EditText feedback;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_pop);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .6), (int) (height * .4));

        WindowManager.LayoutParams par = getWindow().getAttributes();
        par.gravity = Gravity.CENTER;
        par.x = 0;
        par.y = 0;

        getWindow().setAttributes(par);

        rateBar = findViewById(R.id.ratingBar);
        sendDatabtn = findViewById(R.id.popRateButton);

//        userName = findViewById(R.id.nameRev);
//        userPhone = findViewById(R.id.numRev);
//        userEAdd = findViewById(R.id.emailRev);
//        feedback = findViewById(R.id.feedbackRev);
//
        ratingInfotv = findViewById(R.id.tvRate);
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("Reviews");

        rateBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ((TextView) findViewById(R.id.tvRate)).setText("" + rating);
            }
        });

        sendDatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitReview();
                finish();
            }
        });
    }

        private void submitReview() {
            databaseReference = firebaseDatabase.getReference();
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

//            databaseReference.child("User Reviews").child(userName.getText().toString()).child("Phone").setValue(userPhone.getText().toString());
//            databaseReference.child("User Reviews").child(userName.getText().toString()).child("Name").setValue(userName.getText().toString());
//            databaseReference.child("User Reviews").child(userName.getText().toString()).child("Email").setValue(userEAdd.getText().toString());
            databaseReference.child("Ratings").child("Rate").setValue(ratingInfotv.getText().toString());
//            databaseReference.child("User Reviews").child(userName.getText().toString()).child("Feedback").setValue(feedback.getText().toString());


    }
}