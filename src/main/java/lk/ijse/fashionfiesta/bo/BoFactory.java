package lk.ijse.fashionfiesta.bo;

import lk.ijse.fashionfiesta.bo.custom.SupplierBO;
import lk.ijse.fashionfiesta.bo.custom.impl.*;
import lk.ijse.fashionfiesta.dao.custom.impl.CustomerOrderDAOImpl;
import lk.ijse.fashionfiesta.dao.custom.impl.EmployeeAttendanceDAOImpl;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory(){

    }

    public static BoFactory getBoFactory(){return (boFactory == null) ? boFactory=new BoFactory():boFactory;}

    public enum BOTypes{
      ATTENDANCE_BO, CUSTOMER_BO, CUSTOMER_ORDER_BO, EMPLOYEE_BO, SALARY_BO, STOCK_BO, SUPPLIER_BO, SUPPLIER_ORDER_BO

    }

    public <T extends SuperBO> T getBO(BOTypes boTypes){
        switch (boTypes){
            case ATTENDANCE_BO:
                return (T) new AttendanceBOImpl();
            case CUSTOMER_BO:
                return (T) new CustomerBOImpl();
            case CUSTOMER_ORDER_BO:
                return (T) new CustomerOrderBOImpl();
            case EMPLOYEE_BO:
                return (T) new EmployeeBOImpl();
            case  SALARY_BO:
                return (T) new SalaryBOImpl();
            case STOCK_BO:
                return (T) new StockBOImpl();
            case SUPPLIER_BO:
                return (T) new SupplierBOImpl();
            case SUPPLIER_ORDER_BO:
                return (T) new SupplierOrderBOImpl();
            default:
                return null;
        }
    }
}
