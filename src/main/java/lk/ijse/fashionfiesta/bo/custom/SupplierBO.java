package lk.ijse.fashionfiesta.bo.custom;

import lk.ijse.fashionfiesta.bo.SuperBO;
import lk.ijse.fashionfiesta.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {
    public  boolean save(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException;
    public SupplierDTO get(String id) throws SQLException, ClassNotFoundException;
    public  boolean update(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;
    public  boolean remove(String sup_id) throws SQLException, ClassNotFoundException;
    public  ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException;
    public  String getSupplier() throws SQLException, ClassNotFoundException;
    public  String getSupplierOrder() throws SQLException, ClassNotFoundException ;
}
