package controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.IngredientModel;
import model.ProIngModel;
import model.ProductModel;
import util.Conecta;

/**
 *
 * @author Crisley
 */
public class ProIngController {

    public void insert(ProIngModel pim){
        Conecta con = new Conecta();
        con.conect();
        
        try {
            PreparedStatement pst = con.con.prepareStatement("INSERT INTO tbl_pro_ings(pro_code, ing_code) VALUES(?,?)");
            
            pst.setInt(1, pim.getPm().getPro_code());
            pst.setInt(2, pim.getIm().getIng_code());
            pst.executeUpdate();
            //JOptionPane.showMessageDialog(null, "Categoria cadastrada com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Eror ao vincular ingrediente ao produto. \n ERRO: "+ex.getMessage());
        }
        con.disconnect();
    }
    
    public void delete(int pro_code){
        Conecta con = new Conecta();
        con.conect();
        
        try {
            PreparedStatement pst = con.con.prepareStatement("DELETE FROM tbl_pro_ings WHERE pro_code = ?");

            pst.setInt(1, pro_code);
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Ingredientes removidos com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Eror ao desvincular ingrediente do produto. \n ERRO: "+ex.getMessage());
        }
        con.disconnect();
    }
    
    public ArrayList<ProIngModel> list(){
        
        Conecta con = new Conecta();
        con.conect();

        ProductController pc = new ProductController();
        IngredientController ic = new IngredientController();
        
        ArrayList<ProIngModel> list = new ArrayList<>();
        
        try {
            con.executaSql("SELECT * FROM tbl_pro_ings ORDER BY pro_code");
            con.rs.first();
            
            do{
                ProIngModel pim = new ProIngModel();
                ProductModel pm = new ProductModel();
                IngredientModel im = new IngredientModel();
                
                pm = pc.searchById(Integer.parseInt(con.rs.getString("pro_code")));
                im = ic.searchById(Integer.parseInt(con.rs.getString("ing_code")));
                
                pim.setIm(im);
                pim.setPm(pm);
                
                list.add(pim);
                
            }while(con.rs.next());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar lista de ingredientes de produtos");
        }
        con.disconnect();
        return list;
    }
    
    public ArrayList<ProIngModel> searchById(int ref){
        
        Conecta con = new Conecta();
        con.conect();

        ProductController pc = new ProductController();
        IngredientController ic = new IngredientController();
        
        ArrayList<ProIngModel> list = new ArrayList<>();
        
        try {
            con.executaSql("SELECT * FROM tbl_pro_ings WHERE pro_code = "+ref);
            con.rs.first();
            
            do{
                ProIngModel pim = new ProIngModel();
                ProductModel pm = new ProductModel();
                IngredientModel im = new IngredientModel();
                
                pm = pc.searchById(Integer.parseInt(con.rs.getString("pro_code")));
                im = ic.searchById(Integer.parseInt(con.rs.getString("ing_code")));
                
                pim.setIm(im);
                pim.setPm(pm);
                
                list.add(pim);
                
            }while(con.rs.next());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar lista de ingredientes de um único produtos");
        }
        con.disconnect();
        return list;
    }
    
}
