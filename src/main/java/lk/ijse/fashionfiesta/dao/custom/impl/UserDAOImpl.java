package lk.ijse.fashionfiesta.dao.custom.impl;


import lk.ijse.fashionfiesta.dao.custom.UserDAO;
import lk.ijse.fashionfiesta.entity.User;
import lk.ijse.fashionfiesta.dao.custom.impl.utill.SQLUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {

    public  boolean save(User user) throws SQLException, ClassNotFoundException {
        return SQLUtill.crudUtil("insert into user VALUES (?,?,?,?)", user.getEmployee_id(), user.getUser_name(), user.getPassword(), user.getRole());
    }

    public User getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.crudUtil("SELECT * FROM user where employee_id=?", id);

        User user = new User();
        while (set.next()) {
            user.setUser_name(set.getString(2));
            user.setPassword(set.getString(3));
        }
        return user;
    }

    public  boolean Remove(String id) throws SQLException, ClassNotFoundException {
        return SQLUtill.crudUtil("DELETE FROM user WHERE employee_id=?", id);
    }

//    @Override
//    public boolean save(UserDTO dto) throws SQLClientInfoException, ClassNotFoundException, SQLException {
//        return false;
//    }

    public  boolean update(User user) throws SQLException, ClassNotFoundException {
        return SQLUtill.crudUtil("UPDATE user SET " + "user_name=?," + "password=?," + "role=?" + "WHERE employee_id=?", user.getUser_name(), user.getPassword(), user.getRole(), user.getEmployee_id());
    }

    @Override
    public boolean remove(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> getSearchIds(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public User get(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    public  String checkUsernameAndPassword(String userName, String password) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.crudUtil("SELECT role from user WHERE user_name=? AND password=?", userName, password);

        if (set.next()) {
            return set.getString(1);
        } else {
            return "No";
        }


    }
}
