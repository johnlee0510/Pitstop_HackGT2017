package hackgt2017.pitstop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Test extends AppCompatActivity implements View.OnClickListener {

    private Button testButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        testButton = (Button) findViewById(R.id.test);
        testButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        if (view == testButton) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
