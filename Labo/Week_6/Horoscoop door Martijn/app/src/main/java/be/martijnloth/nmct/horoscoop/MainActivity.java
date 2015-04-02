package be.martijnloth.nmct.horoscoop;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.ExtractedTextRequest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

     public static final String EXTRA_BIRTHDAY = "be.martijnloth.nmct.horoscoop.BIRTHYEAR";
    public static final String EXTRA_HOROSCOOP = "be.martijnloth.nmct.horoscoop.HOROSCOOP";
     private static final int REQUEST_BIRTHDAY = 1;
    private static final int REQUEST_HOROSCOOP = 2;

    TextView jaar;
    Button btnJaar;
    Button btnHoro;
    ImageView imgIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jaar = (TextView) findViewById(R.id.jaar);
        btnJaar = (Button) findViewById(R.id.btnJaar);
        btnHoro = (Button) findViewById(R.id.btnHoroscoop);
        imgIcon = (ImageView) findViewById(R.id.imgIcon);

        btnJaar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecteerGeboortejaar(v);
            }
        });
        btnHoro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecteerHoroscoop(v);
            }
        });
    }

    public void selecteerHoroscoop(View v) {
        Intent i = new Intent(MainActivity.this, HoroscoopActivity.class);
        startActivityForResult(i, REQUEST_HOROSCOOP);
    }

    public void selecteerGeboortejaar(View v) {
        Intent i = new Intent(MainActivity.this, SelectGeboorteJaarActivity.class);
        startActivityForResult(i, REQUEST_BIRTHDAY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_BIRTHDAY:
                switch (resultCode) {
                    case RESULT_CANCELED:
                        Toast.makeText(MainActivity.this, "Geen jaar geselecteerd.", Toast.LENGTH_SHORT).show();
                        break;
                    case RESULT_OK:
                        jaar.setText("Geboortejaar: " + data.getStringExtra(EXTRA_BIRTHDAY));
                        break;
                }
                break;
            case REQUEST_HOROSCOOP:
                switch (resultCode) {
                    case RESULT_CANCELED:
                        Toast.makeText(MainActivity.this, "Geen horoscoop geselecteerd.", Toast.LENGTH_SHORT).show();
                        break;
                    case RESULT_OK:
                        // imgIcon.setImageResource();
                        imgIcon.setImageResource(data.getIntExtra(EXTRA_HOROSCOOP, R.drawable.waterman));
                        break;
                }
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
