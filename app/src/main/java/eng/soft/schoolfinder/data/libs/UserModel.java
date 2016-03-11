package eng.soft.schoolfinder.data.libs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import eng.soft.schoolfinder.obj.UserObj;

public class UserModel extends DatabaseHelper {
    Context context;

    public UserModel(Context context) {
        super(context);
        this.context = context;
    }

    public boolean addUser(ContentValues cv){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.insert(USER_TABLE, null, cv);
        return result >= 0;
    }

    public ArrayList<UserObj> getAllUser() {
        ArrayList<UserObj> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+USER_TABLE+ " ORDER BY " +USER_ID+" DESC;", null);
        if(cursor.moveToFirst()){
            do{
                UserObj o = new UserObj();
                o.userid = cursor.getInt(cursor.getColumnIndex(USER_ID));
                o.username = cursor.getString(cursor.getColumnIndex(USER_NAME));
                o.userpass = cursor.getString(cursor.getColumnIndex(USER_PASS));
                o.userfname = cursor.getString(cursor.getColumnIndex(USER_FNAME));
                list.add(o);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public String getFirstName() {
        String name = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT "+USER_FNAME+" FROM "+USER_TABLE, null);
        if(cursor.moveToFirst()){
            do {
                name = cursor.getString(cursor.getColumnIndex(USER_FNAME));
            }
            while(cursor.moveToNext());
        }
        cursor.close();

        return name;
    }
}
