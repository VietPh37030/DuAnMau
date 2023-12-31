package anhpvph37030.fpoly.duanmau.Util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DpHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "duanmau.db";
    private static final int DATABASE_VERSION = 1;

    public DpHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableAdmin = "CREATE TABLE admin(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TENDANGNHAP TEXT," +
                "HOTEN TEXT," +
                "MATKHAU TEXT)";
        db.execSQL(createTableAdmin);// truy vấn
        String dsnd = "INSERT INTO admin (TENDANGNHAP, HOTEN, MATKHAU) VALUES" +
                "('admin','Pham Viet Anh','admin')," +
                "('123','PhamVanB','123')";
        db.execSQL(dsnd);
        String createTableUser = "CREATE TABLE thuthu(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TENDANGNHAP TEXT," +
                "HOTEN TEXT," +
                "MATKHAU TEXT)";
        db.execSQL(createTableUser);// truy vấn
        String dstt = "INSERT INTO thuthu (TENDANGNHAP, HOTEN, MATKHAU) VALUES" +
                "('thuthu','Phạm Việt ','123')";
        db.execSQL(dstt);

        // phiếu mượn
        String createTablePM = "CREATE TABLE PM(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TENTV TEXT," +
                "TENSACH TEXT," +
                "TIENTHUE INTEGER," +
                "NGAYTHUE TEXT," +
                "TRANGTHAI INTEGER)";
        db.execSQL(createTablePM);
        String pms = "INSERT INTO PM (TENTV, TENSACH, TIENTHUE,NGAYTHUE,TRANGTHAI) VALUES" +
                "('Nguyến Anh Việt','Photoshop',3000,'2023-01-10',1)";
        db.execSQL(pms);

        // loại sách
        String createTableLoaiSach = "CREATE TABLE loaiSach(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TENLOAI TEXT)";
        db.execSQL(createTableLoaiSach);
        String dsls = "INSERT INTO loaiSach(TENLOAI) VALUES" +
                "('Đồ họa')," +
                "('Lập trình')";
        db.execSQL(dsls);

        // sách
        String createTableSach = "CREATE TABLE sach(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TENSACH TEXT," +
                "TIENTHUE INTEGER," +
                "LOAISACH TEXT)";
        db.execSQL(createTableSach);// truy vấn

        String dss = "INSERT INTO sach(TENSACH,TIENTHUE,LOAISACH) VALUES" +
                "('JAVA',1000,'Lập trình')," +
                "('Photoshop',3000,'Đồ họa')";
        db.execSQL(dss);

        // thành viên
        String createTableThanhVien = "CREATE TABLE thanhvien(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TENTV TEXT," +
                "NAMSINH INT)";
        db.execSQL(createTableThanhVien);// truy vấn

        String macdinh = "INSERT INTO thanhvien(TENTV,NAMSINH) VALUES" +
                "('Phạm Việt Anh',2004)," +
                "('Phạm Văn B',1999)";
        db.execSQL(macdinh);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {


        String dropTableUser = "DROP TABLE IF EXISTS user";
        db.execSQL(dropTableUser);
        onCreate(db);

        // phiếu mượn
        String dropTablePM = "DROP TABLE IF EXISTS PM";
        db.execSQL(dropTablePM);
        onCreate(db);

        // loại sách
        String dropTableLoaiSach = "DROP TABLE IF EXISTS loaiSach";
        db.execSQL(dropTableLoaiSach);
        onCreate(db);

        // sách
        String dropTableSach = "DROP TABLE IF EXISTS sach";
        db.execSQL(dropTableSach);
        onCreate(db);

        // thành viên
        String dropTableThanhVien = "DROP TABLE IF EXISTS thanhvien";
        db.execSQL(dropTableThanhVien);
        onCreate(db);

    }

}
