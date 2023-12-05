package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Database.DBhelper;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.Album;

public class AlbumDAO {
    private final DBhelper dbHelper;
    private final SQLiteDatabase sqLiteDatabase;

    public AlbumDAO(Context context) {
        dbHelper = new DBhelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }
    private long AddAlbum(Album album){
        ContentValues values = new ContentValues();
        values.put("TenAlbum", album.getTenAlbum());
        values.put("AnhAlbum", album.getAnhAlbum());
        values.put("IdCaSi", album.getIdCaSi());
        return sqLiteDatabase.insert("Album", null, values);
    }
    private int UpdateAlbum(Album album){
        ContentValues values = new ContentValues();
        values.put("TenAlbum",album.getTenAlbum());
        values.put("AnhAlbum",album.getAnhAlbum());
        values.put("IdCaSi", album.getIdCaSi());
        String[] condition = new String[]{String.valueOf(album.getIdAlbum())};
        return sqLiteDatabase.update("Album",values,"IdAlbum=?",condition);
    }
    private int DeleteAlbum(Album album){
        String[] condition = new String[]{String.valueOf(album.getIdAlbum())};
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Album WHERE IdAlbum=?",condition);
        if(cursor.getCount()>0){
            return 0;
        }
        int check = sqLiteDatabase.delete("Album","IdAlbum=?",condition);
        if(check == 0){
            return -1;
        }else {
            return 1;
        }
    }
    public List<Album> getAlbum() {
        List<Album> albumArrayList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Album", null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Album album = new Album();
                int COL_1 = cursor.getInt(0);
                String COL_2 = cursor.getString(1);
                int COL_3 = cursor.getInt(2);
                int COL_4 = cursor.getInt(3);
                album.setIdAlbum(COL_1);
                album.setTenAlbum(COL_2);
                album.setAnhAlbum(COL_3);
                album.setIdCaSi(COL_4);
                albumArrayList.add(album);
                cursor.moveToNext();
            }
        }
        return albumArrayList;
    }
}
