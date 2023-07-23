package lk.ijse.fashionfiesta.dao.custom;

import lk.ijse.fashionfiesta.dao.CrudDAO;
import lk.ijse.fashionfiesta.dto.CustomEntityDTO;


import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierOrderDetailsDAO extends CrudDAO<CustomEntityDTO,String> {

     ArrayList<String> getAllId() throws SQLException, ClassNotFoundException;

    CustomEntityDTO get(String id) throws SQLException, ClassNotFoundException;
}
