package anhpvph37030.fpoly.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import anhpvph37030.fpoly.duanmau.Dao.UserDao;

public class Login extends AppCompatActivity {
        TextInputLayout edtuser, edtpass;
         CheckBox chksave;
         Button btndangnhap;
         UserDao userDao;
         SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtuser = findViewById(R.id.edtuser);
        edtpass = findViewById(R.id.edtpass);
        chksave = findViewById(R.id.chksave);
        btndangnhap = findViewById(R.id.btndangnhap);
        UserDao userDao = new UserDao(this);


        sharedPreferences = getSharedPreferences("THONGTIN", MODE_PRIVATE);

        String savedUsername = sharedPreferences.getString("Mã nv", "");
        String savedPassword = sharedPreferences.getString("Mật khẩu", "");
        boolean savePasswordChecked = sharedPreferences.getBoolean("Lưu mật khẩu", false);

        edtuser.getEditText().setText(savedUsername);
        edtpass.getEditText().setText(savedPassword);
        chksave.setChecked(savePasswordChecked);


        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String user =  edtuser.getEditText().getText().toString();
            String pass = edtpass.getEditText().getText().toString();
            boolean check =userDao.Login_check(user,pass);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Mã nv", check ? user : "");
                editor.putString("Mật khẩu", check && chksave.isChecked() ? pass : "");
                editor.putBoolean("Lưu mật khẩu", check && chksave.isChecked());
                editor.apply();


                if (check){
                Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Login.this,MainActivity.class));

            }else {
                Toast.makeText(Login.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
            }
            }
        });

    }
}