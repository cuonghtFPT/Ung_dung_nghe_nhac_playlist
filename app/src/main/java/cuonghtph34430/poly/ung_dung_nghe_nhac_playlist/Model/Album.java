package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model;

public class

Album {
    private int IdAlbum;
    private String TenAlbum;
    private int AnhAlbum;
    private int IdCaSi;
    public Album() {
    }

    public Album(int idAlbum, String tenAlbum, int anhAlbum, int idCaSi) {
        IdAlbum = idAlbum;
        TenAlbum = tenAlbum;
        AnhAlbum = anhAlbum;
        IdCaSi = idCaSi;
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

    public int getIdCaSi() {
        return IdCaSi;
    }

    public void setIdCaSi(int idCaSi) {
        IdCaSi = idCaSi;
    }
}

