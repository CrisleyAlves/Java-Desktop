package model;

/**
 *
 * @author Crisley
 */
public class ProductModel {

    int pro_code;
    String pro_name;
    Double pro_price;
    TypeModel tm;

    public int getPro_code() {
        return pro_code;
    }

    public void setPro_code(int pro_code) {
        this.pro_code = pro_code;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public Double getPro_price() {
        return pro_price;
    }

    public void setPro_price(Double pro_price) {
        this.pro_price = pro_price;
    }

    public TypeModel getTm() {
        return tm;
    }

    public void setTm(TypeModel tm) {
        this.tm = tm;
    }
    
}
