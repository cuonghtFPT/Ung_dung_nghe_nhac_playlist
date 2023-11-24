package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Adapter.AlbumListAdapter;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.Album;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.R;

public class BlankFragment5 extends Fragment implements AlbumListAdapter.OnAlbumClickListener {
    AlbumListAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank5, container, false);
        List<Album> dataList = new ArrayList<>();

        dataList.add(new Album(1,"Album 1",R.drawable.anh_3));
        dataList.add(new Album(2,"Album 2",R.drawable.anh_1));
        dataList.add(new Album(3,"Album 3",R.drawable.anh_2));
        dataList.add(new Album(4,"Album 4",R.drawable.anh_4));
        dataList.add(new Album(5,"Album 5",R.drawable.anh_3));
        dataList.add(new Album(6,"Album 6",R.drawable.anh_3));
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new AlbumListAdapter(dataList,getContext(), this);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onAlbumClick(int albumId,String albumTitle,int albumCover) {
        BlankFragment3 fragment3 = new BlankFragment3();
        Bundle args = new Bundle();
        args.putInt("albumId", albumId);
        args.putString("albumTitle", albumTitle);
        args.putInt("albumCover", albumCover);
        fragment3.setArguments(args);

        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment1, fragment3);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}