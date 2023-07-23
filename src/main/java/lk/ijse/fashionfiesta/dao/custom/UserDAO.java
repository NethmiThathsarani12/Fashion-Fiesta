package lk.ijse.fashionfiesta.dao.custom;

import lk.ijse.fashionfiesta.dao.CrudDAO;
import lk.ijse.fashionfiesta.entity.User;

import java.sql.SQLException;

public interface UserDAO extends CrudDAO<User, String> {

    // boolean addUser(UserDTO userDTO) throws SQLException, ClassNotFoundException;

     User getData(String id) throws SQLException, ClassNotFoundException;

   // boolean remove(String id) throws SQLException, ClassNotFoundException;

   // boolean update(UserDTO userDTO) throws SQLException, ClassNotFoundException;

    String checkUsernameAndPassword(String userName, String password) throws SQLException, ClassNotFoundException;
}
