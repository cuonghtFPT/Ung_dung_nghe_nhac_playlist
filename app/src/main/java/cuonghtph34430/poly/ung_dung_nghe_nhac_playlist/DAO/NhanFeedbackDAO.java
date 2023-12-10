package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.DAO;


import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Database.DBhelper;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.NhanFeedback;

public class NhanFeedbackDAO {
    DBhelper dBhelper;
    SharedPreferences sharedPreferences;
    Context context =null;

    public NhanFeedbackDAO(Context context) {
        if (context != null) {
            dBhelper = new DBhelper(context);
        } else {
            // Xử lý trường hợp context là null
        }
    }

    public ArrayList<NhanFeedback> getDSFeedback() {
        ArrayList<NhanFeedback> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dBhelper.getReadableDatabase();

        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM NHANFEEDBACK", null);

            if (cursor != null) {
                if (cursor.getCount() != 0) {
                    cursor.moveToFirst();
                    do {
                        list.add(new NhanFeedback(cursor.getInt(0), cursor.getString(1),
                                cursor.getString(2), cursor.getString(3), cursor.getString(4)));
                    } while (cursor.moveToNext());
                }

                cursor.close();
            }

            sqLiteDatabase.close();
        }

        return list;
    }

    public long addFeedback(NhanFeedback nhanFeedback) {
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("fullname", nhanFeedback.getFullname());
        values.put("phone", nhanFeedback.getNumber());
        values.put("email", nhanFeedback.getEmail());
        values.put("comment", nhanFeedback.getComment());

        long result = db.insert("NHANFEEDBACK", null, values);
        db.close();
        return result;
    }

    public boolean themFeedback(NhanFeedback nhanFeedback) {
        SQLiteDatabase sqLiteDatabase = dBhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fullname",nhanFeedback.getFullname());
        contentValues.put("phone",nhanFeedback.getNumber());
        contentValues.put("email",nhanFeedback.getEmail());
        contentValues.put("comment",nhanFeedback.getComment());
        long check = sqLiteDatabase.insert("NHANFEEDBACK",null,contentValues);
        return check != -1;
    }
}
