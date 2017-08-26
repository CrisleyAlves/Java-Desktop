package model;

/**
 *
 * @author Crisley
 */

public class TypeModel {

    int typ_code;
    CategoryModel category;
    String typ_name;

    public int getTyp_code() {
        return typ_code;
    }

    public void setTyp_code(int typ_code) {
        this.typ_code = typ_code;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public String getTyp_name() {
        return typ_name;
    }

    public void setTyp_name(String typ_name) {
        this.typ_name = typ_name;
    }
    
}
