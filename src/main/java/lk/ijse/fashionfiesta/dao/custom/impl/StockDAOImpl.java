package lk.ijse.fashionfiesta.dao.custom.impl;

import lk.ijse.fashionfiesta.dao.custom.StockDAO;
import lk.ijse.fashionfiesta.entity.Item;
import lk.ijse.fashionfiesta.dto.SupplierOrderDetailsDTO;
import lk.ijse.fashionfiesta.tm.OrderTm;
import lk.ijse.fashionfiesta.dao.custom.impl.utill.SQLUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockDAOImpl implements StockDAO {

  public boolean save(Item item) throws SQLException, ClassNotFoundException {
        return SQLUtill.crudUtil("INSERT INTO item VALUES(?,?,?,?,?,?)",
                item.getItem_id(),
                item.getItem_name(),
                item.getQuantity(),
                item.getPrice(),
                item.getModel_color(),
                item.getCategary()

        );
    }

   public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.crudUtil("SELECT item_id FROM item ORDER BY LENGTH(item_id),item_id");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }

    public Item get(String id) throws SQLException, ClassNotFoundException {
        Item item = new Item();
        ResultSet set = SQLUtill.crudUtil("SELECT * from item where item_id=?", id);
        if (set.next()) {
            item.setItem_id(set.getString(1));
            item.setItem_name(set.getString(2));
            item.setQuantity(Integer.parseInt(set.getString(3)));
            item.setPrice(Double.parseDouble(set.getString(4)));
            item.setModel_color(set.getString(5));
            item.setCategary(set.getString(6));

        }
        return item;
    }

//    @Override
//    public boolean save(StockDTO dto) throws SQLClientInfoException, ClassNotFoundException, SQLException {
//        return false;
//    }

    public  boolean update(Item item) throws SQLException, ClassNotFoundException {
        return SQLUtill.crudUtil("UPDATE item SET category=?,model_color=?,price=?,item_name=? WHERE item_id=?",
                item.getCategary(),
                item.getModel_color(),
                item.getPrice(),
                item.getItem_name(),
                item.getItem_id()
        );
    }

    public  boolean remove(String emp_id) throws SQLException, ClassNotFoundException {
        return SQLUtill.crudUtil("DELETE FROM item WHERE item_id=?", emp_id);
    }

    public  boolean updateData(ArrayList<SupplierOrderDetailsDTO> details) throws SQLException, ClassNotFoundException {
        for (SupplierOrderDetailsDTO s : details
        ) {
            if (SQLUtill.crudUtil("UPDATE item SET quantity=quantity+? WHERE item_id=?",
                    s.getQuantity(),
                    s.getItem_id()
            )) {

            } else {
                return false;
            }
        }
        return true;
    }

    public  boolean CustomerOrderupdateData(ArrayList<OrderTm> details) throws SQLException, ClassNotFoundException {
        for (int i = 0; i < details.size(); i++) {
            if (SQLUtill.crudUtil("UPDATE item SET quantity=quantity-? WHERE item_id=?",
                    details.get(i).getQty(),
                    details.get(i).getItem_Id()
            )) {

            } else {
                return false;
            }
        }


        return true;
    }

    public  ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException {
        ArrayList<String> ids = new ArrayList<>();
        ResultSet set = SQLUtill.crudUtil("SELECT item_id from item where item_id LIKE ? or item_name LIKE ?", id + "%", id + "%");
        while (set.next()) {
            ids.add(set.getString(1));
        }
        return ids;
    }

    public  String getStock() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.crudUtil("SELECT COUNT(item_id) FROM item");
        String count = null;
        if (set.next()) {
            count = set.getString(1);
        }
        return count;
    }
}
