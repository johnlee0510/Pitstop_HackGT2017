package hackgt2017.pitstop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by AustinJ on 10/14/17.
 */

public class TrackGridView extends AppCompatActivity implements View.OnClickListener {

    private Button cancelRegButton;

    private FirebaseAuth firebaseAuth;

    private int pos = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_grid_view);

        firebaseAuth = FirebaseAuth.getInstance();
        /*if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginScreen.class));
        }*/

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(TrackGridView.this, "" + position, Toast.LENGTH_SHORT).show();
                if (position == 0) {
                    finish();
                    startActivity(new Intent(TrackGridView.this, Truck1Activity.class));
                }
            }
        });

        //cancel button
        cancelRegButton = (Button) findViewById(R.id.cancelGridView);
        cancelRegButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == cancelRegButton) {
            finish();
            startActivity(new Intent(this, UserMainActivity.class));
        }
    }

}