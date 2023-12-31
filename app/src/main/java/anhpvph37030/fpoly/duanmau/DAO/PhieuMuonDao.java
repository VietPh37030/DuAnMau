package anhpvph37030.fpoly.duanmau.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import anhpvph37030.fpoly.duanmau.Model.PhieuMuon;
import anhpvph37030.fpoly.duanmau.Model.Top10;
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
    public ArrayList<Top10> getTop10() {
        ArrayList<Top10> top10List = new ArrayList<>();

        // Chuỗi truy vấn SQL để lấy ra 10 cuốn sách được thuê nhiều nhất
        String query = "SELECT s.TENSACH AS TenSach, COUNT(pm.ID) AS SoLuotMuon " +
                "FROM sach s " +
                "INNER JOIN PM pm ON s.TENSACH = pm.TENSACH " +
                "GROUP BY s.TENSACH " +
                "ORDER BY SoLuotMuon DESC " +
                "LIMIT 10";

        database = dpHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String tenSach = cursor.getString(cursor.getColumnIndex("TenSach"));
                @SuppressLint("Range") int soLuotMuon = cursor.getInt(cursor.getColumnIndex("SoLuotMuon"));

                Top10 topSach = new Top10(tenSach, soLuotMuon);

                top10List.add(topSach);
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return top10List;
    }
    @SuppressLint("Range")
    public int getTongDoanhThu(String startDate, String endDate) {
        database = dpHelper.getReadableDatabase();
        int tongDoanhThu = 0;

        String query = "SELECT SUM(TIENTHUE) AS TongDoanhThu " +
                "FROM PM " +
                "WHERE NGAYTHUE BETWEEN ? AND ?";

        Cursor cursor = database.rawQuery(query, new String[]{startDate, endDate});

        if (cursor.moveToFirst()) {
            tongDoanhThu = cursor.getInt(cursor.getColumnIndex("TongDoanhThu"));
        }

        cursor.close();
        return tongDoanhThu;
    }
}
