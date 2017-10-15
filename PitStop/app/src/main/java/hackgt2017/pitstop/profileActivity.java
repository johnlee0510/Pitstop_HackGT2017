package hackgt2017.pitstop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.List;

/**
 * Created by AustinJ on 10/15/17.
 */

public class profileActivity extends AppCompatActivity implements View.OnClickListener {
    private String addNew;

    private static final List<String> legalTypes = Arrays.asList("Regular User", "Worker", "Manager", "Admin");
    private static int Next_Id = 0;

    public static int findPosition(String code) {
        int i = 0;
        while (i < legalTypes.size()) {
            if (code.equals(legalTypes.get(i))) return i;
            ++i;
        }
        return 0;
    }

    // FIREBASE
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    // UI ELEMENTS
    private EditText tvName;
    private EditText tvEmail;
    private EditText tvPhone;

    private Button cancelProfileButton;
    private Button saveProfileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        // FIREBASE INITIALIZATION
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {
            //user is not logged in
            finish();
            startActivity(new Intent(this, LoginScreen.class));
        }
        databaseReference = FirebaseDatabase.getInstance().getReference();

        // SET UP UI ELEMENTS
        tvName = (EditText) findViewById(R.id.nameInput);
        tvEmail = (EditText) findViewById(R.id.emailInput);
        tvPhone = (EditText) findViewById(R.id.phoneInput);

        //read data back from database and call setText on EditText widgets
        FirebaseUser user = firebaseAuth.getCurrentUser();
        DatabaseReference nameReference = databaseReference.child("Users").child(user.getUid());
        ValueEventListener nameListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("name").getValue().toString();
                tvName.setText(name, TextView.BufferType.EDITABLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        nameReference.addValueEventListener(nameListener);

        DatabaseReference emailReference = databaseReference.child("Users").child(user.getUid());
        ValueEventListener emailListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String email = dataSnapshot.child("email").getValue().toString();
                tvEmail.setText(email, TextView.BufferType.EDITABLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        emailReference.addValueEventListener(emailListener);

        DatabaseReference phoneReference = databaseReference.child("Users").child(user.getUid());
        ValueEventListener phoneListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String phone = dataSnapshot.child("phone").getValue().toString();
                tvPhone.setText(phone, TextView.BufferType.EDITABLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        phoneReference.addValueEventListener(phoneListener);

        // add listeners to buttons
        cancelProfileButton = (Button) findViewById(R.id.cancelProfile);
        cancelProfileButton.setOnClickListener(this);
        saveProfileButton = (Button) findViewById(R.id.save);
        saveProfileButton.setOnClickListener(this);
    }

    private void saveUserInfo() {
        String name = tvName.getText().toString().trim();
        String email = tvEmail.getText().toString().trim();
        String phone = tvPhone.getText().toString().trim();

        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference.child("Users").child(user.getUid()).child("name").setValue(name);
        databaseReference.child("Users").child(user.getUid()).child("email").setValue(email);
        databaseReference.child("Users").child(user.getUid()).child("phone").setValue(phone);

        Toast.makeText(this, "Information Saved", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        if (view == cancelProfileButton) {
            Intent toMain = new Intent(getApplicationContext(), UserMainActivity.class);
            startActivity(toMain);
            finish();
        }
        if (view == saveProfileButton) {
            saveUserInfo();
            finish();
            startActivity(new Intent(this, UserMainActivity.class));
        }
    }
}
