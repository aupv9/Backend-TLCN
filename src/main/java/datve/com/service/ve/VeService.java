package datve.com.service.ve;

import datve.com.model.Ve;

public interface VeService {
    boolean addVe(Ve ve);
    boolean cancelVe(Ve ve);
    Ve searchVe(String _id, String phone);

}
