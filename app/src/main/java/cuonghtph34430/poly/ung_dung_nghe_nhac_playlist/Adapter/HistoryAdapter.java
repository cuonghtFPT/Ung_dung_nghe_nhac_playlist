package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.DAO.BaiHatDAO;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.DAO.CaSiDAO;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.BaiHat;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.CaSi;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.LichSu;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.R;

public class HistoryAdapter extends BaseAdapter {
    private final List<LichSu> lichSus;
    private final Context context;

    public HistoryAdapter(List<LichSu> lichSus, Context context) {
        this.lichSus = lichSus;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lichSus.size();
    }

    @Override
    public Object getItem(int position) {
        return lichSus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_history,parent,false);
        ImageView imageMusicCover = view.findViewById(R.id.imageMusicCover);
        TextView textTitle = view.findViewById(R.id.textTitle);
        TextView textListening = view.findViewById(R.id.textListening);
        TextView textArtist = view.findViewById(R.id.textArtist);

        BaiHatDAO baiHatDAO = new BaiHatDAO(context);
        BaiHat baiHat = baiHatDAO.getID(String.valueOf(lichSus.get(position).getIdBaiHat()));

        CaSiDAO caSiDAO = new CaSiDAO(context);
        CaSi caSi = caSiDAO.getID(String.valueOf(lichSus.get(position).getIdCaSi()));

        imageMusicCover.setImageResource(baiHat.getAnhBaiHat());
        textTitle.setText(baiHat.getTenBaiHat());
        textArtist.setText(caSi.getTenCaSi());
        textListening.setText(lichSus.get(position).getThoiGianNghe());

        return view;
    }
}
