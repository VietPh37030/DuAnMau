package anhpvph37030.fpoly.duanmau.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import anhpvph37030.fpoly.duanmau.R;


public class FG_ADDMEM extends Fragment {





    public FG_ADDMEM() {

    }


    public static FG_ADDMEM newInstance() {
        FG_ADDMEM fragment = new FG_ADDMEM();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_f_g__a_d_d_m_e_m, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}