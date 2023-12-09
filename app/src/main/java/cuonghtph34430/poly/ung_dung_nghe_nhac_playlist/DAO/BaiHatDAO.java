package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Database.DBhelper;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.BaiHat;

public class BaiHatDAO {
    private final SQLiteDatabase sqLiteDatabase;
    private final DBhelper dbHelper;

    public BaiHatDAO(Context context) {
        dbHelper = new DBhelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }
    public long ThemBaiHat(BaiHat baiHat){
        ContentValues values = new ContentValues();
        values.put("TenBaiHat", baiHat.getTenBaiHat());
        values.put("AnhBaiHat", baiHat.getAnhBaiHat());
        values.put("DuongDan", baiHat.getDuongDan());
        values.put("IdAlbum", baiHat.getIdAlbum());
        values.put("IdCaSi", baiHat.getIdCaSi());
        return (int)sqLiteDatabase.insert("BaiHat", null, values);
    }
    public int CapNhatBaiHat(BaiHat baiHat){
        ContentValues values = new ContentValues();
        values.put("TenBaiHat", baiHat.getTenBaiHat());
        values.put("AnhBaiHat", baiHat.getAnhBaiHat());
        values.put("DuongDan", baiHat.getDuongDan());
        values.put("IdAlbum", baiHat.getIdAlbum());
        values.put("IdCaSi", baiHat.getIdCaSi());
        String[] condition = new String[]{String.valueOf(baiHat.getIdBaiHat())};
        return sqLiteDatabase.update("BaiHat",values,"IdBaiHat=?",condition);
    }
    public int XoaBaiHat(BaiHat baiHat){
        String[] condition = new String[]{String.valueOf(baiHat.getIdBaiHat())};
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM BaiHat WHERE IdBaiHat=?",condition);
        if(cursor.getCount()>0){
            return 0;
        }
        int check = sqLiteDatabase.delete("BaiHat","IdBaiHat=?",condition);
        if(check == 0){
            return -1;
        }else {
            return 1;
        }
    }
    public void tangSoLuotNghe(BaiHat baiHat) {
        ContentValues values = new ContentValues();
        values.put("LuotNghe", baiHat.getLuotNghe() + 1); // Incrementing the play count by 1

        String[] condition = new String[]{String.valueOf(baiHat.getIdBaiHat())};
        sqLiteDatabase.update("BaiHat", values, "IdBaiHat=?", condition);
    }
    public List<BaiHat> getDataByGenre(int genreId) {
        String sql = "SELECT * FROM BaiHat WHERE IdTheLoai = ?";
        Log.d("GetDataByGenre", "SQL query: " + sql + " with genreId: " + genreId);
        return getData(sql, Integer.toString(genreId));
    }

    public List<BaiHat> getAll(){
        String sql = "SELECT * FROM BaiHat";
        return getData(sql);
    }
    public BaiHat getID(String id){
        String sql = "SELECT * FROM BaiHat WHERE IdBaiHat=?";
        List<BaiHat> list = getData(sql,id);
        return list.get(0);
    }
    private List<BaiHat> getData(String sql,String...selectionArgs){
        List<BaiHat> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,selectionArgs);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                BaiHat baiHat = new BaiHat();
                baiHat.IdBaiHat = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("IdBaiHat")));
                baiHat.TenBaiHat = cursor.getString(cursor.getColumnIndexOrThrow("TenBaiHat"));
                baiHat.AnhBaiHat = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("AnhBaiHat")));
                baiHat.DuongDan = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("DuongDan")));
                baiHat.IdAlbum = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("IdAlbum")));
                baiHat.IdCaSi = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("IdCaSi")));
                baiHat.IdTheLoai = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("IdTheLoai")));
                baiHat.LuotNghe = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("LuotNghe")));
                list.add(baiHat);
            }while (cursor.moveToNext());
            cursor.close();
        }
        return list;
    }
}
