package vn.itsol.MyWallet.entity.category;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="CATEGORY")
public class Category {
    @Id
    @Column(name ="category_id")
    private int categoryID;

    @Column(name = "categoryname")
    private String category;

    public Category(int categoryID, String category) {
        this.categoryID = categoryID;
        this.category = category;
    }
    public Category(){}

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
