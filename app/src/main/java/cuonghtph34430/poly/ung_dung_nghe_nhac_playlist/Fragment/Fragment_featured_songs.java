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

import java.util.ArrayList;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Adapter.ListSongAdapter;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Choinhac;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.BaiHat;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.R;

public class Fragment_featured_songs extends Fragment {
    ListSongAdapter nhacAdapter;
    BaiHat nhac;
    ArrayList<BaiHat> listN;
    ListView lv_nhac;
    View view;
    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_feauted_song,container,false);
        lv_nhac = view.findViewById(R.id.feauted_song);

        context = getContext(); // hoặc getActivity()
        listN = new ArrayList<BaiHat>();
        listN.add(new BaiHat(1,"Là anh",R.drawable.anh_3,R.raw.la_anh,1,1));
        listN.add(new BaiHat(2,"Một người đánh mất một người",R.drawable.anh_4,R.raw.mot_nguoi_danh_mat_mot_nguoi,1,1));
        listN.add(new BaiHat(3,"Em đồng ý",R.drawable.anh_1,R.raw.emdongy,1,1));
        listN.add(new BaiHat(4,"Gió",R.drawable.anh_2,R.raw.gio_lofi,1,1));
        listN.add(new BaiHat(5,"Từng quen",R.drawable.anh_5,R.raw.tung_quen,1,1));

        nhacAdapter = new ListSongAdapter(context,listN);
        lv_nhac.setAdapter(nhacAdapter);

        lv_nhac.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
