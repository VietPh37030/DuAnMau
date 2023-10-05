package anhpvph37030.fpoly.duanmau.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import anhpvph37030.fpoly.duanmau.Model.LoaiSach;
import anhpvph37030.fpoly.duanmau.Util.DpHelper;

public class LoaiSachDao {
    public ArrayList<LoaiSach> getLoaiSach(Context context) {
        ArrayList<LoaiSach> list = new ArrayList<>();
        DpHelper dpHelper = new DpHelper(context);
        Cursor cursor = dpHelper.GetData("SELECT * FROM loaisach");
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                list.add(new LoaiSach(cursor.getInt(0), cursor.getString(1)));
            }
        }
        return list;
    }

    public boolean addls(Context context, String tenloai) {
        DpHelper dpHelper = new DpHelper(context);
        SQLiteDatabase database = dpHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENLOAISACH", tenloai);
        long check = database.insert("loaisach", null, values);
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }

    public int deletels(Context context, int id) {
        DpHelper dpHelper = new DpHelper(context);
        SQLiteDatabase database = dpHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM sach WHERE MALOAI=? ", new String[]{String.valueOf(id)});
        if (cursor.getCount() != 0) {
            return -1;
        }
        long check = database.delete("loaisach", "MALOAI=? ", new String[]{String.valueOf(id)});
        if (check == -1) {
            return 0;
        } else {
            return 1;
        }
    }

    public String updatels(Context context, String maLoai, LoaiSach loaiSach) {
        DpHelper dpHelper = new DpHelper(context);
        SQLiteDatabase database = dpHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM loaisach WHERE MALOAI=?", new String[]{maLoai});
        if (cursor.getCount() != 0) {
            ContentValues values = new ContentValues();
            values.put("MALOAI", loaiSach.getMaloai());
            values.put("TENLOAISACH", loaiSach.getTenloaisach());
            long check = database.update("loaisach", values, "MALOAI=?", new String[]{maLoai});
            if (check == -1) {
                return "ERROR";
            } else {
                return "OK";
            }
        }
        return "UPDATE SUSSECFULL";
    }

}
