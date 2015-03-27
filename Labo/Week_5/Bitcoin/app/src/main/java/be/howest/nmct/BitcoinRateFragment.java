package be.howest.nmct;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class BitcoinRateFragment extends Fragment {
    //Controls aanmaken
    private EditText editTextWisselkoers;
    private Button btnSaveWisselkoers;

    private float rate1BitcoinInEuros;

    static final String BITCOIN_RATE = "be.howest.nmct.NEW_BITCOIN_RATE";

    OnStartChangeFragment mCallback;

    public interface OnStartChangeFragment{
        public void showFragmentChange(float wisselkoers);
    }

    //Vereist, default constructor
    public BitcoinRateFragment() {
        // Required empty public constructor
    }

    //Komt hier in als je deze fragment wilt opstarten en waardes wilt meegeven.
    public static BitcoinRateFragment newInstance()
    {
        return new BitcoinRateFragment();
        /*BitcoinRateFragment bitcoinfragment = new BitcoinRateFragment();
        Bundle args = new Bundle();
        //args.putFloat(BITCOIN_RATE, bitcoinrate);
        bitcoinfragment.setArguments(args);
        return bitcoinfragment;*/
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        //Zorgen dat de container activity zeker de callback interface
        //geïmplementeerd is, anders exception gooien
        try
        {
            mCallback = (OnStartChangeFragment) activity;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString() + " moet OnStartChangeFragment implementeren");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null)
            rate1BitcoinInEuros = getArguments().getFloat(BITCOIN_RATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_bitcoinrate, container, false);
        editTextWisselkoers = (EditText) v.findViewById(R.id.editTextWisselkoers);
        btnSaveWisselkoers = (Button) v.findViewById(R.id.buttonSaveWisselkoers);

        btnSaveWisselkoers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HaalWisselkoersOp();
            }
        });
        return v;
    }

    private void HaalWisselkoersOp() {
        rate1BitcoinInEuros = Float.parseFloat(editTextWisselkoers.getText().toString());

        mCallback.showFragmentChange(rate1BitcoinInEuros);
    }



}
