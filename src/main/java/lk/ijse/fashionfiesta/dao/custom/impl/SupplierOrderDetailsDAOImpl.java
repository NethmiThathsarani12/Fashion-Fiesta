package lk.ijse.fashionfiesta.dao.custom.impl;

import lk.ijse.fashionfiesta.dao.custom.SupplierOrderDetailsDAO;

import lk.ijse.fashionfiesta.dao.custom.impl.utill.SQLUtill;
import lk.ijse.fashionfiesta.dto.CustomEntityDTO;

import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierOrderDetailsDAOImpl implements SupplierOrderDetailsDAO {

    @Override
    public boolean save(CustomEntityDTO dto) throws SQLClientInfoException, ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean update(CustomEntityDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean remove(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    public  ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.crudUtil("SELECT supplier_order_id FROM supplier_order_details ORDER BY LENGTH(supplier_order_id),supplier_order_id");
        ArrayList<String> list = new ArrayList<>();
        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }

    @Override
    public CustomEntityDTO get(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> getSearchIds(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

//    public OrderTmDTO get(String id) throws SQLException, ClassNotFoundException {
//        OrderTmDTO orderTmDto = new OrderTmDTO();
//        ResultSet set = SQLUtill.crudUtil("SELECT d.item_id, d.supplier_order_id ,d.quantity ,d.price,o.supplier_order_date,o.payment FROM supplier_order_details d INNER JOIN supplier_order o ON d.supplier_order_id = o.supplier_order_id where o.supplier_order_id=?",id);
//        if (set.next()){
//            orderTmDto.setItem_id(set.getString(1));
//            orderTmDto.setSupplier_order_id(set.getString(2));
//            orderTmDto.setQuantity(set.getInt(3));
//            orderTmDto.setPrice(Double.parseDouble(set.getString(4)));
//            orderTmDto.setSupplier_order_date(set.getString(5));
//            orderTmDto.setPayment(Double.parseDouble(set.getString(6)));
//        }
//        return orderTmDto;
//    }
}
