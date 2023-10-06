package anhpvph37030.fpoly.duanmau.Model;

public class Sach {
    private int maSach;
    private String tenSach;
    private int giaThue;
    private String image;
    private int maloaisach;
    private String tenloaisach;



    public Sach() {
    }

    public Sach(int maSach, String tenSach, int giaThue, String image, int maloaisach) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.giaThue = giaThue;
        this.image = image;
        this.maloaisach = maloaisach;
    }
    public Sach(int maSach, String tenSach, int giaThue, String image, int maloaisach, String tenloaisach) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.giaThue = giaThue;
        this.image = image;
        this.maloaisach = maloaisach;
        this.tenloaisach=tenloaisach;
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

    public int getMaloaisach() {
        return maloaisach;
    }

    public void setMaloaisach(int maloaisach) {
        this.maloaisach = maloaisach;
    }
    public String getTenloaisach() {
        return tenloaisach;
    }

    public void setTenloaisach(String tenloaisach) {
        this.tenloaisach = tenloaisach;
    }
}
