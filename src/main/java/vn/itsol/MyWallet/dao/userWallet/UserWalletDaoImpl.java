package vn.itsol.MyWallet.dao.userWallet;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.itsol.MyWallet.controller.user.UserController;
import vn.itsol.MyWallet.entity.userWallet.UserWallet;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserWalletDaoImpl implements UserWalletDao {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<UserWallet> findAll() {
        String sql = "from UserWallet u";
        Session currentsession = entityManager.unwrap(Session.class);
        Query<UserWallet> query = currentsession.createQuery(sql, UserWallet.class);
        List<UserWallet> list = query.getResultList();
        return list;
    }



    @Override
    public UserWallet findByID(int userWalletId ) {
        Session currentsession = entityManager.unwrap(Session.class);
        try{
            String sql= "from UserWallet as usw where usw.userWalletID = "+ userWalletId;
            Query<UserWallet> userQuery = currentsession.createQuery(sql,UserWallet.class);
            return userQuery.getSingleResult();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            currentsession.getTransaction().rollback();
        }
        return null;
    }

    @Override
    public void save(UserWallet userWallet) {
//        INSERT INTO Articles(title, date_created, author) SELECT title, date_created, author FROM Author_Articles
//        String sql = "INSERT INTO STUDENT (NAME, ADDRESS)"+" VALUES (:p_name, :p_address)";//        String sql = "insert into user_wallet (user_wallet_id, user_id, wallet_id, role) "+" VALUES (:userWalletID,:userID,:walletID,:role)" ;
        Session curentsession = entityManager.unwrap(Session.class);
        try{
            String sql= "insert into user_wallet( user_wallet_id, user_id, wallet_id, role)" +"values(:userWalletID,:userID,:walletID,:role)";

            Query<UserWallet> userQuery = curentsession.createNativeQuery(sql);
            userQuery.setParameter("userWalletID",userWallet.getUserWalletID()).setParameter("userID",userWallet.getUserID()).setParameter("walletID",userWallet.getWalletID()).setParameter("role",userWallet.getRole());
            userQuery.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            curentsession.getTransaction().rollback();
        }
        finally{
            curentsession.close();
        }


    }

    @Override
    public void update(UserWallet userWallet) {
        Session currentsession = entityManager.unwrap(Session.class);
        try{
            String sql ="Update user_wallet set user_id = :userID, wallet_id = :walletID, role = :role where user_wallet_id= :userWalletID";
            Query<UserWallet> userWalletQuery= currentsession.createNativeQuery(sql);
            userWalletQuery
                    .setParameter("userWalletID",userWallet.getUserWalletID())
                    .setParameter("userID",userWallet.getUserID())
                    .setParameter("walletID",userWallet.getWalletID())
                    .setParameter("role",userWallet.getRole());
            userWalletQuery.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            currentsession.close();
        }

    }


    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Override
    public void delete(UserWallet userWallet) {

//        log.info("------------1: " + sql);

//        userWalletQuery.executeUpdate();
//        //userWalletQuery.executeUpdate();

        Session currentSession = entityManager.unwrap(Session.class);

        try{
           String sql = " delete from user_wallet u where u.user_wallet_id =" + ":userWalletID";
           Query<UserWallet> userWalletQuery = currentSession.createNativeQuery(sql, UserWallet.class);
           userWalletQuery.setParameter("userWalletID",userWallet.getUserWalletID());
           userWalletQuery.executeUpdate();
       }
       catch (Exception e){
           e.printStackTrace();
        }
        finally {
            currentSession.close();
        }
    }

    @Override
    public UserWallet getUserWallet(int userWalletID) {
        return  entityManager.find(UserWallet.class,userWalletID);
    }
}
