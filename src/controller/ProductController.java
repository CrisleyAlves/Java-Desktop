package controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.ProductModel;
import model.TypeModel;
import util.Conecta;

/**
 *
 * @author Crisley
 */
public class ProductController {

    public void insert(ProductModel pm){
        Conecta con = new Conecta();
        con.conect();
        
        try {
            PreparedStatement pst = con.con.prepareStatement("INSERT INTO tbl_products(pro_name, pro_price, typ_code) VALUES(?,?,?)");
            
            
            pst.setString(1, pm.getPro_name());
            pst.setDouble(2, pm.getPro_price());
            pst.setInt(3, pm.getTm().getTyp_code());
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Eror ao cadastrar produto. \n ERRO: "+ex.getMessage());
        }
        con.disconnect();
    }
    
    public void update(ProductModel pm){
        Conecta con = new Conecta();
        con.conect();
        
        try {
            PreparedStatement pst = con.con.prepareStatement("UPDATE tbl_products SET pro_name = ?, pro_price = ?, typ_code = ? WHERE pro_code = ?");
            
            pst.setString(1, pm.getPro_name());
            pst.setDouble(2, pm.getPro_price());
            pst.setInt(3, pm.getTm().getTyp_code());
            pst.setInt(4, pm.getPro_code());
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Eror ao atualizar produto. \n ERRO: "+ex.getMessage());
        }
        con.disconnect();
    }
    
    public void delete(ProductModel pm){
        Conecta con = new Conecta();
        con.conect();
        
        try {
            PreparedStatement pst = con.con.prepareStatement("DELETE FROM tbl_products WHERE pro_code = ?");

            pst.setInt(1, pm.getPro_code());
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Produto excluído com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Eror ao excluir produto. \n ERRO: "+ex.getMessage());
        }
        con.disconnect();
    }
    
    public ArrayList<ProductModel> list(){
        
        Conecta con = new Conecta();
        con.conect();
        
        TypeController tc = new TypeController();
        ArrayList<ProductModel> list = new ArrayList<>();
        
        try {
            con.executaSql("SELECT * FROM tbl_products");
            con.rs.first();
            
            do{
                TypeModel tm = new TypeModel();
                ProductModel pm = new ProductModel();
                
                tm = tc.searchById(Integer.parseInt(con.rs.getString("typ_code")));
                
                pm.setPro_code(Integer.parseInt(con.rs.getString("pro_code")));
                pm.setPro_name(con.rs.getString("pro_name"));
                pm.setPro_price(Double.parseDouble(con.rs.getString("pro_price")));
                pm.setTm(tm);
                
                list.add(pm);
                
            }while(con.rs.next());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar lista de produtos");
        }
        con.disconnect();
        return list;
    }
    
    public ProductModel searchById(int ref){
        
        Conecta con = new Conecta();
        con.conect();
        
        ProductModel pm = new ProductModel();
        TypeModel tm = new TypeModel();
        TypeController tc = new TypeController();
        
        try {
            con.executaSql("SELECT * FROM tbl_products WHERE pro_code = "+ref);
            con.rs.first();
        
            tm = tc.searchById(Integer.parseInt(con.rs.getString("typ_code")));
            
            pm.setPro_code(Integer.parseInt(con.rs.getString("pro_code")));
            pm.setPro_name(con.rs.getString("pro_name"));
            pm.setPro_price(Double.parseDouble(con.rs.getString("pro_price")));
            pm.setTm(tm);
            

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao selecionar Produto");
        }
        con.disconnect();
        return pm;
    }
    
    public ProductModel searchByName(String ref){
        
        Conecta con = new Conecta();
        con.conect();
        
        ProductModel pm = new ProductModel();
        TypeModel tm = new TypeModel();
        TypeController tc = new TypeController();
        
        try {
            con.executaSql("SELECT * FROM tbl_products WHERE pro_name = '"+ref+"'");
            con.rs.first();
        
            tm = tc.searchById(Integer.parseInt(con.rs.getString("typ_code")));
            
            pm.setPro_code(Integer.parseInt(con.rs.getString("pro_code")));
            pm.setPro_name(con.rs.getString("pro_name"));
            pm.setPro_price(Double.parseDouble(con.rs.getString("pro_price")));
            pm.setTm(tm);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao selecionar Produto");
        }
        con.disconnect();
        return pm;
    }
    
}
