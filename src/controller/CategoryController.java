package controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.CategoryModel;
import util.Conecta;

/**
 *
 * @author Crisley
 */
public class CategoryController {

    public void insert(CategoryModel cm){
        Conecta con = new Conecta();
        con.conect();
        
        try {
            PreparedStatement pst = con.con.prepareStatement("INSERT INTO tbl_categories(cat_name) VALUES(?)");
        
            pst.setString(1, cm.getCat_name());
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Categoria cadastrada com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Eror ao cadastrar categoria. \n ERRO: "+ex.getMessage());
        }
        con.disconnect();
    }
    
    public void update(CategoryModel cm){
        Conecta con = new Conecta();
        con.conect();
        
        try {
            PreparedStatement pst = con.con.prepareStatement("UPDATE tbl_categories SET cat_name = ? WHERE cat_code = ?");

            pst.setString(1, cm.getCat_name());
            pst.setInt(2, cm.getCat_code());
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Categoria atualizada com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Eror ao atualizar categoria. \n ERRO: "+ex.getMessage());
        }
        con.disconnect();
    }
    
    public void delete(CategoryModel cm){
        Conecta con = new Conecta();
        con.conect();
        
        try {
            PreparedStatement pst = con.con.prepareStatement("DELETE FROM tbl_categories WHERE cat_code = ?");

            pst.setInt(1, cm.getCat_code());
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Categoria excluída com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Eror ao excluir categoria. \n ERRO: "+ex.getMessage());
        }
        con.disconnect();
    }
    
    public ArrayList<CategoryModel> list(){
        
        Conecta con = new Conecta();
        con.conect();
        
        ArrayList<CategoryModel> list = new ArrayList<>();
        
        try {
            con.executaSql("SELECT * FROM tbl_categories");
            con.rs.first();
            
            do{
                CategoryModel cm = new CategoryModel();
                
                cm.setCat_code(Integer.parseInt(con.rs.getString("cat_code")));
                cm.setCat_name(con.rs.getString("cat_name"));
                
                list.add(cm);
                
            }while(con.rs.next());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar lista de categorias");
        }
        con.disconnect();
        return list;
    }
    
    public CategoryModel searchById(int ref){
        
        Conecta con = new Conecta();
        con.conect();
        
        CategoryModel cm = new CategoryModel();
        
        try {
            con.executaSql("SELECT * FROM tbl_categories WHERE cat_code = "+ref);
            con.rs.first();

            
            cm.setCat_code(Integer.parseInt(con.rs.getString("cat_code")));
            cm.setCat_name(con.rs.getString("cat_name"));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao selecionar categoria");
        }
        con.disconnect();
        return cm;
    }
    
    public CategoryModel searchByName(String ref){
        
        Conecta con = new Conecta();
        con.conect();
        
        CategoryModel cm = new CategoryModel();
        
        try {
            con.executaSql("SELECT * FROM tbl_categories WHERE cat_name = '"+ref+"'");
            con.rs.first();

            cm.setCat_code(Integer.parseInt(con.rs.getString("cat_code")));
            cm.setCat_name(con.rs.getString("cat_name"));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao selecionar categoria");
        }
        con.disconnect();
        return cm;
    }
}
