package lk.ijse.fashionfiesta.bo.custom.impl;

import lk.ijse.fashionfiesta.bo.custom.SupplierBO;
import lk.ijse.fashionfiesta.dao.custom.SupplierDAO;
import lk.ijse.fashionfiesta.dao.custom.SupplierOrderDAO;
import lk.ijse.fashionfiesta.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.fashionfiesta.dao.custom.impl.SupplierOrderDAOImpl;
import lk.ijse.fashionfiesta.entity.Supplier;
import lk.ijse.fashionfiesta.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {
    SupplierOrderDAO supplierOrderDAO = new SupplierOrderDAOImpl();
    SupplierDAO supplierDAO = new SupplierDAOImpl();

    public  boolean save(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(new Supplier(
                supplierDTO.getSupplier_id(),
                supplierDTO.getSupplier_Fname(),
                supplierDTO.getSupplier_Fname(),
                supplierDTO.getStreet(),
                supplierDTO.getCity(),
                supplierDTO.getLane(),
                supplierDTO.getContact()
        ));
    }
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        return supplierDAO.getAllId();
    }
    public SupplierDTO get(String id) throws SQLException, ClassNotFoundException {
        Supplier supplier= supplierDAO.get(id);
       return new SupplierDTO(
                supplier.getSupplier_id(),
                supplier.getFirst_name(),
                supplier.getLast_name(),
                supplier.getStreet(),
                supplier.getCity(),
                supplier.getLane(),
                supplier.getContact_number()
        );
    }
    public  boolean update(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(
                supplierDTO.getSupplier_id(),
                supplierDTO.getSupplier_Fname(),
                supplierDTO.getSupplier_Lname(),
                supplierDTO.getStreet(),
                supplierDTO.getCity(),
                supplierDTO.getLane(),
                supplierDTO.getContact()
        ));
    }
    public  boolean remove(String sup_id) throws SQLException, ClassNotFoundException {
        return supplierDAO.remove(sup_id);
    }
    public  ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.getSearchIds(id) ;
    }
    public  String getSupplier() throws SQLException, ClassNotFoundException {
        return supplierDAO.getSupplier();
    }
    public  String getSupplierOrder() throws SQLException, ClassNotFoundException {
        return supplierOrderDAO.getSupplierOrder();
    }
}
