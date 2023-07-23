package lk.ijse.fashionfiesta.bo.custom;

import lk.ijse.fashionfiesta.bo.SuperBO;

import java.sql.SQLException;

public interface CashierBoardBO extends SuperBO {
    public  String getEmpAttendance() throws SQLException, ClassNotFoundException ;

    public  String getSupOrderCount() throws SQLException, ClassNotFoundException;

    public  String getStock() throws SQLException, ClassNotFoundException;

    public  String getCustOrder() throws SQLException, ClassNotFoundException ;
}
