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

/**
 * A login screen that offers login via email/password.
 */
public class LoginScreen extends AppCompatActivity  {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView musernameView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private String currentLoginAttempt;
    private int attempt = 0;
    private final String screen = "Login Screen";
    private AutoCompleteTextView mEmailView;
//    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
//        startActivity(new Intent(LoginScreen.this, RegistrationScreen.class));

        // Set up the login form.

        mEmailView = (AutoCompleteTextView) findViewById(R.id.username);

        musernameView = (AutoCompleteTextView) findViewById(R.id.username);


        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin(textView);
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin(view);
            }
        });

        Button mEmailSignInCancelButton = (Button) findViewById(R.id.cancel_button);
        mEmailSignInCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform action on click

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
                //setContentView(R.layout.activity_login);
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //populateAutoComplete();
            }
        }
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin(View v) {
        if (mAuthTask != null) {
            return;
        }
        DatabaseHandler db = new DatabaseHandler(v.getContext());
        // Reset errors.
        musernameView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String userId = musernameView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
//            db.loginLogging(new LoggingSignin(userId, password, new Date(), false, attempt, false, getString(R.string.error_invalid_password)));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.

        if (!userId.equals(currentLoginAttempt)) {
            currentLoginAttempt = userId;
            attempt = 0;
        }

        if (TextUtils.isEmpty(userId)) {
            musernameView.setError(getString(R.string.error_field_required));
//            db.loginLogging(new LoggingSignin(userId, password, new Date(), false, attempt, false, getString(R.string.error_field_required)));
            focusView = musernameView;
            cancel = true;
        } else if (!isEmailValid(userId)) {
            musernameView.setError(getString(R.string.error_invalid_email));
//            db.loginLogging(new LoggingSignin(userId, password, new Date(), false, attempt, false, getString(R.string.error_field_required)));
            focusView = musernameView;
            cancel = true;
        }

//        if (cancel) {
//            // There was an error; don't attempt login and focus the first
//            // form field with an error.
//            focusView.requestFocus();
//        } else {
//            // Show a progress spinner, and kick off a background task to
//            // perform the user login attempt.
//            if (attempt < 3) {
//                showProgress(true);
//                boolean accountMatch = db.validateUser(userId, password);
//                if (accountMatch) {
//                    Intent intent = new Intent(LoginScreen.this, RegistrationScreen.class);
////                    db.loginLogging(new LoggingSignin(userId, password, new Date(), true, attempt, false, "Successful Login"));
////                    db.actionLogging(new LoggingNavigation(userId, new Date(), screen , "Login Button", "Login successful - "));
//                    startActivity(intent);
//                } else {
//                    attempt++;
//                    showProgress(false);
//                    mEmailView.setError(getString(R.string.error_invalid_email));
//                    mPasswordView.setError(getString(R.string.error_incorrect_password));
//                    db.loginLogging(new LoggingSignin(userId, password, new Date(), false, attempt, false,
//                            getString(R.string.error_invalid_email) + " OR "
//                                    + getString(R.string.error_incorrect_password)));
//                    mPasswordView.requestFocus();
//                }
//            } else {
//                mEmailView.setError(getString(R.string.error_invalid_attemps));
//                db.loginLogging(new LoggingSignin(userId, password, new Date(), false, attempt, true, getString(R.string.error_invalid_attemps)));
//            }
//        }
    }

    /**
     * Checks to see if the login is valid.
     * @param email userId string
     * @return true is valid
     */
    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return true;
    }

    /**
     * Checks to see if the password is valid.
     * @param password password string
     * @return true if length large or equal to 4, and password contain letter and digital.
     */
    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        if (password.length() < 4){
            return false;
        }else {
            return true;
        }
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });

        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mProgressView.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(LoginScreen.this, RegistrationScreen.class);
        startActivity(intent);
        finish();
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    @SuppressWarnings("unused")
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mUser;
        private final String mPassword;

        UserLoginTask(String userId, String password) {
            mUser = userId;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.


            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {

                Intent intent = new Intent(LoginScreen.this, RegistrationScreen.class);
                startActivity(intent);
                finish();
                //setContentView(R.layout.activity_app_screen);

            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}

