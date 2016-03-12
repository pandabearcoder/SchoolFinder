package eng.soft.schoolfinder.data.libs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "schoolfinder.db";
    public static final String USER_TABLE = "tbluser";
    public static final String SCHOOL_TABLE = "tblschool";
    //user table
    public static final String USER_ID = "USER_ID";
    public static final String USER_NAME = "USER_NAME";
    public static final String USER_PASS = "USER_PASS";
    public static final String USER_FNAME = "USER_FNAME";
    //school table
    public static final String SCHOOL_ID = "SCHOOL_ID";
    public static final String SCHOOL_NAME = "SCHOOL_NAME";
    public static final String SCHOOL_ADDRESS = "SCHOOL_ADDRESS";
    public static final String SCHOOL_PRINCIPAL = "SCHOOL_PRINCIPAL";
    public static final String SCHOOL_CONTACT = "SCHOOL_CONTACT";
    public static final String SCHOOL_TRACKS = "SCHOOL_TRACKS";
    public static final String SCHOOL_READY = "SCHOOL_READY";
    private static final int   DATABASE_VERSION = 1;
    private Context context;

    public DatabaseHelper(Context context) {
        super (context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("CREATE TABLE " + USER_TABLE + " ( "
                    + USER_ID + " INTEGER PRIMARY KEY, "
                    + USER_NAME + " VARCHAR(50) NOT NULL, "
                    + USER_PASS + " VARCHAR(60) NOT NULL, "
                    + USER_FNAME + " VARCHAR(40) NOT NULL);"
            );
        }
        catch (Exception e) {
            Toast.makeText(context,"Failed to make User Table", Toast.LENGTH_SHORT).show();
        }

        try {
            db.execSQL("CREATE TABLE " + SCHOOL_TABLE + " ( "
                + SCHOOL_ID + " INTEGER PRIMARY KEY, "
                + SCHOOL_NAME + " VARCHAR(60) NOT NULL, "
                + SCHOOL_ADDRESS + " VARCHAR(125) NOT NULL, "
                + SCHOOL_PRINCIPAL + " VARCHAR(50) NOT NULL, "
                + SCHOOL_CONTACT + " INTEGER NOT NULL, "
                + SCHOOL_READY + " VARCHAR(10) NOT NULL, "
                + SCHOOL_TRACKS + " VARCHAR(125) NOT NULL)"
            );
        }
        catch (Exception e) {
            Toast.makeText(context,"Failed to make User Table", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXIST "+USER_TABLE);
        db.execSQL("DROP TABLE IF EXIST "+SCHOOL_TABLE);
        onCreate(db);
    }
}
