package hackgt2017.pitstop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by AustinJ on 10/15/17.
 */

public class orderListActivity extends AppCompatActivity implements View.OnClickListener {

    private Button ReturnButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        ReturnButton = (Button) findViewById(R.id.ReturnButton);
        ReturnButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // Perform action on click
        if(v == ReturnButton) {
            startActivity(new Intent(getApplicationContext(), ArrivedActivity.class));
        }
    }
}
