package anhpvph37030.fpoly.duanmau.Adapter;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import anhpvph37030.fpoly.duanmau.Dao.ThanhVienDao;
import anhpvph37030.fpoly.duanmau.Model.Thanhvien;
import anhpvph37030.fpoly.duanmau.R;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ViewHolder> {
    Context Mcontext;
    ArrayList<Thanhvien> list = new ArrayList<>();
    public ThanhVienAdapter(Context mContext, ArrayList<Thanhvien> list) {
        this.Mcontext = mContext;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(Mcontext).inflate(R.layout.rcy_tv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Thanhvien vien = list.get(position);
        holder.txtttv.setText("Tên Thành Viên: "+   vien.getHoten());
        holder.txtnstv.setText("Năm Sinh: "+   String.valueOf(vien.getNamsinh()));
        holder.deletetv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Mcontext);
                builder.setTitle("Bạn có muốn xóa TV không?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ThanhVienDao dao = new ThanhVienDao();
                        int check = dao.deleteTV(Mcontext, list.get(holder.getAdapterPosition()).getMatv());
                        switch (check) {
                            case 1:
                                Toast.makeText(Mcontext, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                list.clear();
                                list = dao.ListTV(Mcontext);
                                notifyDataSetChanged();
                                break;
                            case -1:
                                Toast.makeText(Mcontext, "Không thể xóa thành viên  này vì đã có trong phiếu mượn", Toast.LENGTH_SHORT).show();
                                break;
                            case 0:
                                Toast.makeText(Mcontext, "xóa thành viên không thành công ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
//     holder.itemView.setOnClickListener(new View.OnClickListener() {
//         @Override
//         public void onClick(View view) {
//             AlertDialog.Builder builder = new AlertDialog.Builder(Mcontext);
//             LayoutInflater inflater = ((Activity)Mcontext).getLayoutInflater();
//             View view1 = inflater.inflate(R.layout.dialog_tv, null);
//             builder.setView(view);
//             Dialog dialog = builder.create();
//             dialog.show();
//             TextInputEditText edtstentv = view.findViewById(R.id.up_tentv);
//             TextInputEditText edtsnstv = view.findViewById(R.id.up_namsinh);
//             Button btnsuatv = view.findViewById(R.id.btnLuu);
//             btnsuatv.setOnClickListener(new View.OnClickListener() {
//                 @Override
//                 public void onClick(View v) {
//                     ThanhVienDao dao = new ThanhVienDao();
//                     String tentv = edtstentv.getText().toString();
//                     String ns = edtsnstv.getText().toString();
//                     if(edtstentv.getText().toString().equalsIgnoreCase("") || edtsnstv.getText().toString().equalsIgnoreCase("")){
//                         Toast.makeText(Mcontext, "Không được để trống", Toast.LENGTH_SHORT).show();
//                     }
//                     Thanhvien thanhVien = new Thanhvien(list.get(holder.getAdapterPosition()).getMatv(), tentv, ns);
//                     String check = dao.updateTV(Mcontext, list.get(holder.getAdapterPosition()).getMatv(), thanhVien);
//                     Toast.makeText(Mcontext, check, Toast.LENGTH_SHORT).show();
//                     list.clear();
//                     list = dao.ListTV(Mcontext);
//                     notifyDataSetChanged();
//                 }
//             });
//         }
//     });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtttv, txtnstv;
        ImageButton deletetv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtttv = itemView.findViewById(R.id.tvTenTv);
            txtnstv = itemView.findViewById(R.id.tvNamSinh);
            deletetv = itemView.findViewById(R.id.btn_delete);
        }
    }


}
