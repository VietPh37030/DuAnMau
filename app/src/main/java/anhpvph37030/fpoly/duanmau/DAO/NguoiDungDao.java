package anhpvph37030.fpoly.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import anhpvph37030.fpoly.duanmau.Model.ThuThu;
import anhpvph37030.fpoly.duanmau.Util.DpHelper;

public class NguoiDungDao {
    private DpHelper dbHelper;
    SQLiteDatabase database;
    private SharedPreferences sharedPreferences;
    public NguoiDungDao(Context context) {
        dbHelper = new DpHelper(context);
 sharedPreferences = context.getSharedPreferences("myShare",Context.MODE_PRIVATE);
    }

    public long addTT(ThuThu thuThu) {
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENDANGNHAP", thuThu.getTenTT());
        values.put("HOTEN", thuThu.getHoTen());
        values.put("MATKHAU", thuThu.getMatKhau());
        return database.insert("thuthu", null, values);
    }

    public boolean checkTT(String username, String password) {
        database = dbHelper.getReadableDatabase();
        String[] columns = {"ID"};
        String selection = "TENDANGNHAP" + "=? and " + "MATKHAU" + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = database.query("thuthu", columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        database.close();
        return count > 0;
    }
    public boolean doiMKTT(String oldPassword, String newPassword) {
        String username = sharedPreferences.getString("loggedInUser", "");

        if (checkTT(username, oldPassword)) {
            // Mật khẩu cũ đúng
            database = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("MATKHAU", newPassword);


            database.update("thuthu", values, "TENDANGNHAP = ?", new String[]{username});
            database.close();
            return true;
        } else {

            return false;
        }
    }
}
