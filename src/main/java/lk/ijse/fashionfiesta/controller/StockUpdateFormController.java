package lk.ijse.fashionfiesta.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import lk.ijse.fashionfiesta.bo.BoFactory;
import lk.ijse.fashionfiesta.bo.custom.StockBO;
import lk.ijse.fashionfiesta.bo.custom.impl.StockBOImpl;
import lk.ijse.fashionfiesta.dto.StockDTO;
import lk.ijse.fashionfiesta.tm.StockTm;
import lk.ijse.fashionfiesta.utill.Navigation;
import lk.ijse.fashionfiesta.utill.RegexUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static lk.ijse.fashionfiesta.bo.BoFactory.BOTypes.CUSTOMER_ORDER_BO;

public class StockUpdateFormController implements Initializable {

  //  StockDAO stockDAO = new StockDAOImpl();
 // StockBO stockBO = new StockBOImpl();
  StockBO stockBO = BoFactory.getBoFactory().getBO(CUSTOMER_ORDER_BO);

    public static StockTm stockTm;
    public JFXTextField txtItemName;
    public JFXTextField txtQty;
    public JFXTextField txtPrice;
    public JFXTextField txtModelColor;
    public JFXTextField txtCategory;
    public JFXTextField txtContact;
    public JFXButton btnUpdate;


    public static void getData(StockTm stockTm) {
        StockUpdateFormController.stockTm = stockTm;
        System.out.println(StockUpdateFormController.stockTm.getPrice());
    }


    public void btnUpdateOnAction(ActionEvent actionEvent) {

        try {

            StockDTO stockDTO = new StockDTO();
            stockDTO.setItem_id(stockTm.getItemId());
            stockDTO.setItem_name(txtItemName.getText());
            stockDTO.setPrice(Double.parseDouble(txtPrice.getText()));
            stockDTO.setCategory(txtCategory.getText());
            stockDTO.setModel_color(txtModelColor.getText());
            stockDTO.setQuantity(Integer.parseInt(txtQty.getText()));
            boolean update = stockBO.update(stockDTO);
            if (update){
                ItemControllerForm.getInstance().loadDataTable();
                new Alert(Alert.AlertType.CONFIRMATION,"SuccessFully Updated").show();
                Navigation.close(actionEvent);
            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"Error").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private static ItemControllerForm getInstance() {
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setData();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setData() throws ClassNotFoundException {
        try {
            StockDTO stockDTO = stockBO.get(stockTm.getItemId());
            txtItemName.setText(stockTm.getItemName());
            txtQty.setText(String.valueOf(stockDTO.getQuantity()));
            txtModelColor.setText(stockTm.getModel_color());
            txtCategory.setText(stockTm.getCategory());
            txtPrice.setText(stockTm.getPrice());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public void itemNameKeyReleased(KeyEvent keyEvent) {
        System.out.println("itemNameKeyReleased");
        RegexUtil.regex( (JFXTextField) txtItemName,txtItemName.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill",btnUpdate);
    }

   /* public void qtyKeyReleased(KeyEvent keyEvent) {
        System.out.println("qtyKeyReleased");
        RegexUtil.regex(btnUpdate, (JFXTextField) txtItemName,txtItemName.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill");

    }*/

    public void modelColorKeyReleased(KeyEvent keyEvent) {
        System.out.println("modelColorKeyReleased");
        RegexUtil.regex( (JFXTextField) txtModelColor,txtModelColor.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill",btnUpdate);
    }

    public void categaryKeyReleased(KeyEvent keyEvent) {
        System.out.println("modelColorKeyReleased");
        RegexUtil.regex(btnUpdate, (JFXTextField) txtCategory,txtCategory.getText(),"[a-zA-Z-']+[ a-zA-Z-']");
    }

    public void qtyKeyReleased(KeyEvent keyEvent) {
        System.out.println("modelColorKeyReleased");
        RegexUtil.regex(btnUpdate, (JFXTextField) txtCategory,txtCategory.getText(),"^0*(\\d{1,9})$");
    }
}


