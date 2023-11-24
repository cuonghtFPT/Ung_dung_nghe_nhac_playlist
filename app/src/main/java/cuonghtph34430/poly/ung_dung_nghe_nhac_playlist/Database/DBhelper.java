package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DATA";
    private static final int DATABASE_VERSION = 1;

    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTABLEsig = "CREATE TABLE sig(TENDANGKY TEXT PRIMARY KEY ," +
                "USERNAME TEXT," +
                "PASSWORD TEXT)";
        db.execSQL(createTABLEsig);

        String dbAdmin = "CREATE TABLE ADMIN(maadmin text primary key, hoten text, matkhau text)";
        db.execSQL(dbAdmin);


        db.execSQL("INSERT INTO ADMIN VALUES('admin','admin','abc123')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTABLEsig = "DROP TABLE IF EXISTS sig";
        db.execSQL(dropTABLEsig);
        db.execSQL("DROP TABLE IF EXISTS ADMIN");
        onCreate(db);
    }
}
