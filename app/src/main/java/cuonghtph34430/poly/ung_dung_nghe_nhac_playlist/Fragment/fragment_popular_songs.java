package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Adapter.PopularAdapter;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Activity.Choinhac;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.DAO.BaiHatDAO;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.BaiHat;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.R;

public class fragment_popular_songs extends Fragment {
    PopularAdapter popularAdapter;
    List<BaiHat> listN;
    ListView lv_nhac;
    View view;
    Context context;
    BaiHatDAO baiHatDAO;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_popular_song,container,false);
        lv_nhac = view.findViewById(R.id.popular_song);

        baiHatDAO = new BaiHatDAO(getContext());
        listN = baiHatDAO.getAll();
        popularAdapter = new PopularAdapter(listN,getContext());
        lv_nhac.setAdapter(popularAdapter);
        lv_nhac.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BaiHat nhac = listN.get(position);
                Intent openMusicPlayer = new Intent(getContext(), Choinhac.class);
                openMusicPlayer.putExtra("nhac", nhac); // Truyền đối tượng BaiHat thay vì chỉ truyền file nhạc
                startActivity(openMusicPlayer);
            }
        });
        popularAdapter.notifyDataSetChanged();
        return view;
    }
}
