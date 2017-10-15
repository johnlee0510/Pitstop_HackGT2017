package hackgt2017.pitstop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Truck4Activity extends AppCompatActivity implements View.OnClickListener {

    // FIREBASE
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    // UI ELEMENTS
    private Spinner item;
    private EditText quantity;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truck4);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        item = (Spinner) findViewById(R.id.spinnerItem4);
        quantity = (EditText) findViewById(R.id.quantityEdit4);
        submitButton = (Button) findViewById(R.id.submitOrderButton4);
        submitButton.setOnClickListener(this);

        String[] menu = {"Chocolate Chip", "Mint", "Vanilla", "Butter Pecan"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, menu);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        item.setAdapter(adapter);
    }

    private void submitOrder() {
        //pull info from ui
        String food = item.getSelectedItem().toString();
        String num = quantity.getText().toString().trim();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child("Users").child(user.getUid()).child("Orders").push().setValue(food, num);

        Toast.makeText(Truck4Activity.this, "Thank you for ordering!", Toast.LENGTH_LONG).show();

        finish();
        startActivity(new Intent(this, OrderReceived.class));
    }

    @Override
    public void onClick(View view) {
        if (view == submitButton) {
            submitOrder();
        }
    }
}

