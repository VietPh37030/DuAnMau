package anhpvph37030.fpoly.duanmau.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import anhpvph37030.fpoly.duanmau.Model.Top10;
import anhpvph37030.fpoly.duanmau.R;

public class Top10Adapter  extends RecyclerView.Adapter<Top10Adapter.ViewHolder>{
    private Context context;
    private ArrayList<Top10> list;
    public Top10Adapter(Context context, ArrayList<Top10> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.rcy_top10, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Top10 topSach = list.get(position);
        holder.txtTenSach.setText(topSach.getTenSach());
        holder.txtSoLuong.setText(String.valueOf(topSach.getSoLuong()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  static  class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenSach,txtSoLuong;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenSach = itemView.findViewById(R.id.txtTenSach);
            txtSoLuong = itemView.findViewById(R.id.txtSoLuong);
        }
    }
}
