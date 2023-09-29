package anhpvph37030.fpoly.duanmau.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import anhpvph37030.fpoly.duanmau.R;


public class FG_QLLOAISACH extends Fragment {



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

        return inflater.inflate(R.layout.fragment_f_g__q_l_l_o_a_i_s_a_c_h, container, false);
    }
}