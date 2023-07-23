package lk.ijse.fashionfiesta.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.ijse.fashionfiesta.bo.BoFactory;
import lk.ijse.fashionfiesta.bo.custom.StockBO;
import lk.ijse.fashionfiesta.bo.custom.impl.StockBOImpl;
import lk.ijse.fashionfiesta.dto.StockDTO;
import lk.ijse.fashionfiesta.tm.StockTm;
import lk.ijse.fashionfiesta.utill.RegexUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static lk.ijse.fashionfiesta.bo.BoFactory.BOTypes.STOCK_BO;


public class ItemControllerForm implements Initializable {

// StockBO stockBO = new StockBOImpl();
   StockBO stockBO = BoFactory.getBoFactory().getBO(STOCK_BO);
 //   StockDAO stockDAO = new StockDAOImpl();

    protected static StockTm stockTm;
    private static ItemControllerForm controller;
    public TableColumn tblItemName;
    public TableColumn tblItemPrice;
    public TableColumn tblItemModelColor;
    public TableColumn tblCatagory;
    public Text txtStock;
    public JFXTextField txtName;
    public JFXTextField txtColor;
    public JFXTextField txtCategory;
    public JFXTextField txtPrice;
    public JFXTextField txtQty;
    public JFXButton add;
    public JFXButton remove;
    public JFXButton update;

    ObservableList<StockTm> list = FXCollections.observableArrayList();
    @FXML
    private TableView<StockTm> EmployeeTbl;
    @FXML
    private TableColumn tblId;
    @FXML
    private JFXTextField txtSearch;
    private static String itemId;

    public ItemControllerForm() {
        controller = this;
    }

    public static ItemControllerForm getInstance() {
        return controller;
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

        try {

            StockDTO stockDTO = new StockDTO();
            stockDTO.setItem_id(currentId);
            stockDTO.setItem_name(txtName.getText());
            stockDTO.setPrice(Double.parseDouble(txtPrice.getText()));
            stockDTO.setCategory(txtCategory.getText());
            stockDTO.setModel_color(txtColor.getText());
            stockDTO.setQuantity(Integer.parseInt(txtQty.getText()));


            if (stockBO.update(stockDTO)){
                loadDataTable();
                new Alert(Alert.AlertType.CONFIRMATION,"SuccessFully Updated").show();
                clearText();
            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"Error").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private static String currentId;
    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ? .. .", ButtonType.OK, ButtonType.NO);
            alert.showAndWait();
            if (ButtonType.OK.equals(alert.getResult())) {
                if (stockBO.remove(currentId)) {
                    loadDataTable();
                    clearText();
                    new Alert(Alert.AlertType.CONFIRMATION, "SuccessFully Updated").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Error").show();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void clearText() {
        txtName.clear();
        txtQty.clear();
        txtColor.clear();
        txtCategory.clear();
        txtPrice.clear();
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        StockDTO stockDTO = new StockDTO();
        stockDTO.setItem_id(id());
        stockDTO.setItem_name(txtName.getText());
        stockDTO.setQuantity(Integer.parseInt(txtQty.getText()));
        stockDTO.setPrice(Double.parseDouble(txtPrice.getText()));
        stockDTO.setModel_color(txtColor.getText());
        stockDTO.setCategory(txtCategory.getText());

        try {
            boolean add = stockBO.save(stockDTO);
            if (add){
                ItemControllerForm.getInstance().loadDataTable();
                new Alert(Alert.AlertType.CONFIRMATION,"Successfully Added").show();
            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"Error Added").show();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private String id() {

        try {
            ArrayList<String> allId = stockBO.getAllId();
            String lastId = null;
            for (int i = 0; i < allId.size(); i++) {
                lastId = allId.get(i);
                System.out.println(allId.get(i));
            }
            try {
                String[] e00s = lastId.split("I00");
                int idIndex = Integer.parseInt(e00s[1]);
                idIndex++;
                System.out.println(idIndex);
                return "I00" + idIndex;
            } catch (Exception e) {
                return "I001";
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return null;

    }

    private void getAllIds() {
        try {
            ArrayList<String> list = stockBO.getAllId();
            for (int i = 0; i < list.size(); i++) {
                setStockData(list.get(i));
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private void setStockData(String id) {

        try {
            StockDTO stockDTO = stockBO.get(id);
            StockTm tm = new StockTm();
            tm.setItemId(stockDTO.getItem_id());
            tm.setItemName(stockDTO.getItem_name());
            tm.setPrice(String.valueOf(stockDTO.getPrice()));
            tm.setModel_color(stockDTO.getModel_color());
            tm.setCategory(stockDTO.getCategory());
            list.add(tm);
            System.out.println(tm.getItemId());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void loadDataTable() {
        list.clear();
        EmployeeTbl.getItems().clear();
        getAllIds();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getAllIds();
        tblId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        tblItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        tblItemPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tblItemModelColor.setCellValueFactory(new PropertyValueFactory<>("model_color"));
        tblCatagory.setCellValueFactory(new PropertyValueFactory<>("category"));
        EmployeeTbl.setItems(list);
        setStock();
    }

    public void tblMouseClick(MouseEvent mouseEvent) {
        try {
            stockTm = EmployeeTbl.getSelectionModel().getSelectedItem();
            StockDTO stockDTO = stockBO.get(stockTm.getItemId());
            currentId=stockTm.getItemId();
            txtName.setText(stockTm.getItemName());
            txtQty.setText(String.valueOf(stockDTO.getQuantity()));
            txtColor.setText(stockTm.getModel_color());
            txtCategory.setText(stockTm.getCategory());
            txtPrice.setText(stockTm.getPrice());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void searchKeyReleased(KeyEvent event) {
        EmployeeTbl.getItems().clear();
        list.clear();
        try {
            ArrayList<String> ids = stockBO.getSearchIds(txtSearch.getText());
            for (int i = 0; i < ids.size(); i++) {
                setStockData(ids.get(i));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void setStock() {
        try {
            txtStock.setText(stockBO.getStock());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void nameKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex( (JFXTextField) txtName,txtName.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill: black",add,update,remove);

    }

    public void colorKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex( (JFXTextField) txtColor,txtColor.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill: black",add,update,remove);

    }

    public void categaryKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex( (JFXTextField) txtCategory,txtCategory.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill: black",add,update,remove);

    }

    public void priceKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex((JFXTextField) txtPrice,txtPrice.getText(),"([+-]?[0-9]+(?:\\.[0-9]*)?)","-fx-text-fill: black",add,update,remove);

    }

    public void qtyKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex( (JFXTextField) txtQty,txtQty.getText(),"^0*(\\d{1,9})$","-fx-text-fill: black",add,update,remove);
    }
}

