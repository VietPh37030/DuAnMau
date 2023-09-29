package anhpvph37030.fpoly.duanmau.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import anhpvph37030.fpoly.duanmau.R;


public class FG_QLTHANHVIEN extends Fragment {


    public FG_QLTHANHVIEN() {

    }

    public static FG_QLTHANHVIEN newInstance(String param1, String param2) {
        FG_QLTHANHVIEN fragment = new FG_QLTHANHVIEN();
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

        return inflater.inflate(R.layout.fragment_f_g__q_l_t_h_a_n_h_v_i_e_n, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}