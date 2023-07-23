package lk.ijse.fashionfiesta.bo.custom.impl;

import lk.ijse.fashionfiesta.bo.custom.CustomerBO;
import lk.ijse.fashionfiesta.dao.custom.CustomerDAO;
import lk.ijse.fashionfiesta.dao.custom.CustomerOrderDAO;
import lk.ijse.fashionfiesta.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.fashionfiesta.dao.custom.impl.CustomerOrderDAOImpl;
import lk.ijse.fashionfiesta.entity.Customer;
import lk.ijse.fashionfiesta.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = new CustomerDAOImpl();
    CustomerOrderDAO customerOrderDAO = new CustomerOrderDAOImpl();

    public  String getCustOrder() throws SQLException, ClassNotFoundException {
        return customerOrderDAO.getCustOrder();
    }
    public boolean save(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(
                customerDTO.getCustomer_Id(),
                customerDTO.getFirst_name(),
                customerDTO.getLast_name(),
                customerDTO.getStreet(),
                customerDTO.getCity(),
                customerDTO.getLane(),
                customerDTO.getContact_number()
        ));
    }

    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        return customerDAO.getAllId();
    }

    public CustomerDTO get(String id) throws SQLException, ClassNotFoundException {
        Customer customer= customerDAO.get(id);
        return new CustomerDTO(
                customer.getCustomer_id(),
                customer.getFirst_name(),
                customer.getLast_name(),
                customer.getStreet(),
                customer.getCity(),
                customer.getLane(),
                customer.getContact_number()
        );
    }

    public  boolean update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(
                customerDTO.getCustomer_Id(),
                customerDTO.getFirst_name(),
                customerDTO.getLast_name(),
                customerDTO.getStreet(),
                customerDTO.getCity(),
                customerDTO.getLane(),
                customerDTO.getContact_number()
        ));
    }

    public  boolean remove(String cust_id) throws SQLException, ClassNotFoundException {
        return customerDAO.remove(cust_id);
    }
    public  ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.getSearchIds(id);
    }

    public  String getCustomer() throws SQLException, ClassNotFoundException {
        return customerDAO.getCustomer();
    }
}
