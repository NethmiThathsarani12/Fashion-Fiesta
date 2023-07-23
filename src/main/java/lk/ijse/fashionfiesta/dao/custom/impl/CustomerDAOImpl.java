package lk.ijse.fashionfiesta.dao.custom.impl;

import lk.ijse.fashionfiesta.dao.custom.CustomerDAO;
import lk.ijse.fashionfiesta.entity.Customer;
import lk.ijse.fashionfiesta.dao.custom.impl.utill.SQLUtill;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    public boolean save(Customer customer) throws SQLException, ClassNotFoundException {
        return SQLUtill.crudUtil("INSERT INTO customer VALUES(?,?,?,?,?,?,?)",
                customer.getCustomer_id(),
                customer.getFirst_name(),
                customer.getLast_name(),
                customer.getStreet(),
                customer.getCity(),
                customer.getLane(),
                customer.getContact_number()
        );
    }
    public  ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.crudUtil("SELECT customer_id FROM customer ORDER BY LENGTH(customer_id),customer_id");
        ArrayList<String> list = new ArrayList<>();
        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }
    public Customer get(String id) throws SQLException, ClassNotFoundException {
        Customer customer =new Customer();
        ResultSet set = SQLUtill.crudUtil("SELECT * from customer where customer_id=?", id);
        if (set.next()){
            customer.setCustomer_id(set.getString(1));
            customer.setFirst_name(set.getString(2));
            customer.setLast_name(set.getString(3));
            customer.setStreet(set.getString(4));
            customer.setCity(set.getString(5));
            customer.setLane(set.getString(6));
            customer.setContact_number(set.getString(7));
        }
        return customer;
    }
    public  boolean update(Customer customer) throws SQLException, ClassNotFoundException {

        return SQLUtill.crudUtil("UPDATE Customer SET first_name=?,last_name=?,street=?,city=?,lane=?,contact_number=? WHERE customer_id=?",
               customer.getFirst_name(),
                customer.getLast_name(),
                customer.getStreet(),
                customer.getCity(),
                customer.getLane(),
                customer.getContact_number(),
                customer.getCustomer_id()
        );
    }
    public  boolean remove(String cust_id) throws SQLException, ClassNotFoundException {
        return SQLUtill.crudUtil("DELETE FROM customer WHERE customer_id=?",cust_id);
    }
    public  ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException {
        ArrayList<String>ids=new ArrayList<>();
        ResultSet set= SQLUtill.crudUtil("SELECT customer_id from customer where customer_id LIKE ? or first_name LIKE ? or last_name LIKE ?",id+"%",id+"%",id+"%");
        while (set.next()){
            ids.add(set.getString(1));
        }
        return ids ;
    }

    public  String getCustomer() throws SQLException, ClassNotFoundException {
        ResultSet set= SQLUtill.crudUtil("SELECT COUNT(customer_id) FROM customer");
        String count=null;
        if (set.next()){
            count=set.getString(1);
        }
        return count;
    }
}





