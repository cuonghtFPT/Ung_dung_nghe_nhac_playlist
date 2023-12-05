package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Database.DBhelper;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.LichSu;

public class LichSuDAO {
    private final DBhelper dbHelper;
    private final SQLiteDatabase sqLiteDatabase;
    public LichSuDAO(Context context) {
        dbHelper = new DBhelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }
    public void playSong(LichSu lichSu) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("IdBaiHat", lichSu.getIdBaiHat());
        values.put("IdCaSi",lichSu.getIdCaSi());
        values.put("ThoiGian", lichSu.getThoiGianNghe()); // Storing as string

        long newRowId = db.insert("LichSuNghe", null, values);
        if (newRowId != -1) {
            // Successful insertion
        } else {
            // Failed insertion
        }
    }
    public List<LichSu> getLichSuData() {
        List<LichSu> lichSuList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LichSuNghe", null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                LichSu lichSu = new LichSu();
                lichSu.IdLichSu = cursor.getInt(cursor.getColumnIndexOrThrow("IdLichSu"));
                lichSu.IdBaiHat = cursor.getInt(cursor.getColumnIndexOrThrow("IdBaiHat"));
                lichSu.IdCaSi = cursor.getInt(cursor.getColumnIndexOrThrow("IdCaSi"));
                lichSu.ThoiGianNghe = cursor.getString(cursor.getColumnIndexOrThrow("ThoiGian"));
                lichSuList.add(lichSu);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return lichSuList;
    }
}
