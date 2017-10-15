package hackgt2017.pitstop;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by AustinJ on 10/14/17.
 */

public class RegistrationScreen extends AppCompatActivity {
    //private String _acct ="NA";

    //FIREBASE
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseAuth.AuthStateListener mAuthListener;

    // UI ELEMENTS
    private EditText mPasswordView;
    private EditText mUserIdView;
    private EditText mNameView;
    private EditText mEmailView;
    private Spinner typeSpinner;

    // Strings
    String userValue;
    String passValue;
    String nameValue;
    String emailValue;
    String typeValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);

        // FIREBASE
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
            }
        };

        firebaseAuth.addAuthStateListener(mAuthListener);


        //if user is already logged in
        if (firebaseAuth.getCurrentUser() != null) {
            //start activity
            finish();
            startActivity(new Intent(getApplicationContext(), TrackGridView.class));
        }

        // UI
        //configure spinner
//        Spinner spinner = (Spinner) findViewById(R.id.classificationSpinner);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.account_array, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);

        //cancel button
        Button cancelRegButton = (Button) findViewById(R.id.cancelProfile);
        cancelRegButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
                //setContentView(R.layout.activity_login);
            }
        });

        // save button
        Button saveRegButton = (Button) findViewById(R.id.save);
        saveRegButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                DatabaseHandler db = new DatabaseHandler(v.getContext());
                Intent toMain = new Intent(RegistrationScreen.this, LoginScreen.class);

                mUserIdView = (EditText) findViewById(R.id.userIDInput);
                mPasswordView = (EditText) findViewById(R.id.passwordInput);
                mNameView = (EditText) findViewById(R.id.nameInput);
                mEmailView = (EditText) findViewById(R.id.emailInput);
//                typeSpinner = (Spinner) findViewById(R.id.classificationSpinner);

                userValue = mUserIdView.getText().toString();
                passValue = mPasswordView.getText().toString();
                nameValue = mNameView.getText().toString();
                emailValue = mEmailView.getText().toString();
                //typeValue = (String) typeSpinner.getSelectedItem();

                int charUserLength = userValue.length();
                int charPassLength = passValue.length();

                if (userValue.equals("")) {
                    Toast.makeText(RegistrationScreen.this, "UserID is Empty", Toast.LENGTH_SHORT).show();
                } else {
                    boolean userMatch = db.sameUser(userValue);

                    if (userMatch) { //Checks to see if the UserName already exists
                        Toast.makeText(RegistrationScreen.this, "UserID Already Taken", Toast.LENGTH_LONG).show();
                    } else if (!isPasswordValid(passValue)) {
                        Toast.makeText(RegistrationScreen.this, "Password should more than 4 character", Toast.LENGTH_SHORT).show();
                    } else if (nameValue.equals("")) {
                        Toast.makeText(RegistrationScreen.this, "Name is Empty", Toast.LENGTH_SHORT).show();
                    } else if (emailValue.equals("")) {
                        Toast.makeText(RegistrationScreen.this, "Email is Empty", Toast.LENGTH_SHORT).show();
                    } else if (charUserLength <= 2 || charPassLength <= 2) {
                        Toast.makeText(RegistrationScreen.this, "User or password is too short", Toast.LENGTH_SHORT).show();
                    } else {
                        registerUser();
                        //db.addUsers(new Users(userValue, passValue, nameValue, emailValue, typeValue));
                        startActivity(toMain);
                    }
                }
            }
        });
    }

    public void registerUser()  {
        //add validators later
        Log.d("DEBUGGGGG", "REGISTERUSERCALLED");
        firebaseAuth.createUserWithEmailAndPassword(emailValue, passValue)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //user successfully registered and logged in
                            // start home activity

                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            databaseReference.child("Users").child(user.getUid()).child("password").setValue(passValue);
                            databaseReference.child("Users").child(user.getUid()).child("name").setValue(nameValue);
                            databaseReference.child("Users").child(user.getUid()).child("email").setValue(emailValue);
                            databaseReference.child("Users").child(user.getUid()).child("phone").setValue("");

                            startActivity(new Intent(getApplicationContext(), TrackGridView.class));

                            finish();
                        } else {
                            Toast.makeText(RegistrationScreen.this, "Registration unsuccessful, "
                                    + "please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    /**
     * Checks to see if the password is valid.
     * @param password password string
     * @return true if length large or equal to 4, and password contain letter and digital.
     */
    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return (password.length() >= 4);
    }

    // override the account type
//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        //_acct = parent.getItemAtPosition(position).toString();
//        //Toast.makeText(getBaseContext(),parent.getItemAtPosition(position)+ "is selected",Toast.LENGTH_LONG);
//
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }
}
