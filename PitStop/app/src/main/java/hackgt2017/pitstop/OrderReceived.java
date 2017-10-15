package hackgt2017.pitstop;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;


public class OrderReceived extends AppCompatActivity implements View.OnClickListener{

    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_received);


        cancelButton = (Button) findViewById(R.id.cancel);
        cancelButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view == cancelButton) {
            finish();
            startActivity(new Intent(this, UserMainActivity.class));
        }
    }

}
