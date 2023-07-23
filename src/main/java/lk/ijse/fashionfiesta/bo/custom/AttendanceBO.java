package lk.ijse.fashionfiesta.bo.custom;

import lk.ijse.fashionfiesta.bo.SuperBO;
import lk.ijse.fashionfiesta.dto.CustomEntityDTO;
import lk.ijse.fashionfiesta.dto.EmployeeAttendanceDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AttendanceBO extends SuperBO {

    ArrayList<String> getAllEmployeeId() throws SQLException, ClassNotFoundException;

    String getAllEmpAttendanceCount() throws SQLException, ClassNotFoundException;

    ArrayList<String> getAllEmpAttendanceSearchId(String id) throws SQLException, ClassNotFoundException;

    boolean employeeExist(String id) throws SQLException, ClassNotFoundException;

    boolean employeeTodayExist(String id) throws SQLException, ClassNotFoundException;

    CustomEntityDTO getData(String id) throws SQLException, ClassNotFoundException;

    String getEmployee() throws SQLException, ClassNotFoundException ;

    boolean saveAttendance(EmployeeAttendanceDTO employeeAttendanceDTO) throws SQLException, ClassNotFoundException;

    String getEmloyeeCount(String id) throws SQLException, ClassNotFoundException;


}


