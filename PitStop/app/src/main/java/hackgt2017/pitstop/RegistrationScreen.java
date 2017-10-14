package hackgt2017.pitstop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by AustinJ on 10/14/17.
 */

public class RegistrationScreen extends AppCompatActivity {
    //private String _acct ="NA";

    private EditText mPasswordView;
    private EditText mUserIdView;
    private EditText mNameView;
    private EditText mEmailView;
    private Spinner typeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);

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

                Intent intent = new Intent(getApplicationContext(), LoginScreen.class);
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

                String userValue = mUserIdView.getText().toString();
                String passValue = mPasswordView.getText().toString();
                String nameValue = mNameView.getText().toString();
                String emailValue = mEmailView.getText().toString();
                String typeValue = (String) typeSpinner.getSelectedItem();

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
                        db.addUsers(new Users(userValue, passValue, nameValue, emailValue, typeValue));
                        startActivity(toMain);
                    }
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
