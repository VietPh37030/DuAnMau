package anhpvph37030.fpoly.duanmau.Acti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.time.LocalDate;
import java.util.ArrayList;

import anhpvph37030.fpoly.duanmau.Adapter.PhieuMuonAdapter;
import anhpvph37030.fpoly.duanmau.DAO.AdminDao;
import anhpvph37030.fpoly.duanmau.DAO.PhieuMuonDao;
import anhpvph37030.fpoly.duanmau.DAO.SachDao;
import anhpvph37030.fpoly.duanmau.DAO.ThanhVienDao;
import anhpvph37030.fpoly.duanmau.Login;
import anhpvph37030.fpoly.duanmau.Model.PhieuMuon;
import anhpvph37030.fpoly.duanmau.Model.Sach;
import anhpvph37030.fpoly.duanmau.Model.ThanhVien;
import anhpvph37030.fpoly.duanmau.R;

public class Qlphieumuon extends AppCompatActivity {
    private Toolbar toolbar;
    private NavigationView navigationView;
    RecyclerView recyclerView;
    ImageButton btnThem;
    DrawerLayout drawerLayout;
    PhieuMuonDao phieuMuonDAO;
    ArrayList<PhieuMuon> list;
    PhieuMuonAdapter phieuMuonAdapter;
    AdminDao adminDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlphieumuon);
        recyclerView = findViewById(R.id.recycleView);
        btnThem = findViewById(R.id.btnThem);
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.my_toolbar);
        navigationView = findViewById(R.id.navigationView);
        setUpToolbar();

        phieuMuonDAO = new PhieuMuonDao(this);
        adminDao = new AdminDao(this);
        list = phieuMuonDAO.getAllPhieuMuon();
        phieuMuonAdapter = new PhieuMuonAdapter(this, list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(phieuMuonAdapter);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_them();
            }
        });

    }
    private void setUpToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.nav_icon);
        getSupportActionBar().setTitle("Quản lý phiếu mượn");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.qlpm) {
                    drawerLayout.close();
                } else if (item.getItemId() == R.id.qlls) {
                    Intent intent = new Intent(Qlphieumuon.this, Qlloaisach.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.qls) {
                    Intent intent = new Intent(Qlphieumuon.this, QlSach.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.qltv) {
                    Intent intent = new Intent(Qlphieumuon.this, QlThanhVien.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.topten) {
                    Intent intent = new Intent(Qlphieumuon.this, Qlyop10.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.doanhthu) {
                    Intent intent = new Intent(Qlphieumuon.this, Qldoanhthu.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.themThanhVien) {
                    SharedPreferences sharedPreferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
                    String loggedInUser = sharedPreferences.getString("loggedInUser", "");
                    String loggedInPass = sharedPreferences.getString("loggedInPass", "");

                    if (adminDao.checkUser(loggedInUser,loggedInPass)) {
                        Intent intent = new Intent(Qlphieumuon.this, Themthanhvien.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Qlphieumuon.this, "Bạn không có quyền truy cập chức năng này.", Toast.LENGTH_SHORT).show();
                    }
                } else if (item.getItemId() == R.id.doiMatKhau) {
                    Intent intent = new Intent(Qlphieumuon.this, DoiMatKhau.class);
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
                Intent intent = new Intent(Qlphieumuon.this, Login.class);

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
    public void dialog_them() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Qlphieumuon.this);
        View view = getLayoutInflater().inflate(R.layout.add_pm, null);
        builder.setView(view);
        builder.setCancelable(false);
        Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Spinner edtTenSach,edtTenTV;
        CheckBox chkTrangThai;
        Button btnAdd, btnHuy;

        edtTenTV = view.findViewById(R.id.edtTenTV);
        edtTenSach = view.findViewById(R.id.edtTenSach);
        chkTrangThai = view.findViewById(R.id.chkTrangThai);
        btnAdd = view.findViewById(R.id.btnAdd);
        btnHuy = view.findViewById(R.id.btnHuy);

        ArrayList<String> tenTVList = getTenTV();
        ArrayAdapter<String> spinnerTV = new ArrayAdapter<>(
                Qlphieumuon.this,
                android.R.layout.simple_spinner_item,
                tenTVList
        );

        spinnerTV.
                setDropDownViewResource(android.R.layout.
                        simple_spinner_dropdown_item);

        edtTenTV.setAdapter(spinnerTV);

        ArrayList<String> tenSachList = getTenSachList();
        ArrayAdapter<String> spinnerSach = new ArrayAdapter<>(
                Qlphieumuon.this,
                android.R.layout.simple_spinner_item,
                tenSachList
        );

         spinnerSach.
                setDropDownViewResource(android.R.layout.
                        simple_spinner_dropdown_item);
        edtTenSach.setAdapter(spinnerSach);
        ArrayList<Integer> giaTienThueList =
                getGiaTienThueList();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenTV = edtTenTV.getSelectedItem().toString();
                String chasten = edtTenSach.getSelectedItem().toString();
                int giaTienThue =
                        giaTienThueList.get(edtTenSach.getSelectedItemPosition());

                if (tenTV.isEmpty() || chasten.isEmpty()) {
                    Toast.makeText(Qlphieumuon.this, "Vu Lòng điền đủ", Toast.LENGTH_SHORT).show();
                    return;
                }

                String ngay = null; // lấy ngày hiện tại
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    ngay = String.valueOf(LocalDate.now());
                }

                PhieuMuon pm = new PhieuMuon();
                pm.setTenTV(tenTV);
                pm.setTenSach(chasten);
                pm.setTienThue(giaTienThue);
                pm.setNgayThue(ngay);
                if (chkTrangThai.isChecked()) {
                    pm.setTrangThaiMuon(1);
                } else {
                    pm.setTrangThaiMuon(0);
                }
                if (phieuMuonDAO.addPM(pm) > 0) {
                    Toast.makeText(Qlphieumuon.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    list.clear();//xóa tất cả các phần tử trong danh sách list
                    list.addAll(phieuMuonDAO.getAllPhieuMuon());
                    phieuMuonAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                } else {
                    Toast.makeText(Qlphieumuon.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }


            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
    private ArrayList<String> getTenTV() { // lấy danh sách các thành viên
        ThanhVienDao thanhVienDAO = new ThanhVienDao(getApplicationContext());
        ArrayList<ThanhVien> list1 = thanhVienDAO.getThanhVien();
        ArrayList<String> tenTVList = new ArrayList<>();

        for (ThanhVien thanhVien : list1) {
            tenTVList.add(thanhVien.getTenTV());
        }
        return tenTVList;
    }
    private ArrayList<String> getTenSachList() { // lấy danh sách các cuốn sách
        SachDao sachDAO = new SachDao(getApplicationContext());
        ArrayList<Sach> list = sachDAO.getSach();
        ArrayList<String> tenSachList = new ArrayList<>();

        for (Sach sach : list) {
            tenSachList.add(sach.getTenSach());
        }
        return tenSachList;
    }
    private ArrayList<Integer> getGiaTienThueList() { // lấy danh sách tiền thuê của tất cả sách
        SachDao sachDAO = new SachDao(getApplicationContext());
        ArrayList<Sach> list1 = sachDAO.getSach();
        ArrayList<Integer> giaTienThueList = new ArrayList<>();

        for (Sach sach : list1) {
            giaTienThueList.add(sach.getTienThue());
        }
        return giaTienThueList;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

}