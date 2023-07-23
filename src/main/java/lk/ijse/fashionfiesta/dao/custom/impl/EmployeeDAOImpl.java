package lk.ijse.fashionfiesta.dao.custom.impl;

import lk.ijse.fashionfiesta.dao.custom.EmployeeDAO;
import lk.ijse.fashionfiesta.entity.Employee;
import lk.ijse.fashionfiesta.dao.custom.impl.utill.SQLUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {

    public  boolean save(Employee employee) throws SQLException, ClassNotFoundException {
        return SQLUtill.crudUtil("INSERT INTO employee VALUES(?,?,?,?,?,?,?,?)",
                employee.getEmployee_id(),
                employee.getStreet(),
                employee.getCity(),
                employee.getLane(),
                employee.getRole(),
                employee.getFirsrt_name(),
                employee.getLast_name(),
                employee.getContact_number()
                );
    }

    public  ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.crudUtil("SELECT employee_id FROM employee ORDER BY LENGTH(employee_id),employee_id");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }

    public Employee get(String id) throws SQLException, ClassNotFoundException {
        Employee employee =new Employee();
        ResultSet set = SQLUtill.crudUtil("SELECT * from employee where employee_id=?", id);
        if (set.next()){
            employee.setEmployee_id(set.getString(1));
            employee.setStreet(set.getString(2));
            employee.setCity(set.getString(3));
            employee.setLane(set.getString(4));
            employee.setRole(set.getString(5));
            employee.setFirsrt_name(set.getString(6));
            employee.setLast_name(set.getString(7));
            employee.setContact_number(set.getString(8));
        }
        return employee;
    }
    public  boolean update(Employee employee) throws SQLException, ClassNotFoundException {
        return SQLUtill.crudUtil("UPDATE Employee SET street=?,city=?,lane=?,role=?,first_name=?,last_name=?,contact_number=? WHERE employee_id=?",
                employee.getStreet(),
                employee.getCity(),
                employee.getLane(),
                employee.getRole(),
                employee.getFirsrt_name(),
                employee.getLast_name(),
                employee.getContact_number(),
                employee.getEmployee_id()
        );
    }
    public  boolean remove(String emp_id) throws SQLException, ClassNotFoundException {
        System.out.println(emp_id);
        return SQLUtill.crudUtil("DELETE FROM employee WHERE employee_id=?",emp_id);
    }

    public  ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException {
        ArrayList<String>ids=new ArrayList<>();
        ResultSet set= SQLUtill.crudUtil("SELECT employee_id from employee WHERE employee_id LIKE ? or first_name LIKE ? or last_name LIKE ?",id+"%",id+"%",id+"%");
        while (set.next()){
            ids.add(set.getString(1));
        }
        return ids ;
    }
    public  String getEmpCount() throws SQLException, ClassNotFoundException {
        ResultSet set= SQLUtill.crudUtil("SELECT COUNT(employee_id) FROM employee");
        String count=null;
        if (set.next()){
            count=set.getString(1);
        }
        return count;
    }
    public  String getEmployee() throws SQLException, ClassNotFoundException {
        ResultSet set= SQLUtill.crudUtil("SELECT COUNT(employee_id) FROM employee");
        String count=null;
        if (set.next()){
            count=set.getString(1);
        }
        return count;
    }

    @Override
    public String getEmpCount(String valueOf) {
        return null;
    }
}
