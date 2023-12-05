package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Database.DBhelper;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.CaSi;


public class CaSiDAO {
    private final SQLiteDatabase sqLiteDatabase;
    private final DBhelper dbHelper;

    public CaSiDAO(Context context) {
        dbHelper = new DBhelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }
    public long ThemCaSi(CaSi caSi){
        ContentValues values = new ContentValues();
        values.put("TenCaSi",caSi.getTenCaSi());
        values.put("AnhCaSi",caSi.getAnhCaSi());
        return (int) sqLiteDatabase.insert("CaSi",null,values);
    }
    public int SuaCaSi(CaSi caSi){
        ContentValues values = new ContentValues();
        values.put("TenCaSi",caSi.getTenCaSi());
        values.put("AnhCaSi",caSi.getAnhCaSi());
        String[] condition = new String[]{String.valueOf(caSi.getIdCaSi())};
        return sqLiteDatabase.update("CaSi",values,"IdCaSi=?",condition);
    }
    public int XoaCaSi(CaSi caSi){
        String[] condition = new String[]{String.valueOf(caSi.getIdCaSi())};
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM CaSi WHERE IdCaSi=?",condition);
        if(cursor.getCount()>0){
            return 0;
        }
        int check = sqLiteDatabase.delete("CaSi","IdCaSi=?",condition);
        if(check == 0){
            return -1;
        }else {
            return 1;
        }
    }
    public List<CaSi> getAll(){
        String sql = "SELECT * FROM CaSi";
        return getArtist(sql);
    }
    public CaSi getID(String id){
        String sql = "SELECT * FROM CaSi WHERE IdCaSi=?";
        List<CaSi> list = getArtist(sql,id);
        return list.get(0);
    }
    public List<CaSi> getArtist(String sql,String...selectionArgs){
        List<CaSi> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            CaSi caSi = new CaSi();
            caSi.IdCaSi = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("IdCaSi")));
            caSi.TenCaSi = cursor.getString(cursor.getColumnIndexOrThrow("TenCaSi"));
            caSi.AnhCaSi = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("AnhCaSi")));
            list.add(caSi);
        }
        return list;
    }
}
