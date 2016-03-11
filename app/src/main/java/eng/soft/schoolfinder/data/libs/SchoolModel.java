package eng.soft.schoolfinder.data.libs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import eng.soft.schoolfinder.obj.SchoolObj;
public class SchoolModel extends  DatabaseHelper {
    Context context;

    public SchoolModel(Context context) {
        super(context);
        this.context = context;
    }

    public ArrayList<SchoolObj> getAllSchool() {
        ArrayList<SchoolObj> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+SCHOOL_TABLE+ " ;", null);
        if(cursor.moveToFirst()){
            do{
                SchoolObj o = new SchoolObj();
                o.schoolID = cursor.getInt(cursor.getColumnIndex(SCHOOL_ID));
                o.schoolName = cursor.getString(cursor.getColumnIndex(SCHOOL_NAME));
                o.schoolTracks = cursor.getString(cursor.getColumnIndex(SCHOOL_TRACKS));
                o.schoolType = cursor.getString(cursor.getColumnIndex(SCHOOL_TYPE));
                o.schoolCourses = cursor.getString(cursor.getColumnIndex(SCHOOL_COURSES));
                o.schoolAddress = cursor.getString(cursor.getColumnIndex(SCHOOL_ADDRESS));
                o.schoolContact = cursor.getString(cursor.getColumnIndex(SCHOOL_CONTACT));
                o.schoolWebsite = cursor.getString(cursor.getColumnIndex(SCHOOL_WEBSITE));
                o.schoolReady = cursor.getString(cursor.getColumnIndex(SCHOOL_READY));
                list.add(o);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public boolean addSchool(ContentValues cv){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.insert(SCHOOL_TABLE, null, cv);
        return result >= 0;
    }
}
