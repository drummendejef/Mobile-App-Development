package be.howest.nmct.bmi;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by Joren on 27/02/2015.
 */
public class BMI_Fragment extends Fragment{
    //Control-Views afmaken.
    private EditText editTextHoogte, editTextGewicht;
    private Button buttonUpdate;
    private TextView textViewIndexInfo, textViewCategoryResult;
    private ImageView imageViewResult;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflate te layout
        View v = inflater.inflate(R.layout.fragment_bmi,container,false);
        editTextHoogte = (EditText) v.findViewById(R.id.editTextHoogte);
        editTextGewicht = (EditText) v.findViewById(R.id.editTextGewicht);
        buttonUpdate = (Button) v.findViewById(R.id.buttonUpdate);
        textViewIndexInfo = (TextView) v.findViewById(R.id.textViewIndexInfo);
        textViewCategoryResult = (TextView) v.findViewById(R.id.textViewCategoryResult);
        imageViewResult = (ImageView) v.findViewById(R.id.imageViewResult);

        //Listener koppelen aan de button koppelen
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verwerkClickBerekenBMI();
            }
        });

        return v;
    }

    private void verwerkClickBerekenBMI()
    {
        //Alle attributen ophalen
        float height = Float.parseFloat(editTextHoogte.getText().toString());
        int mass = Integer.parseInt(editTextGewicht.getText().toString());

        //Aanmaken BMI object
        BMIInfo bmiInfo = new BMIInfo(height,mass);

        //BMI uitrekenen
        bmiInfo.recalculate();

        //Resultaten ophalen
        String bmiindexresult = Float.toString(bmiInfo.getBmiIndex());

        //Resultaat wegschrijven
        textViewIndexInfo.setText(bmiindexresult);
        textViewCategoryResult.setText(bmiInfo.getCategory().toString());
    }
}
