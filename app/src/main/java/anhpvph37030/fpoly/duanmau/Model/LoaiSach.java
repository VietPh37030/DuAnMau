package anhpvph37030.fpoly.duanmau.Model;

public class LoaiSach {
    private int maloai;
    private String tenloaisach;

    public LoaiSach() {
    }

    public LoaiSach(int maloai, String tenloaisach) {
        this.maloai = maloai;
        this.tenloaisach = tenloaisach;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }

    public String getTenloaisach() {
        return tenloaisach;
    }

    public void setTenloaisach(String tenloaisach) {
        this.tenloaisach = tenloaisach;
    }
}
