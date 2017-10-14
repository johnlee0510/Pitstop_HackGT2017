package hackgt2017.pitstop;


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void sendMessage(View view) {

        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();

        TextView txtView = (TextView) findViewById(R.id.textView1);
        txtView.setText(message);

        new CallMashapeAsync().execute(message);
    }

    private class CallMashapeAsync extends AsyncTask<String, Integer, HttpResponse<String>> {

        protected HttpResponse<String> doInBackground(String... msg) {

            HttpResponse<String> response = null;
            try {
                response = Unirest.get("https://hackgt-api.ncrcloud.com/catalog/2.0.2/items/1.0/snapshot")
                        .header("authorization", "AccessToken eyJhbGciOiJFUzI1NiJ9.eyJtdGgiOlsicGFzc3dvcmQiXSwic3ViIjoiYWNjdDpvcmctMUBhZG1pbiIsIm5iZiI6MTUwNzk5MjI2OCwib3JnIjoiL29yZy0xLyIsImlzcyI6Ik5DUiIsInJscyI6ImVKeUZVMEZ5Z3pBTVBNTnIyaWNvUmlGcXdmTElJcG4wL3crcDdHQVNFeUFuc0x5NzNsbEpYNjNIMERiVW9WZlNlOXMwVjhJYml2MUFONUtucUFMS2RqWUFTaENLYUhlOWdGZjdya0g3OU9WaVpFOVdJZDliOFlJdzZPVlF5TEUvVThKR1ZEVldQRVFIRm9YQktpdzllUG9ESmZhVnJ6ZHVaZWlKQzhKWGlzWitYTVRwRkoxUStLd0h6bUhNTG44eEJicmg5c2xXRHVRcXZTbHV5ejRSU3lKVkpFZXZKR0NzUzFVN204a2krS2hTQlNVOFlPV3F3eXVIckJFRE9pM2hXaFRRUHlnL1BJbUhZUlYwVlRWWGtqUFRlOEJOUDd2emRSUFNkQUVoRE9SSzMxKzhyQ1ppVCtqVjhXN1QzOXMyUTAvcDBHRVkrRDVpWHBHQ2dFQWplT2h4Vlg5MGM1TGlhNzEySTlDUXA5OTNDWjV5TDl1M2dzNHRtWVVEV2pNY3VBc1dwRzBIaVBxTjZlN1I1TW91dWlrcWp4c29GRW5QWEptNnRqbFRhdG9mNXNETFBHZzN0dVd0eVRJQUE4NTI1RHU1c2Qzay9pRDhMTEJRaTlRQ1o4a1o3TkQvQVVxb0ZKOD0iLCJleHAiOjE1MDgwMjEwNjgsImlhdCI6MTUwNzk5MjI2OCwianRpIjoiYTc4MDAxMmEtNzRiOC00NWMyLWFjM2QtNzY4NDgzOGZmNTcxIn0.MEQCIDDjUuVPVdMYd2hWLLuh7KiaLn4c-bGJQ2GNlGAPZzeFAiAHhw91imAxc41LTUTb-aNhpW1JCAq-vA2JTBpm1qzpvQ")
                        .header("cache-control", "no-cache")
                        .header("nep-application-key", "8a82859f5ef21870015ef2fa5e5f0000")
                        .header("accept", "application/json, text/plain, */*")
                        .header("content-type", "application/json")
                        .header("postman-token", "6dfcb64c-c5b4-f085-32fe-0f2db9c68c24")
                        .asString();
                Log.v(response.getBody().toString(),"AAAAAA");
                Log.i(response.getBody().toString(),"gfdf");
                Log.d("cccc",String.valueOf(response));
                Log.e("bb",response.getBody().toString());
                Log.v("aaa",response.getBody().toString());
            } catch (UnirestException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return response;
        }

        protected void onProgressUpdate(Integer...integers) {
        }

        protected void onPostExecute(HttpResponse<JsonNode> response) {
            Log.d("aaaaaaaaaaa","jkjkj");
            String answer = response.getBody().toString();
            TextView txtView = (TextView) findViewById(R.id.textView1);
            txtView.setText(answer);
            Log.v(response.getBody().toString(),"AAAAAA");
            Log.i(response.getBody().toString(),"fjxfhxdkjhjfkfzdjh");
            Log.d("fjxfhxdkjhjfkfzdjh",String.valueOf(answer));
            Log.e("fjxfhxdkjhjfkfzdjh",answer);
            Log.w("fjxfhxdkjhjfkfzdjh",answer);
            Log.v("fjxfhxdkjhjfkfzdjh",answer);

        }
    }
}






