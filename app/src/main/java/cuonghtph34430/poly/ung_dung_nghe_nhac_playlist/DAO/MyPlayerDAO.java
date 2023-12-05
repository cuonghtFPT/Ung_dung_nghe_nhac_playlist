package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.DAO;

import static android.content.Context.MODE_PRIVATE;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Database.DBhelper;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.Login;

public class MyPlayerDAO {

    private final DBhelper dBhelper;
    SharedPreferences sharedPreferences;
    public MyPlayerDAO(Context context) {
        dBhelper = new DBhelper(context);
        sharedPreferences =context.getSharedPreferences("THONGTINADMIN",MODE_PRIVATE);
    }

    public long addsig(Login login){
        SQLiteDatabase database=dBhelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("TENDANGKY",login.getTendangnhap());
        values.put("USERNAME",login.getUsername());
        values.put("PASSWORD",login.getPassword());
        return database.insert("sig",null,values);
    }

    public boolean checklogin(String username, String password) {
        SQLiteDatabase database = dBhelper.getReadableDatabase();
        String sql = "SELECT * FROM sig WHERE USERNAME = ? AND PASSWORD = ?";
        Cursor cursor = database.rawQuery(sql, new String[]{username, password});

        // Check if the cursor has any results
        boolean hasResult = cursor.moveToFirst();

        // Close the cursor
        cursor.close();

        return hasResult;
    }
    public boolean checkIfEmailExists(String email) {
        // đọc dữ liệu
        SQLiteDatabase db = this.dBhelper.getReadableDatabase();
        String query = "SELECT * FROM sig" + " WHERE USERNAME = ?";
        // Thực thi câu truy vấn và trả về đối tượng Cursor
        Cursor cursor = db.rawQuery(query, new String[]{email});
        // Kiểm tra xem có ít nhất một dòng được trả về từ cơ sở dữ liệu hay không
        boolean exists = cursor.moveToFirst();
        cursor.close();
        // Trả về true nếu có ít nhất một dòng được trả về từ cơ sở dữ liệu, ngược lại trả về false
        return exists;
    }
}
