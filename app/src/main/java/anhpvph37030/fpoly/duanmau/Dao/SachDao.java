package anhpvph37030.fpoly.duanmau.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import anhpvph37030.fpoly.duanmau.Model.Sach;
import anhpvph37030.fpoly.duanmau.Util.DpHelper;

public class SachDao {
    public ArrayList<Sach> listSach(Context context) {
        ArrayList<Sach> list = new ArrayList<Sach>();
        DpHelper dpHelper = new DpHelper(context);
        Cursor cursor = dpHelper.GetData("SELECT * FROM sach");
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                list.add(new Sach(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getString(3),
                        cursor.getInt(4)));
            }

        }
        return list;
    }

    public ArrayList<Sach> listSach_tenloaisach(Context context) {
        ArrayList<Sach> list = new ArrayList<>();
        DpHelper dpHelper = new DpHelper(context);
        Cursor cursor = dpHelper.GetData("SELECT sc.MASACH,sc.TENSACH,sc.GIATHUE, sc.Image,ls.MALOAI,ls.TENLOAISACH FROM sach sc,loaisach ls WHERE sc.MALOAI=ls.MALOAI;");
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                list.add(new Sach(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getString(5)));
            }
        }
        return list;
    }

    public boolean addsach(Context context, String tensach, int giathue, String image, int maloai) {
        DpHelper dpHelper = new DpHelper(context);
        SQLiteDatabase database = dpHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENSACH", tensach);
        values.put("GIATHUE", giathue);
        values.put("Image", image);
        values.put("MALOAI", maloai);
        long check = database.insert("sach", null, values);
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }

    public int deletesach(Context context, int id) {
        DpHelper dpHelper = new DpHelper(context);
        SQLiteDatabase database = dpHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT *FROM phieumuon WHERE MASACH=?", new String[]{String.valueOf(id)});
        if (cursor.getCount() != 0) {
            return -1;
        }
        long check = database.delete("sach", "MASACH=?", new String[]{String.valueOf(id)});
        if (check == -1) {
            return 0;
        } else {
            return 1;
        }
    }

    public String updatesach(Context context, String masach, Sach sach) {
        DpHelper dpHelper = new DpHelper(context);
        SQLiteDatabase database = dpHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT *FROM sach WHERE MASACH=?", new String[]{masach});
        if (cursor.getCount() > 0) {
            ContentValues values = new ContentValues();
            values.put("TENSACH", sach.getTenSach());
            values.put("GIATHUE", sach.getGiaThue());
            values.put("Image", sach.getImage());
            values.put("MALOAI", sach.getMaloaisach());
            long checck = database.update("sach", values, "MASACH=?", new String[]{masach});
            if (checck == -1) {
                return " Update false";
            } else {
                return " update sucssesfull";
            }
        }
        return "update sucssesful";
    }
}
