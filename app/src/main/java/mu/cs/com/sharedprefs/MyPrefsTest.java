package mu.cs.com.sharedprefs;

/**
 * Created by thomaslysaght on 20/10/2015.
 */

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;


public class MyPrefsTest extends Activity {

    private String filename = "GamePreferences";
    private long score;
    SharedPreferences prefs;
    EditText et;
    CheckBox cb;
    Spinner mySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefs);
   /*     mySpinner = (Spinner) findViewById(R.id.spinner);
     //   mySpinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapterS = ArrayAdapter.createFromResource(this,R.array.myarray,android.R.layout.simple_spinner_item);
        //adapterS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(adapterS);*/




        // MODE_PRIVATE-the default mode, where the created file can only be accessed by the calling application
        //prefs =getSharedPreferences(filename, Context.MODE_PRIVATE);
        prefs =getPreferences(Context.MODE_PRIVATE);
        Log.d("Prefs", "1. getPrefs onCreate");


        score=0;
        et = (EditText) findViewById(R.id.editText1);
        cb = (CheckBox) findViewById(R.id.checkBox1);
        cb.setChecked(false);

        getPrefs();
        setPrefs();

        cb.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                //	updatePrefs();
            }

        });

      //  cb.setChecked(false);
        int firstExe = (int) prefs.getLong("firstexe", -1);
        //
        getPrefs();

    }

    //This method sets default values for all preferences
    //if it is the first time the app is run
    //we declare the preference "firstexe" to determine if it is the first run of the app

    public void setPrefs() {
        Log.d("Prefs", "3. setPrefs()");

        //here prefs.getLong will return -1 if "firstexe" does not exist
        int firstExe = (int) prefs.getLong("firstexe", -1);

        //The first time we run the app the tag "firstexe" will not exits
        //if not is the first execution, set the initial values for the preferences
        if(firstExe == -1)
        {
           // Toast.makeText(getApplicationContext(), " Set firstExe "+firstExe, Toast.LENGTH_LONG).show();
            Log.d("Prefs", " firstExe");
            Toast.makeText(this, "First setPrefs "+firstExe, Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = prefs.edit();

            editor.putLong("newscore", 0);
            editor.putLong("score", 0);
            editor.putLong("firstexe", 1);
            editor.commit();
        }
    }

    public void updatePrefs() {
        Log.d("Prefs", "1. updatePrefs()");

        SharedPreferences.Editor editor = prefs.edit();

        //We do a test to see if the checkbox is checked
        //If so we test to see if any data was entered in the EditText box
        //We get the data as a String from EditText and then trim it so see if is empty
        //Otherwise we convert the input from EditText to an integer
        //We add this number to our score and save the number and incremented score in our SharedPreferences
        if(cb.isChecked()){
            String ets = et.getText().toString();
            if (ets.trim().equals(""))
                Toast.makeText(this, "Please enter a number ", Toast.LENGTH_SHORT).show();
            else{
                long i = Long.parseLong(ets);
                score+=i;
                editor.putLong("newscore", i);
                editor.putLong("score", score);
                editor.commit();
               // Toast.makeText(getApplicationContext(), " New Score is: "+i, Toast.LENGTH_LONG).show();


                //call our own method to retrieve the updated SharedPreferences
                getPrefs();
            }
        }
        else{
            Toast.makeText(getApplicationContext(), " Not clicked: ", Toast.LENGTH_SHORT).show();
           // getPrefs();
        }

    }


    public void getPrefs() {
        Log.d("Prefs", "2. getPrefs()");
        //get all the preferences stored in SharedPreferences
        long firstexe = (long) prefs.getLong("firstexe", -0); //-0 = a default value to use if the preference does not exist
        long newscore = (long) prefs.getLong("newscore", -1);
        long score = (long) prefs.getLong("score", -2);

      //  Toast.makeText(getApplicationContext(), " FirstExe is: "+firstexe, Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), " NewScore is: "+newscore, Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), " Score is: "+score, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_list_prefs, menu);
        return true;
    }

}

