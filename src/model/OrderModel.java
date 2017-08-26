package model;

/**
 *
 * @author Crisley
 */
public class OrderModel {

    int ord_code;
    String ord_obs;
    String ord_address;
    String ord_date;
    Double ord_freight;
    ClientModel cm;
    StatusModel sm;

    public int getOrd_code() {
        return ord_code;
    }

    public void setOrd_code(int ord_code) {
        this.ord_code = ord_code;
    }

    public String getOrd_obs() {
        return ord_obs;
    }

    public void setOrd_obs(String ord_obs) {
        this.ord_obs = ord_obs;
    }

    public String getOrd_address() {
        return ord_address;
    }

    public void setOrd_address(String ord_address) {
        this.ord_address = ord_address;
    }

    public String getOrd_date() {
        return ord_date;
    }

    public void setOrd_date(String ord_date) {
        this.ord_date = ord_date;
    }

    public Double getOrd_freight() {
        return ord_freight;
    }

    public void setOrd_freight(Double ord_freight) {
        this.ord_freight = ord_freight;
    }

    public ClientModel getCm() {
        return cm;
    }

    public void setCm(ClientModel cm) {
        this.cm = cm;
    }

    public StatusModel getSm() {
        return sm;
    }

    public void setSm(StatusModel sm) {
        this.sm = sm;
    }
    
}
