package be.martijnloth.nmct.horoscoop;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class SelectGeboorteJaarActivity extends ListActivity {

    private final static List<String> GEBOORTEJAREN;

    private ListAdapter lAd;

    static {
        GEBOORTEJAREN = new ArrayList<>(Calendar.getInstance().get(Calendar.YEAR) -1900 );
        for (int j = 1900; j < Calendar.getInstance().get(Calendar.YEAR); j++) {
            GEBOORTEJAREN.add("" + j);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lAd = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, GEBOORTEJAREN);
        setListAdapter(lAd);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String sJaar = GEBOORTEJAREN.get(position);

        Intent i = new Intent();
        i.putExtra(MainActivity.EXTRA_BIRTHDAY, sJaar);
        setResult(RESULT_OK, i);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select_geboorte_jaar, menu);
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
