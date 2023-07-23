package lk.ijse.fashionfiesta.bo.custom;

import lk.ijse.fashionfiesta.bo.SuperBO;
import lk.ijse.fashionfiesta.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {

    boolean save(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    ArrayList<String> getAllId() throws SQLException, ClassNotFoundException;

    boolean update(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    boolean remove(String emp_id) throws SQLException, ClassNotFoundException;

    EmployeeDTO get(String id) throws SQLException, ClassNotFoundException;

    ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException;

    String getEmployee() throws SQLException, ClassNotFoundException;
}
