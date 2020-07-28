package vn.itsol.MyWallet.dao.user;


import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.itsol.MyWallet.entity.user.User;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private EntityManager entityManager;
    @Override
    public List<User> findAll() {
        Session currentsession = entityManager.unwrap(Session.class);
        try{
            String sql = " FROM User ";

            Query<User> query = currentsession.createQuery(sql, User.class);
            List<User> list = query.getResultList();
            return list;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User findById(int userId) {

        Session currentsession = entityManager.unwrap(Session.class);

        try{
            String sql= "from User as us where us.user_id = "+ userId;
            Query<User> userQuery = currentsession.createQuery(sql,User.class);
            return userQuery.getSingleResult();
        }
        catch (Exception e){
            e.printStackTrace();
            currentsession.getTransaction().rollback();

        }
        finally {
            currentsession.close();
        }
        return null;
    }

    @Override
    public void save(User user) {
        Session curentSession = entityManager.unwrap(Session.class);
        try{
            String sql = " insert into User(userName,name,password,phoneNumber,gender,path_ava)"+
                    " values ( \'"+user.getUserName()+"\'"
                    +","+"\'"+user.getName()+"\'"
                    +","+"\'"+user.getPassword()+"\'" +
                    ""+","+user.getPhoneNumber()+
                    ","+"\'"+user.getGender()+"\'"+
                    ","+"\'"+user.getPath_ava()+"\')" ;
            Query<User> userQuery = curentSession.createQuery(sql);
            userQuery.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
            curentSession.getTransaction().rollback();

        }

    }

    @Override
    public void update(User user) {
        Session currentsession = entityManager.unwrap(Session.class);
        try{
            String sql="update users set user_name =:username"+"set name = :name"+"set password = :password"
                    +"set phone_number = :phoneNumber"+"set gender= :gender"+"set path_ava = :path_ava";
            Query <User> userQuery =currentsession.createNativeQuery(sql);
            userQuery.setParameter("username",user.getUserName()).setParameter("name",user.getName()).
                    setParameter("password",user.getPassword()).setParameter("phoneNumber",user.getPhoneNumber())
                    .setParameter("gender",user.getGender()).setParameter("path_ava",user.getPath_ava());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int user_id) {

        Session currentSession = entityManager.unwrap(Session.class);

        try{
            String sql = "delete from Users where Users.user_id =" + user_id;
            Query<User> userQuery = currentSession.createNativeQuery(sql, User.class);
            currentSession.delete(userQuery.getSingleResult());
        }
        catch (Exception e){
            e.printStackTrace();

        }
    }

    @Override
    public List<User> findByUserName(String userName) {
        Session currentSession = entityManager.unwrap(Session.class);

        try{
            String sql="select user_name,password from users  Where user_name="+"'"+userName+"'";
             Query<User> userQuery= currentSession.createNativeQuery(sql);
//            userQuery.executeUpdate();
            List<User> list = userQuery.getResultList();
            return  list;
        }
        catch (Exception e){
            e.printStackTrace();

        }
        return null;
    }
}
