package lojaEletronicos.dao;

import lojaEletronicos.entities.Produto;
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

public class ProdutoDAO implements GenericDAO<Produto>{
    
    public String uri = "jdbc:mysql://localhost:3306/apd3";
    public String user = "root";
    public String pwd = "123456";
    
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            return DriverManager.getConnection(uri,user,pwd);
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public boolean create(Produto Produto) {
        boolean resp = false;
        
        //PASSO 1 - estabelecer a conexão
        Connection conn = getConnection();
        
        //PASSO 2 - preparar a consulta
        String sql = "INSERT INTO Produto(nome, preco, regiao, cod_Produto) VALUES(?, ?, ?, ?);";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, Produto.getNome());
            pst.setString(2, Produto.getPreco());
            pst.setString(3, Produto.getRegiao());
            pst.setString(4, Produto.getCod_Produto());
            
        //PASSO 3 - executar a consulta
        int rows = pst.executeUpdate();
        
        //PASSO 4 - tratar o resultado
        resp = (rows > 0);
        
        //PASSO 5 - fechar a conexão
        conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resp;
    }

    @Override
    public List<Produto> readAll() {
        List<Produto> users = new ArrayList<>();
        
        //PASSO 1
        Connection conn = getConnection();
        
        //PASSO 2
        String sql = "SELECT * FROM Produto";
        try {
            //Statement stm = conn.createStatement();
            
        //PASSO 3
        ResultSet rs = conn.prepareStatement(sql).executeQuery();
        
        //PASSO 4
        while (rs.next()){
            String nome = rs.getString("nome");
            String preco = rs.getString("preco");
            String regiao = rs.getString("regiao");
            String cod_Produto = rs.getNString("cod_Produto");
            long id_Produto = rs.getLong("id_Produto");
            Produto u = new Produto(nome, preco, regiao, cod_Produto);
            u.setId_Produto(id_Produto);
            users.add(u);
        }
        
        //PASSO 5
        conn.close();
        
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return users;
    }

    @Override
    public Produto readyById(long id) {
        Produto user = null;
        
        //PASSO 1
        Connection conn = getConnection();
        
        //PASSO 2
        String sql = "SELECT * FROM Produto WHERE id_Produto = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setLong(1, id);
            
        //PASSO 3
        ResultSet rs = pst.executeQuery();
        
        //PASSO 4
        while (rs.next()){
            String nome = rs.getString("nome");
            String preco = rs.getString("preco");
            String regiao = rs.getString("regiao");
            String cod_Produto = rs.getString("cod_Produto");
            
            user = new Produto(nome, preco, regiao, cod_Produto);
            user.setId_Produto(id);
        }
        
        //PASSO 5
        conn.close();
        
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return user;
    }
    
    public Produto readByUsername(String preco) {
        Produto user = null;
        //PASSO 1
        Connection conn = getConnection();
        
        //PASSO 2
        String sql = "SELECT * FROM Produto WHERE cod_Produto = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, preco);
            
            //PASSO 3
            ResultSet rs = pst.executeQuery();
            
            //PASSO 4
            while(rs.next()){
                String cod_Produto = rs.getNString("cod_Produto");
                String nome = rs.getString("nome");
                Long id = rs.getLong("id_Produto");
                String regiao = rs.getString("regiao");
                
                user = new Produto(nome, preco, regiao, cod_Produto);
                user.setId_Produto(id);
            }
            
            //PASSO 5
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        return user;
    }

    @Override
    public boolean update(Produto Produto) {
        boolean resp = false;
        
        //PASSO 1
        Connection conn = getConnection();
        
        //PASSO 2
        String sql = "UPDATE Produto SET nome = ?, preco = ?, regiao = ?, cod_Produto = ? WHERE nome = ?";
        try {
              PreparedStatement pst = conn.prepareStatement(sql);
              pst.setString(1, Produto.getNome());
              pst.setString(2, Produto.getPreco());
              pst.setString(3, Produto.getRegiao());
              pst.setString(4, Produto.getCod_Produto());
              pst.setString(5, Produto.getNome());
             
        //PASSO 3
        int rows = pst.executeUpdate();
        
        //PASSO 4
        resp = (rows > 0);
        
        //PASSO 5
        conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resp;
    }

    @Override
    public boolean delete(Produto Produto) {
        boolean resp = false;
        
        //PASSO 1
        Connection conn = getConnection();
        
        //PASSO 2
        String sql = "DELETE FROM Produto WHERE id_Produto = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setLong(1, Produto.getId_Produto());
            
        //PASSO 3
        int rows = pst.executeUpdate();
        
        //PASSO 4
        resp = (rows > 0);
        
        //PASSO 5
        conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resp;
    }
}
