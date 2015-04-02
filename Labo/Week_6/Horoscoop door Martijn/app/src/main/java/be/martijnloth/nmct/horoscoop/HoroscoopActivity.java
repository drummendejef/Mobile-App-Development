package be.martijnloth.nmct.horoscoop;

import android.app.ListActivity;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import be.martijnloth.nmct.horoscoop.Data.Data;


public class HoroscoopActivity extends ListActivity {

    private HoroscoopAdapter hAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hAd = new HoroscoopAdapter();
        setListAdapter(hAd);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_horoscoop, menu);
        return true;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Log.d("position", "" + position);
        Data.Horoscoop h = Data.Horoscoop.values()[position];

        Intent i = new Intent();
        i.putExtra(MainActivity.EXTRA_HOROSCOOP, getResourceId(h));
        setResult(RESULT_OK, i);
        finish();
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

    public static int getResourceId(Data.Horoscoop h) {
        switch (h) {
            case WATERMAN:
                return R.drawable.waterman;
            case VISSEN:
                return R.drawable.vissen;
            case RAM:
                return R.drawable.ram;
            case STIER:
                return R.drawable.stier;
            case TWEELING:
                return R.drawable.tweeling;
            case KREEFT:
                return R.drawable.kreeft;
            case LEEUW:
                return R.drawable.leeuw;
            case MAAGD:
                return R.drawable.maagd;
            case WEEGSCHAAL:
                return R.drawable.weegschaal;
            case SCHORPIOEN:
                return R.drawable.schorpioen;
            case BOOGSCHUTTER:
                return R.drawable.boogschutter;
            case STEENBOK:
                return R.drawable.steenbok;
            default:
                return 0;
        }
    }

    public class HoroscoopAdapter extends ArrayAdapter<Data.Horoscoop> {
        public HoroscoopAdapter() {
            super(HoroscoopActivity.this, R.layout.row_horoscoop, R.id.txtName, Data.Horoscoop.values());
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View r = super.getView(position, convertView, parent);
            final Data.Horoscoop h = Data.Horoscoop.values()[position];

            TextView txtName = (TextView) r.findViewById(R.id.txtName);
            txtName.setText(h.getNaamHoroscoop());

            ImageView imgIcon = (ImageView) r.findViewById(R.id.imgIcon);
            imgIcon.setImageResource(getResourceId(h));

            Button btnInfo = (Button) r.findViewById(R.id.btnInfo);
            btnInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), h.getBeginDatum() + " - " + h.getEindDatum(), Toast.LENGTH_LONG).show();
                }
            });
            return r;
        }
    }
}
