package hackgt2017.pitstop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by AustinJ on 10/15/17.
 */

public class orderListActivity extends AppCompatActivity implements View.OnClickListener {

    private GridView grid;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    private Button ReturnButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        ReturnButton = (Button) findViewById(R.id.ReturnButton);
        ReturnButton.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        final ArrayList<String> arr = new ArrayList<>(0);

        DatabaseReference orderRef = databaseReference.child("Users").child(user.getUid());
        ValueEventListener orderListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot i : dataSnapshot.child("Orders").getChildren()) {
                    arr.add(i.getValue().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        orderRef.addValueEventListener(orderListener);

        grid = (GridView) findViewById(R.id.OrderListView);
        //String[] ex = {"a", "b"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
        grid.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        // Perform action on click
        if(v == ReturnButton) {
            startActivity(new Intent(getApplicationContext(), UserMainActivity.class));
        }
    }
}
