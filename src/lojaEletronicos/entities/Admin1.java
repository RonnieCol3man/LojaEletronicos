package lojaEletronicos.entities;

import java.util.Objects;

public class Admin1 {
    private long id_Admin1;
    private String nome, username, password;
    
    public Admin1(Long id_Admin1, String password){
        this.id_Admin1 = id_Admin1;
        this.password = password;
    }

    public Admin1(String nome, String username, String password) {
        this.nome = nome;
        this.username = username;
        this.password = password;
        this.id_Admin1 = 0;
    }

    public long getId_Admin1() {
        return id_Admin1;
    }

    public void setId_Admin1(long id_Admin1) {
        this.id_Admin1 = id_Admin1;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin1{" + "id_Admin1=" + id_Admin1 + ", username=" + username + ", password=" + password + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (int) (this.id_Admin1 ^ (this.id_Admin1 >>> 32));
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
        final Admin1 other = (Admin1) obj;
        if (this.id_Admin1 != other.id_Admin1) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }
}
