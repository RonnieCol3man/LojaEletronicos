package lojaEletronicos.dao;

import lojaEletronicos.entities.Admin1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Admin1DAO implements GenericDAO<Admin1>{
    
    public String uri = "jdbc:mysql://localhost:3306/apd3";
    public String user = "root";
    public String pwd = "123456";
    
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            return DriverManager.getConnection(uri,user,pwd);
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(Admin1DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public boolean create(Admin1 Admin1) {
        boolean resp = false;
        
        //PASSO 1 - estabelecer a conexão
        Connection conn = getConnection();
        
        //PASSO 2 - preparar a consulta
        String sql = "INSERT INTO Admin1(username, password) VALUES(?, ?, ?);";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, Admin1.getUsername());
            pst.setString(2, Admin1.getPassword());
            
        //PASSO 3 - executar a consulta
        int rows = pst.executeUpdate();
        
        //PASSO 4 - tratar o resultado
        resp = (rows > 0);
        
        //PASSO 5 - fechar a conexão
        conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Admin1DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resp;
    }

    @Override
    public List<Admin1> readAll() {
        List<Admin1> users = new ArrayList<>();
        
        //PASSO 1
        Connection conn = getConnection();
        
        //PASSO 2
        String sql = "SELECT * FROM Admin1";
        try {
            //Statement stm = conn.createStatement();
            
        //PASSO 3
        ResultSet rs = conn.prepareStatement(sql).executeQuery();
        
        //PASSO 4
        while (rs.next()){
            String nome = rs.getNString("nome");
            String username = rs.getString("username");
            String password = rs.getString("password");
            long id_Admin1 = rs.getLong("id_Admin1");
            Admin1 u = new Admin1(nome, username, password);
            u.setId_Admin1(id_Admin1);
            users.add(u);
        }
        
        //PASSO 5
        conn.close();
        
        } catch (SQLException ex) {
            Logger.getLogger(Admin1DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return users;
    }

    @Override
    public Admin1 readyById(long id) {
        Admin1 user = null;
        
        //PASSO 1
        Connection conn = getConnection();
        
        //PASSO 2
        String sql = "SELECT * FROM Admin1 WHERE id_Admin1 = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setLong(1, id);
            
        //PASSO 3
        ResultSet rs = pst.executeQuery();
        
        //PASSO 4
        while (rs.next()){
            String nome = rs.getNString("nome");
            String username = rs.getString("username");
            String password = rs.getString("password");
            
            user = new Admin1(nome, username, password);
            user.setId_Admin1(id);
        }
        
        //PASSO 5
        conn.close();
        
        } catch (SQLException ex) {
            Logger.getLogger(Admin1DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return user;
    }
    
    public Admin1 readByUsername(String username) {
        Admin1 user = null;
        //PASSO 1
        Connection conn = getConnection();
        
        //PASSO 2
        String sql = "SELECT * FROM Admin1 WHERE username = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            
            //PASSO 3
            ResultSet rs = pst.executeQuery();
            
            //PASSO 4
            while(rs.next()){
                String nome = rs.getString("nome");
                Long id = rs.getLong("id_Admin1");
                String password = rs.getString("password");
                
                user = new Admin1(nome, username, password);
                user.setId_Admin1(id);
            }
            
            //PASSO 5
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Admin1DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        return user;
    }

    @Override
    public boolean update(Admin1 Admin1) {
        boolean resp = false;
        
        //PASSO 1
        Connection conn = getConnection();
        
        //PASSO 2
        String sql = "UPDATE Admin1 SET username = ?, password = ? WHERE username = ?";
        try {
              PreparedStatement pst = conn.prepareStatement(sql);
              pst.setString(1, Admin1.getUsername());
              pst.setString(2, Admin1.getPassword());
              pst.setString(3, Admin1.getUsername());
             
        //PASSO 3
        int rows = pst.executeUpdate();
        
        //PASSO 4
        resp = (rows > 0);
        
        //PASSO 5
        conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Admin1DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resp;
    }

    @Override
    public boolean delete(Admin1 Admin1) {
        boolean resp = false;
        
        //PASSO 1
        Connection conn = getConnection();
        
        //PASSO 2
        String sql = "DELETE FROM Admin1 WHERE id_Admin1 = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setLong(1, Admin1.getId_Admin1());
            
        //PASSO 3
        int rows = pst.executeUpdate();
        
        //PASSO 4
        resp = (rows > 0);
        
        //PASSO 5
        conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Admin1DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resp;
    }

    public Admin1 login(String username, String password){
        Admin1 user = readByUsername(username);
        if(user !=null && !user.getPassword().equals(password)) user = null;
        return user;
    }   
}
