package anhpvph37030.fpoly.duanmau.Util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DpHelper extends SQLiteOpenHelper  {
    public DpHelper(@Nullable Context context) {
        super(context, "DuAn.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tableone =  "CREATE TABLE thuthu(MATT TEXT PRIMARY KEY ," +
                "HOTEN TEXT," +
                "MATKHAU TEXT)";
            db.execSQL(tableone);
            String tablletwo = "CREATE TABLE thanhvien(MATV INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "HOTEN TEXT," +
                    "NAMSINH TEXT)";
            db.execSQL(tablletwo);
            String tablethree = "CREATE TABLE loaisach(MALOAI INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "TENLOAISACH TEXT)";
            db.execSQL(tablethree);
            String tablefour = "CREATE TABLE sach(MASACH INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "TENSACH TEXT," +
                    "GIATHUE INT," +
                    "MALOAI INT)";
            db.execSQL(tablefour);
            String tablefive = "CREATE TABLE phieumuon(MASPM INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "MATT TEXT," +
                    "MATV INT," +
                    "MASACH INT," +
                    "TIENTHUE INT ," +
                    "NGAY INT," +
                    "TRASACH INT NOT NULL)";
            db.execSQL(tablefive);

        db.execSQL("INSERT INTO thanhvien VALUES (1,'Cao Thu Trang','2000'),(2,'Trần Mỹ Kim','2000')");
        String logthuthu = "INSERT INTO thuthu VALUES('thuthu1','Viet','123')," + "('thuthu2','Viet2','123'),"+"('admin','Vietanh','123')";
        db.execSQL(logthuthu);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion!=newVersion){
            db.execSQL("DROP TABLE IF EXISTS thanhvien");
            db.execSQL("DROP TABLE IF EXISTS loaisach");
            db.execSQL("DROP TABLE IF EXISTS sach");
            db.execSQL("DROP TABLE IF EXISTS thuthu");
            db.execSQL("DROP TABLE IF EXISTS phieumuon");

            onCreate(db);
        }
    }

    public Cursor GetData(String s) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(s, null);
    }
}
