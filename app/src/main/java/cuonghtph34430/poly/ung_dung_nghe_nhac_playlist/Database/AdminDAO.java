package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Database;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AdminDAO {

    DBhelper dBhelper;
    SharedPreferences sharedPreferences;

    public AdminDAO(Context context) {
        dBhelper = new DBhelper(context);
        sharedPreferences =context.getSharedPreferences("THONGTINADMIN",MODE_PRIVATE);
    }

    public boolean checkDangNhap(String maadmin,String matkhau) {
        SQLiteDatabase sqLiteDatabase = dBhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ADMIN WHERE maadmin = ? AND matkhau = ?",new String[]{maadmin,matkhau});
        if(cursor.getCount() != 0) {
            cursor.moveToFirst();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("maadmin",cursor.getString(0));
            editor.putString("matkhau",cursor.getString(2));
            editor.commit();
            return true;
        }else {
            return false;
        }
    }
}
