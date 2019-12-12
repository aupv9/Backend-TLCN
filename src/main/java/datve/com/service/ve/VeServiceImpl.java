package datve.com.service.ve;

import datve.com.dao.ve.VeDaoImpl;
import datve.com.model.Ve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("veService")
public class VeServiceImpl implements VeService {


    @Autowired
    VeDaoImpl veDao;

    @Override
    public boolean addVe(Ve ve) {
        return veDao.addVe(ve);
    }

    @Override
    public List<Ve> getVe() {
        return veDao.getVe();
    }

    @Override
    public boolean cancelVe(Ve ve) {
        return veDao.cancelVe(ve);
    }

    @Override
    public Ve searchVe(String _id, String phone) {
        return veDao.searchVe(_id, phone);
    }

    @Override
    public List<Ve> listVeToDate(String date) {
        return veDao.listVeToDate(date);
    }

}
