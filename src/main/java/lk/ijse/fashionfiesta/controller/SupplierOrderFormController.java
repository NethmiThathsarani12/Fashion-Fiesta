package lk.ijse.fashionfiesta.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.fashionfiesta.bo.BoFactory;
import lk.ijse.fashionfiesta.bo.custom.SupplierOrderBO;
import lk.ijse.fashionfiesta.bo.custom.impl.SupplierOrderBOImpl;
import lk.ijse.fashionfiesta.dto.StockDTO;
import lk.ijse.fashionfiesta.dto.SupplierOrderDTO;
import lk.ijse.fashionfiesta.dto.SupplierOrderDetailsDTO;
import lk.ijse.fashionfiesta.tm.SupplierOrderTm;
import lk.ijse.fashionfiesta.utill.DateTimeUtil;
import lk.ijse.fashionfiesta.utill.Navigation;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static lk.ijse.fashionfiesta.bo.BoFactory.BOTypes.SUPPLIER_ORDER_BO;

public class SupplierOrderFormController implements Initializable {
//    SupplierOrderDAO supplierOrderDAO = new SupplierOrderDAOImpl();
//    StockDAO stockDAO = new StockDAOImpl();
//    SupplierDAO supplierDAO = new SupplierDAOImpl();
//    SupplierOrderDetailsDAO supplierOrderDetailsDAO = new SupplierOrderDetailsDAOImpl();

    public TableColumn coltemId;
    public TableColumn colName;
    public TableColumn colQty;
    public TableColumn colPayment;
    public JFXTextField supplierOrderId;
    public JFXTextField price;
    public JFXTextField qty;
    public JFXComboBox supplierId;
    public JFXComboBox itemId;
    public TableView tblSupplierOrder;

   // SupplierOrderBO supplierOrderBO = new SupplierOrderBOImpl();
    SupplierOrderBO supplierOrderBO = BoFactory.getBoFactory().getBO(SUPPLIER_ORDER_BO);

    int O_i = 0;
    ObservableList<SupplierOrderTm> list = FXCollections.observableArrayList();
    ArrayList<SupplierOrderDetailsDTO> details_list = new ArrayList<>();
    @FXML
   /* void searchKeyReleased(KeyEvent event) {
        tblSupplierOrder.getItems().clear();
        list.clear();
        try {
            ArrayList<String> ids= SupplierOrderModel.getSearchIds(txtSearch.getText());
            for (int i = 0; i < ids.size(); i++) {
                setSupplierData(ids.get(i));
            }

           *//* if (txtSearch.getText().isEmpty()){
                ids.clear();
            }

            if (ids.size()<=0&&txtSearch.getText().isEmpty()){
                loadAllSupplierId();
            }*//*
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }*/
            String o_id = null;
    @FXML
    private JFXTextField txtSearch;

    public void btnAddOnAction(ActionEvent actionEvent) {
        if (O_i == 0) {
            o_id = id();
        }
        O_i++;
        SupplierOrderDetailsDTO details = new SupplierOrderDetailsDTO();
        details.setSupplier_order_id(o_id);
        details.setItem_id(String.valueOf(itemId.getValue()));
        details.setQuantity(qty.getText());
        details.setPrice(price.getText());

        details_list.add(details);

        SupplierOrderTm tm = new SupplierOrderTm();
        try {
            StockDTO stockDTO = supplierOrderBO.get((String) itemId.getValue());
            tm.setItem_id((String) itemId.getValue());
            tm.setName(stockDTO.getItem_name());
            tm.setQuantity(Integer.parseInt(qty.getText()));
            tm.setPayment(Double.parseDouble(price.getText()));
            list.add(tm);
            tblSupplierOrder.refresh();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }


    /*private void setSupplierData(String id) {
        System.out.println("ID : "+id);
        try {
            OrderTmDto orderTmDto = SupplierOrderDetailsModel.get(id);
            SupplierOrderTm supplierOrderTm = new SupplierOrderTm();

            if (orderTmDto.getItem_id()!=null){
                supplierOrderTm.setItem_id(orderTmDto.getItem_id());
                supplierOrderTm.setSupplier_order_id(orderTmDto.getSupplier_order_id());
                supplierOrderTm.setSupplier_order_date(orderTmDto.getSupplier_order_date());
                supplierOrderTm.setPrice(orderTmDto.getPrice());
                supplierOrderTm.setQuantity(orderTmDto.getQuantity());
                supplierOrderTm.setPayment(orderTmDto.getPayment());
                list.addAll(supplierOrderTm);

                System.out.println(supplierOrderTm.toString());

            }



        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }*/

    public void loadAllSupplierId() {
        list.clear();
        try {
            ArrayList<String> ids = supplierOrderBO.getAll();

            for (int i = 0; i < ids.size(); i++) {
//                setSupplierData(ids.get(i));
//                System.out.println(ids.get(i));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setItemId();
        setSupplierId();
        loadAllSupplierId();
//        setSupplierData();
        coltemId.setCellValueFactory(new PropertyValueFactory<>("item_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));

        tblSupplierOrder.setItems(list);
    }

    public void setItemId() {
        try {
            ArrayList<String> ids = supplierOrderBO.getAllItemId();
            itemId.getItems().addAll(ids);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void setSupplierId() {
        try {
            ArrayList<String> ids = supplierOrderBO.getAllId();
            supplierId.getItems().addAll(ids);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public double setTotalPayemnt() {
        double total = 0.0;
        total = Double.parseDouble(qty.getText()) * Double.parseDouble(price.getText());
        return total;
    }

    public void loadDataTable() {
        list.clear();
        tblSupplierOrder.getItems().clear();
        loadAllSupplierId();
//        setSupplierData();
    }

    public void btnDoneOnAction(ActionEvent actionEvent) {
        SupplierOrderDTO supplierOrderDTO = new SupplierOrderDTO();
        supplierOrderDTO.setSupplier_id(String.valueOf(supplierId.getValue()));
        supplierOrderDTO.setSupplier_order_id(o_id);
        supplierOrderDTO.setSupplier_order_date(DateTimeUtil.dateNow());
        supplierOrderDTO.setSupplier_order_time(DateTimeUtil.timeNow());
        supplierOrderDTO.setPayment(String.valueOf(setTotalPayemnt()));

        if (supplierOrderBO.placeOrder(supplierOrderDTO, details_list)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Done").show();
            Navigation.close(actionEvent);
        } else {
            new Alert(Alert.AlertType.ERROR, "Error").show();
        }
    }

    private String id() {
        try {
            ArrayList<String> allId = supplierOrderBO.getAllSupplierOrderId();
            String lastId = null;
            for (int i = 0; i < allId.size(); i++) {
                lastId = allId.get(i);
                //System.out.println(allId.get(i));
            }
            try {
                String[] e00s = lastId.split("O00");
                int idIndex = Integer.parseInt(e00s[1]);
                idIndex++;
                // System.out.println(idIndex);
                return "O00" + idIndex;
            } catch (Exception e) {
                return "O001";
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }
//    public boolean placeOrder(SupplierOrderDTO supOrder, ArrayList<SupplierOrderDetailsDTO> details_list) {
//        Connection connection = null;
//        try {
//            connection = DBConnection.getInstance().getConnection();
//            connection.setAutoCommit(false);
//
//            if (supplierOrderDAO.save(supOrder)){
//                if (supplierOrderDAO.addSupplierOrderDetails(details_list)){
//                    if (stockDAO.updateData(details_list)){
//                        connection.commit();
//                        return true;
//                    }else {
//                        connection.rollback();
//                        return false;
//                    }
//
//                }else {
//                    connection.rollback();
//                    return false;
//                }
//            }else {
//                connection.rollback();
//                return false;
//            }
//
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                connection.setAutoCommit(true);
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//        return true;
//    }
}
