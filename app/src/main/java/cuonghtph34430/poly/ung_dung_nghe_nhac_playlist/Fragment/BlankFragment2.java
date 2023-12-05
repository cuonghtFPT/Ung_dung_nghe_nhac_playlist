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
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.DAO.BaiHatDAO;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.BaiHat;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.R;

public class BlankFragment2 extends Fragment {
    private List<BaiHat> fullSongList;
    private ListSongAdapter adapter;
    private ListView listView;
    private List<BaiHat> allSongs;
    private EditText editText;
    private ImageButton searchButton;
    BaiHatDAO baiHatDAO;
    public interface OnSongClickListener {
        void onSongClick(BaiHat selectedSong);
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
                BaiHat selectedSong = fullSongList.get(position);
                if (songClickListener != null) {
                    songClickListener.onSongClick(selectedSong);
                }
            }
        });
    }

    private List<BaiHat> createSongList() {
        baiHatDAO = new BaiHatDAO(getContext());
        List<BaiHat> songs = baiHatDAO.getAll();
        return songs;
    }

    private void filter(String searchText) {
        List<BaiHat> filteredList = new ArrayList<>();
        if (searchText.isEmpty()) {
            filteredList.addAll(allSongs);
        } else {
            for (BaiHat song : allSongs) {
                if (song.getTenBaiHat().toLowerCase().contains(searchText)) {
                    filteredList.add(song);
                }
            }
        }
        adapter.updateList(filteredList);
    }
}