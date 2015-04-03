package be.howest.nmct.loader;

import android.provider.BaseColumns;

/**
 * Created by Jenthe on 21/03/2015.
 */
public final class Contract {

    public interface StudentColumns extends BaseColumns {
        public static final String COLUMN_STUDENT_NAAM = "student_naam";
        public static final String COLUMN_STUDENT_VOONAAM = "student_voornaam";
        public static final String COLUMN_STUDENT_EMAIL = "student_email";
        public static final String COLUMN_STUDENT_SCORE_TOTAAL = "student_score_totaal";
    }
}
