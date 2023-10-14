package anhpvph37030.fpoly.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import anhpvph37030.fpoly.duanmau.Util.DpHelper;

public class AdminDao {
    private DpHelper dbHelper;
    private SQLiteDatabase database;
    private Context context;
    private SharedPreferences sharedPreferences;

    public AdminDao(Context context) {
        this.context = context;
        dbHelper = new DpHelper(context);
        sharedPreferences = context.getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
    }

    public boolean checkUser(String username, String password) {
        database = dbHelper.getReadableDatabase();
        String[] columns = {"ID"};
        String selection = "TENDANGNHAP" + "=? and " + "MATKHAU" + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = database.query("admin", columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        database.close();
        return count > 0;
    }

    public boolean checkPasswordAndChange(String oldPassword, String newPassword) {
        String username = sharedPreferences.getString("loggedInUser", "");

        if (checkUser(username, oldPassword)) {
            // Mật khẩu cũ đúng
            database = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("MATKHAU", newPassword);


            database.update("admin", values, "TENDANGNHAP = ?", new String[]{username});
            database.close(); //
            return true; //
        } else {

            return false;
        }
    }

}
