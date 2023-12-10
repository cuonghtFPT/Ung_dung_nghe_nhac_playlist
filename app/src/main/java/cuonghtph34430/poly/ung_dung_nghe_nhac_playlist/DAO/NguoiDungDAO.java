package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.DAO;

import static android.content.Context.MODE_PRIVATE;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Database.DBhelper;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.NguoiDung;

public class NguoiDungDAO {
    private DBhelper dBhelper;
    private SQLiteDatabase db;
    public NguoiDungDAO(Context context) {
        dBhelper = new DBhelper(context);
    }

    public long ThemNguoiDung(NguoiDung nguoiDung){
        SQLiteDatabase database=dBhelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("TenDangKy", nguoiDung.TenDangKy);
        values.put("TenNguoiDung", nguoiDung.TenNguoiDung);
        values.put("MatKhau", nguoiDung.MatKhau);
        return database.insert("NguoiDung",null,values);
    }
    public boolean checklogin(String username, String password) {
        db = dBhelper.getReadableDatabase();
        String sql = "SELECT * FROM NguoiDung WHERE TenDangKy = ? AND MatKhau = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{username, password});
        // Check if the cursor has any results
        boolean hasResult = cursor.moveToFirst();

        // Close the cursor
        cursor.close();

        return hasResult;
    }
    public boolean checkIfEmailExists(String email) {
        // đọc dữ liệu
        db = this.dBhelper.getReadableDatabase();
        String query = "SELECT * FROM NguoiDung" + " WHERE TenNguoiDung = ?";
        // Thực thi câu truy vấn và trả về đối tượng Cursor
        Cursor cursor = db.rawQuery(query, new String[]{email});
        // Kiểm tra xem có ít nhất một dòng được trả về từ cơ sở dữ liệu hay không
        boolean exists = cursor.moveToFirst();
        cursor.close();
        // Trả về true nếu có ít nhất một dòng được trả về từ cơ sở dữ liệu, ngược lại trả về false
        return exists;
    }
}
