package be.howest.nmct;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {
    //Aanmaak final String constante
    public static final String EXTRA_BIRTHYEAR = "be.howest.nmct.week5oef1.BIRTHYEAR";
    private static final int REQUEST_BIRTHDAY = 0;
    //Properties
    private Button btnStartSelectGeboortejaarActivity;
    private TextView textViewGeboortejaar;

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
        textViewGeboortejaar = (TextView)findViewById(R.id.textViewGeboortejaar);

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
            case REQUEST_BIRTHDAY:
                String geboortejaar = "Geboortejaar: ";
                geboortejaar = data.getStringExtra(MainActivity.EXTRA_BIRTHYEAR);
                textViewGeboortejaar.setText(geboortejaar);
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }


    }
}