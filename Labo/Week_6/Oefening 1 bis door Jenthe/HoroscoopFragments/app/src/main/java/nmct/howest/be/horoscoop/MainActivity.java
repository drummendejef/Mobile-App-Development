package nmct.howest.be.horoscoop;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity implements SelectGeboortejaarFragment.OnDateSelectedListener, MainFragment.OnButtonListener, HoroscoopFragment.OnHoroscoopSelectListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getFragmentManager();
        fm.beginTransaction()
                .add(R.id.container, new MainFragment(), "horoscoop_fragment")
                .commit();

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

    @Override
    public void onDateSelected(String date) {
        FragmentManager fm = getFragmentManager();
        MainFragment fragment = (MainFragment) fm.findFragmentByTag("horoscoop_fragment");
        fragment.setDate(date);

        getFragmentManager().popBackStack();

    }

    @Override
    public void onButtonGeboorteJaarPressed() {
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction()
                .replace(R.id.container, new SelectGeboortejaarFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void OnButtonHoroscoopPressed() {
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction()
                .replace(R.id.container, new HoroscoopFragment())
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void onHoroscoopSelected(int id) {
        FragmentManager fm = getFragmentManager();
        MainFragment fragment = (MainFragment) fm.findFragmentByTag("horoscoop_fragment");
        fragment.setImgResourceid(id);

        getFragmentManager().popBackStack();
    }
}
