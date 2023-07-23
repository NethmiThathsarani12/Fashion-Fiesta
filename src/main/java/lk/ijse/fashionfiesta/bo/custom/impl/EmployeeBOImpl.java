package lk.ijse.fashionfiesta.bo.custom.impl;

import lk.ijse.fashionfiesta.bo.custom.EmployeeBO;
import lk.ijse.fashionfiesta.dao.custom.EmployeeDAO;
import lk.ijse.fashionfiesta.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.fashionfiesta.entity.Employee;
import lk.ijse.fashionfiesta.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {

    EmployeeDAO employeeDAO = new EmployeeDAOImpl();


    public  boolean save(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(new Employee(employeeDTO.getEmployee_id(),employeeDTO.getStreet(),
                employeeDTO.getCity(),employeeDTO.getLane(),employeeDTO.getRole(),employeeDTO.getEmployee_Fname(),employeeDTO.getEmployee_Fname(),
                employeeDTO.getContact()));
    }

    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        return employeeDAO.getAllId();
    }

    public  boolean update(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(
                employeeDTO.getEmployee_id(),
                employeeDTO.getStreet(),
                employeeDTO.getCity(),
                employeeDTO.getLane(),
                employeeDTO.getRole(),
                employeeDTO.getEmployee_Fname(),
                employeeDTO.getEmployee_Lname(),
                employeeDTO.getContact()
        ));
    }

    public  boolean remove(String emp_id) throws SQLException, ClassNotFoundException {
        return employeeDAO.remove(emp_id);
    }

    public EmployeeDTO get(String id) throws SQLException, ClassNotFoundException {
        Employee employee = employeeDAO.get(id);
        return new EmployeeDTO(
                employee.getEmployee_id(),
                employee.getFirsrt_name(),
                employee.getLast_name(),
                employee.getStreet(),
                employee.getCity(),
                employee.getLane(),
                employee.getRole(),
                employee.getContact_number()
        );
    }

    public  ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.getSearchIds(id);
    }

    public  String getEmployee() throws SQLException, ClassNotFoundException {
        return employeeDAO.getEmployee();
    }

}
