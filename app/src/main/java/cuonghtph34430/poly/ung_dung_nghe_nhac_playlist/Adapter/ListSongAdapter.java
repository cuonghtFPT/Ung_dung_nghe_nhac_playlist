package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.DAO.CaSiDAO;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.BaiHat;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.CaSi;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.R;

public class ListSongAdapter extends BaseAdapter {
    Context context;
    List<BaiHat> arraySong;
    private final List<BaiHat> originalList;

    public ListSongAdapter(Context context, List<BaiHat> arraySong) {
        this.context = context;
        this.arraySong = arraySong;
        this.originalList = new ArrayList<>(arraySong);
    }
    public void updateList(List<BaiHat> newList) {
        arraySong.clear();
        arraySong.addAll(newList);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return arraySong.size();
    }

    @Override
    public Object getItem(int position) {
        return arraySong.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_song, parent, false);

        CaSiDAO caSiDAO = new CaSiDAO(context);
        CaSi caSi = caSiDAO.getID(String.valueOf(arraySong.get(position).getIdCaSi()));

        TextView tvSong = view.findViewById(R.id.tvSong);
        tvSong.setText(arraySong.get(position).getTenBaiHat());

        TextView tvSinger = view.findViewById(R.id.tvSinger);
        tvSinger.setText(caSi.getTenCaSi());

        ImageView ivPicture = view.findViewById(R.id.ivpicture);
        ivPicture.setImageResource(arraySong.get(position).getAnhBaiHat());

        return view;
    }
}