package hackgt2017.pitstop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by AustinJ on 10/15/17.
 */

public class profileActivity extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        DatabaseHandler db = new DatabaseHandler(this);

//        //configure spinner
//        Spinner spinner = (Spinner) findViewById(R.id.classificationSpinner);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.account_array, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);


        //addNew = getIntent().getStringExtra("AddNew");

        if (db.profileExists()) {
            addNew = "edit";
        } else {
            addNew = "new";
        }


        TextView tvName = (TextView) findViewById(R.id.nameInput);
        tvName.setText(db.getUserName());

        TextView tvEmail = (TextView) findViewById(R.id.emailInput);
        tvEmail.setText(db.getEmail());
        //tvEmail.setText(addNew);
//        if (addNew.equals("edit")) {
//
//
//            spinner.setSelection(findPosition(db.getUserType()));
//
//            TextView tvAddress = (TextView) findViewById(R.id.addressInput);
//            tvAddress.setText(db.getHomeAddress());
//            TextView tvPhone = (TextView) findViewById(R.id.phoneInput);
//            tvPhone.setText(db.getPhone());
//
//        }


        Button cancelProfileButton = (Button) findViewById(R.id.cancelProfile);
        cancelProfileButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent toMain = new Intent(getApplicationContext(), UserMainActivity.class);
                startActivity(toMain);
                finish();
                //setContentView(R.layout.activity_login);
            }
        });
        Button saveProfileButton = (Button) findViewById(R.id.save);
        saveProfileButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                DatabaseHandler db = new DatabaseHandler(v.getContext());
                Intent toMain = new Intent(getApplicationContext(), UserMainActivity.class);

                String userValue = db.getCurrentUser();
//                EditText mAddressView = (EditText) findViewById(R.id.addressInput);
                EditText mPhoneView = (EditText) findViewById(R.id.phoneInput);
                EditText mNameValue = (EditText) findViewById(R.id.nameInput);
                EditText mEmailValue = (EditText) findViewById(R.id.emailInput);
//                Spinner typeSpinner = (Spinner) findViewById(R.id.classificationSpinner);
                //EditText mTypeValue = (EditText) findViewById(R.id.classification);


//                String addressValue = mAddressView.getText().toString();
                String phoneValue = mPhoneView.getText().toString();
                String nameValue = mNameValue.getText().toString();
                String emailValue = mEmailValue.getText().toString();
//                String typeValue = (String) typeSpinner.getSelectedItem();

                if (addNew.equals("new")) {
//                    db.updateUsers(userValue, nameValue, emailValue, typeValue);
//                    db.addProfiles(new Profiles(userValue, addressValue, phoneValue));
                } else {
//                    db.updateUsers(userValue, nameValue, emailValue, typeValue);
//                    db.updateProfile(new Profiles(userValue, addressValue, phoneValue));
                }
                startActivity(toMain);
                finish();
                //setContentView(R.layout.activity_login);
            }
        });
    }
}
