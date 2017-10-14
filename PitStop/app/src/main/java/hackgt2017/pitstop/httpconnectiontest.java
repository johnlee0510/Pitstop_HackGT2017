package hackgt2017.pitstop;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Created by John on 2017-10-13.
 */

public class httpconnectiontest extends Activity{

    public void sendMessage(View view) {

        new CallMashapeAsync().execute("hellloooope");
    }

    private class CallMashapeAsync extends AsyncTask<String, Integer, HttpResponse<JsonNode>> {

        protected HttpResponse<JsonNode> doInBackground(String... msg) {

            HttpResponse<JsonNode> request = null;
            try {
                request = Unirest.get("https://webknox-question-answering.p.mashape.com/questions/answers?question=" + msg[0] + "&answerLookup=true&answerSearch=true")
                        .header("X-Mashape-Authorization", "<Insert your Mashape key here>")
                        .asJson();
            } catch (UnirestException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return request;
        }

        protected void onProgressUpdate(Integer...integers) {
        }

        protected void onPostExecute(HttpResponse<JsonNode> response) {
            String answer = response.getBody().toString();
           Log.d("data",answer);
            Log.v("data",answer);
            Log.i("data",answer);
            Log.e("data",answer);
        }
    }
}
