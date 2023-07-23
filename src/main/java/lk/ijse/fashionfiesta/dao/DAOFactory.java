package lk.ijse.fashionfiesta.dao;

import lk.ijse.fashionfiesta.dao.custom.EmployeeAttendanceDAO;
import lk.ijse.fashionfiesta.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDaoFactory(){return (daoFactory==null)? daoFactory= new DAOFactory():daoFactory;}

    public enum DAOType{
        ATTENDANCE,STOCK,CUSTOMER,CUSTOMER_ORDER,CUSTOMER_ORDER_DETAILS,EMPLOYEE,LOGIN,QUARY,SALARY,SUPPLIER,SUPPLIER_ORDER,SUPPLIER_ORDER_DETAILS
    }

  public <T extends SuperDAO> T getDAO(DAOType res){
        switch (res){

            case ATTENDANCE: return (T) new EmployeeAttendanceDAOImpl();
            case STOCK: return (T) new StockDAOImpl();
            case CUSTOMER: return (T) new CustomerDAOImpl();
            case CUSTOMER_ORDER: return (T) new CustomerOrderDAOImpl();
            case CUSTOMER_ORDER_DETAILS: return (T) new CustomerOrderDetailsDAOImpl();
            case LOGIN: return (T) new UserDAOImpl();
            case SALARY: return (T) new SalaryDAOImpl();
            case EMPLOYEE: return (T) new EmployeeDAOImpl();
            case SUPPLIER: return (T) new SupplierDAOImpl();
            case SUPPLIER_ORDER: return (T) new SupplierOrderDAOImpl();
            case SUPPLIER_ORDER_DETAILS: return (T) new SupplierOrderDetailsDAOImpl();
            case QUARY: return (T) new QuaryDAOImpl();
            default:
                return null;


        }
  }

}
