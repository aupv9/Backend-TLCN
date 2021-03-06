package datve.com.dao.xe;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import datve.com.config.MongoFactory;
import datve.com.model.Ghe;
import datve.com.model.LichTrinh;
import datve.com.model.Xe;
import org.apache.log4j.Logger;
import org.bson.Document;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;


@Repository("xeDao")
public class XeDaoImpl implements XeDao {
    private static Logger log = Logger.getLogger(XeDaoImpl.class);
    //get collection từ database
    // create codec registry for POJOs

//    private static CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
//            fromProviders(PojoCodecProvider.builder().automatic(true).build()));
    private MongoCollection coll = MongoFactory.getCollection("datvexe", "xe");
//    private static MongoDatabase database = MongoFactory.getMongo().getDatabase("datvexe").withCodecRegistry(pojoCodecRegistry);
//    private static MongoCollection<Xe> collection = database.getCollection("xe", Xe.class);

    public List<Xe> searchXe(int start, int end, String date) {
        /*
         * Tìm vé xe thông qua nơi đi qua và ngày đi
         * */
        MongoCursor<Document> cursor = coll.find(and(in("tinhdiqua", start), in("tinhdiqua", end), eq("ngaydi", date))).iterator();
        List<Xe> list = new ArrayList<Xe>();
        /*
         * Set dữ liệu search được vào mảng xe
         * */
        while (cursor.hasNext()) {
            Xe xe = new Xe();
            Document doc = cursor.next();
            xe.set_id(doc.get("_id").toString());
            xe.setLoaixe(doc.get("loaixe").toString());
            xe.setNhaxe(doc.get("nhaxe").toString());
            xe.setGiodi(doc.get("giodi").toString());
            xe.setLoaidi(doc.get("loaidi").toString());
            xe.setChuyendi(doc.get("chuyendi").toString());
            xe.setDanhgia(Integer.parseInt(doc.get("danhgia").toString()));
            xe.setHinhanh(doc.get("hinhanh").toString());
            xe.setNgaydi(doc.get("ngaydi").toString());
            xe.setChinhsachhuyve(doc.get("chinhsachhuyve").toString());
            ArrayList<Ghe> listGhe = new ArrayList<Ghe>();
            listGhe = (ArrayList<Ghe>) doc.get("danhsachghe");
            xe.setDanhsachghe(listGhe);
            ArrayList<LichTrinh> listLT = new ArrayList<LichTrinh>();
            listLT = (ArrayList<LichTrinh>) doc.get("lichtrinh");
            xe.setLichtrinh(listLT);
            xe.setDeleted(Boolean.parseBoolean(doc.get("deleted").toString()));
            list.add(xe);

        }
        return list;
    }

    public boolean addXe(Xe xe) {
        try {
            List<Ghe> ghes=new ArrayList<Ghe>();
            for(Ghe ghe:xe.getDanhsachghe()){
                ghes.add(ghe);
            }
            List<LichTrinh> lichtrinh=new ArrayList<>();
            for (LichTrinh lt:xe.getLichtrinh()){
                lichtrinh.add(lt);
            }
            coll.insertOne(
                    new Document("_id", xe.get_id())
                            .append("loaixe",xe.getLoaixe())
                            .append("nhaxe",xe.getNhaxe())
                            .append("loaidi",xe.getLoaidi())
                            .append("chuyendi",xe.getChuyendi())
                            .append("danhgia",xe.getDanhgia())
                            .append("hinhanh",xe.getHinhanh())
                             .append("tinhdiqua",xe.getTinhdiqua())
                             .append("giodi",xe.getGiodi())
                             .append("ngaydi",xe.getNgaydi())
                             .append("chinhsachhuyve",xe.getChinhsachhuyve())
                             .append("lichtrinh", lichtrinh)
                            .append("danhsachghe",ghes)
                              .append("deleted",false)
            );
            return true;
        } catch (MongoException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean updateXe(Xe xe) {
        try {

            List<Ghe> arrGhe=new ArrayList<Ghe>();
            for(Ghe ghe:xe.getDanhsachghe()){
                arrGhe.add(ghe);
            }
            List<LichTrinh> arrLichtrinh=new ArrayList<>();
            for (LichTrinh lt:xe.getLichtrinh()){
                arrLichtrinh.add(lt);
            }
            coll.updateOne(eq("_id", xe.get_id()), new Document("$set", new Document("loaixe", xe.getLoaixe())
                            .append("nhaxe", xe.getNhaxe())
                            .append("loaidi", xe.getLoaidi())
                            .append("chuyendi", xe.getChuyendi())
                            .append("danhgia", xe.getDanhgia())
                            .append("hinhanh", xe.getHinhanh())
                            .append("tinhdiqua", xe.getTinhdiqua())
                            .append("giodi", xe.getGiodi())
                            .append("ngaydi", xe.getNgaydi())
                            .append("chinhsachhuyve", xe.getChinhsachhuyve())
                            .append("lichtrinh", arrLichtrinh)
                            .append("danhsachghe", arrGhe)
                            .append("deleted",xe.getDeleted())
                    )
            );
            return true;
        } catch (MongoException e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public boolean deleteXe(Xe xe) {
        try {
            coll.updateOne(eq("_id", xe.get_id()),
                    new Document("$set", new Document("deleted", true)));
            return true;
        }
        catch (MongoException e){
            System.out.println(e);
        }
        return false;
    }

    public List<Xe> getXes() {
        /*
         * Tìm vé xe thông qua nơi đi qua và ngày đi
         * */
        MongoCursor<Document> cursor = coll.find().iterator();
        List<Xe> list = new ArrayList<Xe>();
        /*
         * Set dữ liệu search được vào mảng xe
         * */
        while (cursor.hasNext()) {
            Xe xe = new Xe();
            Document doc = cursor.next();
            xe.set_id(doc.get("_id").toString());
            xe.setLoaixe(doc.get("loaixe").toString());
            xe.setNhaxe(doc.get("nhaxe").toString());
            xe.setGiodi(doc.get("giodi").toString());
            xe.setLoaidi(doc.get("loaidi").toString());
            xe.setChuyendi(doc.get("chuyendi").toString());
            xe.setDanhgia(Integer.parseInt(doc.get("danhgia").toString()));
            xe.setHinhanh(doc.get("hinhanh").toString());
            xe.setNgaydi(doc.get("ngaydi").toString());
            xe.setChinhsachhuyve(doc.get("chinhsachhuyve").toString());
            ArrayList<Ghe> listGhe = new ArrayList<Ghe>();
            listGhe = (ArrayList<Ghe>) doc.get("danhsachghe");
            xe.setDanhsachghe(listGhe);
            ArrayList<LichTrinh> listLT = new ArrayList<LichTrinh>();
            listLT = (ArrayList<LichTrinh>) doc.get("lichtrinh");
            xe.setLichtrinh(listLT);
            xe.setDeleted(doc.getBoolean("deleted"));
            list.add(xe);

        }
        return list;
    }
}
