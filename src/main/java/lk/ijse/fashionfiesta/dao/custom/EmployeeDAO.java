package lk.ijse.fashionfiesta.dao.custom;

import lk.ijse.fashionfiesta.dao.CrudDAO;
import lk.ijse.fashionfiesta.entity.Employee;

import java.sql.SQLException;

public interface EmployeeDAO extends CrudDAO<Employee,String> {

//     boolean addEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;
//
//     ArrayList<String> getAllId() throws SQLException, ClassNotFoundException;
//
//     EmployeeDTO get(String id) throws SQLException, ClassNotFoundException ;
//
//     boolean update(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;
//
//     boolean remove(String emp_id) throws SQLException, ClassNotFoundException;
//
//     ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException ;

     String getEmpCount() throws SQLException, ClassNotFoundException;

     String getEmployee() throws SQLException, ClassNotFoundException;

     String getEmpCount(String valueOf);
}
