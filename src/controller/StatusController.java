package controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.StatusModel;
import util.Conecta;

/**
 *
 * @author Crisley
 */
public class StatusController {

    public void insert(StatusModel sm){
        Conecta con = new Conecta();
        con.conect();
        
        try {
            PreparedStatement pst = con.con.prepareStatement("INSERT INTO tbl_status(sta_name) VALUES(?)");
        
            pst.setString(1, sm.getSta_name());
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Status cadastrado com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Eror ao cadastrar status. \n ERRO: "+ex.getMessage());
        }
        con.disconnect();
    }
    
    public void update(StatusModel sm){
        Conecta con = new Conecta();
        con.conect();
        
        try {
            PreparedStatement pst = con.con.prepareStatement("UPDATE tbl_status SET sta_name = ? WHERE sta_code = ?");

            pst.setString(1, sm.getSta_name());
            pst.setInt(2, sm.getSta_code());
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Status atualizado com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Eror ao atualizar status. \n ERRO: "+ex.getMessage());
        }
        con.disconnect();
    }
    
    public void delete(StatusModel sm){
        Conecta con = new Conecta();
        con.conect();
        
        try {
            PreparedStatement pst = con.con.prepareStatement("DELETE FROM tbl_status WHERE sta_code = ?");

            pst.setInt(1, sm.getSta_code());
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Status excluído com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Eror ao excluir status. \n ERRO: "+ex.getMessage());
        }
        con.disconnect();
    }
    
    public ArrayList<StatusModel> list(){
        
        Conecta con = new Conecta();
        con.conect();
        
        ArrayList<StatusModel> list = new ArrayList<>();
        
        try {
            con.executaSql("SELECT * FROM tbl_status");
            con.rs.first();
            
            do{
                StatusModel sm = new StatusModel();
                
                sm.setSta_code(Integer.parseInt(con.rs.getString("sta_code")));
                sm.setSta_name(con.rs.getString("sta_name"));
                
                list.add(sm);
                
            }while(con.rs.next());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar lista de status");
        }
        con.disconnect();
        return list;
    }
    
    public StatusModel searchById(int ref){
        
        Conecta con = new Conecta();
        con.conect();
        
        StatusModel sm = new StatusModel();
        
        try {
            con.executaSql("SELECT * FROM tbl_status WHERE sta_code = "+ref);
            con.rs.first();

            sm.setSta_code(Integer.parseInt(con.rs.getString("sta_code")));
            sm.setSta_name(con.rs.getString("sta_name"));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao selecionar status");
        }
        con.disconnect();
        return sm;
    }

}
