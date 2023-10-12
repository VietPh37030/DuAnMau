package anhpvph37030.fpoly.duanmau.Model;

public class ThanhVien {
    private int matv;
    private String tenTV;
    private int namSinh;

    public ThanhVien() {
    }

    public ThanhVien( String tenTV, int namSinh) {
        this.tenTV = tenTV;
        this.namSinh = namSinh;
    }

    public int getMatv() {
        return matv;
    }

    public void setMatv(int matv) {
        this.matv = matv;
    }

    public String getTenTV() {
        return tenTV;
    }

    public void setTenTV(String tenTV) {
        this.tenTV = tenTV;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }
}
