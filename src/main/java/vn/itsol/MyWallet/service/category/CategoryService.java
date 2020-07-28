package vn.itsol.MyWallet.service.category;

import org.springframework.transaction.annotation.Transactional;
import vn.itsol.MyWallet.entity.category.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();


    Category findByID(int categoryID);


    void delete(int categoryID);


    void update(Category category);


    void save(Category category);
}
