package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model;

import java.io.Serializable;

public class BaiHat implements Serializable {
    public int IdBaiHat;
    public String TenBaiHat;
    public Integer AnhBaiHat;
    public Integer DuongDan;
    public int IdAlbum;
    public int IdCaSi;
    public int LuotNghe;
    public int IdTheLoai;
    public BaiHat() {
    }
    public BaiHat(int idBaiHat, String tenBaiHat, Integer anhBaiHat, Integer duongDan, int idAlbum, int idCaSi) {
        IdBaiHat = idBaiHat;
        TenBaiHat = tenBaiHat;
        AnhBaiHat = anhBaiHat;
        DuongDan = duongDan;
        IdAlbum = idAlbum;
        IdCaSi = idCaSi;
    }

    public int getIdBaiHat() {
        return IdBaiHat;
    }

    public void setIdBaiHat(int idBaiHat) {
        IdBaiHat = idBaiHat;
    }

    public String getTenBaiHat() {
        return TenBaiHat;
    }

    public void setTenBaiHat(String tenBaiHat) {
        TenBaiHat = tenBaiHat;
    }

    public Integer getAnhBaiHat() {
        return AnhBaiHat;
    }

    public void setAnhBaiHat(Integer anhBaiHat) {
        AnhBaiHat = anhBaiHat;
    }

    public Integer getDuongDan() {
        return DuongDan;
    }
    public void setDuongDan(Integer duongDan) {
        DuongDan = duongDan;
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

    public int getLuotNghe() {
        return LuotNghe;
    }

    public void setLuotNghe(int luotNghe) {
        LuotNghe = luotNghe;
    }

    public int getIdTheLoai() {
        return IdTheLoai;
    }
    public void setIdTheLoai(int idTheLoai) {
        IdTheLoai = idTheLoai;
    }
}
