package lk.ijse.fashionfiesta.dao.custom.impl;

import lk.ijse.fashionfiesta.dao.custom.CustomerOrderDetailsDAO;
import lk.ijse.fashionfiesta.entity.CustomerOrderDetails;
import lk.ijse.fashionfiesta.dao.custom.impl.utill.SQLUtill;

import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerOrderDetailsDAOImpl implements CustomerOrderDetailsDAO {

    @Override
    public boolean save(CustomerOrderDetails dto) throws SQLClientInfoException, ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean update(CustomerOrderDetails dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean remove(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    public  ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.crudUtil("SELECT customer_order_id FROM customer_order_details ORDER BY LENGTH(customer_order_id),customer_order_id");
        ArrayList<String> list = new ArrayList<>();
        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }

    @Override
    public CustomerOrderDetails get(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> getSearchIds(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

//    public CusOrderTmDTO get(String id) throws SQLException, ClassNotFoundException {
//        CusOrderTmDTO cusOrderTmDto = new CusOrderTmDTO();
//        ResultSet set = SQLUtill.crudUtil("SELECT d.item_id, d.customer_order_id ,d.quantity ,d.price,o.customer_order_date,o.payment FROM customer_order_details d INNER JOIN customer_order o ON d.customer_order_id = o.customer_order_id where o.customer_order_id=?",id);
//        if (set.next()){
//        cusOrderTmDto.setItem_id(set.getString(1));
//            cusOrderTmDto.setCustomer_order_id(set.getString(2));
//            cusOrderTmDto.setQuantity(set.getInt(3));
//            cusOrderTmDto.setPrice(Double.parseDouble(set.getString(4)));
//            cusOrderTmDto.setCustomer_order_date(set.getString(5));
//            cusOrderTmDto.setPayment(Double.parseDouble(set.getString(6)));
//        }
//        return cusOrderTmDto;
//    }
}
