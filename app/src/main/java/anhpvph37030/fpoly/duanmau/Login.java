package anhpvph37030.fpoly.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import anhpvph37030.fpoly.duanmau.Acti.Qlphieumuon;
import anhpvph37030.fpoly.duanmau.DAO.NguoiDungDao;

public class Login extends AppCompatActivity {
    EditText txtUser, txtPass;
    Button btnDangnhap, btnHuy;
    NguoiDungDao nguoiDungDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUser = findViewById(R.id.txtUser);
        txtPass = findViewById(R.id.txtPass);
        btnDangnhap = findViewById(R.id.btnDangnhap);
        btnHuy = findViewById(R.id.btnHuy);
        nguoiDungDao = new NguoiDungDao(Login.this);

        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = txtUser.getText().toString();
                String pass = txtPass.getText().toString();
                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(Login.this, "Không được để trống thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (nguoiDungDao.checkUser(user,pass)){
                    Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, Qlphieumuon.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(Login.this, "Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}