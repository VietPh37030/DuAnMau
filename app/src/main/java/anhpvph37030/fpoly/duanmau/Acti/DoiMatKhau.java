package anhpvph37030.fpoly.duanmau.Acti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import anhpvph37030.fpoly.duanmau.DAO.AdminDao;
import anhpvph37030.fpoly.duanmau.DAO.NguoiDungDao;
import anhpvph37030.fpoly.duanmau.Login;
import anhpvph37030.fpoly.duanmau.R;

public class DoiMatKhau extends AppCompatActivity {
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private EditText edtMkOld,edtMkNew,edtNhapLai;
    private Button btnLuu;
    private NguoiDungDao   nguoiDungDao;
    private AdminDao admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);
        anhxa();
        setUpToolbar();
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDoiMK();
            }
        });
    }
    private void dialogDoiMK() {
        String mkc = edtMkOld.getText().toString();
        String mkm = edtMkNew.getText().toString();
        String nhaplai = edtNhapLai.getText().toString();
        if (mkc.isEmpty() || mkm.isEmpty() || nhaplai.isEmpty()) {
            Toast.makeText(DoiMatKhau.this, "Không được để trống", Toast.LENGTH_SHORT).show();
        } else {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(DoiMatKhau.this);
            builder.setIcon(R.drawable.baseline_warning_24);
            builder.setCancelable(false);
            builder.setTitle("Đổi mật khẩu");
            builder.setMessage("Bạn có chắc chắn muốn đổi mật khẩu không ?");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (mkm.equals(nhaplai)) {
                        if (admin.checkPasswordAndChange(mkc, mkm)) {
                            Toast.makeText(DoiMatKhau.this, "Đổi thành công", Toast.LENGTH_SHORT).show();
                            edtMkNew.setText("");
                            edtMkOld.setText("");
                            edtNhapLai.setText("");
                        } else if (nguoiDungDao.doiMKTT(mkc, mkm)) {
                            Toast.makeText(DoiMatKhau.this, "Đổi thành công", Toast.LENGTH_SHORT).show();
                            edtMkNew.setText("");
                            edtMkOld.setText("");
                            edtNhapLai.setText("");
                        } else {
                            Toast.makeText(DoiMatKhau.this, "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(DoiMatKhau.this, "Nhập lại mật khẩu sai", Toast.LENGTH_SHORT).show();
                    }

                }
            });
            builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // bắt sự kiện nhấn nút No
                    dialog.dismiss();
                }
            });
            builder.show();
        }

    }

    private void anhxa() {
        toolbar = findViewById(R.id.my_toolbar);
        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerLayout);
        edtMkOld = findViewById(R.id.edtMkOld);
        edtMkNew = findViewById(R.id.edtMkNew);
        edtNhapLai = findViewById(R.id.edtNhapLai);
        btnLuu = findViewById(R.id.btnLuu);
        admin = new AdminDao(this);
        nguoiDungDao = new NguoiDungDao(this);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
    private void setUpToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.nav_icon);
        getSupportActionBar().setTitle("Đổi mật khẩu");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.qlpm) {
                    Intent intent = new Intent(DoiMatKhau.this, Qlphieumuon.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.qlls) {
                    Intent intent = new Intent(DoiMatKhau.this, Qlloaisach.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.qls) {
                    Intent intent = new Intent(DoiMatKhau.this, QlSach.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.qltv) {
                    Intent intent = new Intent(DoiMatKhau.this, QlThanhVien.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.topten) {
                    Intent intent = new Intent(DoiMatKhau.this, Qlyop10.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.doanhthu) {
                    Intent intent = new Intent(DoiMatKhau.this, Qldoanhthu.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.themThanhVien) {

                } else if (item.getItemId() == R.id.doiMatKhau) {
                    drawerLayout.close();
                } else if (item.getItemId() == R.id.dangxuat) {
                    dialog_dangxuat();
                }
                return false;
            }
        });
    }
    public void dialog_dangxuat() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.out);
        builder.setCancelable(false);
        builder.setTitle("Đăng xuất");
        builder.setMessage("Bạn có muốn đăng xuất không?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Bắt sự kiện nhấn nút Có
                SharedPreferences sharedPreferences = getSharedPreferences("thongtin", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isLoggedIn", false);
                editor.apply();
                Intent intent = new Intent(DoiMatKhau.this, Login.class);

                // Đặt cờ FLAG_ACTIVITY_NEW_TASK để tạo một nhiệm vụ mới
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Bắt sự kiện nhấn nút Không
            }
        });
        builder.show();
    }
}