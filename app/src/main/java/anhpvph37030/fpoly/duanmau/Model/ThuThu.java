package anhpvph37030.fpoly.duanmau.Model;

public class ThuThu {
    private String matt;
    private String tenTT;
    private String hoTen;
    private String matKhau;

    public ThuThu() {
    }

    public ThuThu(String tenTT, String hoTen, String matKhau) {
        this.tenTT = tenTT;
        this.hoTen = hoTen;
        this.matKhau = matKhau;
    }

    public String getMatt() {
        return matt;
    }

    public void setMatt(String matt) {
        this.matt = matt;
    }

    public String getTenTT() {
        return tenTT;
    }

    public void setTenTT(String tenTT) {
        this.tenTT = tenTT;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
