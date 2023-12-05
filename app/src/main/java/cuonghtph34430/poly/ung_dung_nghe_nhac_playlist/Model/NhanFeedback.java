package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model;

public class NhanFeedback {
    private int mafeedback;
    private String fullname;
    private String number;
    private String email;
    private String comment;

    public NhanFeedback(int mafeedback,String fullname, String number, String email, String comment) {
        this.mafeedback=mafeedback;
        this.fullname = fullname;
        this.number = number;
        this.email = email;
        this.comment = comment;
    }

    public NhanFeedback(String fullname, String number, String email, String comment) {
        this.fullname = fullname;
        this.number = number;
        this.email = email;
        this.comment = comment;
    }

    public NhanFeedback() {
    }

    public int getMafeedback() {
        return mafeedback;
    }

    public void setMafeedback(int mafeedback) {
        this.mafeedback = mafeedback;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
