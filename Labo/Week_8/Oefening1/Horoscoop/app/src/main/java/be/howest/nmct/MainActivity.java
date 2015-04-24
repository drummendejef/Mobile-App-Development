package be.howest.nmct;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
    //Aanmaak final String constante
    public static final String EXTRA_BIRTHYEAR = "be.howest.nmct.week6oef1.BIRTHYEAR";
    public static final String EXTRA_HOROSCOOP = "be.howest.nmct.week6oef1.HOROSCOOP";
    private static final int REQUEST_BIRTHDAY = 0;
    private static final int REQUEST_HOROSCOOP = 1;
    //Properties
    private Button btnStartSelectGeboortejaarActivity, btnStartHoroscoopActivity;
    private TextView textViewGeboortejaar;
    private ImageView imageViewHoroscoopResult;
    //Om te kunnen sharen
    private ShareActionProvider mShareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        //Aanmaken controls
        btnStartSelectGeboortejaarActivity = (Button)findViewById(R.id.buttonStartGeboortejaar);
        btnStartHoroscoopActivity = (Button)findViewById(R.id.buttonStarthoroscoop);
        textViewGeboortejaar = (TextView)findViewById(R.id.textViewGeboortejaar);
        imageViewHoroscoopResult = (ImageView)findViewById(R.id.imageViewHoroscoopResult);
        //Menuitem met shareActionProvider zoeken
        MenuItem item = menu.findItem(R.id.menu_item_share);
        //ShareActionProvider ophalen en opslaan
        mShareActionProvider = (ShareActionProvider) item.getActionProvider();


        //Opvangen buttonclicks
        btnStartSelectGeboortejaarActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Aanmaken intent om op te roepen
                Intent intent = new Intent(MainActivity.this, SelectGeboortejaarActivity.class);
                //Opstarten andere activity, een resultaat terugverwachten
                startActivityForResult(intent, REQUEST_BIRTHDAY);
                //Het resultaat wordt opgevangen in "onActivityResult"
            }
        });
        btnStartHoroscoopActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Aanmaken intent om op te roepen
                Intent intent = new Intent(MainActivity.this, HoroscoopActivity.class);
                //Opstarten andre activity, een resultaat terugverwachten
                startActivityForResult(intent, REQUEST_HOROSCOOP);
                //Het resultaat wordt opgevangen in "onActivityResult"
            }
        });

        return true;
    }

    //Call om de share intent te updaten
    private void setShareIntent(Intent shareIntent)
    {
        if(mShareActionProvider != null)
            mShareActionProvider.setShareIntent(shareIntent);
    }

    private Intent getDefaultIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/*");
        intent.putExtra(Intent.EXTRA_TEXT,textViewGeboortejaar.getText());
        setShareIntent(intent);
        return intent;
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

    //Intent voor selectgeboortejaar
    public void selecteerGeboortejaar(View v)
    {
        Intent intent = new Intent(MainActivity.this, SelectGeboortejaarActivity.class);
        startActivityForResult(intent, REQUEST_BIRTHDAY);
    }

    //Opvangen van resultaat dat SelectGeboortejaarActivity terugstuurt

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode)
            //Controlleren op welke vraag we een antwoord hebben binnen gekregen
        {
            //Het geboortejaar wordt teruggegeven
            case REQUEST_BIRTHDAY:
                String geboortejaar = "Geboortejaar: ";
                geboortejaar += data.getStringExtra(MainActivity.EXTRA_BIRTHYEAR);
                textViewGeboortejaar.setText(geboortejaar);
                //Om te kunnen sharen.
                mShareActionProvider.setShareIntent(getDefaultIntent());
                break;
            //De horoscoop wordt teruggegeven
            case REQUEST_HOROSCOOP:
                switch (resultCode)
                {
                    case RESULT_CANCELED:
                        Toast.makeText(MainActivity.this, "Geen horoscoop geselecteerd.", Toast.LENGTH_SHORT).show();
                        break;
                    case RESULT_OK:
                        //imageViewHoroscoopResult een afbeelding geven
                        imageViewHoroscoopResult.setImageResource(data.getIntExtra(EXTRA_HOROSCOOP, R.drawable.waterman));
                }

                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }


    }
}
