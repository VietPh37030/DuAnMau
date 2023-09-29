package anhpvph37030.fpoly.duanmau.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import anhpvph37030.fpoly.duanmau.R;


public class FG_DOIMK extends Fragment {








    public static FG_DOIMK newInstance(String param1, String param2) {
        FG_DOIMK fragment = new FG_DOIMK();
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
        return inflater.inflate(R.layout.fragment_f_g__d_o_i_m_k, container, false);
    }
}