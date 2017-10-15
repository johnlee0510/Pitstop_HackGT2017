package hackgt2017.pitstop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ArrivedActivity extends AppCompatActivity implements View.OnClickListener {

    //UI elements
    private TextView numOrdersView;
    private EditText maxEdit;
    private Button hereButton;
    private Button signOutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrived);

        String key = getIntent().getStringExtra("selectedItem");

        final TextView tv = (TextView) findViewById(R.id.trackStoreName);
        tv.setText(key);

        numOrdersView = (TextView) findViewById(R.id.NumberOrdersText);
        maxEdit = (EditText) findViewById(R.id.MaxEdit);
        hereButton = (Button) findViewById(R.id.orderButton);
        signOutButton = (Button) findViewById(R.id.signOutButton);

        //
        hereButton.setOnClickListener(this);
        signOutButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if (view == signOutButton) {
            // sign out
            finish();
            startActivity(new Intent(getApplicationContext(), LoginScreen.class));
        }
        if (view == hereButton){
            // here button
            finish();
            startActivity(new Intent(getApplicationContext(), OrderReceived.class));
        }
    }
}
