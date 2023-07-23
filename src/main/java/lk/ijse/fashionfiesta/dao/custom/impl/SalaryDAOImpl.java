package lk.ijse.fashionfiesta.dao.custom.impl;

import lk.ijse.fashionfiesta.dao.custom.SalaryDAO;
import lk.ijse.fashionfiesta.entity.Salary;
import lk.ijse.fashionfiesta.utill.DateTimeUtil;
import lk.ijse.fashionfiesta.dao.custom.impl.utill.SQLUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryDAOImpl implements SalaryDAO {

    public  ArrayList<String> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<String>ids=new ArrayList<>();
        String date= DateTimeUtil.dateNow();
        String[] arDate = date.split("-");
        ResultSet set= SQLUtill.crudUtil("SELECT salary_id FROM salary WHERE date LIKE ?",arDate[0]+"-"+arDate[1]+"%");
        while (set.next()){
            ids.add(set.getString(1));
        }
        return ids;
    }

    public  boolean save(Salary salary) throws SQLException, ClassNotFoundException {
        System.out.println(salary.toString());
        return SQLUtill.crudUtil("INSERT INTO salary VALUES (?,?,?,?,?)",
                salary.getEmployee_id(),
                salary.getSalary_id(),
                salary.getDate(),
                salary.getSalary(),
                salary.getEmployee_attendance_count()
        );
    }

    @Override
    public boolean update(Salary salary) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean remove(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    public Salary get(String id) throws SQLException, ClassNotFoundException {

        ResultSet set = SQLUtill.crudUtil("SELECT * FROM salary where salary_id=?", id);
        Salary salary = new Salary();
        System.out.println("Sal Id : "+ id);
        while (set.next()) {

            salary.setEmployee_id(set.getString(1));
            salary.setSalary_id(set.getString(2));
            salary.setDate(set.getString(3));
            salary.setSalary(Double.parseDouble(set.getString(4)));
            salary.setEmployee_attendance_count(Integer.parseInt(set.getString(5)));

        }
        return salary;
    }

    public  String getCount() {
        try {
            String s = DateTimeUtil.dateNow();
            String[] split = s.split("-");
            ResultSet o = SQLUtill.crudUtil("SELECT COUNT(salary_id) from salary WHERE date LIKE ?", split[0] + "-" + split[1] + "-%");
            if (o.next()){
                return "+ 0"+o.getString(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "+00";
    }

    public  ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.crudUtil("SELECT salary_id FROM salary ORDER BY LENGTH(salary_id),salary_id");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }

    @Override
    public ArrayList<String> getSearchIds(String s) throws SQLException, ClassNotFoundException {
        return null;
    }
}
