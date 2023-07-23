package lk.ijse.fashionfiesta.bo.custom;

import lk.ijse.fashionfiesta.bo.SuperBO;
import lk.ijse.fashionfiesta.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;


public interface CustomerBO extends SuperBO {

    String getCustOrder() throws SQLException, ClassNotFoundException;

    boolean save(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    ArrayList<String> getAllId() throws SQLException, ClassNotFoundException;

    CustomerDTO get(String id) throws SQLException, ClassNotFoundException;

    boolean update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    boolean remove(String cust_id) throws SQLException, ClassNotFoundException;

    ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException;

    String getCustomer() throws SQLException, ClassNotFoundException;

}
