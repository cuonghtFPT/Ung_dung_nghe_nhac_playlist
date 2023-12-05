package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Adapter.ListSongAdapter;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Choinhac;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.DAO.BaiHatDAO;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.BaiHat;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.R;

public class Fragment_All_song extends Fragment {
    ListSongAdapter nhacAdapter;
    List<BaiHat> listN;
    ListView lv_nhac;
    View view;
    Context context;
    ImageView albumCover;
    TextView songTitle;
    BaiHatDAO baiHatDAO;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_all_song,container,false);
        lv_nhac = view.findViewById(R.id.fragment_all_song);
        albumCover = view.findViewById(R.id.albumCover);
        songTitle = view.findViewById(R.id.songTitle);
        context = getContext(); // hoặc getActivity()
        baiHatDAO = new BaiHatDAO(context);
        listN = baiHatDAO.getAll();
        nhacAdapter = new ListSongAdapter(context,listN);
        lv_nhac.setAdapter(nhacAdapter);
        lv_nhac.setOnItemClickListener((parent, view, position, id) -> {
            BaiHat nhac = listN.get(position);
            Intent openMusicPlayer = new Intent(getContext(), Choinhac.class);
            openMusicPlayer.putExtra("nhac", nhac); // Truyền đối tượng BaiHat thay vì chỉ truyền file nhạc
            startActivity(openMusicPlayer);
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
        List<BaiHat> filteredList = new ArrayList<>();
        for (BaiHat song : listN) {
            if (song.getIdAlbum() == id) {
                filteredList.add(song);
            }
        }
        listN.clear();
        listN.addAll(filteredList);
        nhacAdapter.notifyDataSetChanged();
    }
    private void filterArtistById(int id) {
        List<BaiHat> filteredList = new ArrayList<>();
        for (BaiHat song : listN) {
            if (song.getIdCaSi() == id) {
                filteredList.add(song);
            }
        }
        listN.clear();
        listN.addAll(filteredList);
        nhacAdapter.notifyDataSetChanged();
    }
}
