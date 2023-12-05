package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Adapter;

import static java.util.Collections.addAll;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.DAO.CaSiDAO;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.BaiHat;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.CaSi;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.R;

public class ChartAdapter extends BaseAdapter {
    public Context context;
    public List<BaiHat> baiHats;

    public ChartAdapter(Context context, List<BaiHat> baiHats) {
        this.context = context;
        this.baiHats = baiHats;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return baiHats.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.item_chart,parent,false);
        ImageView imageMusicCover = view.findViewById(R.id.imageMusicCover);
        TextView textTitle = view.findViewById(R.id.textTitle);
        TextView numberingchart = view.findViewById(R.id.numberingchart);
        TextView textArtist = view.findViewById(R.id.textArtist);
        CaSiDAO caSiDAO = new CaSiDAO(context);
        CaSi caSi = caSiDAO.getID(String.valueOf(baiHats.get(position).getIdCaSi()));
        textArtist.setText(caSi.getTenCaSi());
        numberingchart.setText(String.valueOf(position + 1));
        textTitle.setText(baiHats.get(position).getTenBaiHat());
        imageMusicCover.setImageResource(baiHats.get(position).getAnhBaiHat());
        return view;
    }
    public void updateAndSortList(List<BaiHat> newList) {
        Collections.sort(newList, (song1, song2) -> Integer.compare(song2.getLuotNghe(), song1.getLuotNghe()));
        addAll(newList);
        notifyDataSetChanged();
    }
}
