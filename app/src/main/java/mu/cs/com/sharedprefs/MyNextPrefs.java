package mu.cs.com.sharedprefs;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MyNextPrefs extends Activity implements View.OnClickListener {

    private String filename = "GamePreferences";
    private int score;
    SharedPreferences prefs;
    EditText et;
    Button bt,bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_nextprefs);

        //        prefs =getPreferences(Context.MODE_PRIVATE);

        prefs =getSharedPreferences(filename,Context.MODE_PRIVATE);
        getChoice();
        bt = (Button) findViewById(R.id.button);
        bt.setOnClickListener(this);
        et = (EditText) findViewById(R.id.editText1);
        bt2 = (Button) findViewById(R.id.button2);



        //using an anonymous inner class we need to use getApplicationContext() to access
        //the resources of our Activity. The 'this' context will only access resources within
        //the inner class
        bt2.setOnClickListener(new OnClickListener() {
            String s=this.getClass().toString();

            @Override
            public void onClick(View arg0) {

                Toast.makeText(getApplicationContext(), " Button 2 "+s, Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = prefs.edit();
                int score = (int) prefs.getInt("score", -2);
                this.getClass();

                Intent intent = new Intent(getApplicationContext(),MyPrefsScore.class);
                startActivity(intent);
            }
            });



    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        String s=this.getClass().toString();
//        prefs =getPreferences(Context.MODE_PRIVATE);
     //   prefs =getSharedPreferences(filename,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        String ets = et.getText().toString();
        int i = Integer.parseInt(ets);
        int firstScore = (int) prefs.getInt("score", -2);

        if(firstScore==-2){
            editor.putString("level", ets);
            editor.putInt("score",i);
            editor.commit();
            Toast.makeText(getApplicationContext(), " Puttng: " +i+" "+s, Toast.LENGTH_SHORT).show();
        }

        else {
            int oldscore = (int) prefs.getInt("score", -2);
            i += oldscore;
            //store the choice with the tag "level" in preferences
            editor.putString("level", ets);
            editor.putInt("score", i);
            Toast.makeText(getApplicationContext(), " Puttng: " +i+" "+s, Toast.LENGTH_SHORT).show();
            //save to the preferences file
            editor.commit();
        }
        getChoice();

    }



    public void getChoice() {
        //retrieve the shared preference stored for "level"
        //return "not set" if level does not exist
        String opt = (String) prefs.getString("level", "Not Set");
        int score = (int) prefs.getInt("score", -2);

        Toast.makeText(getApplicationContext(), " Score is: " +score, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.my_prefs_test, menu);
        return true;
    }


}
