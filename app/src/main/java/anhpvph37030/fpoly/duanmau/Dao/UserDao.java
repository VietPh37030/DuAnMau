package anhpvph37030.fpoly.duanmau.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import anhpvph37030.fpoly.duanmau.Util.DpHelper;

public class UserDao {
private DpHelper dpHelper;
public UserDao(Context context){
    dpHelper = new DpHelper(context);

}
public boolean Login_check(String username, String password){
    SQLiteDatabase database =dpHelper.getReadableDatabase();
    Cursor cursor = database.rawQuery("SELECT * FROM thuthu WHERE MATT = ? AND MATKHAU = ?", new  String[]{username,password});
    if (cursor.getCount()>0){
        return true;
    }
    return false;
}
}
