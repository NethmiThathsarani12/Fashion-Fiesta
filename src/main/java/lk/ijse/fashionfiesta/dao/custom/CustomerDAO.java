package lk.ijse.fashionfiesta.dao.custom;

import lk.ijse.fashionfiesta.dao.CrudDAO;
import lk.ijse.fashionfiesta.entity.Customer;

import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO<Customer,String> {
//    public  boolean addCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
//    public  ArrayList<String> getAllId() throws SQLException, ClassNotFoundException ;
//    public CustomerDTO get(String id) throws SQLException, ClassNotFoundException;
//    public  boolean update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
//    public  boolean remove(String cust_id) throws SQLException, ClassNotFoundException;
//    public  ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException;

    public  String getCustomer() throws SQLException, ClassNotFoundException;
}
