package datve.com.dao.ve;


import datve.com.model.Ve;

import java.util.List;


public interface VeDao {

    boolean addVe(Ve ve);

    boolean cancelVe(Ve ve);

    Ve searchVe(String _id, String phone);

    List<Ve> listVeToDate(String date);

    List<Ve> getVe();
}
