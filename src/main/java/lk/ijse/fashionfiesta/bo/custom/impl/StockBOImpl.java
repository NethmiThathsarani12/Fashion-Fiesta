package lk.ijse.fashionfiesta.bo.custom.impl;

import lk.ijse.fashionfiesta.bo.custom.StockBO;
import lk.ijse.fashionfiesta.dao.custom.StockDAO;
import lk.ijse.fashionfiesta.dao.custom.impl.StockDAOImpl;
import lk.ijse.fashionfiesta.entity.Item;
import lk.ijse.fashionfiesta.dto.StockDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class StockBOImpl implements StockBO {
    StockDAO stockDAO = new StockDAOImpl();
    public  boolean update(StockDTO stockDTO) throws SQLException, ClassNotFoundException {
        return stockDAO.update(new Item(
                stockDTO.getItem_id(),
                stockDTO.getItem_name(),
                stockDTO.getQuantity(),
                stockDTO.getPrice(),
                stockDTO.getModel_color(),
                stockDTO.getCategory()
        ));
    }
    public  boolean remove(String emp_id) throws SQLException, ClassNotFoundException {
        return stockDAO.remove(emp_id);
    }
    public boolean save(StockDTO stockDTO) throws SQLException, ClassNotFoundException {
        return stockDAO.save(new Item(
                stockDTO.getItem_id(),
                stockDTO.getItem_name(),
                stockDTO.getQuantity(),
                stockDTO.getPrice(),
                stockDTO.getModel_color(),
                stockDTO.getCategory()
        ));
    }
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        return stockDAO.getAllId();
    }
    public StockDTO get(String id) throws SQLException, ClassNotFoundException {
        Item item = stockDAO.get(id);
        return new StockDTO(
                item.getItem_id(),
                item.getItem_name(),
                item.getQuantity(),
                item.getPrice(),
                item.getModel_color(),
                item.getCategary()
        );
    }
    public  ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException {
        return stockDAO.getSearchIds(id);
    }
    public  String getStock() throws SQLException, ClassNotFoundException {
        return stockDAO.getStock();
    }
}
