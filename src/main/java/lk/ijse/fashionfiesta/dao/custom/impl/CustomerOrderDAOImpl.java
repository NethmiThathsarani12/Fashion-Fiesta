package lk.ijse.fashionfiesta.dao.custom.impl;

import lk.ijse.fashionfiesta.dao.custom.CustomerOrderDAO;
import lk.ijse.fashionfiesta.entity.CustomerOrder;
import lk.ijse.fashionfiesta.tm.OrderTm;
import lk.ijse.fashionfiesta.utill.DateTimeUtil;
import lk.ijse.fashionfiesta.dao.custom.impl.utill.SQLUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerOrderDAOImpl implements CustomerOrderDAO {
    public  boolean save(CustomerOrder customerOrder) throws SQLException, ClassNotFoundException {
        System.out.println(customerOrder.toString());
        return SQLUtill.crudUtil("INSERT INTO customer_order VALUES(?,?,?,?,?)",
                customerOrder.getCustomer_id(),
                customerOrder.getCustomer_order_id(),
                customerOrder.getCustomer_order_date(),
                customerOrder.getCustomer_order_time(),
                customerOrder.getPayment()
        );
    }

    public  ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.crudUtil("SELECT customer_order_id FROM customer_order ORDER BY LENGTH(customer_order_id),customer_order_id");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }
//    public  ArrayList<String> AllId() throws SQLException, ClassNotFoundException {
////SELECT customer_order_id FROM customer_order ORDER BY LENGTH(customer_order_id),customer_order_id
//
//        ResultSet set = SQLUtill.crudUtil("SELECT customer_order_id FROM customer_order ORDER BY LENGTH(customer_order_id),customer_order_id");
//        ArrayList<String> list = new ArrayList<>();
//
//        while (set.next()) {
//            list.add(set.getString(1));
//        }
//        return list;
//    }

    public CustomerOrder get(String id) throws SQLException, ClassNotFoundException {
        CustomerOrder customerOrder = new CustomerOrder();
        ResultSet set = SQLUtill.crudUtil("SELECT * from customer_order where customer_id=?", id);
        if (set.next()) {
            customerOrder.setCustomer_id(set.getString(1));
            customerOrder.setCustomer_order_id(set.getString(2));
            customerOrder.setCustomer_order_date(set.getString(3));
            customerOrder.setCustomer_order_time(set.getString(4));
            customerOrder.setPayment(set.getString(5));

        }
        return customerOrder;
    }

    public  boolean update(CustomerOrder customerOrder) throws SQLException, ClassNotFoundException {
        return SQLUtill.crudUtil("UPDATE customer_order SET customer_order_time=?,customer_order_date=?,customer_order_id=? WHERE customer_id=?",
                customerOrder.getCustomer_id(),
                customerOrder.getCustomer_order_id(),
                customerOrder.getCustomer_order_date(),
                customerOrder.getPayment()

        );
    }

    public  boolean remove(String customer_id) throws SQLException, ClassNotFoundException {
        return SQLUtill.crudUtil("DELETE FROM customer_order WHERE supplier_id=?", customer_id);
    }

    public  boolean addCustomerOrderDetails(CustomerOrder customerOrder, ArrayList<OrderTm> details) throws SQLException, ClassNotFoundException {
        for (int i = 0; i < details.size(); i++) {
            System.out.println( details.get(i).toString());
            if (SQLUtill.crudUtil("INSERT INTO customer_order_details VALUES(?,?,?,?)",
                    customerOrder.getCustomer_order_id(),
                    details.get(i).getItem_Id(),
                    details.get(i).getQty(),
                    details.get(i).getPrice()
            )){}else {
                return false;
            }

        }
        return true;

    }

//    public static boolean placeOrder(CustomerOrder cusOrder, ArrayList<OrderTm> details) throws SQLException {
//
//        Connection connection = null;
//        try {
//            connection = DBConnection.getInstance().getConnection();
//            connection.setAutoCommit(false);
//
//            if (CustomerOrderDAOImpl.addCusOrder(cusOrder)) {
//                System.out.println("1");
//                if (CustomerOrderDAOImpl.addCustomerOrderDetails(cusOrder,details)) {
//                    System.out.println("2");
//                    if (StockDAOImpl.CustomerOrderupdateData(details)) {
//                        System.out.println("3");
//                        connection.commit();
//                    } else {
//                       System.out.println("3-else");
//                        connection.rollback();
//                        return false;
//                    }
//                } else {
//                    System.out.println("2-else");
//                    connection.rollback();
//                    return false;
//                }
//
//            } else {
//                System.out.println("1-else");
//                connection.rollback();
//                return false;
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            connection.rollback();
//            throw new RuntimeException(e);
//        } finally {
//            connection.setAutoCommit(true);
//        }
//        return true;
//    }

    public  ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException {
        ArrayList<String> ids = new ArrayList<>();
        ResultSet set = SQLUtill.crudUtil("SELECT customer_id from customer_order where customer_id LIKE ? or customer_order_id LIKE ? or customer_order_date LIKE ?", id + "%", id + "%", id + "%");
        while (set.next()) {
            ids.add(set.getString(1));
        }
        return ids;
    }

    public  String getCustOrder() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.crudUtil("SELECT COUNT(customer_order_id) FROM customer_order");
        String count = null;
        if (set.next()) {
            count = set.getString(1);
        }
        return count;
    }

    public  String getCustomerOrder(int i) throws SQLException, ClassNotFoundException {
        String dateNow = DateTimeUtil.dateNow();
        String[] date = dateNow.split("-");
        String currentDate = null;
        if (String.valueOf(i).length() == 1) {
            currentDate = "0" + i;
        } else {
            currentDate = String.valueOf(i);
        }
        ResultSet set = SQLUtill.crudUtil("SELECT payment FROM customer_order WHERE customer_order_date =?", date[0] + "-" + date[1] + "-" + currentDate);

        while (set.next()) {
            return set.getString(1);
        }
        return "0";
    }
}
