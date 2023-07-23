package lk.ijse.fashionfiesta.bo.custom;

import lk.ijse.fashionfiesta.bo.SuperBO;
import lk.ijse.fashionfiesta.dto.CustomEntityDTO;
import lk.ijse.fashionfiesta.dto.CustomerOrderDTO;
import lk.ijse.fashionfiesta.dto.StockDTO;
import lk.ijse.fashionfiesta.tm.OrderTm;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerOrderBO extends SuperBO {
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException ;
    public ArrayList<String> getAllCustomerId() throws SQLException, ClassNotFoundException ;
    public StockDTO get(String id) throws SQLException, ClassNotFoundException;
    public  boolean CustomerOrderupdateData(ArrayList<OrderTm> details) throws SQLException, ClassNotFoundException ;
    public ArrayList<String> getAllIdCust() throws SQLException, ClassNotFoundException ;
    public  ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException ;
    public  ArrayList<String> getAllIdOrder() throws SQLException, ClassNotFoundException;
    public  boolean save(CustomerOrderDTO cusOrder) throws SQLException, ClassNotFoundException ;
    public CustomEntityDTO getCust(String id) throws SQLException, ClassNotFoundException;
    public boolean placeOrder(CustomerOrderDTO cusOrder, ArrayList<OrderTm> details) throws SQLException ;
}
