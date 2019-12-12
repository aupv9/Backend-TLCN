package datve.com.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import datve.com.model.LichTrinh;
import datve.com.model.Ghe;

//@JsonSubTypes({
//        @JsonSubTypes.Type(value = LichTrinh.class, name = "lichtrinh")})
public class Xe {

    private int _id;
    private String loaixe;
    private String nhaxe;
    private String giodi;

    public Xe(int _id, String loaixe, String nhaxe, String giodi, boolean deleted, String loaidi, String chuyendi, int danhgia, ArrayList<Integer> tinhdiqua, String hinhanh, String ngaydi, String chinhsachhuyve, ArrayList<LichTrinh> lichtrinh, ArrayList<Ghe> danhsachghe) {
        this._id = _id;
        this.loaixe = loaixe;
        this.nhaxe = nhaxe;
        this.giodi = giodi;
        this.deleted = deleted;
        this.loaidi = loaidi;
        this.chuyendi = chuyendi;
        this.danhgia = danhgia;
        this.tinhdiqua = tinhdiqua;
        this.hinhanh = hinhanh;
        this.ngaydi = ngaydi;
        this.chinhsachhuyve = chinhsachhuyve;
        this.lichtrinh = lichtrinh;
        this.danhsachghe = danhsachghe;
    }

    private boolean deleted;

    public Xe() {

    }

    public ArrayList<Integer> getTinhdiqua() {
        return tinhdiqua;
    }

    public void setTinhdiqua(ArrayList<Integer> tinhdiqua) {
        this.tinhdiqua = tinhdiqua;
    }

    public String getGiodi() {
        return giodi;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void setGiodi(String giodi) {
        this.giodi = giodi;
    }

    private String loaidi;
    private String chuyendi;
    private int danhgia;
    private ArrayList<Integer> tinhdiqua;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getLoaixe() {
        return loaixe;
    }

    public void setLoaixe(String loaixe) {
        this.loaixe = loaixe;
    }

    public String getNhaxe() {
        return nhaxe;
    }

    public void setNhaxe(String nhaxe) {
        this.nhaxe = nhaxe;
    }

    public String getLoaidi() {
        return loaidi;
    }

    public void setLoaidi(String loaidi) {
        this.loaidi = loaidi;
    }

    public String getChuyendi() {
        return chuyendi;
    }

    public void setChuyendi(String chuyendi) {
        this.chuyendi = chuyendi;
    }

    public int getDanhgia() {
        return danhgia;
    }

    public void setDanhgia(int danhgia) {
        this.danhgia = danhgia;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getNgaydi() {
        return ngaydi;
    }

    public void setNgaydi(String ngaydi) {
        this.ngaydi = ngaydi;
    }

    public String getChinhsachhuyve() {
        return chinhsachhuyve;
    }

    public void setChinhsachhuyve(String chinhsachhuyve) {
        this.chinhsachhuyve = chinhsachhuyve;
    }

    public ArrayList<LichTrinh> getLichtrinh() {
        return lichtrinh;
    }

    public void setLichtrinh(ArrayList<LichTrinh> lichtrinh) {
        this.lichtrinh = lichtrinh;
    }

    public ArrayList<Ghe> getDanhsachghe() {
        return danhsachghe;
    }

    public void setDanhsachghe(ArrayList<Ghe> danhsachghe) {
        this.danhsachghe = danhsachghe;
    }

    private String hinhanh;
    private String ngaydi;
    private String chinhsachhuyve;
    private ArrayList<LichTrinh> lichtrinh;
    private ArrayList<Ghe> danhsachghe;


}
