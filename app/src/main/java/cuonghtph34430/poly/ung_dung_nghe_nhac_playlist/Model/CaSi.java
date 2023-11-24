package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model;

public class CaSi {
    private int IdCaSi;
    private String TenCaSi;
    private int AnhCaSi;
    public CaSi() {
    }
    public CaSi(int idCaSi, String tenCaSi, int anhCaSi) {
        IdCaSi = idCaSi;
        TenCaSi = tenCaSi;
        AnhCaSi = anhCaSi;
    }
    public int getIdCaSi() {
        return IdCaSi;
    }

    public void setIdCaSi(int idCaSi) {
        IdCaSi = idCaSi;
    }

    public String getTenCaSi() {
        return TenCaSi;
    }

    public void setTenCaSi(String tenCaSi) {
        TenCaSi = tenCaSi;
    }

    public int getAnhCaSi() {
        return AnhCaSi;
    }

    public void setAnhCaSi(int anhCaSi) {
        AnhCaSi = anhCaSi;
    }

}
