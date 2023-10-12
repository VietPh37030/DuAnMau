package anhpvph37030.fpoly.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import anhpvph37030.fpoly.duanmau.Model.ThanhVien;
import anhpvph37030.fpoly.duanmau.Util.DpHelper;

public class ThanhVienDao {
    private DpHelper dbHelper;
    SQLiteDatabase database;
    public ThanhVienDao(Context context) {
        dbHelper = new DpHelper(context);
    }
    public ArrayList<ThanhVien> getThanhVien() {
        ArrayList<ThanhVien> list = new ArrayList<>();
        database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM thanhvien", null);
        while (cursor.moveToNext()) {
            ThanhVien tv = new ThanhVien(
                    cursor.getString(1),
                    cursor.getInt(2)
            );
            tv.setMatv(cursor.getInt(0));
            list.add(tv);
        }
        return list;
    }
    public long addTV(ThanhVien thanhVien) {
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENTV", thanhVien.getTenTV());
        values.put("NAMSINH", thanhVien.getNamSinh());
        return database.insert("thanhvien", null, values);
    }

    public long deleteTV(int id) {
        database = dbHelper.getWritableDatabase();
        long check = database.delete("thanhvien", "ID=?", new String[]{String.valueOf(id)});
        return check;
    }

    public long updateTV(ThanhVien thanhVien) {
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENTV", thanhVien.getTenTV());
        values.put("NAMSINH", thanhVien.getNamSinh());
        long check = database.update("thanhvien", values, "ID=?", new String[]{String.valueOf(thanhVien.getMatv())});
        return check;
    }
}
