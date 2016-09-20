package softwaredongjune.jsontest;

import android.os.Handler;
import android.os.Message;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by 김동준 on 2016-09-20.
 */
public class GetExample extends Thread {
    private String uurl = "http://api.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=2de143494c0b295cca9337e1e96b00e0";
    private String parse;
    private OkHttpClient client = new OkHttpClient();
    private Handler handler;

    public GetExample(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        super.run();

        try {
            parse = run(uurl);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Mhandler(parse);


    }

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    private void Mhandler(String parse) {

        Message msg = new Message();
        msg.obj = parse;
        handler.sendMessage(msg);
    }
}
