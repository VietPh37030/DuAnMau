package anhpvph37030.fpoly.duanmau.Model;

public class PhieuMuon {
    private int mapm;
    private String matt;
    private int matv;
    private int masach;
    private int tienthue;
    private int trasach;

    public PhieuMuon() {
    }

    public PhieuMuon(int mapm, String matt, int matv, int masach, int tienthue, int trasach) {
        this.mapm = mapm;
        this.matt = matt;
        this.matv = matv;
        this.masach = masach;
        this.tienthue = tienthue;
        this.trasach = trasach;
    }

    public int getMapm() {
        return mapm;
    }

    public void setMapm(int mapm) {
        this.mapm = mapm;
    }

    public String getMatt() {
        return matt;
    }

    public void setMatt(String matt) {
        this.matt = matt;
    }

    public int getMatv() {
        return matv;
    }

    public void setMatv(int matv) {
        this.matv = matv;
    }

    public int getMasach() {
        return masach;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

    public int getTienthue() {
        return tienthue;
    }

    public void setTienthue(int tienthue) {
        this.tienthue = tienthue;
    }

    public int getTrasach() {
        return trasach;
    }

    public void setTrasach(int trasach) {
        this.trasach = trasach;
    }
}
