package mu.cs.com.sharedprefs;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MyPrefsScore extends Activity {

    TextView tv;
    private String filename = "GamePreferences";
    private int score;
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        tv = (TextView) findViewById(R.id.textView);

        prefs =getSharedPreferences(filename, Context.MODE_PRIVATE);
        score = (int) prefs.getInt("score", -2);
        tv.setText(""+score);

        Toast.makeText(getApplicationContext(), " Score is: " +score, Toast.LENGTH_SHORT).show();



    }
}
