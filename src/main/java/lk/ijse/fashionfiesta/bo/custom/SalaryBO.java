package lk.ijse.fashionfiesta.bo.custom;

import lk.ijse.fashionfiesta.bo.SuperBO;
import lk.ijse.fashionfiesta.dto.EmployeeDTO;
import lk.ijse.fashionfiesta.dto.SalaryDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SalaryBO extends SuperBO {
    EmployeeDTO get(String id) throws SQLException, ClassNotFoundException;

    ArrayList<String> getAllId() throws SQLException, ClassNotFoundException;

    String getEmloyeeCount(String id) throws SQLException, ClassNotFoundException;

    boolean save(SalaryDTO salaryDTO) throws SQLException, ClassNotFoundException;

    ArrayList<String> getAllIdSalary() throws SQLException, ClassNotFoundException;

    SalaryDTO getSalary(String id) throws SQLException, ClassNotFoundException;
}
