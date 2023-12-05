package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Database.DBhelper;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.NhanFeedback;

public class FeedbackDAO {
    DBhelper dBhelper;

    public FeedbackDAO(Context context) {
        dBhelper = new DBhelper(context);
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

    public ArrayList<NhanFeedback> getAllFeedbacks() {
        ArrayList<NhanFeedback> feedbacks = new ArrayList<>();
        SQLiteDatabase db = dBhelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM NHANFEEDBACK", null);

        if (cursor.moveToFirst()) {
            do {
                NhanFeedback feedback = new NhanFeedback();
                feedback.setMafeedback(cursor.getInt(0));
                feedback.setFullname(cursor.getString(1));
                feedback.setNumber(cursor.getString(2));
                feedback.setEmail(cursor.getString(3));
                feedback.setComment(cursor.getString(4));

                feedbacks.add(feedback);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return feedbacks;
    }
}
