package anhpvph37030.fpoly.duanmau.Dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import anhpvph37030.fpoly.duanmau.Model.Thanhvien;
import anhpvph37030.fpoly.duanmau.Util.DpHelper;

public class ThanhVienDao {
    DpHelper sqLite;

    public ArrayList<Thanhvien> ListTV(Context context){
        ArrayList<Thanhvien> list=new ArrayList<>();
        DpHelper sqLite = new DpHelper(context);
        Cursor cursor= sqLite.GetData("SELECT * FROM THANHVIEN");
        if(cursor.getCount()!=0){
            while (cursor.moveToNext()){
                list.add(new Thanhvien(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)));
            }
        }
        return list;
    }
    public boolean addTV(Context context,String hoten,String namsinh){
        DpHelper dbHelper=new DpHelper(context);
        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("HOTEN",hoten);
        contentValues.put("NAMSINH",namsinh);
        long check =  sqLiteDatabase.insert("thanhvien",null,contentValues);;
        if(check==-1){
            return false;
        } else {
            return true;
        }
    }

    public int deleteTV(Context context, int id) {
        DpHelper dbHelper = new DpHelper(context);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM phieumuon WHERE MATV =?", new String[]{String.valueOf(id)});
        if (cursor.getCount() != 0) {
            return -1;
        }
        long check = sqLiteDatabase.delete("thanhvien", "MATV =?", new String[]{String.valueOf(id)});
        if (check == -1) {
            return 0;
        } else {
            return 1;
        }
    }

    public String updateTV(Context context, int id, Thanhvien thanhVien) {
        DpHelper dbHelper = new DpHelper(context);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM thanhvien WHERE MATV= ?", new String[]{String.valueOf(id)});
        if (cursor.getCount() > 0) {
            ContentValues contentValues=new ContentValues();
            contentValues.put("HOTEN",thanhVien.getHoten());
            contentValues.put("NAMSINH",thanhVien.getNamsinh());
            long check = sqLiteDatabase.update("thanhvien", contentValues, "MATV = ?", new String[]{String.valueOf(id)});
            if (check == -1) {
                return "Cập nhật thất Bại";
            } else {
                return "Thành công";
            }
        }
        return "Thành công";
    }

}
