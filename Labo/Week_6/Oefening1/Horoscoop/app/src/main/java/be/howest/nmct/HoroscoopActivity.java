package be.howest.nmct;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import be.howest.nmct.Data.Data;

public class HoroscoopActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_horoscoop);//Wordt niet gebruikt in een ListActivity

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_horoscoop, menu);
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

    //Om de listview op te vullen hebben we een eigen adapter nodig.
    public class HoroscoopAdapter extends ArrayAdapter<Data.Horoscoop>
    {
        public HoroscoopAdapter()
        {
            super(HoroscoopActivity.this, R.layout.row_horoscoop, R.id.textViewNaamHoroscoop, Data.Horoscoop.values());
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View r = super.getView(position, convertView, parent);

            Data.Horoscoop horoscoop = Data.Horoscoop.values()[position];

            TextView textViewNaamHoroscoop = (TextView) r.findViewById(R.id.textViewNaamHoroscoop);
            textViewNaamHoroscoop.setText(horoscoop.getNaamHoroscoop());

            Button btnInfo = (Button) r.findViewById(R.id.buttonInfo);
            //btnInfo.setOnClickListener();

            return r;
        }
    }
}
