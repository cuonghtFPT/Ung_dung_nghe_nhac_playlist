package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Adapter.GenreAdapter;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.DAO.BaiHatDAO;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.BaiHat;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.R;

public class Fragment_The_Loai extends Fragment {
    GenreAdapter genreAdapter1,genreAdapter2,genreAdapter3,genreAdapter4;
    RecyclerView rv_genre1,rv_genre2,rv_genre3,rv_genre4;
    BaiHatDAO baiHatDAO1,baiHatDAO2,baiHatDAO3,baiHatDAO4;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__the__loai, container, false);
        rv_genre1 = view.findViewById(R.id.rv_genre1);
        rv_genre2 = view.findViewById(R.id.rv_genre2);
        rv_genre3 = view.findViewById(R.id.rv_genre3);
        rv_genre4 = view.findViewById(R.id.rv_genre4);

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager4 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        rv_genre1.setLayoutManager(layoutManager1);
        rv_genre2.setLayoutManager(layoutManager2);
        rv_genre3.setLayoutManager(layoutManager3);
        rv_genre4.setLayoutManager(layoutManager4);

        List<BaiHat> baiHat1 = new ArrayList<>(getDataForDataset1());
        List<BaiHat> baiHat2 = new ArrayList<>(getDataForDataset2());
        List<BaiHat> baiHat3 = new ArrayList<>(getDataForDataset3());
        List<BaiHat> baiHat4 = new ArrayList<>(getDataForDataset4());

        genreAdapter1 = new GenreAdapter(baiHat1, getContext());
        genreAdapter2 = new GenreAdapter(baiHat2, getContext());
        genreAdapter3 = new GenreAdapter(baiHat3, getContext());
        genreAdapter4 = new GenreAdapter(baiHat4, getContext());

        rv_genre1.setAdapter(genreAdapter1);
        rv_genre2.setAdapter(genreAdapter2);
        rv_genre3.setAdapter(genreAdapter3);
        rv_genre4.setAdapter(genreAdapter4);
        return view;
    }
    public List<BaiHat> getDataForDataset1() {
        baiHatDAO1 = new BaiHatDAO(getContext());
        return baiHatDAO1.getDataByGenre(1);
    }

    public List<BaiHat> getDataForDataset2() {
        baiHatDAO2 = new BaiHatDAO(getContext());
        return baiHatDAO2.getDataByGenre(2);
    }

    public List<BaiHat> getDataForDataset3() {
        baiHatDAO3 = new BaiHatDAO(getContext());
        return baiHatDAO3.getDataByGenre(3);
    }

    public List<BaiHat> getDataForDataset4() {
        baiHatDAO4 = new BaiHatDAO(getContext());
        return baiHatDAO4.getDataByGenre(4);
    }
}