package anhpvph37030.fpoly.duanmau.Model;

public class Sach {
    private int maSach;
    private String tenSach;
    private int giaThue;
    private String image;
    private String maloaisach;
    private String tenloaisach;
    private int soluongdamuon;

    public Sach() {
    }

    public Sach(int maSach, String tenSach, int giaThue, String image, String maloaisach, String tenloaisach, int soluongdamuon) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.giaThue = giaThue;
        this.image = image;
        this.maloaisach = maloaisach;
        this.tenloaisach = tenloaisach;
        this.soluongdamuon = soluongdamuon;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(int giaThue) {
        this.giaThue = giaThue;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMaloaisach() {
        return maloaisach;
    }

    public void setMaloaisach(String maloaisach) {
        this.maloaisach = maloaisach;
    }

    public String getTenloaisach() {
        return tenloaisach;
    }

    public void setTenloaisach(String tenloaisach) {
        this.tenloaisach = tenloaisach;
    }

    public int getSoluongdamuon() {
        return soluongdamuon;
    }

    public void setSoluongdamuon(int soluongdamuon) {
        this.soluongdamuon = soluongdamuon;
    }
}
