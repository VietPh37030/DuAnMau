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
import anhpvph37030.fpoly.duanmau.DAO.NguoiDungDao;
import anhpvph37030.fpoly.duanmau.Login;
import anhpvph37030.fpoly.duanmau.Model.ThuThu;
import anhpvph37030.fpoly.duanmau.R;

public class Themthanhvien extends AppCompatActivity {
    EditText edtUser, edtHoten, edtPass, edtNhapLai;
    Button btnLuu;
    private NguoiDungDao dao;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themthanhvien);
        anhxa(); // Initialize UI elements
        setUpToolbar(); // Set up the app's toolbar
        // Add a click listener to the "Luu" (Save) button
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve user input
                String user = edtUser.getText().toString();
                String hoten = edtHoten.getText().toString();
                String pass = edtPass.getText().toString();
                String nhaplai = edtNhapLai.getText().toString();

                // Validate user input
                if (user.isEmpty() || hoten.isEmpty() || pass.isEmpty() || nhaplai.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!pass.equals(nhaplai)) {
                    Toast.makeText(getApplicationContext(), "Nhập lại mật khẩu sai", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Create a librarian object and add it to the database
                ThuThu thuThu = new ThuThu(user, hoten, pass);
                if (dao.addTT(thuThu) > 0) {
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    // Clear the input fields
                    edtHoten.setText("");
                    edtUser.setText("");
                    edtPass.setText("");
                    edtNhapLai.setText("");
                } else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    private void anhxa() {
        // Initialize UI elements by finding their views in the layout
        edtUser = findViewById(R.id.edtUser);
        edtHoten = findViewById(R.id.edtHoten);
        edtPass = findViewById(R.id.edtPass);
        edtNhapLai = findViewById(R.id.edtNhapLai);
        btnLuu = findViewById(R.id.btnLuu);
        dao = new NguoiDungDao(this);
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.my_toolbar);
        navigationView = findViewById(R.id.navigationView);
    }

    private void setUpToolbar() {
        // Set up the app's toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.nav_icon);
        getSupportActionBar().setTitle("Thêm Thành Viên");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle navigation menu item clicks
                if (item.getItemId() == R.id.qlpm) {
                    Intent intent = new Intent(Themthanhvien.this, Qlphieumuon.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.qlls) {
                    Intent intent = new Intent(Themthanhvien.this, Qlloaisach.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.qls) {
                    Intent intent = new Intent(Themthanhvien.this, QlSach.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.qltv) {
                    Intent intent = new Intent(Themthanhvien.this, QlThanhVien.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.topten) {
                    Intent intent = new Intent(Themthanhvien.this, Qlyop10.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.doanhthu) {
                    Intent intent = new Intent(Themthanhvien.this, Qldoanhthu.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.themThanhVien) {
                    drawerLayout.close();
                } else if (item.getItemId() == R.id.doiMatKhau) {
                    Intent intent = new Intent(Themthanhvien.this, DoiMatKhau.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.dangxuat) {
                    dialog_dangxuat(); // Show a logout confirmation dialog
                }
                return false;
            }
        });
    }

    public void dialog_dangxuat() {
        // Show a dialog to confirm logout
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.out);
        builder.setCancelable(false);
        builder.setTitle("Đăng xuất");
        builder.setMessage("Bạn có muốn đăng xuất không?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle the "Có" (Yes) button click
                SharedPreferences sharedPreferences = getSharedPreferences("thongtin", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isLoggedIn", false);
                editor.apply();
                Intent intent = new Intent(Themthanhvien.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle the "Không" (No) button click
            }
        });
        builder.show();
    }
}
