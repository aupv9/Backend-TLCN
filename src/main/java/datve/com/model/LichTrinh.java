package datve.com.model;

public class LichTrinh {

    private String thoigiandi;
    private String diemdi;

    public String getThoigiandi() {
        return thoigiandi;
    }

    public void setThoigiandi(String thoigiandi) {
        this.thoigiandi = thoigiandi;
    }

    public String getDiemdi() {
        return diemdi;
    }

    public void setDiemdi(String diemdi) {
        this.diemdi = diemdi;
    }

    public String getDiachi() {
        return diachi;
    }

    public LichTrinh() {

    }

    public LichTrinh(String thoigiandi, String diemdi, String diachi, int tinh) {
        this.thoigiandi = thoigiandi;
        this.diemdi = diemdi;
        this.diachi = diachi;
        this.tinh = tinh;
    }


    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public int getTinh() {
        return tinh;
    }

    public void setTinh(int tinh) {
        this.tinh = tinh;
    }

    private String diachi;
    private int tinh;
}
