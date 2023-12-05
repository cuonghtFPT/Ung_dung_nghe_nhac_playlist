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

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Adapter.ArtistListAdapter;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.DAO.CaSiDAO;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.CaSi;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.R;

public class BlankFragment4 extends Fragment implements ArtistListAdapter.OnArtistClickListener {
    ArtistListAdapter adapter;
    CaSiDAO caSiDAO;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_blank4, container, false);
        RecyclerView recycler_view = view.findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new GridLayoutManager(getContext(), 2));
        caSiDAO = new CaSiDAO(getContext());
        List<CaSi> data = caSiDAO.getAll();
        adapter = new ArtistListAdapter(data,getContext(), this);
        recycler_view.setAdapter(adapter);
        return view;
    }
    @Override
    public void onArtistClick(int artistId,String artistTitle,int artistCover) {
        Fragment_All_song fragment_all_song = new Fragment_All_song();
        Bundle args = new Bundle();
        args.putInt("artistId", artistId);
        args.putString("artistTitle", artistTitle);
        args.putInt("artistCover", artistCover);
        fragment_all_song.setArguments(args);

        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment1, fragment_all_song);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}