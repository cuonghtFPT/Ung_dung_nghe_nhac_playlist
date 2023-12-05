package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model;

public class TheLoai {
    public int IdTheLoai;
    public String TenTheLoai;

    public TheLoai() {
    }

    public TheLoai(int idTheLoai, String tenTheLoai) {
        IdTheLoai = idTheLoai;
        TenTheLoai = tenTheLoai;
    }

    public int getIdTheLoai() {
        return IdTheLoai;
    }

    public void setIdTheLoai(int idTheLoai) {
        IdTheLoai = idTheLoai;
    }

    public String getTenTheLoai() {
        return TenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        TenTheLoai = tenTheLoai;
    }
}
