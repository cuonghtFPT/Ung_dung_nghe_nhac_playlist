package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Database.DBhelper;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.TheLoai;

public class TheLoaiDAO {
    private SQLiteDatabase sqLiteDatabase;
    private DBhelper dbHelper;

    public TheLoaiDAO(Context context) {
        dbHelper = new DBhelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }
    public long ThemTheLoai(TheLoai theLoai){
        ContentValues values = new ContentValues();
        values.put("TenTheLoai", theLoai.getTenTheLoai());
        return (int)sqLiteDatabase.insert("TheLoai", null, values);
    }
    public int CapNhatTheLoai(TheLoai theLoai){
        ContentValues values = new ContentValues();
        values.put("TenTheLoai", theLoai.getTenTheLoai());
        String[] condition = new String[]{String.valueOf(theLoai.getIdTheLoai())};
        return sqLiteDatabase.update("TheLoai",values,"IdTheLoai=?",condition);
    }
    public int XoaTheLoai(TheLoai theLoai){
        String[] condition = new String[]{String.valueOf(theLoai.getIdTheLoai())};
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM TheLoai WHERE IdTheLoai=?",condition);
        if(cursor.getCount()>0){
            return 0;
        }
        int check = sqLiteDatabase.delete("TheLoai","IdTheLoai=?",condition);
        if(check == 0){
            return -1;
        }else {
            return 1;
        }
    }
    public List<TheLoai> getAll(){
        String sql = "SELECT * FROM TheLoai";
        return getTheLoai(sql);
    }
    public TheLoai getID(String id){
        String sql = "SELECT * FROM TheLoai WHERE IdTheLoai=?";
        List<TheLoai> list = getTheLoai(sql,id);
        return list.get(0);
    }
    private List<TheLoai> getTheLoai(String sql,String...selectionArgs){
        List<TheLoai> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            TheLoai theLoai = new TheLoai();
            theLoai.IdTheLoai = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("IdTheLoai")));
            theLoai.TenTheLoai = cursor.getString(cursor.getColumnIndexOrThrow("TenTheLoai"));
            list.add(theLoai);
        }
        return list;
    }
}
