package vn.itsol.MyWallet.service.userWallet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.itsol.MyWallet.dao.userWallet.UserWalletDao;
import vn.itsol.MyWallet.entity.userWallet.UserWallet;

import java.util.List;
@Service
public class UserWalletServiceImpl implements UserWalletService {
    @Autowired
    private UserWalletDao userWalletDao;

    @Transactional
    @Override
    public List<UserWallet> findAll() {
        return userWalletDao.findAll();
    }

    @Transactional
    @Override
    public UserWallet findById(int userWalletID) {
        return userWalletDao.findByID(userWalletID);
    }

    @Transactional
    @Override
    public void save(UserWallet userWallet) {
        userWalletDao.save(userWallet);
    }

    @Transactional
    @Override
    public void delete(int userWalletId) {
        UserWallet userWallet =userWalletDao.getUserWallet(userWalletId);
        userWalletDao.delete(userWallet);
        //return "deleted" + userWalletId;
    }

    @Transactional
    @Override
    public UserWallet update(int userWalletID) {
        UserWallet userWallet = userWalletDao.getUserWallet(userWalletID);
        userWalletDao.update(userWallet);
        return userWallet;
    }
}
