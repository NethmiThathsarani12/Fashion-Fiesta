package lk.ijse.fashionfiesta.dao.custom;

import lk.ijse.fashionfiesta.dao.CrudDAO;
import lk.ijse.fashionfiesta.entity.Supplier;

import java.sql.SQLException;


public interface SupplierDAO extends CrudDAO<Supplier,String> {

   // boolean addSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException ;

    // ArrayList<String> getAllId() throws SQLException, ClassNotFoundException;

    // SupplierDTO get(String id) throws SQLException, ClassNotFoundException;

   //  boolean update(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;

    //boolean remove(String sup_id) throws SQLException, ClassNotFoundException;

     String getSupplier() throws SQLException, ClassNotFoundException;

   // ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException;
}
