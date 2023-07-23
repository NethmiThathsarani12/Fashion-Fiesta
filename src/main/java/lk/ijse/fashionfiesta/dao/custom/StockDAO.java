package lk.ijse.fashionfiesta.dao.custom;

import lk.ijse.fashionfiesta.dao.CrudDAO;
import lk.ijse.fashionfiesta.entity.Item;
import lk.ijse.fashionfiesta.dto.SupplierOrderDetailsDTO;
import lk.ijse.fashionfiesta.tm.OrderTm;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StockDAO extends CrudDAO<Item,String> {

    // boolean addStock(StockDTO stockDTO) throws SQLException, ClassNotFoundException;

    // ArrayList<String> getAllId() throws SQLException, ClassNotFoundException;

   //StockDTO get(String id) throws SQLException, ClassNotFoundException;

  // boolean update(StockDTO stockDTO) throws SQLException, ClassNotFoundException;

  // boolean remove(String emp_id) throws SQLException, ClassNotFoundException ;

   boolean updateData(ArrayList<SupplierOrderDetailsDTO> details) throws SQLException, ClassNotFoundException ;

    boolean CustomerOrderupdateData(ArrayList<OrderTm> details) throws SQLException, ClassNotFoundException;

   // ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException;

     String getStock() throws SQLException, ClassNotFoundException;
}
