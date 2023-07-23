package lk.ijse.fashionfiesta.bo.custom.impl;

import lk.ijse.fashionfiesta.bo.custom.SalaryBO;
import lk.ijse.fashionfiesta.dao.custom.EmployeeAttendanceDAO;
import lk.ijse.fashionfiesta.dao.custom.EmployeeDAO;
import lk.ijse.fashionfiesta.dao.custom.SalaryDAO;
import lk.ijse.fashionfiesta.dao.custom.impl.EmployeeAttendanceDAOImpl;
import lk.ijse.fashionfiesta.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.fashionfiesta.dao.custom.impl.SalaryDAOImpl;
import lk.ijse.fashionfiesta.dao.custom.impl.utill.SQLUtill;
import lk.ijse.fashionfiesta.entity.Employee;
import lk.ijse.fashionfiesta.entity.Salary;
import lk.ijse.fashionfiesta.dto.EmployeeDTO;
import lk.ijse.fashionfiesta.dto.SalaryDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryBOImpl implements SalaryBO {
    EmployeeAttendanceDAO employeeAttendanceDAO = new EmployeeAttendanceDAOImpl();
    EmployeeDAO employeeDAO = new EmployeeDAOImpl();
    SalaryDAO salaryDAO = new SalaryDAOImpl();

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
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.crudUtil("SELECT employee_id FROM employee ORDER BY LENGTH(employee_id),employee_id");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return employeeDAO.getAllId();
    }
    public  String getEmpCount() throws SQLException, ClassNotFoundException {
        return employeeDAO.getEmpCount();
    }
    public  boolean save(SalaryDTO salaryDTO) throws SQLException, ClassNotFoundException {
        return salaryDAO.save(new Salary(
                salaryDTO.getEmployee_id(),
                salaryDTO.getSalary_id(),
                salaryDTO.getDate(),
                salaryDTO.getSalary(),
                salaryDTO.getEmployee_attandance_count()
        ));
    }
    public  ArrayList<String> getAllIdSalary() throws SQLException, ClassNotFoundException {
        return salaryDAO.getAllId();
    }
    public SalaryDTO getSalary(String id) throws SQLException, ClassNotFoundException {
        Salary salary=  salaryDAO.get(id);
        return new SalaryDTO(
                salary.getSalary_id(),
                salary.getSalary_id(),
                salary.getDate(),
                salary.getSalary(),
                salary.getEmployee_attendance_count()
        );
    }
    public  String getEmloyeeCount(String id) throws SQLException, ClassNotFoundException {
        return employeeAttendanceDAO.getEmloyeeCount(id);
    }

}
