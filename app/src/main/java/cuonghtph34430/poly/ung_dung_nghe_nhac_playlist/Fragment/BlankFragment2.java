package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Adapter.ListSongAdapter;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.ListSong;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.R;

public class BlankFragment2 extends Fragment {
    private List<ListSong> fullSongList;
    private ListSongAdapter adapter;
    private ListView listView;
    private List<ListSong> allSongs;
    private EditText editText;
    private ImageButton searchButton;
    public interface OnSongClickListener {
        void onSongClick(ListSong selectedSong);
    }

    private OnSongClickListener songClickListener;

    public void setOnSongClickListener(OnSongClickListener listener) {
        this.songClickListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blank2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fullSongList = createSongList();
        allSongs = new ArrayList<>(fullSongList);

        editText = view.findViewById(R.id.editSeach);
        searchButton = view.findViewById(R.id.imgSeach);

        listView = view.findViewById(R.id.listViewseach);
        adapter = new ListSongAdapter(getActivity(), fullSongList);
        listView.setAdapter(adapter);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchText = editText.getText().toString().toLowerCase();
                filter(searchText);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListSong selectedSong = fullSongList.get(position);
                if (songClickListener != null) {
                    songClickListener.onSongClick(selectedSong);
                }
            }
        });
    }

    private List<ListSong> createSongList() {
        List<ListSong> songs = new ArrayList<>();
        songs.add(new ListSong("Em đồng ý", "Singer 1", R.drawable.anh_1, R.raw.emdongy,1,1));
        songs.add(new ListSong("Là anh", "Singer 2", R.drawable.anh_2, R.raw.la_anh,1,1));
        songs.add(new ListSong("Gió", "Singer 1", R.drawable.anh_1, R.raw.gio_lofi,1,1));
        songs.add(new ListSong("Từng quen", "Singer 2", R.drawable.anh_2, R.raw.tung_quen,1,1));
        songs.add(new ListSong("Một người đánh mất một người", "Singer 1", R.drawable.anh_1, R.raw.mot_nguoi_danh_mat_mot_nguoi,1,1));
        // ...
        return songs;
    }

    private void filter(String searchText) {
        List<ListSong> filteredList = new ArrayList<>();

        if (searchText.isEmpty()) {
            filteredList.addAll(allSongs);
        } else {
            for (ListSong song : allSongs) {
                if (song.getSong().toLowerCase().contains(searchText)
                        || song.getSinger().toLowerCase().contains(searchText)) {
                    filteredList.add(song);
                }
            }
        }

        adapter.updateList(filteredList);
    }
}