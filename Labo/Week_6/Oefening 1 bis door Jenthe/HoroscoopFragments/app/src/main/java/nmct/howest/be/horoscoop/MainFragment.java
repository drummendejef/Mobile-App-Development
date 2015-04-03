package nmct.howest.be.horoscoop;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MainFragment extends Fragment {
    private Button btnGeboortejaar;
    private Button btnHoroscoop;
    private TextView tvGeboortejaar;
    private ImageView imgIcoonGroot;

    public void setImgResourceid(int imgResourceid) {
        this.imgResourceid = imgResourceid;
    }

    private int imgResourceid;
    private String date;
    private OnButtonListener mCallback;

    public MainFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (OnButtonListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " hasn't implemented interface OnDateSelectedListener.");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_horoscoop, container, false);
        btnGeboortejaar = (Button) v.findViewById(R.id.btnGeboortejaar);
        btnHoroscoop = (Button) v.findViewById(R.id.btnHoroscoop);
        tvGeboortejaar = (TextView) v.findViewById(R.id.tvGeboortejaar);
        imgIcoonGroot = (ImageView) v.findViewById(R.id.imgHoroscoopBig);

        btnHoroscoop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.OnButtonHoroscoopPressed();
            }
        });

        btnGeboortejaar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.onButtonGeboorteJaarPressed();
            }
        });

        if (date != null) {
            tvGeboortejaar.setText(date);
        }

        if (imgResourceid != 0){
            imgIcoonGroot.setImageResource(imgResourceid);
        }

        return v;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public interface OnButtonListener {
        public void onButtonGeboorteJaarPressed();

        public void OnButtonHoroscoopPressed();
    }


}
