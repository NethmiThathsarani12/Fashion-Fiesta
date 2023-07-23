package lk.ijse.fashionfiesta.dao.custom;

import lk.ijse.fashionfiesta.dao.CrudDAO;
import lk.ijse.fashionfiesta.entity.CustomerOrder;
import lk.ijse.fashionfiesta.tm.OrderTm;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerOrderDAO extends CrudDAO<CustomerOrder,String> {

//    boolean addCusOrder(CustomerOrderDTO cusOrder) throws SQLException, ClassNotFoundException;
//
//    ArrayList<String> getAll() throws SQLException, ClassNotFoundException;
//
//    CusOrderTmDTO get(String id) throws SQLException, ClassNotFoundException;
//
//    boolean update(CustomerOrderDTO cusOrder) throws SQLException, ClassNotFoundException;
//
//     boolean remove(String customer_id) throws SQLException, ClassNotFoundException;

     boolean addCustomerOrderDetails(CustomerOrder customerOrder, ArrayList<OrderTm> details) throws SQLException, ClassNotFoundException;

 //    ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException;

   String getCustOrder() throws SQLException, ClassNotFoundException;

    String getCustomerOrder(int i) throws SQLException, ClassNotFoundException;

  //   ArrayList<String> AllId() throws SQLException, ClassNotFoundException;

}
