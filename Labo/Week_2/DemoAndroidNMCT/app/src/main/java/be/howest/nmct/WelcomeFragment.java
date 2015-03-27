package be.howest.nmct;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Joren on 20/02/2015.
 */
public class WelcomeFragment extends Fragment {

    //Attributen: de verschillende controls (views)
    private EditText editTextMaand;
    private Button buttonOk;
    private TextView textViewAntwoord;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Deze methode gaat de bijbehorende XML-layout-file gaan inladen en als view teruggeven.
        View v = inflater.inflate(R.layout.fragment_welcome,container,false);

        //Attributes invullen
        editTextMaand = (EditText) v.findViewById(R.id.editTextMaand);
        buttonOk = (Button) v.findViewById(R.id.buttonOK);
        textViewAntwoord = (TextView) v.findViewById(R.id.textViewAntwoord);

        //Listener koppelen aan de button
        buttonOk.setOnClickListener( new View.OnClickListener(){
            @Override
        public void onClick(View v) {
                verwerkClickOk();
            }
        } );

        return v;
    }

    private void verwerkClickOk()
    {
        String maand = editTextMaand.getText().toString();

        if(maand.equals(R.string.maandkeuze))
        {
            textViewAntwoord.setText(R.string.goedeKeuze);
            //kort pop-up bericht
            Toast.makeText(getActivity(), "dit is een test", Toast.LENGTH_LONG).show();
        }
        else
            textViewAntwoord.setText(R.string.slechteKeuze);
    }
}
