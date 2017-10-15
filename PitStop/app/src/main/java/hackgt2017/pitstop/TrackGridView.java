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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by AustinJ on 10/14/17.
 */

public class TrackGridView extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_grid_view);

        //cancel button
        Button cancelRegButton = (Button) findViewById(R.id.cancelGridView);
        cancelRegButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                Intent intent = new Intent(getApplicationContext(), UserMainActivity.class);
                startActivity(intent);
                finish();
                //setContentView(R.layout.activity_login);
            }
        });

        // Get the widgets reference from XML layout
        GridView gv = (GridView) findViewById(R.id.gridview);
        final TextView tv = (TextView) findViewById(R.id.testShow);

        String[] plants = new String[]{
                "Striped alder",
                "Amy root",
                "Arizona sycamore",
                "Green ash",
                "Cherry birch",
                "Gray birch",
                "Mahogany birch",
                "Spice birch",
                "Yellow birch"
        };

        List<String> plantsList = new ArrayList<String>(Arrays.asList(plants));

        /*
            setAdapter (ListAdapter adapter)
                Sets the data behind this GridView.

                Parameters
                adapter : the adapter providing the grid's data
         */
        // Data bind GridView with ArrayAdapter (String Array elements)
        gv.setAdapter(new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1,plantsList));

        /*
            setOnItemClickListener (AdapterView.OnItemClickListener listener)
                Register a callback to be invoked when an item
                in this AdapterView has been clicked.

                Parameters
                listener : The callback that will be invoked.
         */
        // Set an item click listener for GridView widget
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the GridView selected/clicked item text
                String selectedItem = parent.getItemAtPosition(position).toString();

                // Display the selected/clicked item text and position on TextView
                tv.setText("GridView item clicked : " +selectedItem
                        + "\nAt index position : " + position);
                Intent intent = new Intent();
                intent.putExtra("selectedItem",selectedItem);
                startActivity(new Intent(getApplicationContext(), ArrivedActivity.class));
            }
        });
    }
}
