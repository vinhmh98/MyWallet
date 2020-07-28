package vn.itsol.MyWallet.dao.category;

import vn.itsol.MyWallet.entity.category.Category;

import java.util.List;

public interface CategoryDao {

    List<Category> findAll();

    Category findByID(int categoryID);

    void delete(Category category);

    void update(Category category);

    void save(Category category);

}
