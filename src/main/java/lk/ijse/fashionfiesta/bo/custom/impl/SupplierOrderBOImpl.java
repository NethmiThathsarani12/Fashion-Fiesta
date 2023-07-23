package lk.ijse.fashionfiesta.bo.custom.impl;

import lk.ijse.fashionfiesta.bo.custom.SupplierOrderBO;
import lk.ijse.fashionfiesta.dao.custom.StockDAO;
import lk.ijse.fashionfiesta.dao.custom.SupplierDAO;
import lk.ijse.fashionfiesta.dao.custom.SupplierOrderDAO;
import lk.ijse.fashionfiesta.dao.custom.impl.StockDAOImpl;
import lk.ijse.fashionfiesta.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.fashionfiesta.dao.custom.impl.SupplierOrderDAOImpl;
import lk.ijse.fashionfiesta.db.DBConnection;
import lk.ijse.fashionfiesta.entity.Item;
import lk.ijse.fashionfiesta.entity.SupplierOrder;
import lk.ijse.fashionfiesta.dto.StockDTO;
import lk.ijse.fashionfiesta.dto.SupplierOrderDTO;
import lk.ijse.fashionfiesta.dto.SupplierOrderDetailsDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierOrderBOImpl implements SupplierOrderBO {
    StockDAO stockDAO = new StockDAOImpl();
    SupplierDAO supplierDAO = new SupplierDAOImpl();
    SupplierOrderDAO supplierOrderDAO = new SupplierOrderDAOImpl();

    public StockDTO get(String id) throws SQLException, ClassNotFoundException {
        Item item= stockDAO.get(id);
        return new StockDTO(
                item.getItem_id(),
                item.getItem_name(),
                item.getQuantity(),
                item.getPrice(),
                item.getModel_color(),
                item.getCategary()
        );
    }
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        return supplierDAO.getAllId();
    }

    @Override
    public ArrayList<String> getAllItemId() throws SQLException, ClassNotFoundException {
        return stockDAO.getAllId();
    }

    @Override
    public ArrayList<String> getAllSupplierOrderId() throws SQLException, ClassNotFoundException {
        return supplierOrderDAO.getAllId();
    }

    public  boolean updateData(ArrayList<SupplierOrderDetailsDTO> details) throws SQLException, ClassNotFoundException {
        return stockDAO.updateData(details);
    }
    public ArrayList<String> getAllIdSup() throws SQLException, ClassNotFoundException {
        return supplierDAO.getAllId();
    }
    public  ArrayList<String> getAll() throws SQLException, ClassNotFoundException {
        return supplierOrderDAO.getAll();
    }
    public  ArrayList<String> getAllIdOrder() throws SQLException, ClassNotFoundException {
        return supplierOrderDAO.getAllId();
    }
    public boolean placeOrder(SupplierOrderDTO supOrder, ArrayList<SupplierOrderDetailsDTO> details_list) {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            if (supplierOrderDAO.save(new SupplierOrder(
                    supOrder.getSupplier_id(),
                    supOrder.getSupplier_order_id(),
                    supOrder.getSupplier_order_date(),
                    supOrder.getSupplier_order_time(),
                    Double.parseDouble(supOrder.getPayment() )
            ))){
                if (supplierOrderDAO.addSupplierOrderDetails(details_list)){
                    if (stockDAO.updateData(details_list)){
                        connection.commit();
                        return true;
                    }else {
                        connection.rollback();
                        return false;
                    }

                }else {
                    connection.rollback();
                    return false;
                }
            }else {
                connection.rollback();
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return true;
    }
}
