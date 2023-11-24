package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model;

public class Album {
    private int IdAlbum;
    private String TenAlbum;
    private int AnhAlbum;

    public Album() {
    }

    public Album(int idAlbum, String tenAlbum, int anhAlbum) {
        IdAlbum = idAlbum;
        TenAlbum = tenAlbum;
        AnhAlbum = anhAlbum;
    }

    public int getIdAlbum() {
        return IdAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        IdAlbum = idAlbum;
    }

    public String getTenAlbum() {
        return TenAlbum;
    }

    public void setTenAlbum(String tenAlbum) {
        TenAlbum = tenAlbum;
    }

    public int getAnhAlbum() {
        return AnhAlbum;
    }

    public void setAnhAlbum( int anhAlbum) {
        AnhAlbum = anhAlbum;
    }
}

