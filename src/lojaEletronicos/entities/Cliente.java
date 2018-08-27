package lojaEletronicos.entities;

import java.util.Objects;

public class Cliente {
    private long id_Cliente;
    private String nome, address, tel, username, password;
    
    public Cliente(Long id_Cliente, String password){
        this.id_Cliente = id_Cliente;
        this.password = password;
    }

    public Cliente(String nome, String address, String tel, String username, String password) {
        this.nome = nome;
        this.address = address;
        this.tel = tel;
        this.username = username;
        this.password = password;
        this.id_Cliente = 0;
    }
    
    public Cliente (String nome, String username, String password){
        this.nome = nome;
        this.username = username;
        this.password = password;
    }

    public long getId_Cliente() {
        return id_Cliente;
    }

    public void setId_Cliente(long id_Cliente) {
        this.id_Cliente = id_Cliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String toString() {
        return "Cliente{" + "id_Cliente=" + id_Cliente + ", nome=" + nome + ", endereço=" + address + ", telefone=" + tel +   ", username=" + username + ", password=" + password + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (int) (this.id_Cliente ^ (this.id_Cliente >>> 32));
        hash = 53 * hash + Objects.hashCode(this.nome);
        hash = 53 * hash + Objects.hashCode(this.username);
        hash = 53 * hash + Objects.hashCode(this.password);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (this.id_Cliente != other.id_Cliente) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }
}
