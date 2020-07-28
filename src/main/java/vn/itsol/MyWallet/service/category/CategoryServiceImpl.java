package vn.itsol.MyWallet.service.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.itsol.MyWallet.dao.category.CategoryDao;
import vn.itsol.MyWallet.entity.category.Category;
import vn.itsol.MyWallet.entity.userWallet.UserWallet;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryDao categoryDao;

    @Transactional
    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Transactional
    @Override
    public Category findByID(int categoryID) {
        return categoryDao.findByID(categoryID);
    }

    @Transactional
    @Override
    public void delete(int categoryID){
        Category category = categoryDao.findByID(categoryID);
        categoryDao.delete(category);
    }

    @Transactional
    @Override
    public void update(Category category) {
        categoryDao.update(category);
    }

    @Transactional
    @Override
    public void save(Category category) {
        categoryDao.save(category);
    }
}
