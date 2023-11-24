package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model;

import java.io.Serializable;

public class ListSong implements Serializable {
    public String Song;
    public String Singer;
    public Integer Picture;
    public Integer File;
    public int IdAlbum;
    public int IdCaSi;

    public ListSong(String song, String singer, Integer picture, Integer file, int idAlbum, int idCaSi) {
        Song = song;
        Singer = singer;
        Picture = picture;
        File = file;
        IdAlbum = idAlbum;
        IdCaSi = idCaSi;
    }

    public String getSong() {
        return Song;
    }

    public void setSong(String song) {
        Song = song;
    }

    public String getSinger() {
        return Singer;
    }

    public void setSinger(String singer) {
        Singer = singer;
    }

    public Integer getPicture() {
        return Picture;
    }

    public void setPicture(Integer picture) {
        Picture = picture;
    }

    public Integer getFile() {
        return File;
    }

    public void setFile(Integer file) {
        File = file;
    }

    public int getIdAlbum() {
        return IdAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        IdAlbum = idAlbum;
    }

    public int getIdCaSi() {
        return IdCaSi;
    }

    public void setIdCaSi(int idCaSi) {
        IdCaSi = idCaSi;
    }
}
