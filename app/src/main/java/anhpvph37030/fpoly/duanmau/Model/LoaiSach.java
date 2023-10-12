package anhpvph37030.fpoly.duanmau.Model;

public class LoaiSach {
    private int maloaisach;
    private String tenLoai;

    public LoaiSach() {
    }

    public LoaiSach(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public int getMaloaisach() {
        return maloaisach;
    }

    public void setMaloaisach(int maloaisach) {
        this.maloaisach = maloaisach;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}
