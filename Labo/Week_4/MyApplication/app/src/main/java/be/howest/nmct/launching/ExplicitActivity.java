package be.howest.nmct.launching;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ExplicitActivity extends Activity {

    //Initialiseren
    private TextView txtvwRichting;
    private Button btnOK, btnCANCEL, btnSpecial, btnNoIdea;
    //constante string aanmaken
    public static final String EXTRA_INFO = "be.howest.nmct.android.launching.EXTRA_INFO";

    //Constante int aanmaken
    public static final int RESULT_SPECIAL = 1;
    public static final int RESULT_CODE_NOIDEA = 2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit);

        //Aanmaken view controlls
        txtvwRichting = (TextView) findViewById(R.id.txtvwRichting);
        btnOK = (Button) findViewById(R.id.btnOK);
        btnCANCEL = (Button) findViewById(R.id.btnCANCEL);
        btnSpecial = (Button) findViewById(R.id.btnSpecial);
        btnNoIdea = (Button)findViewById(R.id.btnNoIdea);

        //Waarde ophalen uit const
        String value = getIntent().getStringExtra(ExplicitActivity.EXTRA_INFO);

        //Waarde wegschrijven
        txtvwRichting.setText(value);

        //Buttonsclicks opvangen
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });
        btnCANCEL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
        btnSpecial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_SPECIAL);
                finish();
            }
        });
        btnNoIdea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //aanmaken intent om op te roepen
                Intent intent = new Intent();
                intent.putExtra(MainActivity.EXTRA_INFO_BACK_LASTNAME, "Vandamme");
                intent.putExtra(MainActivity.EXTRA_INFO_BACK_AGE,30);
                setResult(ExplicitActivity.RESULT_CODE_NOIDEA, intent);
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_explicit, menu);
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
