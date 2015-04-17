package com.xepheros.myfirstsqlite;


import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
    EditText sqlName, sqlHotness ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqlName = (EditText) findViewById(R.id.SQLName);
        sqlHotness = (EditText) findViewById(R.id.SQLHotness);
    }

public void sqlUpdate(View v)
{
    boolean didItWork = true;
    try {
        String name = sqlName.getText().toString();
        String hotness = sqlHotness.getText().toString();

        HotOrNot entry = new HotOrNot(this);
        entry.open();
        entry.createEntry(name, hotness);
        entry.close();
    } catch (Exception e) {
        didItWork = false;
        String error = e.toString();
        Toast.makeText(this, "Error!" + error, Toast.LENGTH_SHORT).show();

    } finally {
        if (didItWork) {
            Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show();
        }
    }
}
    public void sqlView(View v) {
        Intent i = new Intent(this, SQLview.class);
        startActivity(i);
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
