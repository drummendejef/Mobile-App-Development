package nmct.howest.be.horoscoop;


import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class HoroscoopFragment extends ListFragment {
    OnHoroscoopSelectListener mCallback;


    public HoroscoopFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (OnHoroscoopSelectListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " hasn't implemented interface OnHoroscoopSelectListener.");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setListAdapter(new HoroscoopAdapter());
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        mCallback.onHoroscoopSelected(getImageId(Data.Horoscoop.values()[position].getNaamHoroscoop()));
    }

    class HoroscoopAdapter extends ArrayAdapter<Data.Horoscoop> {
        public HoroscoopAdapter() {
            super(getActivity(), R.layout.row_horoscoop, R.id.tvHoroscoopNaam, Data.Horoscoop.values());
        }

        class ViewHolder {
            public ImageView imgIcon = null;
            public TextView tvHoroscoop = null;
            public Button btnInfo = null;

            public ViewHolder(View row) {
                this.imgIcon = (ImageView) row.findViewById(R.id.imgHoroscoopIcon);
                this.tvHoroscoop = (TextView) row.findViewById(R.id.tvHoroscoopNaam);
                this.btnInfo = (Button) row.findViewById(R.id.btnInfo);
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = super.getView(position, convertView, parent);

            final Data.Horoscoop horoscoop = Data.Horoscoop.values()[position];

            ViewHolder holder = (ViewHolder) row.getTag();

            if (holder == null) {
                holder = new ViewHolder(row);
                row.setTag(holder);
            }

            holder.tvHoroscoop.setText(horoscoop.getNaamHoroscoop());

            holder.imgIcon.setImageResource(getImageId(horoscoop.getNaamHoroscoop()));

            holder.btnInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), horoscoop.getBeginDatum() + " - " + horoscoop.getEindDatum(), Toast.LENGTH_SHORT).show();
                }
            });

            return row;

        }
    }

    public int getImageId(String name) {
        switch (name) {
            case "Waterman":
                return R.drawable.waterman;
            case "Boogschutter":
                return R.drawable.boogschutter;
            case "Kreeft":
                return R.drawable.kreeft;
            case "Leeuw":
                return R.drawable.leeuw;
            case "Maagd":
                return R.drawable.maagd;
            case "Ram":
                return R.drawable.ram;
            case "Schorpioen":
                return R.drawable.schorpioen;
            case "Steenbok":
                return R.drawable.steenbok;
            case "Stier":
                return R.drawable.stier;
            case "Tweeling":
                return R.drawable.tweeling;
            case "Vissen":
                return R.drawable.vissen;
            default:
                return R.drawable.weegschaal;

        }
    }

    public interface OnHoroscoopSelectListener {
        public void onHoroscoopSelected(int id);
    }

}
