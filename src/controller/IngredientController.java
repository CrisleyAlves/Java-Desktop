package controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.IngredientModel;
import util.Conecta;

/**
 *
 * @author Crisley
 */
public class IngredientController {

    public void insert(IngredientModel im){
        Conecta con = new Conecta();
        con.conect();
        
        try {
            PreparedStatement pst = con.con.prepareStatement("INSERT INTO tbl_ingredients(ing_name) VALUES(?)");
        
            pst.setString(1, im.getIng_name());
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Ingrediente cadastrado com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Eror ao cadastrar ingrediente. \n ERRO: "+ex.getMessage());
        }
        con.disconnect();
    }
    
    public void update(IngredientModel im){
        Conecta con = new Conecta();
        con.conect();
        
        try {
            PreparedStatement pst = con.con.prepareStatement("UPDATE tbl_ingredients SET ing_name = ? WHERE ing_code = ?");

            pst.setString(1, im.getIng_name());
            pst.setInt(2, im.getIng_code());
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Ingrediente atualizado com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Eror ao atualizar ingrediente. \n ERRO: "+ex.getMessage());
        }
        con.disconnect();
    }
    
    public void delete(IngredientModel im){
        Conecta con = new Conecta();
        con.conect();
        
        try {
            PreparedStatement pst = con.con.prepareStatement("DELETE FROM tbl_ingredients WHERE ing_code = ?");

            pst.setInt(1, im.getIng_code());
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Ingrediente excluído com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Eror ao excluir ingrediente. \n ERRO: "+ex.getMessage());
        }
        con.disconnect();
    }
    
    public ArrayList<IngredientModel> list(){
        
        Conecta con = new Conecta();
        con.conect();
        
        ArrayList<IngredientModel> list = new ArrayList<>();
        
        try {
            con.executaSql("SELECT * FROM tbl_ingredients");
            con.rs.first();
            
            do{
                IngredientModel im = new IngredientModel();
                   
                im.setIng_code(Integer.parseInt(con.rs.getString("ing_code")));
                im.setIng_name(con.rs.getString("ing_name"));
                
                list.add(im);
                
            }while(con.rs.next());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar lista de ingredientes");
        }
        con.disconnect();
        return list;
    }
    
    public IngredientModel searchById(int ref){
        
        Conecta con = new Conecta();
        con.conect();
        
        IngredientModel im = new IngredientModel();
        
        try {
            con.executaSql("SELECT * FROM tbl_ingredients WHERE ing_code = "+ref);
            con.rs.first();

            im.setIng_code(Integer.parseInt(con.rs.getString("ing_code")));
            im.setIng_name(con.rs.getString("ing_name"));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao selecionar ingrediente");
        }
        con.disconnect();
        return im;
    }
    
    public IngredientModel searchByName(String ref){
        
        Conecta con = new Conecta();
        con.conect();
        
        IngredientModel im = new IngredientModel();
        
        try {
            con.executaSql("SELECT * FROM tbl_ingredients WHERE ing_name = '"+ref+"'");
            con.rs.first();

            im.setIng_code(Integer.parseInt(con.rs.getString("ing_code")));
            im.setIng_name(con.rs.getString("ing_name"));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao selecionar ingrediente");
        }
        con.disconnect();
        return im;
    }
}