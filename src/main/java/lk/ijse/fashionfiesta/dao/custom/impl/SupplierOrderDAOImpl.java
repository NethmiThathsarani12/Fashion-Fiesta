package lk.ijse.fashionfiesta.dao.custom.impl;

import lk.ijse.fashionfiesta.dao.custom.SupplierOrderDAO;
import lk.ijse.fashionfiesta.entity.SupplierOrder;
import lk.ijse.fashionfiesta.dto.SupplierOrderDetailsDTO;
import lk.ijse.fashionfiesta.utill.DateTimeUtil;
import lk.ijse.fashionfiesta.dao.custom.impl.utill.SQLUtill;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierOrderDAOImpl implements SupplierOrderDAO {
    public  boolean save(SupplierOrder supplierOrder) throws SQLException, ClassNotFoundException {
        return SQLUtill.crudUtil("INSERT INTO supplier_order VALUES(?,?,?,?,?)",
                supplierOrder.getSupplier_id(),
                supplierOrder.getSupplier_order_id(),
                supplierOrder.getSupplier_order_date(),
                supplierOrder.getSupplier_order_time(),
                supplierOrder.getPayment()
        );

}
    public  ArrayList<String> getAll() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.crudUtil("SELECT supplier_order_id FROM supplier_order ORDER BY LENGTH(supplier_order_id),supplier_order_id");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }
    public SupplierOrder get(String id) throws SQLException, ClassNotFoundException {
        SupplierOrder supplierOrder=new SupplierOrder();
        ResultSet set = SQLUtill.crudUtil("SELECT * from supplier_order where supplier_id=?", id);
        if (set.next()){
            supplierOrder.setSupplier_id(set.getString(1));
            supplierOrder.setSupplier_order_id(set.getString(2));
            supplierOrder.setSupplier_order_date(set.getString(3));
            supplierOrder.setSupplier_order_time(set.getString(4));
            supplierOrder.setPayment(Double.parseDouble(set.getString(5)));

        }
        return supplierOrder;
    }

//    @Override
//    public boolean save(SupplierOrderDTO dto) throws SQLClientInfoException, ClassNotFoundException, SQLException {
//        return false;
//    }

    public  boolean update(SupplierOrder supplierOrder) throws SQLException, ClassNotFoundException {
        return SQLUtill.crudUtil("UPDATE supplier_order SET supplier_order_time=?,supplier_order_date=?,supplier_order_id=? WHERE supplier_id=?",
                supplierOrder.getSupplier_id(),
                supplierOrder.getSupplier_order_id(),
                supplierOrder.getSupplier_order_date(),
                supplierOrder.getPayment()

        );
    }
    public  boolean remove(String supplier_id) throws SQLException, ClassNotFoundException {
        return SQLUtill.crudUtil("DELETE FROM supplier_order WHERE supplier_id=?",supplier_id);
    }

    public  boolean addSupplierOrderDetails(ArrayList<SupplierOrderDetailsDTO> details) throws SQLException, ClassNotFoundException {
        for (SupplierOrderDetailsDTO s:
             details) {
            if ( SQLUtill.crudUtil("INSERT INTO supplier_order_details VALUES(?,?,?,?)",
                    s.getSupplier_order_id(),
                    s.getItem_id(),
                    s.getQuantity(),
                    s.getPrice()
            )){

            }else {
                return false;
            }
        }
        return true;

    }
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
   public  ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException {
        ArrayList<String>ids=new ArrayList<>();
        ResultSet set= SQLUtill.crudUtil("SELECT supplier_id from supplier_order where supplier_id LIKE ? or supplier_order_id LIKE ? or supplier_order_date LIKE ?",id+"%",id+"%",id+"%");
        while (set.next()){
            ids.add(set.getString(1));
        }
        return ids ;
    }

    public  String getSupOrderCount() throws SQLException, ClassNotFoundException {
        ResultSet set= SQLUtill.crudUtil("SELECT COUNT(supplier_order_id) FROM supplier_order");
        String count=null;
        if (set.next()){
            count=set.getString(1);
        }
        return count;
    }
    public  String getSupplierOrder() throws SQLException, ClassNotFoundException {
        ResultSet set= SQLUtill.crudUtil("SELECT COUNT(supplier_order_id) FROM supplier_order");
        String count=null;
        if (set.next()){
            count=set.getString(1);
        }
        return count;
    }

    public  String getCustomerOrder(int i) throws SQLException, ClassNotFoundException {
        String dateNow = DateTimeUtil.dateNow();
        String[] date = dateNow.split("-");
        String currentDate=null;
        if (String.valueOf(i).length()==1){
            currentDate="0"+i;
        }else {
            currentDate= String.valueOf(i);
        }
        ResultSet set= SQLUtill.crudUtil("SELECT payment FROM supplier_order WHERE supplier_order_date =?",date[0]+"-"+date[1]+"-"+currentDate);

        while (set.next()){
            return set.getString(1);
        }
        return "0";
    }

    public  ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.crudUtil("SELECT supplier_order_id FROM supplier_order ORDER BY LENGTH(supplier_order_id),supplier_order_id");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }
}
