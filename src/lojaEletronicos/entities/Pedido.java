package lojaEletronicos.entities;

import java.util.Objects;

public class Pedido {
    private long num_Pedido;
    private int valor;
    
    public Pedido (Long num_Pedido){
        this.num_Pedido = num_Pedido;
    }
    
    public Pedido(Long num_Pedido, int valor){
        this.num_Pedido = num_Pedido;
        this.valor = valor;
    }

    public long getNum_Pedido() {
        return num_Pedido;
    }

    public void setNum_Pedido(long num_Pedido) {
        this.num_Pedido = num_Pedido;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Produto{" + "num_Pedido=" + num_Pedido + ", valor=" + valor + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (int) (this.num_Pedido ^ (this.num_Pedido >>> 32));
        hash = 53 * hash + Objects.hashCode(this.valor);
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
        final Pedido other = (Pedido) obj;
        if (this.num_Pedido != other.num_Pedido) {
            return false;
        }
        return true;
    }
}
