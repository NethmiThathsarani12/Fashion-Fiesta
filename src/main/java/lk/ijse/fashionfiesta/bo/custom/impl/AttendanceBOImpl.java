package lk.ijse.fashionfiesta.bo.custom.impl;

import lk.ijse.fashionfiesta.bo.custom.AttendanceBO;
import lk.ijse.fashionfiesta.dao.custom.EmployeeAttendanceDAO;
import lk.ijse.fashionfiesta.dao.custom.EmployeeDAO;
import lk.ijse.fashionfiesta.dao.custom.QuaryDAO;
import lk.ijse.fashionfiesta.dao.custom.impl.EmployeeAttendanceDAOImpl;
import lk.ijse.fashionfiesta.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.fashionfiesta.dao.custom.impl.QuaryDAOImpl;
import lk.ijse.fashionfiesta.dao.custom.impl.utill.SQLUtill;
import lk.ijse.fashionfiesta.dto.CustomEntityDTO;
import lk.ijse.fashionfiesta.entity.Attendance;
import lk.ijse.fashionfiesta.dto.EmployeeAttendanceDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AttendanceBOImpl implements AttendanceBO {
    QuaryDAO quaryDAO = new QuaryDAOImpl();
    EmployeeAttendanceDAO employeeAttendanceDAO = new EmployeeAttendanceDAOImpl();
    EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    @Override
    public ArrayList<String> getAllEmployeeId() throws SQLException, ClassNotFoundException {
        return employeeAttendanceDAO.getAllId();
    }

    @Override
    public String getAllEmpAttendanceCount() throws SQLException, ClassNotFoundException {
        return employeeAttendanceDAO.getEmpAttendance();
    }

    @Override
    public ArrayList<String> getAllEmpAttendanceSearchId(String id) throws SQLException, ClassNotFoundException {
        return employeeAttendanceDAO.getSearchIds(id);
    }

    @Override
    public boolean employeeExist(String id) throws SQLException, ClassNotFoundException {
        return employeeAttendanceDAO.isEmployeeExist(id);
    }

    @Override
    public boolean employeeTodayExist(String id) throws SQLException, ClassNotFoundException {
        return employeeAttendanceDAO.isEmployeeTodayExist(id);
    }

    @Override
    public boolean saveAttendance(EmployeeAttendanceDTO employeeAttendanceDTO) throws SQLException, ClassNotFoundException {
        return employeeAttendanceDAO.save(new Attendance(
                employeeAttendanceDTO.getEmployee_id(),
                employeeAttendanceDTO.getDate(),
                employeeAttendanceDTO.getTime()
        ));
    }

    @Override
    public String getEmloyeeCount(String id) throws SQLException, ClassNotFoundException {
        return employeeAttendanceDAO.getEmloyeeCount(id);
    }

    public CustomEntityDTO getData(String id) throws SQLException, ClassNotFoundException {
        return quaryDAO.getData(id);
    }
    public  String getEmployee() throws SQLException, ClassNotFoundException {
        return employeeDAO.getEmployee();
    }

//    public String getEmloyeeCount() throws SQLException, ClassNotFoundException {
//        return employeeAttendanceDAO.getEmpAttendance();
//    }
}
