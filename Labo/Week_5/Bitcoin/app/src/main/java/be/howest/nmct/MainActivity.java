package be.howest.nmct;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;


public class MainActivity extends Activity
    implements BitcoinRateFragment.OnStartChangeFragment,
     ChangeFragment.OnRateChangeListener {
    //Implementeren om te kunnen communiceren van bitcoinratefragment naar changefragment

    //De Shared Preferences, om data op te slaan als de activity wordt afgesloten
    public static final String SavedWisselKoers = "be.howest.nmct.SavedWisselKoers";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new ChangeFragment())
                    .commit();
        }

        //De opgeslagen wisselkoers terug ophalen
        SharedPreferences settings = getSharedPreferences(SavedWisselKoers, 0);
        Float wisselkoers = settings.getFloat("wisselKoers", 1);
        showFragmentChange(wisselkoers);
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

    //Roept BitcoinRate Fragment op
    public void showFragmentBitcoinRate()
    {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        BitcoinRateFragment bitcoinRateFragment = BitcoinRateFragment.newInstance();
        fragmentTransaction.replace(R.id.container, bitcoinRateFragment);

        //Add this transaction to the back stack. This means that the transaction will be remembered
        //after it is committed, and will reverse its operation when later popped off the stack.
        //name: An optional name for this back stack state, or null.
        //AKA VANGT DE BACK TOETS OP
        fragmentTransaction.addToBackStack("showfragmentbitcoinrate");
        fragmentTransaction.commit();



        setTitle("BitcoinRate");

    }

    //Roept de Change Fragment op
    public void showFragmentChange(float wisselkoers)
    {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ChangeFragment changeFragment = ChangeFragment.newInstance(wisselkoers);
        fragmentTransaction.replace(R.id.container, changeFragment);

        fragmentTransaction.commit();

        setTitle("ChangeFragment");
    }

    //Bij het afsluiten van de activity de bitcoinrate opslaan
    @Override
    protected void onStop() {
        super.onStop();

        //Bitcoinrate opslaan

        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences settings = getSharedPreferences(SavedWisselKoers, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat("wisselKoers",);

        //Commit
        editor.commit();



    }
}
