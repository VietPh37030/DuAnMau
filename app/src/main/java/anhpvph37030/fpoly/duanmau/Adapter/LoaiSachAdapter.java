package anhpvph37030.fpoly.duanmau.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

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
