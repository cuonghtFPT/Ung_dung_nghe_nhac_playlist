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

import java.util.ArrayList;
import java.util.List;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Adapter.ListSongAdapter;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Choinhac;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.ListSong;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.R;


public class BlankFragment3 extends Fragment {
    ListSongAdapter musicListAdapter;
    ArrayList<ListSong> listN;
    Context context;
    ImageView albumCover;
    TextView songTitle;
    ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank3, container, false);
        listView = view.findViewById(R.id.lv_thongKe);
        albumCover = view.findViewById(R.id.albumCover);
        songTitle = view.findViewById(R.id.songTitle);
        context = getContext();
        listN = new ArrayList<>();
        listN.add(new ListSong("Em đồng ý","Ca Sĩ 1",R.drawable.anh_1,R.raw.emdongy,1,1));
        listN.add(new ListSong("Gió","Ca Sĩ 2",R.drawable.anh_2,R.raw.gio_lofi,2,1));
        listN.add(new ListSong("Là anh","Ca Sĩ 4",R.drawable.anh_3,R.raw.la_anh,3,1));
        listN.add(new ListSong("Một người đánh mất một người","Ca Sĩ 2",R.drawable.anh_4,R.raw.mot_nguoi_danh_mat_mot_nguoi,4,4));
        listN.add(new ListSong("Từng quen","Ca Sĩ 8",R.drawable.anh_5,R.raw.tung_quen,9,1));
        listN.add(new ListSong("Jade Emperor","Ca Sĩ 10",R.drawable.anh_2,R.raw.jade_emperor,7,1));
        listN.add(new ListSong("Numinous Tresure","Ca Sĩ 5",R.drawable.anh_3,R.raw.numinous_treasure,3,3));
        listN.add(new ListSong("Wuliang Tianzun","Ca Sĩ 3",R.drawable.anh_4,R.raw.wuliang_tianzun,4,4));
        listN.add(new ListSong("Wuji","Ca Sĩ 2",R.drawable.anh_5,R.raw.wuji,4,5));
        listN.add(new ListSong("Em đồng ý","Ca Sĩ 6",R.drawable.anh_1,R.raw.emdongy,1,1));
        listN.add(new ListSong("Gió","Ca Sĩ 3",R.drawable.anh_2,R.raw.gio_lofi,2,1));
        listN.add(new ListSong("Là anh","Ca Sĩ 2",R.drawable.anh_3,R.raw.la_anh,3,1));
        listN.add(new ListSong("Một người đánh mất một người","Ca Sĩ 4",R.drawable.anh_4,R.raw.mot_nguoi_danh_mat_mot_nguoi,4,4));
        listN.add(new ListSong("Từng quen","Ca Sĩ 3",R.drawable.anh_5,R.raw.tung_quen,4,1));
        listN.add(new ListSong("Jade Emperor","Ca Sĩ 5",R.drawable.anh_2,R.raw.jade_emperor,4,1));
        listN.add(new ListSong("Numinous Tresure","Ca Sĩ 9",R.drawable.anh_3,R.raw.numinous_treasure,3,3));
        listN.add(new ListSong("Wuliang Tianzun","Ca Sĩ 2",R.drawable.anh_4,R.raw.wuliang_tianzun,4,4));
        listN.add(new ListSong("Wuji","Ca Sĩ 3",R.drawable.anh_5,R.raw.wuji,4,5));
        musicListAdapter = new ListSongAdapter(context,listN);
        listView.setAdapter(musicListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListSong nhac = listN.get(position);
                Intent openMusicPlayer = new Intent(getContext(), Choinhac.class);
                openMusicPlayer.putExtra("nhac", nhac); // Truyền đối tượng ListSong thay vì chỉ truyền file nhạc
                startActivity(openMusicPlayer);
            }
        });
        if (getArguments() != null && getArguments().containsKey("albumId")) {
            int selectedAlbumId = getArguments().getInt("albumId");
            filterListById(selectedAlbumId);
            if (getArguments().containsKey("albumTitle")) {
                String title = getArguments().getString("albumTitle", "Default Title");
                songTitle.setText(title);
            }
            if (getArguments().containsKey("albumCover")) {
                int cover = getArguments().getInt("albumCover", R.drawable.anh_1);
                albumCover.setImageResource(cover);
            }
        }
        if (getArguments() != null && getArguments().containsKey("artistId")) {
            int selectedArtistId = getArguments().getInt("artistId");
            filterArtistById(selectedArtistId);
            if (getArguments().containsKey("artistTitle")) {
                String title = getArguments().getString("artistTitle", "Default Title");
                songTitle.setText(title);
            }
            if (getArguments().containsKey("artistCover")) {
                int cover = getArguments().getInt("artistCover", R.drawable.anh_1);
                albumCover.setImageResource(cover);
            }
        }
        return view;
    }
    private void filterListById(int id) {
        List<ListSong> filteredList = new ArrayList<>();
        for (ListSong song : listN) {
            if (song.getIdAlbum() == id) {
                filteredList.add(song);
            }
        }
        listN.clear();
        listN.addAll(filteredList);
        musicListAdapter.notifyDataSetChanged();
    }
    private void filterArtistById(int id) {
        List<ListSong> filteredList = new ArrayList<>();
        for (ListSong song : listN) {
            if (song.getIdCaSi() == id) {
                filteredList.add(song);
            }
        }
        listN.clear();
        listN.addAll(filteredList);
        musicListAdapter.notifyDataSetChanged();
    }
}
