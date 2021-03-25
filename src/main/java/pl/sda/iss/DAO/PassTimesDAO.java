package pl.sda.iss.DAO;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class PassTimesDAO {

    private final static String BASE_URL = "http://api.open-notify.org/iss-pass.json?";


    public String getPassTimes(double latitude, double longitude, int numberOfPasses) throws IOException {
        String url = BASE_URL + "lat=" + latitude +"&lon=" + longitude +"&n=" + numberOfPasses;
        Request request = new Request.Builder()
                .url(url)
                .build();

        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(request);
        Response response = call.execute();
        return response.body().string();
    }
}
