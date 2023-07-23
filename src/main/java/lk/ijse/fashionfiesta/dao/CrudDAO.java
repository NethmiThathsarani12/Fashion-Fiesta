package lk.ijse.fashionfiesta.dao;

import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T , ID> extends SuperDAO{

    boolean save(T dto) throws SQLClientInfoException,ClassNotFoundException, SQLException;
    boolean update(T dto) throws SQLException, ClassNotFoundException;
    boolean remove(ID id) throws SQLException, ClassNotFoundException;
    ArrayList<ID> getAllId() throws SQLException, ClassNotFoundException;
    ArrayList<ID> getSearchIds(ID id) throws SQLException, ClassNotFoundException;
    T get(ID id) throws SQLException, ClassNotFoundException;


}
