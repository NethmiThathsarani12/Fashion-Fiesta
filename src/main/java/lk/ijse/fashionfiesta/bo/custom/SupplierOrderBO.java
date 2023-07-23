package lk.ijse.fashionfiesta.bo.custom;

import lk.ijse.fashionfiesta.bo.SuperBO;
import lk.ijse.fashionfiesta.dto.StockDTO;
import lk.ijse.fashionfiesta.dto.SupplierOrderDTO;
import lk.ijse.fashionfiesta.dto.SupplierOrderDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierOrderBO extends SuperBO {
    public StockDTO get(String id) throws SQLException, ClassNotFoundException ;
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException;
    public ArrayList<String> getAllItemId() throws SQLException, ClassNotFoundException;
    public ArrayList<String> getAllSupplierOrderId() throws SQLException, ClassNotFoundException;
    public  boolean updateData(ArrayList<SupplierOrderDetailsDTO> details) throws SQLException, ClassNotFoundException;
    public ArrayList<String> getAllIdSup() throws SQLException, ClassNotFoundException;
    public  ArrayList<String> getAll() throws SQLException, ClassNotFoundException;
    public  ArrayList<String> getAllIdOrder() throws SQLException, ClassNotFoundException ;
    public boolean placeOrder(SupplierOrderDTO supOrder, ArrayList<SupplierOrderDetailsDTO> details_list) ;
}
