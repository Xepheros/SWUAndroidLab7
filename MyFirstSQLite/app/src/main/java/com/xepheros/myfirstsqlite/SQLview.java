package com.xepheros.myfirstsqlite;

import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class SQLview extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlview);
        TextView nameText = (TextView) findViewById(R.id.SQLInfoName);
        TextView hotnessText = (TextView) findViewById(R.id.SQLInfoHotness);
        HotOrNot info = new HotOrNot(this);
        info.open();
        Cursor data = info.getData();
        if(data != null) {
            String nameStr = "";
            String hotStr = "";
            int iName = data.getColumnIndex(HotOrNot.KEY_NAME);
            int iHotness = data.getColumnIndex(HotOrNot.KEY_HOTNESS);
            for (data.moveToFirst(); !data.isAfterLast(); data.moveToNext()) {
                nameStr += data.getString(iName) + "\n";
                hotStr += data.getString(iHotness) + "\n";
            }
            nameText.setText(nameStr);
            hotnessText.setText(hotStr);
        }
        info.close();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sqlview, menu);
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
