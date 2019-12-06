package datve.com.dao.ve;


import datve.com.model.Ve;


public interface VeDao {

    boolean addVe(Ve ve);
    boolean cancelVe(Ve ve);
    Ve searchVe(String _id, String phone);

}
