package com.example.mysharedprefs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private final String defaultText = "Cannot load data";
    private final String mydata = "MY_PREF_DATA";
    private final String myfiles = "MY_PREF_FILES";
    SharedPreferences MySharedString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView myTxtV = (TextView)findViewById(R.id.textView);
        myTxtV.setText(defaultText);
        MySharedString = getSharedPreferences(myfiles, Activity.MODE_PRIVATE);

    }
    public void savePrefs(View v) {
        EditText myTxt = (EditText)findViewById(R.id.editText);
        if(myTxt.getText().toString().isEmpty()) {
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("Please enter data you want to Save!");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        } else {
            SharedPreferences.Editor editor = MySharedString.edit();
            editor.putString(mydata, myTxt.getText().toString());
            editor.commit();
            Toast toast = Toast.makeText(this, "Data Saved!",Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    public void loadPrefs(View v) {

        if (!MySharedString.contains(mydata)) {
            TextView myTxtV = (TextView)findViewById(R.id.textView);
            myTxtV.setText(defaultText);
        } else {
            String text = MySharedString.getString(mydata, "");
            TextView myTxtV = (TextView)findViewById(R.id.textView);
            myTxtV.setText(text);
        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
