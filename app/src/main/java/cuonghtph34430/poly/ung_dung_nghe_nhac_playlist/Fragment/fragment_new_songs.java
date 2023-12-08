package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Adapter.ListSongAdapter;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Choinhac;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.DAO.BaiHatDAO;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.BaiHat;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.R;

public class fragment_new_songs extends Fragment {
    ListSongAdapter nhacAdapter;
    List<BaiHat> listN;
    ListView lv_nhac;
    View view;
    Context context;
    BaiHatDAO baiHatDAO;
    int start = 0;
    int limit = 28;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frament_new_song,container,false);
        lv_nhac = view.findViewById(R.id.new_song);

        context = getContext(); // hoặc getActivity()
        baiHatDAO = new BaiHatDAO(context);
        listN = baiHatDAO.getAll();
        List<BaiHat> displayedItemList = listN.subList(start, Math.min(listN.size(), start + limit));
        nhacAdapter = new ListSongAdapter(context,displayedItemList);
        lv_nhac.setAdapter(nhacAdapter);

        lv_nhac.setOnItemClickListener((parent, view, position, id) -> {
            BaiHat nhac = listN.get(position);
            Intent openMusicPlayer = new Intent(getContext(), Choinhac.class);
            openMusicPlayer.putExtra("nhac", nhac); // Truyền đối tượng BaiHat thay vì chỉ truyền file nhạc
            startActivity(openMusicPlayer);
        });
        return view;
    }
}
