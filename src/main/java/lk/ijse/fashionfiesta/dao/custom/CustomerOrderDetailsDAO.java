package lk.ijse.fashionfiesta.dao.custom;

import lk.ijse.fashionfiesta.dao.CrudDAO;
import lk.ijse.fashionfiesta.entity.CustomerOrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerOrderDetailsDAO extends CrudDAO<CustomerOrderDetails,String> {

    ArrayList<String> getAllId() throws SQLException, ClassNotFoundException;

    CustomerOrderDetails get(String id) throws SQLException, ClassNotFoundException;
}
