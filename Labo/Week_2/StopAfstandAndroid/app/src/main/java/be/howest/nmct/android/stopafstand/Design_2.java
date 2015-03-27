package be.howest.nmct.android.stopafstand;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Design_2 extends Fragment {

    //Attributen: de verschillende controls (views) aanmaken
    private SeekBar seekBarSnelheid, seekBarReactieSnelheid;
    private RadioGroup radioGroupWegtype;
    private Button buttonBerekenStopAfstand;
    private TextView textViewStopafstandResult, textViewSnelheidFeedback, textViewReactieSnelheidFeedback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v2 = inflater.inflate(R.layout.fragment_design_2,container,false);
        seekBarSnelheid = (SeekBar) v2.findViewById(R.id.seekBarSnelheid);
        seekBarReactieSnelheid = (SeekBar) v2.findViewById(R.id.seekBarReactieSnelheid);
        radioGroupWegtype = (RadioGroup) v2.findViewById(R.id.radioGroupWegtype);
        buttonBerekenStopAfstand = (Button) v2.findViewById(R.id.buttonBerekenStopAfstand);
        textViewStopafstandResult = (TextView) v2.findViewById(R.id.textViewStopafstandResultaat);
        textViewSnelheidFeedback = (TextView) v2.findViewById(R.id.textViewSnelheidFeedBack);
        textViewReactieSnelheidFeedback = (TextView) v2.findViewById(R.id.textViewReactiesnelheidFeedback);

        //Listener koppelen aan de seekbars
        seekBarSnelheid.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewSnelheidFeedback.setText(String.valueOf(progress + " km/u"));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Auto generated method
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Auto generated method
            }
        });

        seekBarReactieSnelheid.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewReactieSnelheidFeedback.setText(String.valueOf(progress /10.0f + " seconden"));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        //Listener koppelen aan de button
        buttonBerekenStopAfstand.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                verwerkClickBerekenStopAfstand();
            }
        });

        return v2;
    }

    private void verwerkClickBerekenStopAfstand() {
        //Alle attributen ophalen
        int snelheid = seekBarSnelheid.getProgress();
        float reactiesnelheid = seekBarReactieSnelheid.getProgress() / 10.0f;

        //Aanmaken stopafstandobject en invullen
        StopAfstandInfo s = new StopAfstandInfo(snelheid,reactiesnelheid, StopAfstandInfo.WegType.WEGDEK_DROOG);

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
        Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
    }


}
