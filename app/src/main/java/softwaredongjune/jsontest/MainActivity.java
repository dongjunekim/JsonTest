package softwaredongjune.jsontest;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button main_btn;
    private TextView main_txt;
    public String mStrJson;
    private Handler handler;
    private GetExample getExample;
    private String jsonparse, parseData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_btn = (Button) findViewById(R.id.main_btn);
        main_txt = (TextView) findViewById(R.id.main_txt);


        main_btn.setOnClickListener(this);

    }

    private void parsing(String mStrJson) {
        try {
            JSONObject object0 = new JSONObject(mStrJson);
            JSONObject coord = object0.getJSONObject("coord");


            String coorddata = "lon : " + coord.getString("lon") + "   lat : " + coord.getString("lat");
            Log.e("coorddata : ", coorddata);
            main_txt.setText(coorddata);

        } catch (JSONException e) {
            Log.e("", e.getMessage());
        }

    }

    @Override
    public void onClick(View v) {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                mStrJson = (String) msg.obj;
                Log.e("점검", mStrJson);
                parsing(mStrJson);

            }
        };
        GetExample getExample = new GetExample(handler);
        getExample.start();
    }


}
