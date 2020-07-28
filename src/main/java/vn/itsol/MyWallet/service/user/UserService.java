package vn.itsol.MyWallet.service.user;

import vn.itsol.MyWallet.entity.user.User;

import java.util.List;

public interface  UserService  {

    List<User> findAll();

    User findById(int userId);

    void save(User user);

    void delete(int userId);

    void update(User user);

}
