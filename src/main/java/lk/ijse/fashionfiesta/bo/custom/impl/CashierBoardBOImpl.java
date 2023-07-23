package lk.ijse.fashionfiesta.bo.custom.impl;

import lk.ijse.fashionfiesta.bo.custom.CashierBoardBO;
import lk.ijse.fashionfiesta.dao.custom.CustomerOrderDAO;
import lk.ijse.fashionfiesta.dao.custom.EmployeeAttendanceDAO;
import lk.ijse.fashionfiesta.dao.custom.StockDAO;
import lk.ijse.fashionfiesta.dao.custom.SupplierOrderDAO;
import lk.ijse.fashionfiesta.dao.custom.impl.CustomerOrderDAOImpl;
import lk.ijse.fashionfiesta.dao.custom.impl.EmployeeAttendanceDAOImpl;
import lk.ijse.fashionfiesta.dao.custom.impl.StockDAOImpl;
import lk.ijse.fashionfiesta.dao.custom.impl.SupplierOrderDAOImpl;

import java.sql.SQLException;

public class CashierBoardBOImpl implements CashierBoardBO {
    CustomerOrderDAO customerOrderDAO = new CustomerOrderDAOImpl();
    EmployeeAttendanceDAO employeeAttendanceDAO = new EmployeeAttendanceDAOImpl();
    StockDAO stockDAO = new StockDAOImpl();
    SupplierOrderDAO supplierOrderDAO = new SupplierOrderDAOImpl();

    public  String getEmpAttendance() throws SQLException, ClassNotFoundException {
        return employeeAttendanceDAO.getEmpAttendance();
    }

    public  String getSupOrderCount() throws SQLException, ClassNotFoundException {
        return supplierOrderDAO.getSupOrderCount();
    }

    public  String getStock() throws SQLException, ClassNotFoundException {
        return stockDAO.getStock();
    }

    public  String getCustOrder() throws SQLException, ClassNotFoundException {
        return customerOrderDAO.getCustOrder();
    }
}
