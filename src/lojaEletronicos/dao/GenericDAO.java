package lojaEletronicos.dao;

import java.util.List;

public interface GenericDAO<E> {
    public boolean create (E e);
    
    public List<E> readAll();
    public E readyById(long id);
    
    public boolean update (E e);
    public boolean delete (E e);
    
}
