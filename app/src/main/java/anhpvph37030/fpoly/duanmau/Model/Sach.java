package anhpvph37030.fpoly.duanmau.Model;

public class Sach {
    private int masach;
    private String tenSach;
    private int tienThue;
    private String loaiSach;

    public Sach() {
    }

    public Sach( String tenSach, int tienThue, String loaiSach) {
        this.tenSach = tenSach;
        this.tienThue = tienThue;
        this.loaiSach = loaiSach;
    }

    public int getMasach() {
        return masach;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getTienThue() {
        return tienThue;
    }

    public void setTienThue(int tienThue) {
        this.tienThue = tienThue;
    }

    public String getLoaiSach() {
        return loaiSach;
    }

    public void setLoaiSach(String loaiSach) {
        this.loaiSach = loaiSach;
    }
}
