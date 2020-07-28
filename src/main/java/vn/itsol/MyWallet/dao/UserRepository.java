package vn.itsol.MyWallet.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.itsol.MyWallet.entity.user.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserName(String userName);
}
