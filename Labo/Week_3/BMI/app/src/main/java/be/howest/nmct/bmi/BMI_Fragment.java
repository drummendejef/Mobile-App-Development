package be.howest.nmct.bmi;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * Created by Joren on 27/02/2015.
 */
public class BMI_Fragment extends Fragment{
    //Control-Views afmaken.
    private EditText editTextHoogte, editTextGewicht;
    private Button buttonUpdate;
    private TextView textViewIndexInfo, textViewCategoryResult;
    private ImageView imageViewResult;
    //Shared Preferences klasse, om data op te slaan als het bestand al eens afgesloten is.
    //declareer naam van preference file als static String-constante
    public static final String PREFS_BMI="MyPrefsFile";

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

        //Als er al iets ingevuld was, terugzetten.
        if(!editTextHoogte.getText().toString().equals("") && !editTextGewicht.getText().toString().equals(""))
        {
            SharedPreferences settings = getActivity().getSharedPreferences(PREFS_BMI, 0);
            editTextHoogte.setText(settings.getString("Length", ""));
            editTextGewicht.setText(settings.getString("Mass",""));
        }

        return v;
    }

    //BMI uitrekenen en afdrukken
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

        imageViewResult.setImageResource(getResourceId(bmiInfo.getCategory()));
    }

    //Zie opgave
    private int getResourceId(BMIInfo.Category category)
    {
        if (category.equals(BMIInfo.Category.ERNSTIG_ONDERGEWICHT)) {
            return R.drawable.silhouette_1;
        } else if (category.equals(BMIInfo.Category.GROOT_ONDERGEWICHT)) {
            return R.drawable.silhouette_2;
        } else if (category.equals(BMIInfo.Category.ONDERGEWICHT)) {
            return R.drawable.silhouette_3;
        } else if (category.equals(BMIInfo.Category.NORMAAL)) {
            return R.drawable.silhouette_4;
        } else if (category.equals(BMIInfo.Category.OVERGEWICHT)) {
            return R.drawable.silhouette_5;
        } else if (category.equals(BMIInfo.Category.MATIG_OVERGEWICHT)) {
            return R.drawable.silhouette_6;
        } else if (category.equals(BMIInfo.Category.ZEER_GROOT_OVERGEWICHT)) {
            return R.drawable.silhouette_7;
        } else {
            return R.drawable.silhouette_8;
        }
    }

    @Override
    public void onResume() {
        //Kijken of er al iets is ingevuld, anders gaat hij crashen
        if(!editTextHoogte.getText().toString().equals("") && !editTextGewicht.getText().toString().equals(""))
            verwerkClickBerekenBMI();

        super.onResume();
    }

    @Override
    public void onStop() {
        if(!editTextHoogte.getText().toString().equals("") && !editTextGewicht.getText().toString().equals(""))
        {
            SharedPreferences settings = getActivity().getSharedPreferences(PREFS_BMI, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("Length", editTextHoogte.getText().toString());
            editor.putString("Mass", editTextGewicht.getText().toString());

            editor.commit();
        }
        super.onStop();
    }
}
