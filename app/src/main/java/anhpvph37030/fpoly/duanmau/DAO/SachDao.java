package anhpvph37030.fpoly.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import anhpvph37030.fpoly.duanmau.Model.Sach;
import anhpvph37030.fpoly.duanmau.Util.DpHelper;

public class SachDao {
    private DpHelper dbHelper;
    SQLiteDatabase database;

    public SachDao(Context context) {
        dbHelper = new DpHelper(context);
    }
    public ArrayList<Sach> getSach() {
        ArrayList<Sach> list = new ArrayList<>();
        database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM sach", null);
        while (cursor.moveToNext()) {
            Sach s = new Sach(
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getString(3)
            );
            s.setMasach(cursor.getInt(0));
            list.add(s);
        }
        return list;
    }

    public long addS(Sach sach) {
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENSACH", sach.getTenSach());
        values.put("TIENTHUE", sach.getTienThue());
        values.put("LOAISACH", sach.getLoaiSach());
        return database.insert("sach", null, values);
    }

    public long deleteS(int id) {
        database = dbHelper.getWritableDatabase();
        long check = database.delete("sach", "ID=?", new String[]{String.valueOf(id)});
        return check;
    }

    public long updateS(Sach sach) {
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENSACH", sach.getTenSach());
        values.put("TIENTHUE", sach.getTienThue());
        values.put("LOAISACH", sach.getLoaiSach());
        long check = database.update("sach", values, "ID=?", new String[]{String.valueOf(sach.getMasach())});
        return check;
    }
}
