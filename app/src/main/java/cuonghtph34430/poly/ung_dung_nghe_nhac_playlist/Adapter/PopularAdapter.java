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

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.BaiHat;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.R;

public class PopularAdapter extends BaseAdapter {
    private final List<BaiHat> luotNgheList;
    private final Context context;

    public PopularAdapter(List<BaiHat> luotNgheList, Context context) {
        this.luotNgheList = luotNgheList;
        this.context = context;
    }
    @Override
    public int getCount() {
        return luotNgheList.size();
    }

    @Override
    public Object getItem(int position) {
        return luotNgheList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.item_popular,parent,false);
        TextView textTitle = convertView.findViewById(R.id.textTitle);
        TextView textListening = convertView.findViewById(R.id.textListening);
        ImageView imageMusicCover = convertView.findViewById(R.id.imageMusicCover);
        textTitle.setText(luotNgheList.get(position).getTenBaiHat());
        textListening.setText("Lượt Nghe: "+luotNgheList.get(position).getLuotNghe());
        imageMusicCover.setImageResource(luotNgheList.get(position).getAnhBaiHat());
        return convertView;
    }
    public void updateAndSortList(List<BaiHat> newList) {
        Collections.sort(newList, (song1, song2) -> Integer.compare(song2.getLuotNghe(), song1.getLuotNghe()));
        addAll(newList);
        notifyDataSetChanged();
    }
}
