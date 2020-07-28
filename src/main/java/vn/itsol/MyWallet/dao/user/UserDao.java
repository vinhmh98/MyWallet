package vn.itsol.MyWallet.dao.user;
import vn.itsol.MyWallet.entity.user.User;
import java.util.List;
public interface UserDao  {
    List<User> findAll();

    User findById(int userId);

    void save(User user);

    void update(User user);

    void delete(int userId);

    List<User> findByUserName(String userName);

}
