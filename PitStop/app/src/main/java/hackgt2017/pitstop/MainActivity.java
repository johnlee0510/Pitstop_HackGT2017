package hackgt2017.pitstop;


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configure Login Button
        Button loginButton = (Button) findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                loginClicked();
            }
        });

        // Configure Registration Button
        Button regButton = (Button) findViewById(R.id.register);
        regButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                registrationClicked();
            }
        });

    }

    private void loginClicked() {
        Intent intent = new Intent(MainActivity.this, LoginScreen.class);
        startActivity(intent);
    }

    private void registrationClicked() {
        Intent intent2 = new Intent(MainActivity.this, RegistrationScreen.class);
        startActivity(intent2);
    }
}






