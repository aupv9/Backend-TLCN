package datve.com.model;

public class Ghe {
    private int maGhe;
    private int stt;

    public int getMaGhe() {
        return maGhe;
    }

    public void setMaGhe(int maGhe) {
        this.maGhe = maGhe;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public boolean isDat() {
        return dat;
    }

    public Ghe() {
    }

    public Ghe(int maGhe, int stt, int gia, boolean dat) {
        this.maGhe = maGhe;
        this.stt = stt;
        this.gia = gia;
        this.dat = dat;
    }

    public void setDat(boolean dat) {
        this.dat = dat;
    }

    private int gia;
    private boolean dat;

}
