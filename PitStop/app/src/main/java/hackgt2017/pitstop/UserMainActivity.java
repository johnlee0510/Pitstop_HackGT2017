package hackgt2017.pitstop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by AustinJ on 10/14/17.
 */

public class UserMainActivity extends AppCompatActivity implements View.OnClickListener{
    //UI elements
    private Button cancelUserMain;
    private Button profileButton;
    private Button trackScheduleButton;
    private Button checkOrderButton;
//    private EditText maxEdit;

    // FIREBASE
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        // FIREBASE
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.getCurrentUser();

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginScreen.class));
        }

        // UI ELEMENTS
        cancelUserMain = (Button) findViewById(R.id.cancelUserMain);
        profileButton = (Button) findViewById(R.id.profileButton);
        trackScheduleButton = (Button) findViewById(R.id.trackScheduleButton);
        checkOrderButton = (Button) findViewById(R.id.checkOrderButton);

        //
        cancelUserMain.setOnClickListener(this);
        profileButton.setOnClickListener(this);
        trackScheduleButton.setOnClickListener(this);
        checkOrderButton.setOnClickListener(this);
    }
    //cancel button
    public void onClick(View v) {
        // Perform action on click
        if(v == cancelUserMain) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(getApplicationContext(), LoginScreen.class));
        }
        if(v == profileButton) {
            finish();
            startActivity(new Intent(getApplicationContext(), profileActivity.class));
        }
        if(v == trackScheduleButton) {
            finish();
            startActivity(new Intent(getApplicationContext(), TrackGridView.class));
        }
        if(v == checkOrderButton) {
            finish();
            startActivity(new Intent(getApplicationContext(), orderListActivity.class));
        }
    }

}
