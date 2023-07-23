package lk.ijse.fashionfiesta.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import lk.ijse.fashionfiesta.bo.BoFactory;
import lk.ijse.fashionfiesta.bo.custom.StockBO;
import lk.ijse.fashionfiesta.bo.custom.impl.StockBOImpl;
import lk.ijse.fashionfiesta.dto.StockDTO;
import lk.ijse.fashionfiesta.utill.Navigation;
import lk.ijse.fashionfiesta.utill.RegexUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static lk.ijse.fashionfiesta.bo.BoFactory.BOTypes.CUSTOMER_ORDER_BO;

public class StockAddFormController implements Initializable {

  //  StockDAO stockDAO = new StockDAOImpl();
  //StockBO stockBO = new StockBOImpl();
  StockBO stockBO = BoFactory.getBoFactory().getBO(CUSTOMER_ORDER_BO);

    public JFXButton btnAdd;
    @FXML
    private JFXTextField txtItemId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtColor;

    @FXML
    private JFXTextField txtCategory;

    @FXML
    void addOnAction(ActionEvent event) {
        StockDTO stockDTO = new StockDTO();
        stockDTO.setItem_id(txtItemId.getText());
        stockDTO.setItem_name(txtName.getText());
        stockDTO.setQuantity(0);
        stockDTO.setPrice(Double.parseDouble(txtPrice.getText()));
        stockDTO.setModel_color(txtColor.getText());
        stockDTO.setCategory(txtCategory.getText());

        try {
            boolean add = stockBO.save(stockDTO);
            if (add){
                ItemControllerForm.getInstance().loadDataTable();
                new Alert(Alert.AlertType.CONFIRMATION,"Successfully Added").show();
                Navigation.close(event);

            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"Error Added").show();
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void nameKeyReleased(KeyEvent keyEvent) {
        System.out.println("laneKeyReleased");
        RegexUtil.regex(btnAdd,txtName,txtName.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill");
    }

   /* public void priceKeyReleased(KeyEvent keyEvent) {
        System.out.println("priceKeyReleased");
        RegexUtil.regex(btnAdd,txtPrice,txtPrice.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill");
    }*/

    public void colorKeyReleased(KeyEvent keyEvent) {
        System.out.println("colorKeyReleased");
        RegexUtil.regex(btnAdd,txtColor,txtColor.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill");
    }

    public void categaryKeyReleased(KeyEvent keyEvent) {
        System.out.println("categaryKeyReleased");
        RegexUtil.regex(btnAdd,txtCategory,txtCategory.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill");
    }

    public void priceKeyReleased(KeyEvent keyEvent) {
    }
}
