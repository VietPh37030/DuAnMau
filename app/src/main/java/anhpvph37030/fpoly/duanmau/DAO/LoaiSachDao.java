package anhpvph37030.fpoly.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import anhpvph37030.fpoly.duanmau.Model.LoaiSach;
import anhpvph37030.fpoly.duanmau.Util.DpHelper;

public class LoaiSachDao {
    private DpHelper dbHelper;
    SQLiteDatabase database;

    public LoaiSachDao(Context context) {
        dbHelper = new DpHelper(context);
    }
    public ArrayList<LoaiSach> getAllLoaiSach() {
        ArrayList<LoaiSach> list = new ArrayList<>();
        database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM loaiSach", null);
        while (cursor.moveToNext()) {
            LoaiSach ls = new LoaiSach(
                    cursor.getString(1)
            );
            ls.setMaloaisach(cursor.getInt(0));
            list.add(ls);
        }
        return list;
    }
    public long addLS(LoaiSach loaiSach) {
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENLOAI", loaiSach.getTenLoai());
        return database.insert("loaiSach", null, values);
    }

    public long deleteLS(int id) {
        database = dbHelper.getWritableDatabase();
        long check = database.delete("loaiSach", "ID=?", new String[]{String.valueOf(id)});
        return check;
    }

    public long updateLS(LoaiSach loaiSach) {
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENLOAI", loaiSach.getTenLoai());
        long check = database.update("loaiSach", values, "ID=?", new String[]{String.valueOf(loaiSach.getMaloaisach())});
        return check;
    }
}
