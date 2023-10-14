package anhpvph37030.fpoly.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import anhpvph37030.fpoly.duanmau.Acti.Qlphieumuon;
import anhpvph37030.fpoly.duanmau.DAO.AdminDao;
import anhpvph37030.fpoly.duanmau.DAO.NguoiDungDao;

public class Login extends AppCompatActivity {
    EditText txtUser, txtPass;
    Button btnDangnhap, btnHuy;
    AdminDao adminDao;
    NguoiDungDao nguoiDungDao;
    CheckBox chkRemember;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        anhxa();

        SharedPreferences sharedPreferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        String savedUsername = sharedPreferences.getString("loggedInUser", "");
        String savedPassword = sharedPreferences.getString("loggedInPass", "");

        if (!savedUsername.isEmpty() && !savedPassword.isEmpty()) {
            txtUser.setText(savedUsername);
            txtPass.setText(savedPassword);

            // Cho checkbox Nhớ mật khẩu có dấu tích v
            chkRemember.setChecked(true);
        }

        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = txtUser.getText().toString();
                String pass = txtPass.getText().toString();
                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(Login.this, "Không được để trống thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (adminDao.checkUser(user, pass)) {
                    // Lưu tên đăng nhập vào SharedPreferences khi đăng nhập thành công
                    if (chkRemember.isChecked()){
                        SharedPreferences sharedPreferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("loggedInUser", user); // Lưu tên đăng nhập
                        editor.putString("loggedInPass", pass); // Lưu tên đăng nhập
                        editor.apply();
                    }
                    else{
                        SharedPreferences sharedPreferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("loggedInUser");
                        editor.remove("loggedInPass");
                        editor.apply();
                    }
                    Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, Qlphieumuon.class);
                    startActivity(intent);
                    finish();
                } else if (nguoiDungDao.checkTT(user,pass)) {
                    if (chkRemember.isChecked()){
                        SharedPreferences sharedPreferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("loggedInUser", user);
                        editor.putString("loggedInPass", pass);
                        editor.apply();
                    }
                    else{
                        SharedPreferences sharedPreferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("loggedInUser");
                        editor.remove("loggedInPass");
                        editor.apply();
                    }
                    Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, Qlphieumuon.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(Login.this, "Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }





            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Đóng màn hình hiện tại
                finish();

                // Đóng ứng dụng
                System.exit(0);
            }
        });

    }

    private void anhxa() {
        txtUser = findViewById(R.id.txtUser);
        txtPass = findViewById(R.id.txtPass);
        btnDangnhap = findViewById(R.id.btnDangnhap);
        btnHuy = findViewById(R.id.btnHuy);
        chkRemember = findViewById(R.id.chkRemember);
        adminDao = new AdminDao(this);
        nguoiDungDao = new NguoiDungDao(this);
    }

}