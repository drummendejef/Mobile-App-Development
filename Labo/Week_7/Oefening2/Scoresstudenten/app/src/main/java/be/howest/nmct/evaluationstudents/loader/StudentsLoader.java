package be.howest.nmct.evaluationstudents.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.provider.BaseColumns;

import java.util.Objects;

import be.howest.nmct.evaluationstudents.admin.Student;
import be.howest.nmct.evaluationstudents.admin.StudentAdmin;

/**
 * Created by Joren on 3/04/2015.
 */
public class StudentsLoader extends AsyncTaskLoader<Cursor> {
//Deze klasse gaat alles in de background inladen, om te zorgen dat de app niet
//bevriest.

    private Cursor mCursor;

    private final String[] mColumnNames = new String[]{
            BaseColumns._ID,
            Contract.StudentColumns.COLUMN_STUDENT_NAAM,
            Contract.StudentColumns.COLUMN_STUDENT_VOORNAAM,
            Contract.StudentColumns.COLUMN_STUDENT_EMAIL,
            Contract.StudentColumns.COLUMN_STUDENT_SCORE_TOTAAL
    };

    private static Object lock = new Object();

    //Constructor, moet.
    public StudentsLoader(Context context) {
        super(context);
    }

    //Kijken of er al data ingeladen is, of content gewijzigd.
    @Override
    protected void onStartLoading() {
        if(mCursor != null)//mCursor is al ingevuld.
        {
            deliverResult(mCursor);
        }
        if(takeContentChanged() || mCursor == null)//Cursor nog niet aangemaakt of inhoud veranderd.
            forceLoad();
    }

    @Override
    public Cursor loadInBackground() {
        if(mCursor == null)
            loadCursor();

        return mCursor;
    }

    //creëert een MatrixCursor.
    //http://developer.android.com/reference/android/database/MatrixCursor.html
    private void loadCursor() {
        synchronized (lock)
        {
            if(mCursor != null) return;//Als de cursor al bestaat moet je hem niet nog eens aanmaken

            MatrixCursor cursor = new MatrixCursor(mColumnNames);
            int id = 1;

            for(Student student : StudentAdmin.getStudenten())
            {
                MatrixCursor.RowBuilder row = cursor.newRow();
                row.add(id);
                row.add(student.getNaamStudent());
                row.add(student.getVoornaamStudent());
                row.add(student.getEmailStudent());
                row.add(student.getTotaleScoreStudent());
                id++;
            }
            mCursor = cursor;


        }

    }
}
