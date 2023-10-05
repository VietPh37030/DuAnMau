package anhpvph37030.fpoly.duanmau.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import anhpvph37030.fpoly.duanmau.Dao.LoaiSachDao;
import anhpvph37030.fpoly.duanmau.Model.LoaiSach;
import anhpvph37030.fpoly.duanmau.R;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.ViewHolder> {

    public Context mContext;
    public ArrayList<LoaiSach> list = new ArrayList<>();
    LoaiSachDao dao;

    public LoaiSachAdapter(Context mContext, ArrayList<LoaiSach> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rcy_loaisach, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LoaiSach loaiSach = list.get(position);
        holder.txtmals.setText("Ma:" + String.valueOf(loaiSach.getMaloai()));
        holder.txttenls.setText("Tên Loại Sách:" + loaiSach.getTenloaisach());
        holder.btn_deletels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Xoá Loại Sách?");
                builder.setMessage("Bạn Có chắc Muốn Xóa :?");
                builder.setIcon(R.drawable.baseline_warning_24);

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        LoaiSachDao dao = new LoaiSachDao();
                        int check = dao.deletels(mContext, list.get(holder.getAdapterPosition()).getMaloai());
                        switch (check) {
                            case 1:
                                Toast.makeText(mContext, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                                list.clear();
                                list = dao.getLoaiSach(mContext);
                                notifyDataSetChanged();
                                break;
                            case -1:
                                Toast.makeText(mContext, "Xóa Thất Bại vi da co loai sach nay", Toast.LENGTH_SHORT).show();
                                break;
                            case 0:
                                Toast.makeText(mContext, " Xoa khog thanh cong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();

            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
                view = inflater.inflate(R.layout.edit_loaisach, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();
                TextInputEditText edt_updatels = view.findViewById(R.id.edt_updatels);
                Button btn_updatels = view.findViewById(R.id.btn_updatebutton);
                edt_updatels.setText(loaiSach.getTenloaisach());
                btn_updatels.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (edt_updatels.getText().toString().equals("")) {
                            Toast.makeText(mContext, "Vui Lòg nhập loại sách", Toast.LENGTH_SHORT).show();
                        } else {
                            LoaiSach loaiSach1 = new LoaiSach(list.get(holder.getAdapterPosition()).getMaloai(), edt_updatels.getText().toString());
                            dao = new LoaiSachDao();
                            String check = dao.updatels(mContext, String.valueOf(list.get(holder.getAdapterPosition()).getMaloai()), loaiSach1);
                            list.clear();
                            list = dao.getLoaiSach(mContext);
                            dialog.dismiss();
                            notifyDataSetChanged();
                        }
                    }
                });


                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtmals, txttenls;
        ImageButton btn_deletels;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtmals = itemView.findViewById(R.id.tv_addmals);
            txttenls = itemView.findViewById(R.id.tv_addtenloai);
            btn_deletels = itemView.findViewById(R.id.add_delete);
        }
    }
}
