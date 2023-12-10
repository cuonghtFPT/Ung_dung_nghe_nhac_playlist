package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model;

public class NguoiDung {
    public String TenDangKy;
    public String TenNguoiDung;
    public String MatKhau;

    public NguoiDung() {
    }

    public NguoiDung(String tenDangKy, String tenNguoiDung, String matKhau) {
        TenDangKy = tenDangKy;
        TenNguoiDung = tenNguoiDung;
        MatKhau = matKhau;
    }

    public String getTendangnhap() {
        return TenDangKy;
    }

    public void setTendangnhap(String tendangnhap) {
        this.TenDangKy = tendangnhap;
    }

    public String getUsername() {
        return TenNguoiDung;
    }

    public void setUsername(String username) {
        this.TenNguoiDung = username;
    }

    public String getPassword() {
        return MatKhau;
    }

    public void setPassword(String password) {
        this.MatKhau = password;
    }
}
