package hackgt2017.pitstop;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Created by John on 2017-10-13.
 */

public class httpconnectiontest {

    HttpResponse<JsonNode> jsonResponse = Unirest.post("https://hackgt-api.ncrcloud.com/order/orders")
            .header("accept", "application/json")
            .queryString("apiKey", "8a82859f5ef21870015ef2fa5e5f0000")
            .field("parameter", "value")
            .field("foo", "bar")
            .asJson();

    public httpconnectiontest() throws UnirestException {
    }
}
