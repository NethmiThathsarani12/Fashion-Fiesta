package lk.ijse.fashionfiesta.controller;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import lk.ijse.fashionfiesta.bo.BoFactory;
import lk.ijse.fashionfiesta.bo.custom.CustomerOrderBO;
import lk.ijse.fashionfiesta.bo.custom.impl.CustomerOrderBOImpl;
import lk.ijse.fashionfiesta.dto.CustomEntityDTO;
import lk.ijse.fashionfiesta.dto.CustomerOrderDTO;
import lk.ijse.fashionfiesta.dto.CustomerOrderDetailsDTO;
import lk.ijse.fashionfiesta.dto.StockDTO;
import lk.ijse.fashionfiesta.tm.CustomerOrderTm;
import lk.ijse.fashionfiesta.tm.OrderTm;
import lk.ijse.fashionfiesta.utill.DateTimeUtil;
import lk.ijse.fashionfiesta.utill.Navigation;
import lk.ijse.fashionfiesta.utill.RegexUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static lk.ijse.fashionfiesta.bo.BoFactory.BOTypes.CUSTOMER_ORDER_BO;

public class OrderFormController implements Initializable {

//    StockDAO stockDAO = new StockDAOImpl();
//    CustomerDAO customerDAO = new CustomerDAOImpl();
//    CustomerOrderDAO customerOrderDAO =  new CustomerOrderDAOImpl();
//    QuaryDAO quaryDAO = new QuaryDAOImpl();
   // CustomerOrderBO customerOrderBO = new CustomerOrderBOImpl();
    CustomerOrderBO customerOrderBO = BoFactory.getBoFactory().getBO(CUSTOMER_ORDER_BO);

    public TableView tblSupplierOrder;
    public TableColumn coltemId;
    public TableColumn colCustomerOrderId;
    public TableColumn colDate;
    public TableColumn colPrice;
    public TableColumn colQty;
    public TableColumn colPayment;
    public JFXComboBox CustomerId;
    public JFXComboBox itemId;
    public JFXTextField CustomerOrderId;
    public JFXTextField price;
    //    public JFXTextField qty;
    public JFXButton btnDone;
    public JFXButton btnAdd;
    public Text txtTotal;
    public JFXTextField txtQty;
    ObservableList<CustomerOrderTm> list = FXCollections.observableArrayList();
    ArrayList<OrderTm> orderTms = new ArrayList<OrderTm>();
    @FXML
    private JFXTextField txtSearch;

    public void btnstockOnAction(ActionEvent actionEvent) {
        try {
            Navigation.switchNavigation("CashierStockForm.fxml", actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnSupplierOnAction(ActionEvent actionEvent) {

    }

    public void btnCustomerOnAction(ActionEvent actionEvent) {
        try {
            Navigation.switchNavigation("CustomerForm.fxml", actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnHomeOnAction(ActionEvent actionEvent) {
        try {
            Navigation.switchNavigation("CashierDashboardForm.fxml", actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnAttendanceOnAction(ActionEvent actionEvent) {
        try {
            Navigation.switchNavigation("EmployeeAttendanceForm.fxml", actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) {

       // btnDone.setDisable(false);

        OrderTm tm=new OrderTm();
        tm.setOrder_Id(CustomerOrderId.getText());
        tm.setDate(DateTimeUtil.dateNow());
        tm.setQty(Integer.parseInt(txtQty.getText()));
        tm.setPrice(Double.parseDouble(price.getText()));
        tm.setItem_Id(String.valueOf(itemId.getValue()));
        tm.setPayment(Integer.parseInt(txtQty.getText())*Double.parseDouble(price.getText()));
        orderTms.add(tm);
       // System.out.println(tm.toString());
       // System.out.println(orderTms);
//        System.out.println(qty.getText());
        txtTotal.setText(String.valueOf(Double.parseDouble(price.getText()) * Double.parseDouble(txtQty.getText())));

    setTableData(tm);
    }

    private void setTableData(OrderTm tm) {
        CustomerOrderTm customerOrderTm = new CustomerOrderTm();

        customerOrderTm.setItem_id(tm.getItem_Id());
        customerOrderTm.setCustomer_order_id(tm.getOrder_Id());
        customerOrderTm.setCustomer_order_date(tm.getDate());
        customerOrderTm.setPrice(tm.getPrice());
        customerOrderTm.setQuantity(tm.getQty());
        customerOrderTm.setPayment(tm.getPayment());
        list.addAll(customerOrderTm);
        tblSupplierOrder.refresh();
    }

    /*public void loadAllCustomerId() {
        try {
            ArrayList<String> ids = CustomerOrderModel.getAll();

            for (int i = 0; i < ids.size(); i++) {
                setCustomerData(ids.get(i));
                System.out.println(ids.get(i));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }*/

    private void setCustomerData(String id) {
        try {
            CustomEntityDTO customEntityDto = customerOrderBO.getCust(id);
            CustomerOrderTm customerOrderTm = new CustomerOrderTm();

            customerOrderTm.setItem_id(customEntityDto.getItem_id());
            customerOrderTm.setCustomer_order_id(customEntityDto.getOrder_id());
            customerOrderTm.setCustomer_order_date(customEntityDto.getDate());
            customerOrderTm.setPrice(customEntityDto.getPrice());
            customerOrderTm.setQuantity(customEntityDto.getQuantity());
            customerOrderTm.setPayment(customEntityDto.getPayment());
            list.addAll(customerOrderTm);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setItemId();
        setCustomerId();
     //   loadAllCustomerId();
      //  btnDone.setDisable(true);
//        setSupplierData();
        coltemId.setCellValueFactory(new PropertyValueFactory<>("item_id"));
        colCustomerOrderId.setCellValueFactory(new PropertyValueFactory<>("customer_order_id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("Customer_order_date"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));
        tblSupplierOrder.setItems(list);
    }


    public void setItemId() {
        try {
            ArrayList<String> ids = customerOrderBO.getAllId();
            itemId.getItems().addAll(ids);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void setCustomerId() {
        try {
            ArrayList<String> ids = customerOrderBO.getAllCustomerId();
            CustomerId.getItems().addAll(ids);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadDataTable() {
        list.clear();
        tblSupplierOrder.getItems().clear();
      //  loadAllCustomerId();
//        setSupplierData();
    }

    @FXML
    void searchKeyReleased(KeyEvent event) {
        tblSupplierOrder.getItems().clear();
        list.clear();
        try {
            ArrayList<String> ids = customerOrderBO.getSearchIds(txtSearch.getText());
            for (int i = 0; i < ids.size(); i++) {
                setCustomerData(ids.get(i));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void btnDoneOnAction(ActionEvent actionEvent) throws SQLException {
        String oId=id();
        CustomerOrderDTO customerOrderDTO = new CustomerOrderDTO();
        customerOrderDTO.setCustomer_id((String) CustomerId.getValue());
        customerOrderDTO.setCustomer_order_id(oId);
        customerOrderDTO.setCustomer_order_date(DateTimeUtil.dateNow());
        customerOrderDTO.setCustomer_order_time(DateTimeUtil.timeNow());
        customerOrderDTO.setPayment(txtTotal.getText());

        CustomerOrderDetailsDTO details = new CustomerOrderDetailsDTO();
        details.setCustomer_order_id(oId);
        details.setItem_id(String.valueOf(itemId.getValue()));
        details.setQuantity(txtQty.getText());
        details.setPrice(price.getText());

        if (customerOrderBO.placeOrder(customerOrderDTO, orderTms)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Successfully Added").show();
            loadDataTable();
        } else {
            new Alert(Alert.AlertType.ERROR, "Error").show();
        }
    }

    private String id() {
        try {
            ArrayList<String> allId = customerOrderBO.getAllId();
            String lastId = null;
            for (int i = 0; i < allId.size(); i++) {
                lastId = allId.get(i);
                System.out.println(allId.get(i));
            }
            try {
                String[] e00s = lastId.split("O00");
                int idIndex = Integer.parseInt(e00s[1]);
                idIndex++;
                System.out.println(idIndex);
                return "O00" + idIndex;
            } catch (Exception e) {
                return "O001";
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public void cmbItemIdOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        StockDTO stockDTO = customerOrderBO.get(String.valueOf(itemId.getValue()));
        price.setText(String.valueOf(stockDTO.getPrice()));
    }

    public void qtyKeyReleased(KeyEvent keyEvent) {
        //  System.out.println("modelColorKeyReleased");
        RegexUtil.regex(btnAdd, txtQty, txtQty.getText(), "^0*(\\d{1,9})$");

    }
//    public boolean placeOrder(CustomerOrderDTO cusOrder, ArrayList<OrderTm> details) throws SQLException {
//
//        Connection connection = null;
//        try {
//            connection = DBConnection.getInstance().getConnection();
//            connection.setAutoCommit(false);
//
//            if (customerOrderDAO.save(cusOrder)) {
//                System.out.println("1");
//                if (customerOrderDAO.addCustomerOrderDetails(cusOrder,details)) {
//                    System.out.println("2");
//                    if (stockDAO.CustomerOrderupdateData(details)) {
//                        System.out.println("3");
//                        connection.commit();
//                    } else {
//                        System.out.println("3-else");
//                        connection.rollback();
//                        return false;
//                    }
//                } else {
//                    System.out.println("2-else");
//                    connection.rollback();
//                    return false;
//                }
//
//            } else {
//                System.out.println("1-else");
//                connection.rollback();
//                return false;
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            connection.rollback();
//            throw new RuntimeException(e);
//        } finally {
//            connection.setAutoCommit(true);
//        }
//        return true;
//    }
}


