package anhpvph37030.fpoly.duanmau.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;

import anhpvph37030.fpoly.duanmau.Adapter.SachAdapter;
import anhpvph37030.fpoly.duanmau.Dao.LoaiSachDao;
import anhpvph37030.fpoly.duanmau.Dao.SachDao;
import anhpvph37030.fpoly.duanmau.Model.LoaiSach;
import anhpvph37030.fpoly.duanmau.Model.Sach;
import anhpvph37030.fpoly.duanmau.R;


public class FG_QLSACH extends Fragment {
    RecyclerView recysach;
    FloatingActionButton fls;
    ArrayList<Sach> list = new ArrayList<>();
    SachDao dao;
    SachAdapter adapter;



    public FG_QLSACH() {

    }

    public static FG_QLSACH newInstance() {
        FG_QLSACH fragment = new FG_QLSACH();
        Bundle args = new Bundle();

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
        View view = inflater.inflate(R.layout.fragment_f_g__q_l_s_a_c_h, container, false);
        recysach = view.findViewById(R.id.rcy_qlsach);
        fls = view.findViewById(R.id.floatAdd);
        loadrecy();
        fls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.add_sach, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();
                TextInputEditText edts = view.findViewById(R.id.edts);
                TextInputEditText edgs = view.findViewById(R.id.edgs);
                TextInputEditText edanh = view.findViewById(R.id.edanh);
                Button btnts = view.findViewById(R.id.btnts);
                Spinner spnmls = view.findViewById(R.id.spnmls);
                btnts.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tens = edts.getText().toString();
                        String anh = edanh.getText().toString();
                        int gs = Integer.parseInt(edgs.getText().toString());
                        if (tens.equals("") || anh.equals("") || String.valueOf(gs).equals("")) {
                            Toast.makeText(getActivity(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                        }
                        HashMap<String, Object> hsTV = (HashMap<String, Object>) spnmls.getSelectedItem();
                        String maloai = String.valueOf(hsTV.get("MALOAI"));
                        boolean check = dao.addsach(getActivity(), edts.getText().toString(), Integer.parseInt(edgs.getText().toString()), edanh.getText().toString(), Integer.parseInt(maloai));
                        if (check) {
                            Toast.makeText(getActivity(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                            loadrecy();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(getActivity(), "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                getDataSach(spnmls);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    public void loadrecy(){
        dao = new SachDao();
        list = dao.listSach_tenloaisach(getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recysach.setLayoutManager(manager);
        adapter = new SachAdapter(getActivity(), list);
        recysach.setAdapter(adapter);
    }
    private void getDataSach(Spinner spnSach) {
        LoaiSachDao dao1 = new LoaiSachDao();
        ArrayList<LoaiSach> list = dao1.getLoaiSach(getContext());
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (LoaiSach x : list) {
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("MALOAI", x.getMaloai());
            hs.put("MALOAI_TENLOAISACH", x.getMaloai() + ":" + x.getTenloaisach());
            listHM.add(hs);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(), listHM, android.R.layout.simple_list_item_1,
                new String[]{"MALOAI_TENLOAISACH"},
                new int[]{android.R.id.text1}
        );
        spnSach.setAdapter(simpleAdapter);
    }
}