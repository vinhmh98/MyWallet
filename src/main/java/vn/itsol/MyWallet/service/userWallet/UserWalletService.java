package vn.itsol.MyWallet.service.userWallet;

import vn.itsol.MyWallet.entity.userWallet.UserWallet;

import java.util.List;

public interface UserWalletService {
    List<UserWallet> findAll();

    UserWallet findById(int userWalletId);

    void save(UserWallet userWallet);

    void delete(int userWalletID);

    UserWallet update(int userWalletID);
}
