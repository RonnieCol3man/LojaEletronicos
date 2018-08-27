package lojaEletronicos.entities;

import java.util.Objects;

public class Produto {
    private long id_Produto;
    private String nome, preco, regiao, cod_Produto;
    
    public Produto(Long id_Produto, String regiao){
        this.id_Produto = id_Produto;
        this.regiao = regiao;
    }

    public Produto(String nome, String preco, String regiao, String cod_Produto) {
        this.nome = nome;
        this.preco = preco;
        this.regiao = regiao;
        this.cod_Produto = cod_Produto;
        this.id_Produto = 0;
    }

    public long getId_Produto() {
        return id_Produto;
    }

    public void setId_Produto(long id_Produto) {
        this.id_Produto = id_Produto;
    }
    
    public String getCod_Produto() {
        return cod_Produto;
    }

    public void setCod_Produto(String cod_Produto) {
        this.cod_Produto = cod_Produto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    @Override
    public String toString() {
        return "Produto{" + "id_Produto=" + id_Produto + ", nome=" + nome + ", preco=" + preco + ", regiao=" + regiao + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (int) (this.id_Produto ^ (this.id_Produto >>> 32));
        hash = 53 * hash + Objects.hashCode(this.nome);
        hash = 53 * hash + Objects.hashCode(this.preco);
        hash = 53 * hash + Objects.hashCode(this.regiao);
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
        final Produto other = (Produto) obj;
        if (this.id_Produto != other.id_Produto) {
            return false;
        }
        if (!Objects.equals(this.preco, other.preco)) {
            return false;
        }
        return true;
    }

    public void setNum_Pedido(long num_Pedido) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
