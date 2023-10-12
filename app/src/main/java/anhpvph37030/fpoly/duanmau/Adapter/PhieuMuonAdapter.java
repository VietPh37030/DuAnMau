package anhpvph37030.fpoly.duanmau.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import anhpvph37030.fpoly.duanmau.DAO.PhieuMuonDao;
import anhpvph37030.fpoly.duanmau.DAO.SachDao;
import anhpvph37030.fpoly.duanmau.DAO.ThanhVienDao;
import anhpvph37030.fpoly.duanmau.Model.PhieuMuon;
import anhpvph37030.fpoly.duanmau.Model.Sach;
import anhpvph37030.fpoly.duanmau.Model.ThanhVien;
import anhpvph37030.fpoly.duanmau.R;

public class PhieuMuonAdapter  extends RecyclerView.Adapter<PhieuMuonAdapter.ViewHolder>{
    private Context context;
    ArrayList<PhieuMuon> list;
    PhieuMuonDao phieuMuonDAO;

    public PhieuMuonAdapter(Context context, ArrayList<PhieuMuon> list) {
        this.context = context;
        this.list = list;
        phieuMuonDAO = new PhieuMuonDao(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.rcy_phieumuon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PhieuMuon phieuMuon = list.get(position);
        holder.txtTenTV.setText(phieuMuon.getTenTV());
        holder.txtNgay.setText(phieuMuon.getNgayThue());
        holder.txtTenSach.setText(phieuMuon.getTenSach());
        holder.txtTienThue.setText(String.valueOf(phieuMuon.getTienThue()));
        if (phieuMuon.getTrangThaiMuon()==1){
            holder.txtTrangThai.setText("Đã trả ");
            holder.txtTrangThai.setTextColor(Color.parseColor("#1D0FC6"));
        }else{
            holder.txtTrangThai.setText("Chưa trả ");
            holder.txtTrangThai.setTextColor(Color.parseColor("#ED0C0C"));
        }

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(R.drawable.baseline_warning_24);
                builder.setCancelable(false);
                builder.setTitle("Xóa phiếu mượn");
                builder.setMessage("Bạn có muốn xóa không ?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // bắt sự kiện nhấn nút Yes
                        if (phieuMuonDAO.deletePM(phieuMuon.getId()) > 0) {
                            list.clear();
                            list.addAll(phieuMuonDAO.getAllPhieuMuon());
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        } else {
                            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // bắt sự kiện nhấn nút No
                    }
                });
                builder.show();
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                updateDialog(phieuMuon);
                return true;
            }
        });
    }
    public void updateDialog(PhieuMuon phieuMuon) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.update_pm, null);
        builder.setView(view);
        builder.setCancelable(false);
        Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Spinner edtTenSach,edtTenTV;
        TextView txtNgayThue, txtTienThue;
        CheckBox chkTrangThai;
        Button btnSua, btnHuy;

        edtTenTV = view.findViewById(R.id.edtTenTV);
        edtTenSach = view.findViewById(R.id.edtTenSach);
        txtNgayThue = view.findViewById(R.id.txtNgayThue);
        txtTienThue = view.findViewById(R.id.txtTienThue);
        chkTrangThai = view.findViewById(R.id.chkTrangThai);
        btnSua = view.findViewById(R.id.btnSua);
        btnHuy = view.findViewById(R.id.btnHuy);

        edtTenTV.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, getTenThanhVienList()));
        edtTenTV.setSelection(getTenThanhVienList().indexOf(phieuMuon.getTenTV()));

        edtTenSach.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, getTenSachList()));
        edtTenSach.setSelection(getTenSachList().indexOf(phieuMuon.getTenSach()));

        txtNgayThue.setText(phieuMuon.getNgayThue());
        txtTienThue.setText(String.valueOf(phieuMuon.getTienThue()));
        if (phieuMuon.getTrangThaiMuon() == 1) {
            chkTrangThai.setChecked(true);
        } else {
            chkTrangThai.setChecked(false);
        }
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenTV = edtTenTV.getSelectedItem().toString();
                String tenSach = edtTenSach.getSelectedItem().toString();

                if (chkTrangThai.isChecked()){
                    phieuMuon.setTrangThaiMuon(1);
                }else{
                    phieuMuon.setTrangThaiMuon(0);
                }

                if(tenTV.isEmpty()||tenSach.isEmpty()){
                    Toast.makeText(context, "Không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                phieuMuon.setTenTV(tenTV);
                phieuMuon.setTenSach(tenSach);

                if (phieuMuonDAO.updatePM(phieuMuon)>0){
                    list.clear();
                    list.addAll(phieuMuonDAO.getAllPhieuMuon());
                    notifyDataSetChanged();
                    dialog.dismiss();
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Lỗi cập nhật", Toast.LENGTH_SHORT).show();
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
    private ArrayList<String> getTenSachList() {
        SachDao sachDAO = new SachDao(context);
        ArrayList<Sach> list1 = sachDAO.getSach();
        ArrayList<String> tenSachList = new ArrayList<>();

        for (Sach sach: list1){
            tenSachList.add(sach.getTenSach());
        }
        return tenSachList;
    }
    private ArrayList<String> getTenThanhVienList() {
        ThanhVienDao thanhVienDAO = new ThanhVienDao(context);
        ArrayList<ThanhVien> list1 = thanhVienDAO.getThanhVien();
        ArrayList<String> tenThanhVienList = new ArrayList<>();

        for (ThanhVien thanhVien: list1){
            tenThanhVienList.add(thanhVien.getTenTV());
        }
        return tenThanhVienList;
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenTV, txtTenSach, txtTienThue, txtTrangThai, txtNgay;
        ImageButton btnDelete;
         public ViewHolder(@NonNull View itemView) {
             super(itemView);
             txtTenTV = itemView.findViewById(R.id.txtTenTV);
             txtTenSach = itemView.findViewById(R.id.txtTenSach);
             txtTienThue = itemView.findViewById(R.id.txtTienThue);
             txtTrangThai = itemView.findViewById(R.id.txtTrangThai);
             txtNgay = itemView.findViewById(R.id.txtNgay);
             btnDelete = itemView.findViewById(R.id.btnDelete);
         }
     }
}
