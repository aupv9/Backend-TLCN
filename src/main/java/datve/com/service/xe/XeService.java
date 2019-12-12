package datve.com.service.xe;

import datve.com.model.Xe;

import java.util.List;

public interface XeService {

    List<Xe> searchXe(int start, int end, String date);
    boolean addXe(Xe xe);
    boolean updateXe(Xe xe);
    List<Xe> getXes();
    boolean deleteXe(Xe xe);

}
