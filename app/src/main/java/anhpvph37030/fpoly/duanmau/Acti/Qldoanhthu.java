package anhpvph37030.fpoly.duanmau.Acti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Calendar;

import anhpvph37030.fpoly.duanmau.DAO.AdminDao;
import anhpvph37030.fpoly.duanmau.DAO.PhieuMuonDao;
import anhpvph37030.fpoly.duanmau.Login;
import anhpvph37030.fpoly.duanmau.R;

public class Qldoanhthu extends AppCompatActivity {
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    EditText edtNgayBatDau, edtNgayKetThuc;
    Button btnTinhDoanhThu, btnTuNgay, btnDenNgay;
    TextView txtTongDoanhThu;
    PhieuMuonDao phieumuonDAO;
    String ngayBatDau, ngayKetThuc;
    AdminDao adminDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qldoanhthu);
        anhxa();
        setUpToolbar();

        btnTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonNgayBatDau();
            }
        });

        edtNgayBatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonNgayBatDau();
            }
        });

        btnDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonNgayKetThuc();
            }
        });
        edtNgayKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonNgayKetThuc();
            }
        });

        btnTinhDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tongDoanhThu = phieumuonDAO.getTongDoanhThu(ngayBatDau, ngayKetThuc);
                txtTongDoanhThu.setText(String.valueOf(tongDoanhThu));
            }
        });
    }

    private void chonNgayKetThuc() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                ngayKetThuc = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay; // Tháng bắt đầu từ 0
                edtNgayKetThuc.setText(ngayKetThuc); // Hiển thị ngày đã chọn lên TextView
            }
        }, year, month, day);

        datePickerDialog.show();
    }

    private void chonNgayBatDau() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                ngayBatDau = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay; // Tháng trong and từ 0-11
                edtNgayBatDau.setText(ngayBatDau); // Hiển thị ngày đã chọn
            }
        }, year, month, day);

        datePickerDialog.show();
    }

    private void anhxa() {
        toolbar = findViewById(R.id.my_toolbar);
        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerLayout);
        edtNgayBatDau = findViewById(R.id.edtNgayBatDau);
        edtNgayKetThuc = findViewById(R.id.edtNgayKetThuc);
        btnTinhDoanhThu = findViewById(R.id.btnTinhDoanhThu);
        txtTongDoanhThu = findViewById(R.id.txtTongDoanhThu);
        btnDenNgay = findViewById(R.id.btnDenNgay);
        btnTuNgay = findViewById(R.id.btnTuNgay);
        phieumuonDAO = new PhieuMuonDao(this);
        adminDao = new AdminDao(this);
    }

    private void setUpToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.nav_icon);
        getSupportActionBar().setTitle("Doanh thu");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.qlpm) {
                    Intent intent = new Intent(Qldoanhthu.this, Qlphieumuon.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.qlls) {
                    Intent intent = new Intent(Qldoanhthu.this, Qlloaisach.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.qls) {
                    Intent intent = new Intent(Qldoanhthu.this, QlSach.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.qltv) {
                    Intent intent = new Intent(Qldoanhthu.this, QlThanhVien.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.topten) {
                    Intent intent = new Intent(Qldoanhthu.this, Qlyop10.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.doanhthu) {
                    drawerLayout.close();
                } else if (item.getItemId() == R.id.themThanhVien) {
                    SharedPreferences sharedPreferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
                    String loggedInUser = sharedPreferences.getString("loggedInUser", "");
                    String loggedInPass = sharedPreferences.getString("loggedInPass", "");

                    if (adminDao.checkUser(loggedInUser,loggedInPass)) {
                        // Người dùng có quyền admin
                        // Cho phép họ truy cập chức năng thêm thành viên
                        Intent intent = new Intent(Qldoanhthu.this, Themthanhvien.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Qldoanhthu.this, "Bạn không có quyền truy cập chức năng này.", Toast.LENGTH_SHORT).show();
                    }
                } else if (item.getItemId() == R.id.doiMatKhau) {
                    Intent intent = new Intent(Qldoanhthu.this, DoiMatKhau.class);
                    startActivity(intent);
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
                Intent intent = new Intent(Qldoanhthu.this, Login.class);

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}