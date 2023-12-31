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
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import anhpvph37030.fpoly.duanmau.DAO.LoaiSachDao;
import anhpvph37030.fpoly.duanmau.DAO.SachDao;
import anhpvph37030.fpoly.duanmau.Model.LoaiSach;
import anhpvph37030.fpoly.duanmau.Model.Sach;
import anhpvph37030.fpoly.duanmau.R;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Sach> list,list1;
    SachDao sachDAO;

    public SachAdapter(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;
        this.list1 = list;
        sachDAO = new SachDao(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.rcy_sach, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Sach sach = list.get(position);
        holder.txtTenSach.setText(sach.getTenSach());
        holder.txtTienThue.setText(String.valueOf(sach.getTienThue()));
        holder.txtLoaiSach.setText(sach.getLoaiSach());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(R.drawable.baseline_warning_24);
                builder.setCancelable(false);
                builder.setTitle("Xóa sách");
                builder.setMessage("Bạn có muốn xóa không ?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // bắt sự kiện nhấn nút Yes
                        if (sachDAO.deleteS(sach.getMasach()) > 0) {
                            list.clear();
                            list.addAll(sachDAO.getSach());
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
                updateDialog(sach);
                return true;
            }
        });
    }
    private void updateDialog(Sach sach) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.update_sach, null);
        builder.setView(view);
        builder.setCancelable(false);
        Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        EditText edtTenSach,edtTienThue;
        Spinner edtTenLoai;
        Button btnSua, btnHuy;

        edtTenSach = view.findViewById(R.id.edtTenSach);
        edtTienThue = view.findViewById(R.id.edtTienThue);
        edtTenLoai = view.findViewById(R.id.edtTenLoai);
        btnSua = view.findViewById(R.id.btnSua);
        btnHuy = view.findViewById(R.id.btnHuy);

        edtTenSach.setText(sach.getTenSach());
        edtTienThue.setText(String.valueOf(sach.getTienThue()));
        // Thay đổi dòng này:
// edtTenLoai.setText(sach.getLoaiSach());
// Thành dòng này:
        edtTenLoai.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, getTenLoaiSachList()));
        edtTenLoai.setSelection(getTenLoaiSachList().indexOf(sach.getLoaiSach()));


        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tensach = edtTenSach.getText().toString();
                String tienthue = edtTienThue.getText().toString();
                String tenloai = edtTenLoai.getSelectedItem().toString();

                if (tensach.isEmpty()||tienthue.isEmpty()){
                    Toast.makeText(context, "Không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!tienthue.matches("\\d+")){
                    Toast.makeText(context, "Tiền thuê phải là sô", Toast.LENGTH_SHORT).show();
                    return;
                }

                sach.setTenSach(tensach);
                sach.setTienThue(Integer.parseInt(tienthue));
                sach.setLoaiSach(tenloai);

                if (sachDAO.updateS(sach)>0){
                    list.clear();
                    list.addAll(sachDAO.getSach());
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
    private ArrayList<String> getTenLoaiSachList() {
        LoaiSachDao loaiSachDAO = new LoaiSachDao(context);
        ArrayList<LoaiSach> list1 = loaiSachDAO.getAllLoaiSach();
        ArrayList<String> tenLoaiSachList = new ArrayList<>();

        for (LoaiSach loaiSach: list1){
            tenLoaiSachList.add(loaiSach.getTenLoai());
        }
        return tenLoaiSachList;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if (strSearch.isEmpty()){
                    list = list1;
                }else{
                    ArrayList<Sach> listSearch = new ArrayList<>();
                    for (Sach sach: list1){
                        if (sach.getTenSach().toLowerCase().contains(strSearch.toLowerCase())){
                            listSearch.add(sach);
                        }
                    }
                    list = listSearch;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = list;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (ArrayList<Sach>) results.values;
                notifyDataSetChanged();
            }
        };
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenSach,txtTienThue,txtLoaiSach;
        ImageButton btnDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenSach = itemView.findViewById(R.id.txtTenSach);
            txtTienThue = itemView.findViewById(R.id.txtTienThue);
            txtLoaiSach = itemView.findViewById(R.id.txtLoaiSach);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
