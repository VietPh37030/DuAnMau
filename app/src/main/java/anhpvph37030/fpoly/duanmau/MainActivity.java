package anhpvph37030.fpoly.duanmau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;

import anhpvph37030.fpoly.duanmau.Fragment.FG_ADDMEM;
import anhpvph37030.fpoly.duanmau.Fragment.FG_DOANHTHU;
import anhpvph37030.fpoly.duanmau.Fragment.FG_DOIMK;
import anhpvph37030.fpoly.duanmau.Fragment.FG_PhieuMuon;
import anhpvph37030.fpoly.duanmau.Fragment.FG_QLLOAISACH;
import anhpvph37030.fpoly.duanmau.Fragment.FG_QLSACH;
import anhpvph37030.fpoly.duanmau.Fragment.FG_QLTHANHVIEN;
import anhpvph37030.fpoly.duanmau.Fragment.FG_TOP10;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.navigationDrawer1);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigation_view);
//`navigationView.setItemIconTintList(null)` được sử dụng để bỏ qua việc tô màu cho icon của các MenuItem trong NavigationView.

// su li toolbar
        setSupportActionBar(toolbar);// gan toolbar
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        toggle.syncState();//`toggle.syncState()` được sử dụng để đồng bộ trạng thái của toggle button với trạng thái của Navigation Drawer.
        navigationView.setItemIconTintList(null);
        getSupportActionBar().setTitle("Quản Lý Phiếu Mượn");



        //// su li navigation
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
           if (item.getItemId() ==R.id.nav_PhieuMuon){
               FG_PhieuMuon fgPhieuMuon = new FG_PhieuMuon();
               replaceFrg(fgPhieuMuon);
           } else if (item.getItemId() == R.id.nav_LoaiSach) {
               FG_QLLOAISACH fg_qlloaisach = new FG_QLLOAISACH();
               replaceFrg(fg_qlloaisach);
           } else if (item.getItemId()==R.id.nav_Sach) {
               FG_QLSACH fg_qlsach = new FG_QLSACH();
               replaceFrg(fg_qlsach);
           } else if (item.getItemId()==R.id.nav_TopMuon) {
               FG_TOP10 fgTop10 = new FG_TOP10();
               replaceFrg(fgTop10);
           }else if(item.getItemId()==R.id.nav_DoanhThu){
               FG_DOANHTHU fg_doanhthu = new FG_DOANHTHU();
               replaceFrg(fg_doanhthu);
           } else if (item.getItemId()==R.id.nav_ThemThanhVien) {
               FG_ADDMEM fg_adm = new FG_ADDMEM();
               replaceFrg(fg_adm);
           }else if (item.getItemId()==R.id.nav_DoiMatKhau){
               FG_DOIMK fg_doimk = new FG_DOIMK();
               replaceFrg(fg_doimk);
           }else if(item.getItemId()==R.id.nav_ThanhVien){
               FG_QLTHANHVIEN fg_qlthanhvien = new FG_QLTHANHVIEN();
               replaceFrg(fg_qlthanhvien);
           }else {
               AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
               builder.setTitle("Cảnh báo");
               builder.setIcon(R.drawable.out);
               builder.setMessage("Bạn có muốn đăng xuất không?");
               builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       Toast.makeText(MainActivity.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                       Intent intent = new Intent(MainActivity.this, Login.class);
                       startActivity(intent);
                   }
               });
               builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {

                   }
               });
               builder.show();
           }
                getSupportActionBar().setTitle(item.getTitle()); //khi click vào item hiển thị tieu de lên toolbar
                drawerLayout.close(); //đóng nav
                return true;

            }
        });
    }
    private void replaceFrg(Fragment frg) {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frag_content, frg).commit();
    }


}