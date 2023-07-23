package lk.ijse.fashionfiesta.dao.custom.impl;

import lk.ijse.fashionfiesta.dao.custom.QuaryDAO;
import lk.ijse.fashionfiesta.dao.custom.impl.utill.SQLUtill;
import lk.ijse.fashionfiesta.dto.CustomEntityDTO;


import java.sql.ResultSet;
import java.sql.SQLException;

public class QuaryDAOImpl implements QuaryDAO {
    public CustomEntityDTO getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.crudUtil("SELECT a.employee_id,a.time,a.date,e.first_name,e.last_name FROM employee_attendance a INNER JOIN employee e ON a.employee_id = e.employee_id WHERE a.employee_id=?",id);
        CustomEntityDTO customEntityDTO = new CustomEntityDTO();
        while (set.next()){
            customEntityDTO.setEmployee_id(set.getString(1));
            customEntityDTO.setTime(set.getString(2));
            customEntityDTO.setDate(set.getString(3));
            customEntityDTO.setFirst_name(set.getString(4));
            customEntityDTO.setLast_name(set.getString(5));
        }
        return customEntityDTO;
    }


    @Override
    public CustomEntityDTO getCust(String id) throws SQLException, ClassNotFoundException {
        CustomEntityDTO customEntityDto = new CustomEntityDTO();
        ResultSet set = SQLUtill.crudUtil("SELECT d.item_id, d.customer_order_id ,d.quantity ,d.price,o.customer_order_date,o.payment FROM customer_order_details d INNER JOIN customer_order o ON d.customer_order_id = o.customer_order_id where o.customer_order_id=?",id);
        if (set.next()){
            customEntityDto.setItem_id(set.getString(1));
            customEntityDto.setOrder_id(set.getString(2));
            customEntityDto.setQuantity(set.getInt(3));
            customEntityDto.setPrice(Double.parseDouble(set.getString(4)));
            customEntityDto.setDate(set.getString(5));
            customEntityDto.setPayment(Double.parseDouble(set.getString(6)));
        }
        return customEntityDto;
    }

    @Override
    public CustomEntityDTO getSup(String id) throws SQLException, ClassNotFoundException {
        CustomEntityDTO orderTmDto = new CustomEntityDTO();
        ResultSet set = SQLUtill.crudUtil("SELECT d.item_id, d.supplier_order_id ,d.quantity ,d.price,o.supplier_order_date,o.payment FROM supplier_order_details d INNER JOIN supplier_order o ON d.supplier_order_id = o.supplier_order_id where o.supplier_order_id=?",id);
        if (set.next()){
            orderTmDto.setItem_id(set.getString(1));
            orderTmDto.setOrder_id(set.getString(2));
            orderTmDto.setQuantity(set.getInt(3));
            orderTmDto.setPrice(Double.parseDouble(set.getString(4)));
            orderTmDto.setDate(set.getString(5));
            orderTmDto.setPayment(Double.parseDouble(set.getString(6)));
        }
        return orderTmDto;
    }



}
