package anhpvph37030.fpoly.duanmau.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import anhpvph37030.fpoly.duanmau.Adapter.ThanhVienAdapter;
import anhpvph37030.fpoly.duanmau.Dao.ThanhVienDao;
import anhpvph37030.fpoly.duanmau.Model.Thanhvien;
import anhpvph37030.fpoly.duanmau.R;


public class FG_QLTHANHVIEN extends Fragment {

    RecyclerView recytv;
    FloatingActionButton fltv;
    ThanhVienDao dao;
    ArrayList<Thanhvien> list = new ArrayList<>();
    ThanhVienAdapter adapter;

    public FG_QLTHANHVIEN() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_f_g__q_l_t_h_a_n_h_v_i_e_n, container, false);
        recytv = view.findViewById(R.id.rcy_thanhvien);
        fltv = view.findViewById(R.id.floatAdd);
        loadrecytv();
        fltv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                additemtv();
            }
        });
        return view;
    }

    private void additemtv() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.addtv, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        TextInputEditText edtttentv = view.findViewById(R.id.edtttentv);
        TextInputEditText edttnstv = view.findViewById(R.id.edttnstv);
        Button btnthemtv = view.findViewById(R.id.btnthemtv);
        btnthemtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tentv = edtttentv.getText().toString();
                int nstv = Integer.parseInt(edttnstv.getText().toString());
                if(edtttentv.getText().toString().equalsIgnoreCase("")||edttnstv.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(getActivity(), "Không được để trống", Toast.LENGTH_SHORT).show();
                }else{
                    boolean check = dao.addTV(getActivity(), tentv, String.valueOf(nstv));
                    if (check) {
                        Toast.makeText(getActivity(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                        loadrecytv();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(getActivity(), "Thêm Thành Viên Thất Bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }



    public void loadrecytv(){
        dao = new ThanhVienDao();
        list = dao.ListTV(getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recytv.setLayoutManager(manager);
        adapter = new ThanhVienAdapter(getActivity(), list);
        recytv.setAdapter(adapter);
    }


}