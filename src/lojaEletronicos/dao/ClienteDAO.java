package lojaEletronicos.dao;

import lojaEletronicos.entities.Cliente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDAO implements GenericDAO<Cliente>{
    
    public String uri = "jdbc:mysql://localhost:3306/apd3";
    public String user = "root";
    public String pwd = "123456";
    
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            return DriverManager.getConnection(uri,user,pwd);
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public boolean create(Cliente Cliente) {
        boolean resp = false;
        Random ran = new Random();
        int x = ran.nextInt(999999);
        
        //PASSO 1 - estabelecer a conexão
        Connection conn = getConnection();
        
        //PASSO 2 - preparar a consulta
        String sql = "INSERT INTO Cliente(nome, address, tel, username, password, cod_Cliente) VALUES(?, ?, ?, ?, ?, '" +x+ "');";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, Cliente.getNome());
            pst.setString(2, Cliente.getAddress());
            pst.setString(3, Cliente.getTel());
            pst.setString(4, Cliente.getUsername());
            pst.setString(5, Cliente.getPassword());
            
        //PASSO 3 - executar a consulta
        int rows = pst.executeUpdate();
        
        //PASSO 4 - tratar o resultado
        resp = (rows > 0);
        
        //PASSO 5 - fechar a conexão
        conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resp;
    }

    @Override
    public List<Cliente> readAll() {
        List<Cliente> users = new ArrayList<>();
        
        //PASSO 1
        Connection conn = getConnection();
        
        //PASSO 2
        String sql = "SELECT * FROM Cliente";
        try {
            //Statement stm = conn.createStatement();
            
        //PASSO 3
        ResultSet rs = conn.prepareStatement(sql).executeQuery();
        
        //PASSO 4
        while (rs.next()){
            String nome = rs.getString("nome");
            String address = rs.getNString("address");
            String tel = rs.getNString("tel");
            String username = rs.getString("username");
            String password = rs.getString("password");
            int cod = rs.getInt("cod_Cliente");
            long id_Cliente = rs.getLong("id_Cliente");
            Cliente u = new Cliente(nome, address, tel, username, password);
            u.setId_Cliente(id_Cliente);
            users.add(u);
        }
        
        //PASSO 5
        conn.close();
        
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return users;
    }

    @Override
    public Cliente readyById(long id) {
        Cliente user = null;
        
        //PASSO 1
        Connection conn = getConnection();
        
        //PASSO 2
        String sql = "SELECT * FROM Cliente WHERE id_Cliente = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setLong(1, id);
            
        //PASSO 3
        ResultSet rs = pst.executeQuery();
        
        //PASSO 4
        while (rs.next()){
            String nome = rs.getString("nome");
            String address = rs.getNString("adress");
            String tel = rs.getNString("tel");
            String username = rs.getString("username");
            String password = rs.getString("password");
            
            user = new Cliente(nome, address, tel, username, password);
            user.setId_Cliente(id);
        }
        
        //PASSO 5
        conn.close();
        
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return user;
    }
    
    public Cliente readByUsername(String username) {
        Cliente user = null;
        //PASSO 1
        Connection conn = getConnection();
        
        //PASSO 2
        String sql = "SELECT * FROM Cliente WHERE username = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            
            //PASSO 3
            ResultSet rs = pst.executeQuery();
            
            //PASSO 4
            while(rs.next()){
                String nome = rs.getString("nome");
                Long id = rs.getLong("id_Cliente");
                String password = rs.getString("password");
                
                user = new Cliente(nome, username, password);
                user.setId_Cliente(id);
            }
            
            //PASSO 5
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        return user;
    }

    @Override
    public boolean update(Cliente Cliente) {
        boolean resp = false;
        
        //PASSO 1
        Connection conn = getConnection();
        
        //PASSO 2
        String sql = "UPDATE Cliente SET nome = ?, address = ?, tel = ?, username = ?, password = ? WHERE nome = ?";
        try {
              PreparedStatement pst = conn.prepareStatement(sql);
              pst.setString(1, Cliente.getNome());
              pst.setString(2, Cliente.getAddress());
              pst.setString(3, Cliente.getTel());
              pst.setString(4, Cliente.getUsername());
              pst.setString(5, Cliente.getPassword());
              pst.setString(6, Cliente.getNome());
             
        //PASSO 3
        int rows = pst.executeUpdate();
        
        //PASSO 4
        resp = (rows > 0);
        
        //PASSO 5
        conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resp;
    }

    @Override
    public boolean delete(Cliente Cliente) {
        boolean resp = false;
        
        //PASSO 1
        Connection conn = getConnection();
        
        //PASSO 2
        String sql = "DELETE FROM Cliente WHERE id_Cliente = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setLong(1, Cliente.getId_Cliente());
            
        //PASSO 3
        int rows = pst.executeUpdate();
        
        //PASSO 4
        resp = (rows > 0);
        
        //PASSO 5
        conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resp;
    }

    public Cliente login(String username, String password){
        Cliente user = readByUsername(username);
        if(user !=null && !user.getPassword().equals(password)) user = null;
        return user;
    }   
}
