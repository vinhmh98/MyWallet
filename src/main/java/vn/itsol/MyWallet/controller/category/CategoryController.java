package vn.itsol.MyWallet.controller.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.itsol.MyWallet.entity.category.Category;
import vn.itsol.MyWallet.service.category.CategoryService;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping(path = "/categories")
    public ResponseEntity<List<Category>> getCategories(){
        List<Category> list = categoryService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(path="/category/{categoryID}")
    public Category getCategory(@PathVariable("categoryID") int categoryID){
        return categoryService.findByID(categoryID);
    }

    @PostMapping(path="/category",consumes ={"application/json"})
    public Category addCategory(@RequestBody Category category){
        categoryService.save(category);
        return category;
    }

    @PutMapping( path="/category " ,consumes ={"application/json"})
    public Category updateCategory(@RequestBody Category category){
        categoryService.update(category);
        return category;
    }

    @DeleteMapping( path="/category/{categoryID}" )
    public String deleteCategory(@PathVariable("categoryID")  int categoryID){
        categoryService.delete(categoryID);
        return "deleted CategoryID= "+ categoryID;
    }
}
