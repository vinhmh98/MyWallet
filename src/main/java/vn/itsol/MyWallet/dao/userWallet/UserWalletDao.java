package vn.itsol.MyWallet.dao.userWallet;

import vn.itsol.MyWallet.entity.userWallet.UserWallet;

import java.util.List;

public interface UserWalletDao {
    List<UserWallet> findAll();

    UserWallet findByID(int userWalletID);

    void save(UserWallet userWallet);

    void update(UserWallet userWallet);

    void delete(UserWallet userWallet);

    UserWallet getUserWallet( int userWalletID);
}
