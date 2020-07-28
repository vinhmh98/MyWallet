package vn.itsol.MyWallet.dao.category;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.itsol.MyWallet.dao.category.CategoryDao;
import vn.itsol.MyWallet.entity.category.Category;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao {
    @Autowired
    private EntityManager entityManager;
    @Override
    public List<Category> findAll() {
        Session currentsession =entityManager.unwrap(Session.class);
        try {
            String hql = "from Category";
            Query <Category> categoryQuery= currentsession.createQuery(hql,Category.class);
            List<Category> list= categoryQuery.getResultList();
            return list;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            currentsession.close();
        }
        return null;
    }

    @Override
    public Category findByID(int categoryID) {
       Session currentSession = entityManager.unwrap(Session.class);
       try {
           String hql="from Category c where c.categoryID ="+categoryID;
           Query<Category> categoryQuery= currentSession.createQuery(hql,Category.class);
           return categoryQuery.getSingleResult();
       }
       catch (Exception e){
           e.printStackTrace();
           currentSession.getTransaction().rollback();
       }
       finally {
           currentSession.close();
       }
       return null;
    }


    @Override
    public void delete(Category category) {
        Session currentSession = entityManager.unwrap(Session.class);
        try{
            String sql= "delete from category where category_id = :categoryID";
            Query<Category> categoryQuery = currentSession.createNativeQuery(sql);
            categoryQuery.setParameter("categoryID",category.getCategoryID());
            categoryQuery.executeUpdate();
        }
         catch (Exception e){
            e.printStackTrace();
         }
        finally {
            currentSession.close();
        }
    }


    @Override
    public void update(Category category) {
        Session currentSession = entityManager.unwrap(Session.class);
        try{
            String sql= "update category set categoryname = :categoryName where category_id = :categoryID";
            Query<Category> categoryQuery = currentSession.createNativeQuery(sql);
            categoryQuery.setParameter("categoryID",category.getCategoryID()).setParameter("categoryName",category.getCategory());
            categoryQuery.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            currentSession.close();
        }
    }

    @Override
    public void save(Category category) {
        Session currentSession = entityManager.unwrap(Session.class);
        try{
            String sql= "INSERT INTO category(category_id,categoryname) " +
                    " values(:categoryID,:categoryName) ";
            Query<Category> categoryQuery = currentSession.createNativeQuery(sql);
            categoryQuery.setParameter("categoryID",category.getCategoryID()).setParameter("categoryName",category.getCategory());
            categoryQuery.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
            currentSession.getTransaction().rollback();
        }
        finally {
            currentSession.close();
        }
    }

}
