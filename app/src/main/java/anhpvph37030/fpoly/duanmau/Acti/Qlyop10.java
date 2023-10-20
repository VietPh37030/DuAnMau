package anhpvph37030.fpoly.duanmau.Acti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import anhpvph37030.fpoly.duanmau.Adapter.Top10Adapter;
import anhpvph37030.fpoly.duanmau.DAO.AdminDao;
import anhpvph37030.fpoly.duanmau.DAO.PhieuMuonDao;
import anhpvph37030.fpoly.duanmau.Login;
import anhpvph37030.fpoly.duanmau.Model.Top10;
import anhpvph37030.fpoly.duanmau.R;

public class Qlyop10 extends AppCompatActivity {
    RecyclerView recyclerView;
    PhieuMuonDao phieumuonDAO;
    ArrayList<Top10> list = new ArrayList<>();
    Top10Adapter topAdapter;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    AdminDao adminDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlyop10);
        anhxa(); // Initialize UI elements
        setUpToolbar(); // Set up the app's toolbar

        phieumuonDAO = new PhieuMuonDao(this);
        list = (ArrayList<Top10>) phieumuonDAO.getTop10();
        topAdapter = new Top10Adapter(Qlyop10.this, list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(topAdapter);
    }

    private void anhxa() {
        // Initialize UI elements by finding their views in the layout
        recyclerView = findViewById(R.id.recycleView);
        toolbar = findViewById(R.id.my_toolbar);
        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerLayout);
        adminDao = new AdminDao(this);
    }

    private void setUpToolbar() {
        // Set up the app's toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.nav_icon);
        getSupportActionBar().setTitle("Top 10 sách mượn nhiều nhất");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle navigation menu item clicks
                if (item.getItemId() == R.id.qlpm) {
                    Intent intent = new Intent(Qlyop10.this, Qlphieumuon.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.qlls) {
                    Intent intent = new Intent(Qlyop10.this, Qlloaisach.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.qls) {
                    Intent intent = new Intent(Qlyop10.this, QlSach.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.qltv) {
                    Intent intent = new Intent(Qlyop10.this, QlThanhVien.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.topten) {
                    drawerLayout.close();
                } else if (item.getItemId() == R.id.doanhthu) {
                    Intent intent = new Intent(Qlyop10.this, Qldoanhthu.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.themThanhVien) {
                    SharedPreferences sharedPreferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
                    String loggedInUser = sharedPreferences.getString("loggedInUser", "");
                    String loggedInPass = sharedPreferences.getString("loggedInPass", "");

                    if (adminDao.checkUser(loggedInUser, loggedInPass)) {
                        // User has admin privileges
                        // Allow them to access the add member feature
                        Intent intent = new Intent(Qlyop10.this, Themthanhvien.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Qlyop10.this, "Bạn không có quyền truy cập chức năng này.", Toast.LENGTH_SHORT).show();
                    }
                } else if (item.getItemId() == R.id.doiMatKhau) {
                    Intent intent = new Intent(Qlyop10.this, DoiMatKhau.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.dangxuat) {
                    dialog_dangxuat(); // Show a logout confirmation dialog
                }
                return false;
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
                Intent intent = new Intent(Qlyop10.this, Login.class);
                // Set the FLAG_ACTIVITY_NEW_TASK flag to create a new task
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
