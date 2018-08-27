package lojaEletronicos.dao;

import lojaEletronicos.entities.Pedido;
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
import lojaEletronicos.entities.Pedido;

public class PedidoDAO implements GenericDAO<Pedido>{
    
    public String uri = "jdbc:mysql://localhost:3306/apd3";
    public String user = "root";
    public String pwd = "123456";
    
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            return DriverManager.getConnection(uri,user,pwd);
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public boolean create(Pedido Pedido) {
        boolean resp = false;
        Random ran = new Random();
        long x = ran.nextInt(999999);
        
        //PASSO 1 - estabelecer a conexão
        Connection conn = getConnection();
        
        //PASSO 2 - preparar a consulta
        String sql = "INSERT INTO Pedido('"+x+"', valor) VALUES(?, ?);";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setLong(1, Pedido.getNum_Pedido());
            pst.setInt(2, Pedido.getValor());
            
        //PASSO 3 - executar a consulta
        int rows = pst.executeUpdate();
        
        //PASSO 4 - tratar o resultado
        resp = (rows > 0);
        
        //PASSO 5 - fechar a conexão
        conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resp;
    }

    @Override
    public List<Pedido> readAll() {
        List<Pedido> users = new ArrayList<>();
        
        //PASSO 1
        Connection conn = getConnection();
        
        //PASSO 2
        String sql = "SELECT * FROM Pedido";
        try {
            //Statement stm = conn.createStatement();
            
        //PASSO 3
        ResultSet rs = conn.prepareStatement(sql).executeQuery();
        
        //PASSO 4
        while (rs.next()){
            long num_Pedido = rs.getLong("num_Pedido");
            int valor = rs.getInt("valor");
            Pedido u = new Pedido(num_Pedido, valor);
            u.setNum_Pedido(num_Pedido);
            users.add(u);
        }
        
        //PASSO 5
        conn.close();
        
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return users;
    }

    @Override
    public Pedido readyById(long id) {
        Pedido user = null;
        
        //PASSO 1
        Connection conn = getConnection();
        
        //PASSO 2
        String sql = "SELECT * FROM Pedido WHERE num_Pedido = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setLong(1, id);
            
        //PASSO 3
        ResultSet rs = pst.executeQuery();
        
        //PASSO 4
        while (rs.next()){
            long num_Pedido = rs.getLong("num_Pedido");
            int valor = rs.getInt("valor");
            
            user = new Pedido(num_Pedido, valor);
            user.setNum_Pedido(id);
        }
        
        //PASSO 5
        conn.close();
        
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return user;
    }
    
    public Pedido readByUsername(String valor) {
        Pedido user = null;
        //PASSO 1
        Connection conn = getConnection();
        
        //PASSO 2
        String sql = "SELECT * FROM Pedido WHERE num_Pedido = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, valor);
            
            //PASSO 3
            ResultSet rs = pst.executeQuery();
            
            //PASSO 4
            while(rs.next()){
                Long num_Pedido = rs.getLong("num_Pedido");
                
                user = new Pedido(num_Pedido);
                user.setNum_Pedido(num_Pedido);
            }
            
            //PASSO 5
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        return user;
    }

    @Override
    public boolean update(Pedido Pedido) {
        boolean resp = false;
        
        //PASSO 1
        Connection conn = getConnection();
        
        //PASSO 2
        String sql = "UPDATE Pedido SET num_Pedido = ?, valor = ?";
        try {
              PreparedStatement pst = conn.prepareStatement(sql);
              pst.setLong(1, Pedido.getNum_Pedido());
              pst.setDouble(2, Pedido.getValor());
             
        //PASSO 3
        int rows = pst.executeUpdate();
        
        //PASSO 4
        resp = (rows > 0);
        
        //PASSO 5
        conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resp;
    }

    @Override
    public boolean delete(Pedido Pedido) {
        boolean resp = false;
        
        //PASSO 1
        Connection conn = getConnection();
        
        //PASSO 2
        String sql = "DELETE FROM Pedido WHERE num_Pedido = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setLong(1, Pedido.getNum_Pedido());
            
        //PASSO 3
        int rows = pst.executeUpdate();
        
        //PASSO 4
        resp = (rows > 0);
        
        //PASSO 5
        conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resp;
    }
}
