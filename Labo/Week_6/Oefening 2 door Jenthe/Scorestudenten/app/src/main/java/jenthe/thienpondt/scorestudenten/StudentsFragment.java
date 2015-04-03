package jenthe.thienpondt.scorestudenten;


import android.app.Fragment;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;

import be.howest.nmct.loader.Contract;
import be.howest.nmct.loader.StudentsLoader;


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
                new String[]{
                        Contract.StudentColumns.COLUMN_STUDENT_NAAM,
                        Contract.StudentColumns.COLUMN_STUDENT_VOONAAM,
                        Contract.StudentColumns.COLUMN_STUDENT_EMAIL,
                        Contract.StudentColumns.COLUMN_STUDENT_SCORE_TOTAAL},
                new int[]{R.id.tvLastName,
                        R.id.tvFirstName,
                        R.id.tvEmail,
                        R.id.tvScore}, 0);

        setListAdapter(mAdapter);

        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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

    class StudentAdapter extends SimpleCursorAdapter {

        private int layout;

        public StudentAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
            super(context, layout, c, from, to, flags);
            this.layout = layout;
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            final LayoutInflater inflater = LayoutInflater.from(context);
            View row = inflater.inflate(layout, parent, false);
            ImageView icon = (ImageView) row.findViewById(R.id.imgIcoon);

            int colnr = cursor.getColumnIndex(Contract.StudentColumns.COLUMN_STUDENT_SCORE_TOTAAL);

            if (cursor.getDouble(colnr) < 8) {
                icon.setImageResource(R.drawable.student_red);
            } else if (cursor.getDouble(colnr) < 10) {
                icon.setImageResource(R.drawable.student_orange);
            } else {
                icon.setImageResource(R.drawable.student_green);
            }

            return row;
        }
    }


}
