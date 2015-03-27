package be.howest.nmct.launching;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

    //Initialiseren button
    private Button buttonStartActivity2, btnScoreDialoog;
    //Teruggegeven informatie van activity 2 opvangen
    public static final int REQUEST_CODE_EXPLICIT = 1;
    //Extra informatie teruggeven/meegeven
    public static final String EXTRA_INFO_BACK_LASTNAME = "be.howest.nmct.android.launching.EXTRA_INFO_BACK_LASTNAME";
    public static final String EXTRA_INFO_BACK_AGE = "be.howest.nmct.android.launching.EXTRA_INFO_BACK_AGE";
    //Dialoogvenster stuff
    final Context context = this;
    AlertDialog alertDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Aanmakenbutton
        buttonStartActivity2 = (Button)findViewById(R.id.btnStart2deActivity);
        btnScoreDialoog = (Button)findViewById(R.id.btnScoreDialoog);

        //Opvangen clicks buttons
        buttonStartActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //aanmaken intent om op te roepen
                Intent intent = new Intent(MainActivity.this, ExplicitActivity.class);
                intent.putExtra(ExplicitActivity.EXTRA_INFO,"2NMCT2");
                //startActivity(intent);//Opstarten andere activity
                startActivityForResult(intent,REQUEST_CODE_EXPLICIT);//Andere activity opstarten en antwoord terugverwachten
            }
        });
        btnScoreDialoog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toon het dialoogvenster
                alertDialog.show();
            }
        });

        //Dialoogbox
        //*************
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.dialoogvenster, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        //Set dialoogvenster.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextDialogUserInput);

        //Set dialog message
        alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Get user input en zet het naar resultaat
                Toast.makeText(MainActivity.this, userInput.getText(),Toast.LENGTH_LONG).show();
            }
        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

        //Maak alert dialoog
        alertDialog = alertDialogBuilder.create();




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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode)
        {
            //Controleren van welke vraag e antwoord hebben binnen gekregen
            case REQUEST_CODE_EXPLICIT:
                //Wat was het antwoord?
                switch(resultCode)
                {
                    case RESULT_CANCELED:
                        Toast.makeText(MainActivity.this, "User selects CANCELED", Toast.LENGTH_LONG).show();
                        break;
                    case RESULT_OK:
                        Toast.makeText(MainActivity.this, "User selects OK", Toast.LENGTH_LONG).show();
                        break;
                    case ExplicitActivity.RESULT_SPECIAL:
                        Toast.makeText(MainActivity.this, "User selects SPECIAL", Toast.LENGTH_LONG).show();
                        break;
                    case ExplicitActivity.RESULT_CODE_NOIDEA:
                        String naam = data.getStringExtra(MainActivity.EXTRA_INFO_BACK_LASTNAME);
                        int age = data.getIntExtra(MainActivity.EXTRA_INFO_BACK_AGE, 0);
                        Toast.makeText(MainActivity.this, "Naam: " + naam + ", Leeftijd: " + age, Toast.LENGTH_LONG).show();
                        break;

                }
                break;

            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
