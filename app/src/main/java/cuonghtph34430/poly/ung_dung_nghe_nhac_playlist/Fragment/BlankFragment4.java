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
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.CaSi;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.R;

public class BlankFragment4 extends Fragment implements ArtistListAdapter.OnArtistClickListener {
    ArtistListAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_blank4, container, false);
        RecyclerView recycler_view = view.findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new GridLayoutManager(getContext(), 2));
        List<CaSi> data = new ArrayList<>();
        data.add(new CaSi(1,"Ca Sĩ 1",R.drawable.anh_4));
        data.add(new CaSi(2,"Ca Sĩ 2",R.drawable.anh_2));
        data.add(new CaSi(3,"Ca Sĩ 3",R.drawable.anh_3));
        data.add(new CaSi(4,"Ca Sĩ 4",R.drawable.anh_1));
        data.add(new CaSi(5,"Ca Sĩ 5",R.drawable.anh_3));
        data.add(new CaSi(6,"Ca Sĩ 6",R.drawable.anh_2));
        data.add(new CaSi(7,"Ca Sĩ 7",R.drawable.anh_3));
        data.add(new CaSi(8,"Ca Sĩ 8",R.drawable.anh_1));
        data.add(new CaSi(9,"Ca Sĩ 9",R.drawable.anh_3));
        adapter = new ArtistListAdapter(data,getContext(), this);
        recycler_view.setAdapter(adapter);
        return view;
    }

    @Override
    public void onArtistClick(int artistId,String artistTitle,int artistCover) {
        BlankFragment3 fragment3 = new BlankFragment3();
        Bundle args = new Bundle();
        args.putInt("artistId", artistId);
        args.putString("artistTitle", artistTitle);
        args.putInt("artistCover", artistCover);
        fragment3.setArguments(args);

        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment1, fragment3);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}