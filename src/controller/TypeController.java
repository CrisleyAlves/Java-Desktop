package controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.CategoryModel;
import model.TypeModel;
import util.Conecta;

/**
 *
 * @author Crisley
 */
public class TypeController {

    public void insert(TypeModel tm){
        Conecta con = new Conecta();
        con.conect();
        
        try {
            PreparedStatement pst = con.con.prepareStatement("INSERT INTO tbl_types(typ_name, cat_code) VALUES(?,?)");
            
            pst.setString(1, tm.getTyp_name());
            pst.setInt(2, tm.getCategory().getCat_code());
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Tipo cadastrado com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Eror ao cadastrar tipo. \n ERRO: "+ex.getMessage());
        }
        con.disconnect();
    }
    
    public void update(TypeModel tm){
        Conecta con = new Conecta();
        con.conect();
        
        try {
            PreparedStatement pst = con.con.prepareStatement("UPDATE tbl_types SET typ_name = ?, cat_code = ? WHERE typ_code = ?");

            pst.setString(1, tm.getTyp_name());
            pst.setInt(2, tm.getCategory().getCat_code());
            pst.setInt(3, tm.getTyp_code());
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Tipo atualizado com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Eror ao atualizar tipo. \n ERRO: "+ex.getMessage());
        }
        con.disconnect();
    }
    
    public void delete(TypeModel tm){
        Conecta con = new Conecta();
        con.conect();
        
        try {
            PreparedStatement pst = con.con.prepareStatement("DELETE FROM tbl_types WHERE typ_code = ?");

            pst.setInt(1, tm.getTyp_code());
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Tipo excluído com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Eror ao excluir tipo. \n ERRO: "+ex.getMessage());
        }
        con.disconnect();
    }
    
    public ArrayList<TypeModel> list(){
        
        Conecta con = new Conecta();
        con.conect();
        
        CategoryController cc = new CategoryController();
        ArrayList<TypeModel> list = new ArrayList<>();
        
        try {
            con.executaSql("SELECT * FROM tbl_types");
            con.rs.first();
            
            do{
                TypeModel tm = new TypeModel();
                CategoryModel cm = new CategoryModel();
                
                cm = cc.searchById(Integer.parseInt(con.rs.getString("cat_code")));
                
                tm.setTyp_code(Integer.parseInt(con.rs.getString("typ_code")));
                tm.setTyp_name(con.rs.getString("typ_name"));
                tm.setCategory(cm);
                
                list.add(tm);
                
            }while(con.rs.next());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar lista de tipos");
        }
        con.disconnect();
        return list;
    }
    
    public TypeModel searchById(int ref){
        
        Conecta con = new Conecta();
        con.conect();
        
        TypeModel tm = new TypeModel();
        CategoryController cc = new CategoryController();
        
        try {
            con.executaSql("SELECT * FROM tbl_types WHERE typ_code = "+ref);
            con.rs.first();

            tm.setTyp_code(Integer.parseInt(con.rs.getString("typ_code")));
            tm.setTyp_name(con.rs.getString("typ_name"));
            tm.setCategory(cc.searchById(Integer.parseInt(con.rs.getString("cat_code"))));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao selecionar Tipo");
        }
        con.disconnect();
        return tm;
    }
    
    public TypeModel searchByName(String ref){
        
        Conecta con = new Conecta();
        con.conect();
        
        TypeModel tm = new TypeModel();
        CategoryController cc = new CategoryController();
        
        try {
            con.executaSql("SELECT * FROM tbl_types WHERE typ_name = '"+ref+"'");
            con.rs.first();

            tm.setTyp_code(Integer.parseInt(con.rs.getString("typ_code")));
            tm.setTyp_name(con.rs.getString("typ_name"));
            tm.setCategory(cc.searchById(Integer.parseInt(con.rs.getString("cat_code"))));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao selecionar Tipo");
        }
        con.disconnect();
        return tm;
    }

    public TypeModel searchById(Object selectedItem) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
