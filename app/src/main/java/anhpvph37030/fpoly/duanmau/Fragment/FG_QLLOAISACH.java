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

import anhpvph37030.fpoly.duanmau.Adapter.LoaiSachAdapter;
import anhpvph37030.fpoly.duanmau.Dao.LoaiSachDao;
import anhpvph37030.fpoly.duanmau.Model.LoaiSach;
import anhpvph37030.fpoly.duanmau.R;


public class FG_QLLOAISACH extends Fragment {

    FloatingActionButton fltheloai;
    RecyclerView rcyloaisach;
    LoaiSachDao dao;
    LoaiSachAdapter adapter;
    ArrayList<LoaiSach> list = new ArrayList<>();


    public FG_QLLOAISACH() {

    }


    // TODO: Rename and change types and number of parameters
    public static FG_QLLOAISACH newInstance(String param1, String param2) {
        FG_QLLOAISACH fragment = new FG_QLLOAISACH();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_f_g__q_l_l_o_a_i_s_a_c_h, container, false);
        fltheloai = view.findViewById(R.id.floatAdd);
        rcyloaisach = view.findViewById(R.id.rcy_qlloaisach);
        loadform();
        fltheloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addtl();
            }
        });
        return view;


    }
    private void addtl() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.addloaisach, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        TextInputEditText mals = view.findViewById(R.id.add_maloai);
        TextInputEditText tenls = view.findViewById(R.id.add_tenloaisach);
        //TextInputEditText edtml = view.findViewById(R.id.edml);
        Button  btnAdd = view.findViewById(R.id.ls_button);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = tenls.getText().toString();
                if(tenls.equals("")){
                    Toast.makeText(getActivity(), "Vui lòng nhập tên loại sách", Toast.LENGTH_SHORT).show();
                }else{
                    boolean check= dao.addls(getActivity(),tenls.getText().toString());
                    if(check){
                        Toast.makeText(getActivity(), "THÊM THÀNH CÔNG", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        loadform();
                        tenls.setText("");
                    }else {
                        Toast.makeText(getActivity(), "THÊM THẤT BẠI", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void loadform(){
        dao = new LoaiSachDao();
        list = dao.getLoaiSach(getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        rcyloaisach.setLayoutManager(manager);
        adapter = new LoaiSachAdapter(getActivity(), list);
        rcyloaisach.setAdapter(adapter);
    }
}