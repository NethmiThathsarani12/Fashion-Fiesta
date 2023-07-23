package lk.ijse.fashionfiesta.dao.custom;

import lk.ijse.fashionfiesta.dao.SuperDAO;
import lk.ijse.fashionfiesta.dto.CustomEntityDTO;


import java.sql.SQLException;

public interface QuaryDAO extends SuperDAO {

     CustomEntityDTO getData(String id) throws SQLException, ClassNotFoundException;

     CustomEntityDTO getCust(String id) throws SQLException, ClassNotFoundException;

     CustomEntityDTO getSup(String id) throws SQLException, ClassNotFoundException;

}
