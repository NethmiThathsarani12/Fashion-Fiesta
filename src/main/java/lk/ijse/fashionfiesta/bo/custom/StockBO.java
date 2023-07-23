package lk.ijse.fashionfiesta.bo.custom;

import lk.ijse.fashionfiesta.bo.SuperBO;
import lk.ijse.fashionfiesta.dto.StockDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StockBO extends SuperBO {
    public  boolean update(StockDTO stockDTO) throws SQLException, ClassNotFoundException;
    public  boolean remove(String emp_id) throws SQLException, ClassNotFoundException;
    public boolean save(StockDTO stockDTO) throws SQLException, ClassNotFoundException ;
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException ;
    public StockDTO get(String id) throws SQLException, ClassNotFoundException;
    public  ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException;
    public  String getStock() throws SQLException, ClassNotFoundException;
}
