package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Adapter.HistoryAdapter;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.DAO.LichSuDAO;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.LichSu;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.R;


public class Fragment_Lich_Su extends Fragment {
    HistoryAdapter historyAdapter;
    List<LichSu> lichSus;
    LichSuDAO lichSuDAO;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__lich__su, container, false);
        ListView lv_history = view.findViewById(R.id.lv_history);
        lichSuDAO = new LichSuDAO(getContext());
        lichSus = lichSuDAO.getLichSuData();
        historyAdapter = new HistoryAdapter(lichSus,getContext());
        lv_history.setAdapter(historyAdapter);
        historyAdapter.notifyDataSetChanged();
        return view;
    }
}