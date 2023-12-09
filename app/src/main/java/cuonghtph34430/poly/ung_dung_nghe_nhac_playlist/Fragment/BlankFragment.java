package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Adapter.AlbumAdapter;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Adapter.ArtistAdapter;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Adapter.ListSongAdapter;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Activity.Choinhac;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.DAO.AlbumDAO;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.DAO.BaiHatDAO;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.DAO.CaSiDAO;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.Album;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.BaiHat;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.CaSi;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.R;


public class BlankFragment extends Fragment implements ArtistAdapter.OnArtistGlideClickListener, AlbumAdapter.OnAlbumGlideClickListener{
    private ViewPager2 viewPager;
    private final int[] images = {R.drawable.anh_10, R.drawable.anh_11, R.drawable.anh_12};//Tôi đặt ảnh slider ở đây
    private int currentPage = 0;
    private Timer timer;
    private final long DELAY_MS = 2000;
    private final long PERIOD_MS = 3000;
    RecyclerView recycle1,recycle2;
    Context context;
    ArtistAdapter artistAdapter;
    AlbumAdapter albumAdapter;
    ListSongAdapter nhacAdapter;
    List<BaiHat> listN;
    ListView lv_nhac;
    View view;
    TextView btnXemAlbum,btnXemNgheSi;
    CaSiDAO caSiDAO;
    AlbumDAO albumDAO;
    int start = 0;
    int limit = 20;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        viewPager = view.findViewById(R.id.viewPager);
        ImageAdapter adapter = new ImageAdapter(images);
        viewPager.setAdapter(adapter);
        btnXemAlbum = view.findViewById(R.id.btnXemAlbum);
        btnXemNgheSi = view.findViewById(R.id.btnXemNgheSi);
        recycle1 = view.findViewById(R.id.recycle1);
        recycle2 = view.findViewById(R.id.recycle2);
        btnXemAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragmentB = new BlankFragment5();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment1, fragmentB);
                transaction.addToBackStack(null); // Optional: Adds the transaction to the back stack
                transaction.commit();
            }
        });
        btnXemNgheSi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragmentB = new BlankFragment4();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment1, fragmentB);
                transaction.addToBackStack(null); // Optional: Adds the transaction to the back stack
                transaction.commit();
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recycle1.setLayoutManager(layoutManager);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recycle2.setLayoutManager(linearLayoutManager);
        caSiDAO = new CaSiDAO(getContext());
        List<CaSi> artistUrls = caSiDAO.getAll();
        artistAdapter = new ArtistAdapter(artistUrls,getContext(),this);
        recycle2.setAdapter(artistAdapter);
        albumDAO = new AlbumDAO(getContext());
        List<Album> albumUrls = albumDAO.getAlbum();
        albumAdapter = new AlbumAdapter(getContext(), albumUrls, this);
        recycle1.setAdapter(albumAdapter);

        lv_nhac = view.findViewById(R.id.listView);
        BaiHatDAO baiHatDAO = new BaiHatDAO(getContext());
        context = getContext(); // hoặc getActivity()
        listN = baiHatDAO.getAll();
        List<BaiHat> displayedItemList = listN.subList(start, Math.min(listN.size(), start + limit));
        nhacAdapter = new ListSongAdapter(context,displayedItemList);
        lv_nhac.setAdapter(nhacAdapter);

        lv_nhac.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BaiHat nhac = listN.get(position);
                Intent openMusicPlayer = new Intent(getContext(), Choinhac.class);
                openMusicPlayer.putExtra("nhac", nhac); // Truyền đối tượng BaiHat thay vì chỉ truyền file nhạc
                startActivity(openMusicPlayer);
            }
        });

        if (!checkPermission()) {
            requestPermission();
        }

        String[] projection = {
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DURATION
        };
        // Auto-scrolling task
        final Handler handler = new Handler();
        final Runnable update = () -> {
            if (currentPage == images.length) {
                currentPage = 0;
            }
            viewPager.setCurrentItem(currentPage++, true);
        };

        timer = new Timer(); // Timer to schedule auto-scrolling task
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, DELAY_MS, PERIOD_MS);
        return view;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    public void onAlbumGlideClick(int albumId, String albumTitle, int albumCover) {
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

    @Override
    public void onArtistGlideClick(int artistId, String artistTitle, int artistCover) {
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

    private class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

        private final int[] images;

        ImageAdapter(int[] images) {
            this.images = images;
        }

        @NonNull
        @Override
        public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
            return new ImageViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
            holder.imageView.setImageResource(images[position]);
        }

        @Override
        public int getItemCount() {
            return images.length;
        }

        class ImageViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;

            ImageViewHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.imageView);
            }
        }
    }
    boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }
    void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(getContext(), "READ PERMISSION IS REQUIRED, PLEASE ALLOW FROM SETTINGS", Toast.LENGTH_SHORT).show();
        } else
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 123);
    }
//    @Override
//    public void onResume() {
//        super.onResume();
//        if(recyclerView!=null){
//            recyclerView.setAdapter(new MusicListAdapter(getContext(), songList));
//        }
//    }
}