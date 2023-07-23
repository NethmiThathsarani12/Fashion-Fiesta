package lk.ijse.fashionfiesta.dao.custom;

import lk.ijse.fashionfiesta.dao.CrudDAO;
import lk.ijse.fashionfiesta.entity.Attendance;

import java.sql.SQLException;

public interface EmployeeAttendanceDAO extends CrudDAO<Attendance,String> {

 //  ArrayList<String> getAllId() throws SQLException, ClassNotFoundException;

   boolean add(String text) throws SQLException, ClassNotFoundException;

   boolean isEmployeeExist(String text) throws SQLException, ClassNotFoundException;

   boolean isEmployeeTodayExist(String text) throws SQLException, ClassNotFoundException;

     Attendance getData(String id) throws SQLException, ClassNotFoundException;

    String getEmployeeCount() throws SQLException, ClassNotFoundException;

     String getEmpAttendance() throws SQLException, ClassNotFoundException;

     String getEmloyeeCount(String id) throws SQLException, ClassNotFoundException;

 //   ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException;
}
