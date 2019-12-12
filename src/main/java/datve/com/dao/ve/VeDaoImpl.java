package datve.com.dao.ve;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import datve.com.config.MongoFactory;
import datve.com.model.Ghe;
import datve.com.model.LichTrinh;
import datve.com.model.Ve;
import datve.com.model.Xe;
import datve.com.service.ve.VeService;
import org.apache.log4j.Logger;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Filters.in;


@Repository(value = "veDao")
public class VeDaoImpl implements VeDao {

    private static Logger log = Logger.getLogger(VeDaoImpl.class);
    //get collection từ database

    private MongoCollection coll = MongoFactory.getCollection("datvexe", "ve");

    @Override
    public boolean addVe(Ve ve) {
        try {
            coll.insertOne(
                    new Document("_id", ve.get_id())
                            .append("hangxe", ve.getHangxe())
                            .append("noidon", ve.getNoidon())
                            .append("giodon", ve.getGiodon())
                            .append("noitra", ve.getNoitra())
                            .append("giotra", ve.getGiotra())
                            .append("soghe", ve.getSoghe())
                            .append("tuyenduong", ve.getTuyenduong())
                            .append("giave", ve.getGiave())
                            .append("phuthu", ve.getPhuthu())
                            .append("hinhthucthanhtoan", ve.getHinhthucthanhtoan())
                            .append("huy", ve.getHuy())
                            .append("ngaydat", ve.getNgaydat())
                            .append("tuyenduong", ve.getTuyenduong())
                            .append("tinhtrang", ve.getTinhtrang())
                            .append("sdt", ve.getSdt())
                            .append("email", ve.getEmail())
            );
            return true;
        } catch (MongoException e) {
            return false;
        }
    }

    @Override
    public boolean cancelVe(Ve ve) {
        try {
            coll.updateOne(eq("_id", ve.get_id()), new Document("$set",
                    new Document("huy", true)));
            return true;
        } catch (MongoException e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public Ve searchVe(String _id, String phone) {

        try {
            MongoCursor<Document> cursor = coll.find(and(eq("_id", _id), eq("sdt", phone))).iterator();
            Ve ve = new Ve();
            while (cursor.hasNext()) {

                Document doc = cursor.next();
                ve.set_id(doc.get("_id").toString());
                ve.setHangxe(doc.get("hangxe").toString());
                ve.setNoidon(doc.get("noidon").toString());
                ve.setGiodon(doc.get("giodon").toString());
                ve.setNoitra(doc.get("noitra").toString());
                ve.setGiotra(doc.get("giotra").toString());
                ve.setSoghe(doc.get("soghe").toString());
                ve.setTuyenduong(doc.get("tuyenduong").toString());
                ve.setGiave(Integer.parseInt(doc.get("giave").toString()));
                ve.setPhuthu(Integer.parseInt(doc.get("phuthu").toString()));
                ve.setHinhthucthanhtoan(doc.get("hinhthucthanhtoan").toString());
                ve.setTinhtrang(Boolean.parseBoolean(doc.get("tinhtrang").toString()));
                ve.setHuy(Boolean.parseBoolean(doc.get("huy").toString()));
                ve.setNgaydat(doc.get("ngaydat").toString());
            }
            return ve.get_id() != null ? ve:null;
        } catch (MongoException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public List<Ve> listVeToDate(String date) {

        return null;
    }
}
