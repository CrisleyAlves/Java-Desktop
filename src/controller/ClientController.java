package controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.ClientModel;
import util.Conecta;

/**
 *
 * @author Crisley
 */
public class ClientController {

    public void insert(ClientModel cm){
        Conecta con = new Conecta();
        con.conect();
        
        try {
            PreparedStatement pst = con.con.prepareStatement("INSERT INTO tbl_clients(cli_name, cli_address, cli_cpf, cli_rg, cli_contact) VALUES(?,?,?,?,?)");
        
            pst.setString(1, cm.getCli_name());
            pst.setString(2, cm.getCli_address());
            pst.setString(3, cm.getCli_cpf());
            pst.setString(4, cm.getCli_rg());
            pst.setString(5, cm.getCli_contact());
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Eror ao cadastrar cliente. \n ERRO: "+ex.getMessage());
        }
        con.disconnect();
    }
    
    public void update(ClientModel cm){
        Conecta con = new Conecta();
        con.conect();
        
        try {
            PreparedStatement pst = con.con.prepareStatement("UPDATE tbl_clients SET cli_name = ?, cli_address = ?, cli_cpf = ?, cli_rg = ?, cli_contact = ? WHERE cli_code = ?");

            pst.setString(1, cm.getCli_name());
            pst.setString(2, cm.getCli_address());
            pst.setString(3, cm.getCli_cpf());
            pst.setString(4, cm.getCli_rg());
            pst.setString(5, cm.getCli_contact());
            pst.setInt(6, cm.getCli_code());
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Eror ao atualizar cliente. \n ERRO: "+ex.getMessage());
        }
        con.disconnect();
    }
    
    public void delete(ClientModel cm){
        Conecta con = new Conecta();
        con.conect();
        
        try {
            PreparedStatement pst = con.con.prepareStatement("DELETE FROM tbl_clients WHERE cli_code = ?");
            pst.setInt(1, cm.getCli_code());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Eror ao excluir cliente. \n ERRO: "+ex.getMessage());
        }
        con.disconnect();
    }
    
    public ArrayList<ClientModel> list(){
        
        Conecta con = new Conecta();
        con.conect();
        
        ArrayList<ClientModel> list = new ArrayList<>();
        
        try {
            con.executaSql("SELECT * FROM tbl_clients");
            con.rs.first();
            
            do{
                ClientModel cm = new ClientModel();
                
                cm.setCli_code(Integer.parseInt(con.rs.getString("cli_code")));
                cm.setCli_name(con.rs.getString("cli_name"));
                cm.setCli_address(con.rs.getString("cli_address"));
                cm.setCli_cpf(con.rs.getString("cli_cpf"));
                cm.setCli_rg(con.rs.getString("cli_rg"));
                cm.setCli_contact(con.rs.getString("cli_contact"));
                
                list.add(cm);
                
            }while(con.rs.next());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar lista de clientes");
        }
        con.disconnect();
        return list;
    }
    
    public ClientModel searchById(int ref){
        
        Conecta con = new Conecta();
        con.conect();
        
        ClientModel cm = new ClientModel();
        
        try {
            con.executaSql("SELECT * FROM tbl_clients WHERE cli_code = "+ref);
            con.rs.first();

            cm.setCli_code(Integer.parseInt(con.rs.getString("cli_code")));
            cm.setCli_name(con.rs.getString("cli_name"));
            cm.setCli_address(con.rs.getString("cli_address"));
            cm.setCli_cpf(con.rs.getString("cli_cpf"));
            cm.setCli_rg(con.rs.getString("cli_rg"));
            cm.setCli_contact(con.rs.getString("cli_contact"));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao selecionar cliente");
        }
        con.disconnect();
        return cm;
    }
}
