package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Adapter.ChartAdapter;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Activity.Choinhac;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.DAO.BaiHatDAO;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.BaiHat;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.R;


public class BlankFragment3 extends Fragment {
    ChartAdapter chartAdapter;
    List<BaiHat> listN;
    Context context;
    ImageView albumCover;
    TextView songTitle;
    ListView listView;
    BaiHatDAO baiHatDAO;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank3, container, false);
        listView = view.findViewById(R.id.lv_thongKe);
        albumCover = view.findViewById(R.id.albumCover);
        songTitle = view.findViewById(R.id.songTitle);
        context = getContext();
        baiHatDAO = new BaiHatDAO(context);
        listN = baiHatDAO.getAll();
        chartAdapter = new ChartAdapter(context,listN);
        chartAdapter.updateAndSortList(listN);
        listView.setAdapter(chartAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BaiHat nhac = listN.get(position);
                Intent openMusicPlayer = new Intent(getContext(), Choinhac.class);
                openMusicPlayer.putExtra("nhac", nhac); // Truyền đối tượng BaiHat thay vì chỉ truyền file nhạc
                startActivity(openMusicPlayer);
            }
        });
        return view;
    }
}
