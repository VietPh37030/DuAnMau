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
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;

import anhpvph37030.fpoly.duanmau.Dao.LoaiSachDao;
import anhpvph37030.fpoly.duanmau.Dao.SachDao;
import anhpvph37030.fpoly.duanmau.Model.LoaiSach;
import anhpvph37030.fpoly.duanmau.Model.Sach;
import anhpvph37030.fpoly.duanmau.R;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.ViewHolder> {
    public Context context;
    public ArrayList<Sach> list = new ArrayList<>();

    public SachAdapter(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rcy_qlsach, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Sach sach = list.get(position);

        holder.tvtensach.setText("Tên Sách:"  +sach.getTenSach());
        holder.tvgiathue.setText("Giá Thuê:" +String.valueOf(sach.getGiaThue()));
        holder.tvmaloaisach.setText("Mã Loại Sách:"+String.valueOf(sach.getMaloaisach()));
        holder.tvtenloaisach.setText("Tên Loại Sách:"+sach.getTenloaisach());
        holder.imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Bạn có muốn xóa không?");
                builder.setIcon(R.drawable.baseline_warning_24);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SachDao dao = new SachDao();
                        int check = dao.deletesach(context, list.get(holder.getAdapterPosition()).getMaSach());
                        switch (check) {
                            case 1:
                                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                list.clear();
                                list = dao.listSach_tenloaisach(context);
                                notifyDataSetChanged();
                                break;

                            case -1:
                                Toast.makeText(context, "Không thể xóa sách này vì phiếu mượn có", Toast.LENGTH_SHORT).show();
                                break;

                            case 0:
                                Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
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
        ////////////////////////////////
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                view = inflater.inflate(R.layout.editsach, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();
                TextInputEditText edtts = view.findViewById(R.id.edtts);
                TextInputEditText edtgs = view.findViewById(R.id.edtgs);
                TextInputEditText edtanh = view.findViewById(R.id.edtanh);
                Button btnss = view.findViewById(R.id.btnss);
                Spinner spnnmls = view.findViewById(R.id.spnnmls);
                edtts.setText(sach.getTenSach());
                edtgs.setText(String.valueOf(sach.getGiaThue()));
                edtanh.setText(sach.getImage());
                btnss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tens = edtts.getText().toString();
                        int gs = Integer.parseInt(edtgs.getText().toString());
                        String anh = edtanh.getText().toString();
                        if (tens.equals("") || String.valueOf(gs).equals("")) {
                            Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                        }else{
                            HashMap<String, Object> hsTV = (HashMap<String, Object>) spnnmls.getSelectedItem();
                            int maloai = Integer.parseInt(String.valueOf(hsTV.get("MALOAI")));
                            Sach sach = new Sach(list.get(holder.getAdapterPosition()).getMaSach(), edtts.getText().toString(), Integer.parseInt(edtgs.getText().toString()), edtanh.getText().toString(), maloai);
                            SachDao dao = new SachDao();
                            String check = dao.updatesach(context, String.valueOf(list.get(holder.getAdapterPosition()).getMaSach()), sach);
                            Toast.makeText(context, check, Toast.LENGTH_SHORT).show();
                            list.clear();
                            list = dao.listSach_tenloaisach(context);
                            dialog.dismiss();
                            notifyDataSetChanged();
                        }
                    }
                });
                getDataSach(spnnmls);
                return false;
            }
        });
    }
    private void getDataSach(Spinner spnSach) {
        LoaiSachDao dao1 = new LoaiSachDao();
        ArrayList<LoaiSach> list = dao1.getLoaiSach(context);
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (LoaiSach x : list) {
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("MALOAI", x.getMaloai());
            hs.put("MALOAI_TENLOAISACH", x.getMaloai() + ":" + x.getTenloaisach());
            listHM.add(hs);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                context, listHM, android.R.layout.simple_list_item_1,
                new String[]{"MALOAI_TENLOAISACH"},
                new int[]{android.R.id.text1}
        );
        spnSach.setAdapter(simpleAdapter);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvtensach, tvgiathue, tvmaloaisach, tvtenloaisach;
        ImageView imgsach;
        ImageButton imgdelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvtensach = itemView.findViewById(R.id.tvTenSach);
            tvgiathue = itemView.findViewById(R.id.tvGiaThue);
            tvmaloaisach = itemView.findViewById(R.id.tvMaloaiSach);
            tvtenloaisach = itemView.findViewById(R.id.tvLoai);
            imgsach = itemView.findViewById(R.id.imgsach);
            imgdelete = itemView.findViewById(R.id.imgDeleteS);
        }
    }
}
