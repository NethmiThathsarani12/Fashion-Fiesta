package lk.ijse.fashionfiesta.dao.custom;

import lk.ijse.fashionfiesta.dao.CrudDAO;
import lk.ijse.fashionfiesta.entity.SupplierOrder;
import lk.ijse.fashionfiesta.dto.SupplierOrderDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierOrderDAO extends CrudDAO<SupplierOrder,String> {
   // public  boolean addSupOrder(SupplierOrderDTO supOrder) throws SQLException, ClassNotFoundException ;
    public  ArrayList<String> getAll() throws SQLException, ClassNotFoundException;
   // public SupplierOrderDTO get(String id) throws SQLException, ClassNotFoundException ;
   // public  boolean update(SupplierOrderDTO supOrder) throws SQLException, ClassNotFoundException;
   // public  boolean remove(String supplier_id) throws SQLException, ClassNotFoundException ;
    public  boolean addSupplierOrderDetails(ArrayList<SupplierOrderDetailsDTO> details) throws SQLException, ClassNotFoundException;

    //    public static boolean placeOrder(SupplierOrder supOrder, ArrayList<SupplierOrderDetails> details_list) {
//        Connection connection = null;
//        try {
//            connection = DBConnection.getInstance().getConnection();
//            connection.setAutoCommit(false);
//
//            if (SupplierOrderDAOImpl.addSupOrder(supOrder)){
//                if (SupplierOrderDAOImpl.addSupplierOrderDetails(details_list)){
//                    if (StockDAOImpl.updateData(details_list)){
//                        connection.commit();
//                        return true;
//                    }else {
//                        connection.rollback();
//                        return false;
//                    }
//
//                }else {
//                    connection.rollback();
//                    return false;
//                }
//            }else {
//                connection.rollback();
//                return false;
//            }
//
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                connection.setAutoCommit(true);
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//        return true;
//    }
    public  ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException ;

    public  String getSupOrderCount() throws SQLException, ClassNotFoundException;
    public  String getSupplierOrder() throws SQLException, ClassNotFoundException ;

    public  String getCustomerOrder(int i) throws SQLException, ClassNotFoundException;

    public  ArrayList<String> getAllId() throws SQLException, ClassNotFoundException;
}
