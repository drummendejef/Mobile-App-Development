package be.howest.nmct.android.stopafstand;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Joren on 20/02/2015.
 */
public class StopAfstandFragment extends Fragment {

    //Attributen: de verschillende controls (views)
    private EditText editTextSnelheid, editTextReactieSnelheid;
    private RadioGroup radioGroupWegtype;
    private Button buttonBerekenStopAfstand;
    private TextView textViewStopafstandResult;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Deze methode gaat de bijbehorende XML-layout-file gaan inladen en als view teruggeven
        View v = inflater.inflate(R.layout.fragment_stop_afstand,container,false);

        //Attributes verbinden
        editTextSnelheid = (EditText) v.findViewById(R.id.editTextSnelheid);
        editTextReactieSnelheid = (EditText) v.findViewById(R.id.editTextReactieSnelheid);
        radioGroupWegtype = (RadioGroup) v.findViewById(R.id.radioGroupWegtype);
        buttonBerekenStopAfstand = (Button) v.findViewById(R.id.buttonBerekenStopAfstand);
        textViewStopafstandResult = (TextView) v.findViewById(R.id.textViewStopafstandResultaat);




        //Listener koppelen aan de button
        buttonBerekenStopAfstand.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                verwerkClickBerekenStopAfstand();
            }
        });

        //View terugsturen
        return v;
    }

    //Berekenen en tonen van resultaat
    private void verwerkClickBerekenStopAfstand()
    {
        //Alle attributes ophalen
        int snelheid = Integer.parseInt(editTextSnelheid.getText().toString());
        float reactiesnelheid = Integer.parseInt(editTextReactieSnelheid.getText().toString());

        //Aanmaken stopafstandobject en invullen.
        StopAfstandInfo s = new StopAfstandInfo(snelheid,reactiesnelheid, StopAfstandInfo.WegType.WEGDEK_NAT);

        //Ophalen welke radiobutton geselecteerd is
        int selectedRadioButtonID = radioGroupWegtype.getCheckedRadioButtonId();
        //Droge radiobutton is geselecteerd
        if(selectedRadioButtonID == R.id.radioButtonDroog)
            s.setWegtype(StopAfstandInfo.WegType.WEGDEK_DROOG);

        //Stopafstand berekenen en in string zetten.
        String result = Float.toString(s.getStopafstand());
        result += " meter";

        textViewStopafstandResult.setText(result);

        //Kort pop-up bericht
        Toast.makeText(getActivity(),result,Toast.LENGTH_LONG).show();

    }


}
