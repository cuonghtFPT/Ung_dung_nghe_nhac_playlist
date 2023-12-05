package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Adapter.AlbumListAdapter;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.DAO.AlbumDAO;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.Album;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.R;

public class BlankFragment5 extends Fragment implements AlbumListAdapter.OnAlbumClickListener {
    AlbumListAdapter adapter;
    AlbumDAO albumDAO;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank5, container, false);
        albumDAO = new AlbumDAO(getContext());
        List<Album> dataList = albumDAO.getAlbum();
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new AlbumListAdapter(dataList,getContext(), this);
        recyclerView.setAdapter(adapter);
        return view;
    }
    @Override
    public void onAlbumClick(int albumId,String albumTitle,int albumCover) {
        Fragment_All_song fragment_all_song = new Fragment_All_song();
        Bundle args = new Bundle();
        args.putInt("albumId", albumId);
        args.putString("albumTitle", albumTitle);
        args.putInt("albumCover", albumCover);
        fragment_all_song.setArguments(args);

        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment1, fragment_all_song);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}