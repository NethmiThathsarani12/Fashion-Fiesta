package lk.ijse.fashionfiesta.dao.custom;

import lk.ijse.fashionfiesta.dao.CrudDAO;
import lk.ijse.fashionfiesta.entity.Salary;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SalaryDAO extends CrudDAO<Salary,String> {

    ArrayList<String> getAll() throws SQLException, ClassNotFoundException;

  //  boolean add(SalaryDTO salaryDTO) throws SQLException, ClassNotFoundException;

  //  SalaryDTO getData(String id) throws SQLException, ClassNotFoundException;

    String getCount();

  //  ArrayList<String> getId() throws SQLException, ClassNotFoundException;
}
