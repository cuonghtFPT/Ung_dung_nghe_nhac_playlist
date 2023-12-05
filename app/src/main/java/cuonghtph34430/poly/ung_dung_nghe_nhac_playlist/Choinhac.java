package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.DAO.BaiHatDAO;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.DAO.CaSiDAO;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.DAO.LichSuDAO;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.BaiHat;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.CaSi;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.LichSu;

public class Choinhac extends AppCompatActivity {
    BaiHatDAO baiHatDAO;
    TextView tvTime, tvDuration, tvTacgia, tvNhac;
    SeekBar sbTime, sbVolume;
    ImageView imgPlay;
    ImageButton imgLui,imgTien;
    CaSiDAO caSiDAO;
    MediaPlayer musicPlayer;
    ImageView anh_nhac;
    BaiHat selectedSong;
    boolean isPlaying = false;
    Handler handler;
    CaSi caSi;
    List<BaiHat> songList; // Danh sách các bài hát
    int currentSongIndex = 0; // Vị trí hiện tại trong danh sách
    LichSuDAO lichSuDAO;
    LichSu lichSu;
    Date currentTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choi_nhac);
        tvNhac = findViewById(R.id.tv_tenNhac);
        tvTacgia = findViewById(R.id.tv_tenTacgia);
        tvTime = findViewById(R.id.tv_time);
        tvDuration = findViewById(R.id.tv_duration);
        anh_nhac = findViewById(R.id.anh_nhac);
        sbTime = findViewById(R.id.sb_time);
        sbVolume = findViewById(R.id.sb_volume);
        imgPlay = findViewById(R.id.img_play);
        imgLui = findViewById(R.id.imgLui);
        imgTien = findViewById(R.id.imgTien);
        baiHatDAO = new BaiHatDAO(getApplicationContext());

        // Khởi tạo songList nếu chưa được khởi tạo
        if (songList == null) {
            songList = baiHatDAO.getAll();
            Log.e("Gyaaaaatttt",String.valueOf(songList.size()));
        }
        imgLui.setOnClickListener(v -> {
            if (musicPlayer != null && !songList.isEmpty()) {
                currentSongIndex = (currentSongIndex - 1 + songList.size()) % songList.size();
                playSelectedSong();
            }
        });

        imgTien.setOnClickListener(v -> {
            if (musicPlayer != null && !songList.isEmpty()) {
                currentSongIndex = (currentSongIndex + 1) % songList.size();
                playSelectedSong();
            }
        });

        imgPlay.setOnClickListener(v -> {
            if (musicPlayer != null) {
                if (musicPlayer.isPlaying()) {
                    musicPlayer.pause();
                    isPlaying = false;
                } else {
                    musicPlayer.start();
                    isPlaying = true;
                }
                updatePlayButton();
            }
        });

        releaseMediaPlayer();

        startUpdatingSeekBar();

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("nhac")) {
            selectedSong = (BaiHat) intent.getSerializableExtra("nhac");
            int albumId = selectedSong.getIdAlbum();
            for (int i = 0; i < songList.size(); i++) {
                if (songList.get(i).getIdBaiHat() == selectedSong.getIdBaiHat()) {
                    currentSongIndex = i;
                    break;
                }
            }
            baiHatDAO.tangSoLuotNghe(selectedSong);

            List<BaiHat> filteredList = new ArrayList<>();
            for (BaiHat song : songList) {
                if (song.getIdAlbum() == albumId) {
                    filteredList.add(song);
                }
            }

            caSiDAO = new CaSiDAO(getApplicationContext());
            caSi = caSiDAO.getID(String.valueOf(selectedSong.getIdCaSi()));

            lichSuDAO = new LichSuDAO(getApplicationContext());
            lichSu = new LichSu();
            lichSu.setIdBaiHat(selectedSong.getIdBaiHat());
            currentTime = Calendar.getInstance().getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String formattedTime = sdf.format(currentTime);
            lichSu.setIdCaSi(caSi.getIdCaSi());
            lichSu.setThoiGianNghe(formattedTime);
            lichSuDAO.playSong(lichSu);

            tvNhac.setText(selectedSong.getTenBaiHat());
            tvTacgia.setText(caSi.getTenCaSi());
            anh_nhac.setImageResource(selectedSong.getAnhBaiHat());
            createAndStartMediaPlayer(selectedSong.getDuongDan());

            String duration = millisecondsToString(musicPlayer.getDuration());
            if (tvDuration != null) {
                tvDuration.setText(duration);
            }

            sbTime.setMax(musicPlayer.getDuration());
        }
        if (intent != null && intent.hasExtra("filteredSongs")) {
            List<BaiHat> filteredList = (List<BaiHat>) intent.getSerializableExtra("filteredSongs");
            if (!filteredList.isEmpty()) {
                selectedSong = filteredList.get(0);
                tvNhac.setText(selectedSong.getTenBaiHat());
                createAndStartMediaPlayer(selectedSong.getDuongDan());
            }
        }
        sbVolume.setProgress(50);
        sbVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float volume = progress / 100f;
                musicPlayer.setVolume(volume, volume);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        sbTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    musicPlayer.seekTo(progress);
                    seekBar.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (musicPlayer != null && !musicPlayer.isPlaying() && isPlaying) {
            musicPlayer.start();
            updatePlayButton();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (musicPlayer != null && musicPlayer.isPlaying()) {
            musicPlayer.pause();
        }
    }

    @Override
    public void onBackPressed() {
        releaseMediaPlayer();
        finish();
    }

    private void releaseMediaPlayer() {
        if (musicPlayer != null) {
            musicPlayer.stop();
            musicPlayer.release();
            musicPlayer = null;
        }
    }

    private void createAndStartMediaPlayer(int songResource) {
        musicPlayer = MediaPlayer.create(this, songResource);
        musicPlayer.setLooping(true);
        musicPlayer.setVolume(0.5f, 0.5f);
        musicPlayer.start();
        isPlaying = true;
        updatePlayButton();
    }

    private void startUpdatingSeekBar() {
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateSeekBar();
                handler.postDelayed(this, 1000);
            }
        }, 1000);
    }

    private void updateSeekBar() {
        if (musicPlayer != null && musicPlayer.isPlaying()) {
            int current = musicPlayer.getCurrentPosition();
            sbTime.setProgress(current);
            tvTime.setText(millisecondsToString(current));
        }
    }

    public String millisecondsToString(int time) {
        String elapsedTime = "";
        int minutes = time/ 1000 / 60;
        int seconds = time / 1000 % 60;
        elapsedTime = minutes + ":";
        if (seconds < 10) {
            elapsedTime += "0";
        }
        elapsedTime += seconds;

        return elapsedTime;
    }

    private void updatePlayButton() {
        if (isPlaying) {
            imgPlay.setImageResource(R.drawable.baseline_pause_circle_outline_24);
        } else {
            imgPlay.setImageResource(R.drawable.baseline_play_circle_outline_24);
        }
    }

    private void playSelectedSong() {
        if (!songList.isEmpty()) {
            if (currentSongIndex >= 0 && currentSongIndex < songList.size()) {
                selectedSong = songList.get(currentSongIndex);
                for (int i = 0; i < songList.size(); i++) {
                    if (songList.get(i).getIdBaiHat() == selectedSong.getIdBaiHat()) {
                        currentSongIndex = i;
                        break;
                    }
                }
                baiHatDAO.tangSoLuotNghe(selectedSong);
                // Set the details of the currently playing song
                tvNhac.setText(selectedSong.getTenBaiHat());
                caSiDAO = new CaSiDAO(getApplicationContext());
                caSi = caSiDAO.getID(String.valueOf(selectedSong.getIdCaSi()));
                tvTacgia.setText(caSi.getTenCaSi());
                anh_nhac.setImageResource(selectedSong.getAnhBaiHat());

                lichSuDAO = new LichSuDAO(getApplicationContext());
                lichSu = new LichSu();
                lichSu.setIdBaiHat(selectedSong.getIdBaiHat());
                currentTime = Calendar.getInstance().getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String formattedTime = sdf.format(currentTime);
                lichSu.setIdCaSi(caSi.getIdCaSi());
                lichSu.setThoiGianNghe(formattedTime);
                lichSuDAO.playSong(lichSu);

                releaseMediaPlayer();
                createAndStartMediaPlayer(selectedSong.getDuongDan());

                String duration = millisecondsToString(musicPlayer.getDuration());
                if (tvDuration != null) {
                    tvDuration.setText(duration);
                }
                sbTime.setMax(musicPlayer.getDuration());
            } else {
                currentSongIndex = 0;
                playSelectedSong();
            }
        }
    }
}