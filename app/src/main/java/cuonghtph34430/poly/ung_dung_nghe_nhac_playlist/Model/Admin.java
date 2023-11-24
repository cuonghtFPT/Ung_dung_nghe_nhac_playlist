package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model;

public class Admin {

    private String maadmin;
    private String hoten;
    private String matkhau;

    public Admin(String maadmin, String hoten, String matkhau) {
        this.maadmin = maadmin;
        this.hoten = hoten;
        this.matkhau = matkhau;
    }

    public Admin() {
    }

    public String getMaadmin() {
        return maadmin;
    }

    public void setMaadmin(String maadmin) {
        this.maadmin = maadmin;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }
}
