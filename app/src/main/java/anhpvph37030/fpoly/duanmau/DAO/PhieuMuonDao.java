package anhpvph37030.fpoly.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import anhpvph37030.fpoly.duanmau.Model.PhieuMuon;
import anhpvph37030.fpoly.duanmau.Util.DpHelper;

public class PhieuMuonDao {
        private DpHelper dpHelper ;
SQLiteDatabase database;

    public PhieuMuonDao(Context context) {
       dpHelper = new DpHelper(context);
    }
    public ArrayList<PhieuMuon> getAllPhieuMuon() {
        ArrayList<PhieuMuon> list = new ArrayList<>();
        database = dpHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM PM", null);
        while (cursor.moveToNext()) {
            PhieuMuon pm = new PhieuMuon(
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getString(4),
                    cursor.getInt(5)
            );
            pm.setId(cursor.getInt(0));
            list.add(pm);
        }
        return list;
    }
    public long addPM(PhieuMuon pm) {
        database = dpHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENTV", pm.getTenTV());
        values.put("TENSACH", pm.getTenSach());
        values.put("TIENTHUE", pm.getTienThue());
        values.put("NGAYTHUE", pm.getNgayThue());
        values.put("TRANGTHAI", pm.getTrangThaiMuon());
        return database.insert("PM", null, values);
    }

    public long deletePM(int id) {
        database = dpHelper.getWritableDatabase();
        long check = database.delete("PM", "ID=?", new String[]{String.valueOf(id)});
        return check;
    }

    public long updatePM(PhieuMuon pm) {
        database = dpHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENTV", pm.getTenTV());
        values.put("TENSACH", pm.getTenSach());
        values.put("TIENTHUE", pm.getTienThue());
        values.put("NGAYTHUE", pm.getNgayThue());
        values.put("TRANGTHAI", pm.getTrangThaiMuon());
        long check = database.update("PM", values, "ID=?", new String[]{String.valueOf(pm.getId())});
        return check;
    }


}
