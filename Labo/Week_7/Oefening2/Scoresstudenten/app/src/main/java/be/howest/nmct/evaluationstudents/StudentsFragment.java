package be.howest.nmct.evaluationstudents;


import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;

import be.howest.nmct.evaluationstudents.loader.Contract;
import be.howest.nmct.evaluationstudents.loader.StudentsLoader;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudentsFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {
    StudentAdapter mAdapter;

    public StudentsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAdapter = new StudentAdapter(getActivity(),
                R.layout.row_student,
                null,
                new String[] {
                        Contract.StudentColumns.COLUMN_STUDENT_NAAM,
                        Contract.StudentColumns.COLUMN_STUDENT_VOORNAAM,
                        Contract.StudentColumns.COLUMN_STUDENT_EMAIL,
                        Contract.StudentColumns.COLUMN_STUDENT_SCORE_TOTAAL
                },
                new int[]{R.id.textViewNaam,
                    R.id.textViewVoornaam,
                    R.id.textViewEmail,
                    R.id.textViewScore}, 0);

    setListAdapter(mAdapter);

    getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_students, container, false);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new StudentsLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    //Studentadapter, erft over van simplecursoradapter en visualiseert data van een cursor
    class StudentAdapter extends SimpleCursorAdapter
    {
        //Constructor, kan hij zelf aanmaken
        public StudentAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
            super(context, layout, c, from, to, flags);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            super.bindView(view, context, cursor);

            ImageView icon = (ImageView) view.findViewById(R.id.imageViewKleur);

            int colnr = cursor.getColumnIndex(Contract.StudentColumns.COLUMN_STUDENT_SCORE_TOTAAL);
            double score = cursor.getDouble(colnr);
            DecimalFormat df = new DecimalFormat("##.00");
            TextView textviewTotaleScore = (TextView) view.findViewById(R.id.textViewScore);
            textviewTotaleScore.setText(df.format(score));

            if(score < 8)
                icon.setImageResource(R.drawable.student_red);
            else if (score < 10)
                icon.setImageResource(R.drawable.student_orange);
            else
                icon.setImageResource(R.drawable.student_green);

        }
    }


}
