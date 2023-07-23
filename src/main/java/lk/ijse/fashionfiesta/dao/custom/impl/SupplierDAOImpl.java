package lk.ijse.fashionfiesta.dao.custom.impl;

import lk.ijse.fashionfiesta.dao.custom.SupplierDAO;
import lk.ijse.fashionfiesta.entity.Supplier;
import lk.ijse.fashionfiesta.dao.custom.impl.utill.SQLUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class SupplierDAOImpl implements SupplierDAO {

    @Override
    public  boolean save(Supplier supplier) throws SQLException, ClassNotFoundException {
        return SQLUtill.crudUtil("INSERT INTO supplier VALUES(?,?,?,?,?,?,?)",
                supplier.getSupplier_id(),
                supplier.getFirst_name(),
                supplier.getLast_name(),
                supplier.getStreet(),
                supplier.getCity(),
                supplier.getLane(),
                supplier.getContact_number()
        );
    }

    public  ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.crudUtil("SELECT supplier_id FROM supplier ORDER BY LENGTH(supplier_id),supplier_id");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }

    public Supplier get(String id) throws SQLException, ClassNotFoundException {
        Supplier supplier =new Supplier();
        ResultSet set = SQLUtill.crudUtil("SELECT * from supplier where supplier_id=?", id);
        if (set.next()){
            supplier.setSupplier_id(set.getString(1));
            supplier.setStreet(set.getString(4));
            supplier.setCity(set.getString(5));
            supplier.setLane(set.getString(6));
            supplier.setFirst_name(set.getString(2));
            supplier.setLast_name(set.getString(3));
            supplier.setContact_number(set.getString(7));
        }
        return supplier;
    }

    public  boolean update(Supplier supplier) throws SQLException, ClassNotFoundException {
        return SQLUtill.crudUtil("UPDATE Supplier SET street=?,city=?,lane=?,first_name=?,last_name=?,contact_number=? WHERE supplier_id=?",
                supplier.getStreet(),
                supplier.getCity(),
                supplier.getLane(),
                supplier.getFirst_name(),
                supplier.getLast_name(),
                supplier.getContact_number(),
                supplier.getSupplier_id()
        );
    }

        public  boolean remove(String sup_id) throws SQLException, ClassNotFoundException {
        return SQLUtill.crudUtil("DELETE FROM supplier WHERE supplier_id=?",sup_id);
    }
    public  ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException {
        ArrayList<String>ids=new ArrayList<>();
        ResultSet set= SQLUtill.crudUtil("SELECT supplier_id from supplier where supplier_id LIKE ? or first_name LIKE ? or last_name LIKE ?",id+"%",id+"%",id+"%");
        while (set.next()){
            ids.add(set.getString(1));
        }
        return ids ;
    }
    public  String getSupplier() throws SQLException, ClassNotFoundException {
        ResultSet set= SQLUtill.crudUtil("SELECT COUNT(supplier_id) FROM supplier");
        String count=null;
        if (set.next()){
            count=set.getString(1);
        }
        return count;
    }
}
