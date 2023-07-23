package lk.ijse.fashionfiesta.bo.custom.impl;

import lk.ijse.fashionfiesta.bo.custom.CustomerOrderBO;
import lk.ijse.fashionfiesta.dao.custom.CustomerDAO;
import lk.ijse.fashionfiesta.dao.custom.CustomerOrderDAO;
import lk.ijse.fashionfiesta.dao.custom.QuaryDAO;
import lk.ijse.fashionfiesta.dao.custom.StockDAO;
import lk.ijse.fashionfiesta.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.fashionfiesta.dao.custom.impl.CustomerOrderDAOImpl;
import lk.ijse.fashionfiesta.dao.custom.impl.QuaryDAOImpl;
import lk.ijse.fashionfiesta.dao.custom.impl.StockDAOImpl;
import lk.ijse.fashionfiesta.db.DBConnection;
import lk.ijse.fashionfiesta.entity.CustomerOrder;
import lk.ijse.fashionfiesta.entity.Item;
import lk.ijse.fashionfiesta.dto.CustomEntityDTO;
import lk.ijse.fashionfiesta.dto.CustomerOrderDTO;
import lk.ijse.fashionfiesta.dto.StockDTO;
import lk.ijse.fashionfiesta.tm.OrderTm;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerOrderBOImpl implements CustomerOrderBO {
    StockDAO stockDAO = new StockDAOImpl();
    CustomerDAO customerDAO = new CustomerDAOImpl();
    CustomerOrderDAO customerOrderDAO =  new CustomerOrderDAOImpl();
    QuaryDAO quaryDAO = new QuaryDAOImpl();

    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        return stockDAO.getAllId();
    }

    @Override
    public ArrayList<String> getAllCustomerId() throws SQLException, ClassNotFoundException {
        return customerDAO.getAllId();
    }

    public StockDTO get(String id) throws SQLException, ClassNotFoundException {
        Item item = stockDAO.get(id);
        return new StockDTO(
                item.getItem_id(),
                item.getItem_name(),
                item.getQuantity(),
                item.getPrice(),
                item.getModel_color(),
                item.getCategary()
        );
    }
    public  boolean CustomerOrderupdateData(ArrayList<OrderTm> details) throws SQLException, ClassNotFoundException {
        return stockDAO.CustomerOrderupdateData(details);
    }
    public ArrayList<String> getAllIdCust() throws SQLException, ClassNotFoundException {
        return customerDAO.getAllId();
    }
    public  ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException {
        return customerOrderDAO.getSearchIds(id);
    }
    public  ArrayList<String> getAllIdOrder() throws SQLException, ClassNotFoundException {
        return customerOrderDAO.getAllId();
    }
    public  boolean save(CustomerOrderDTO customerOrderDTO) throws SQLException, ClassNotFoundException {
        return customerOrderDAO.save(new CustomerOrder(
                customerOrderDTO.getCustomer_id(),
                customerOrderDTO.getCustomer_order_id(),
                customerOrderDTO.getCustomer_order_date(),
                customerOrderDTO.getCustomer_order_time(),
                customerOrderDTO.getPayment()

        ));
    }
    public CustomEntityDTO getCust(String id) throws SQLException, ClassNotFoundException {
        return quaryDAO.getCust(id);
    }
    public boolean placeOrder(CustomerOrderDTO customerOrderDTO, ArrayList<OrderTm> details) throws SQLException {

        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            if (customerOrderDAO.save(new CustomerOrder(
                    customerOrderDTO.getCustomer_id(),
                    customerOrderDTO.getCustomer_order_id(),
                    customerOrderDTO.getCustomer_order_date(), customerOrderDTO.getCustomer_order_time(),
                    customerOrderDTO.getPayment()
            ))) {
                System.out.println("1");
                if (customerOrderDAO.addCustomerOrderDetails(new CustomerOrder(
                        customerOrderDTO.getCustomer_id(),
                        customerOrderDTO.getCustomer_order_id(),
                        customerOrderDTO.getCustomer_order_date(),
                        customerOrderDTO.getCustomer_order_time(),
                        customerOrderDTO.getPayment()
                ),details)) {
                    System.out.println("2");
                    if (stockDAO.CustomerOrderupdateData(details)) {
                        System.out.println("3");
                        connection.commit();
                    } else {
                        System.out.println("3-else");
                        connection.rollback();
                        return false;
                    }
                } else {
                    System.out.println("2-else");
                    connection.rollback();
                    return false;
                }

            } else {
                System.out.println("1-else");
                connection.rollback();
                return false;
            }
        } catch (ClassNotFoundException | SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        } finally {
            connection.setAutoCommit(true);
        }
        return true;
    }
}
