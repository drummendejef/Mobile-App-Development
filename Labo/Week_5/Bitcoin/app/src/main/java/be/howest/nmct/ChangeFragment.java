package be.howest.nmct;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class ChangeFragment extends Fragment {

    //Controls aanmaken
    private EditText editTextInvoer, editTextResult;
    private Button btnBitcoin, btnEuro, btnWijzigWisselkoers;
    private TextView textViewWisselkoers;

    private float currentRateBitcoinInEuro = 1;

    static final String BITCOIN_RATE = "be.howest.nmct.NEW_BITCOIN_RATE";

    OnRateChangeListener mCallback;

    //Interface toevoegen
    public interface OnRateChangeListener {
        public void showFragmentBitcoinRate();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (OnRateChangeListener) activity;
        } catch (ClassCastException e){
            throw new ClassCastException(activity.toString()
                    + " must implement OnRateChangeListener");
        }
    }

    //Noodzakelijk, default constructor
    public ChangeFragment(){}

    //Komt hier in als je deze fragment wilt opstarten en waardes wilt meegeven.
    public static ChangeFragment newInstance(float wisselkoers) {
        ChangeFragment fragment = new ChangeFragment();
        Bundle args = new Bundle();//Het mandje waar je vanale waarden in kan opslaan
        args.putFloat(BITCOIN_RATE, wisselkoers);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null)
            currentRateBitcoinInEuro = getArguments().getFloat(BITCOIN_RATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflate de layout
        View v = inflater.inflate(R.layout.fragment_change, container, false);

        //ViewControls koppelen.
        editTextInvoer = (EditText) v.findViewById(R.id.editTextInvoer);
        editTextResult = (EditText) v.findViewById(R.id.editTextResult);
        btnBitcoin = (Button) v.findViewById(R.id.buttonBitcoin);
        btnEuro = (Button) v.findViewById(R.id.buttonEuro);
        textViewWisselkoers = (TextView) v.findViewById(R.id.textViewWisselkoers);
        btnWijzigWisselkoers = (Button) v.findViewById(R.id.buttonChangeWisselkoers);

        //Listener aan de button koppelen
        btnBitcoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToBitcoin();
            }
        });
        btnEuro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToEuro();
            }
        });
        btnWijzigWisselkoers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Wisselkoers verander scherm oproepen
                showWisselkoers();
            }
        });

        //Waarde van stuff stuff
        textViewWisselkoers.setText("1 Bitcoin = " + Float.toString(currentRateBitcoinInEuro) + " Euro");



        return v;
    }

    //Waarde aanzien als bitcoin en omzetten naar euro
    private void changeToEuro() {
        //Ophalen
        float invoer = Float.parseFloat(editTextInvoer.getText().toString());
        //Berekenen
        float uitvoer = invoer * currentRateBitcoinInEuro;
        //Wegschrijven
        editTextResult.setText(Float.toString(uitvoer) + " euro");
    }

    //Waarde aanzien als euro en omzetten naar bitcoin
    private void changeToBitcoin() {
        //Ophalen
        float invoer = Float.parseFloat(editTextInvoer.getText().toString());
        //Berekenen
        float uitvoer = invoer / currentRateBitcoinInEuro;
        //Wegschrijven
        editTextResult.setText(Float.toString(uitvoer) + " bitcoin");
    }

    //Wisselkoers weergeven, opgevangen van knop
    private void showWisselkoers()
    {


        mCallback.showFragmentBitcoinRate();
    }


}
