package hackgt2017.pitstop;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

/**
 * A login screen that offers login via email/password.
 */
public class LoginScreen extends AppCompatActivity implements View.OnClickListener {
    // UI references.
    private AutoCompleteTextView usernameView;
    private EditText passwordView;
    //private View mProgressView;
    //private View mLoginFormView;
    //private String currentLoginAttempt;
    //private int attempt = 0;
    //private final String screen = "Login Screen";
    private AutoCompleteTextView emailView;
    private Button signInButton;
    private Button cancelButton;
//    private GoogleApiClient mGoogleApiClient;

    // FIREBASE
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        // FIREBASE
        firebaseAuth = FirebaseAuth.getInstance();

        //if user is already logged in
        if (firebaseAuth.getCurrentUser() != null) {
            //start activity
            finish();
            startActivity(new Intent(getApplicationContext(), TrackGridView.class));
        }

        // UI ELEMENTS
        usernameView = (AutoCompleteTextView) findViewById(R.id.username);
        passwordView = (EditText) findViewById(R.id.password);
        signInButton = (Button) findViewById(R.id.email_button);
        cancelButton = (Button) findViewById(R.id.cancel_button);

        signInButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == signInButton) {
            finish();
            startActivity(new Intent(this, TrackGridView.class));
        }
        if (view == cancelButton) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}

