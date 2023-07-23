package lk.ijse.fashionfiesta.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.fxml.FXML;
import  javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import lk.ijse.fashionfiesta.bo.BoFactory;
import lk.ijse.fashionfiesta.bo.custom.SupplierBO;
import lk.ijse.fashionfiesta.bo.custom.impl.SupplierBOImpl;
import lk.ijse.fashionfiesta.dto.SupplierDTO;
import lk.ijse.fashionfiesta.utill.Navigation;
import lk.ijse.fashionfiesta.utill.RegexUtil;

import java.sql.SQLException;
import java.util.ResourceBundle;
import java.net.URL;
import java.sql.ResultSet;

import static lk.ijse.fashionfiesta.bo.BoFactory.BOTypes.SUPPLIER_BO;

public class SupplierAddController implements Initializable {

   // SupplierDAO supplierDAO = new SupplierDAOImpl();
   //SupplierBO supplierBO = new SupplierBOImpl();
   SupplierBO supplierBO = BoFactory.getBoFactory().getBO(SUPPLIER_BO);

    public JFXButton btnAdd;
    @FXML
    private JFXTextField txtSupId;

    @FXML
    private JFXTextField txtFirstName;

    @FXML
    private JFXTextField txtLastName;

    @FXML
    private JFXTextField txtStreet;

    @FXML
    private JFXTextField txtCity;

    @FXML
    private JFXTextField txtLane;

    @FXML
    private JFXTextField txtContact;

    private ResultSet set;
    private ObservableList<SupplierDTO> data;

    public void addOnAction(ActionEvent actionEvent) {
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setSupplier_id(txtSupId.getText());
        supplierDTO.setSupplier_Fname(txtFirstName.getText());
        supplierDTO.setSupplier_Lname(txtLastName.getText());
        supplierDTO.setStreet(txtStreet.getText());
        supplierDTO.setCity(txtCity.getText());
        supplierDTO.setLane(txtLane.getText());
        supplierDTO.setContact(txtContact.getText());

        try {
            boolean add = supplierBO.save(supplierDTO);
            if (add){
                SupplierFormController.getInstance().loadDataTable();
                new Alert(Alert.AlertType.CONFIRMATION,"SuccessFully Added").show();
                Navigation.close(actionEvent);
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

    public void firstNameKeyReleased(KeyEvent keyEvent) {
        System.out.println("firstNameKeyReleased");
        RegexUtil.regex(btnAdd,txtFirstName,txtFirstName.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill");
    }

    public void lastNameKeyReleased(KeyEvent keyEvent) {
        System.out.println("lastNameKeyReleased");
        RegexUtil.regex(btnAdd,txtLastName,txtLastName.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill");
    }

    public void streetKeyReleased(KeyEvent keyEvent) {
        System.out.println("streetKeyReleased");
        RegexUtil.regex(btnAdd,txtStreet,txtStreet.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill");

    }

    public void cityKeyReleased(KeyEvent keyEvent) {
        System.out.println("cityKeyReleased");
        RegexUtil.regex(btnAdd,txtCity,txtCity.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill");

    }

    public void laneKeyReleased(KeyEvent keyEvent) {
        System.out.println("laneKeyReleased");
        RegexUtil.regex(btnAdd,txtLane,txtLane.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill");
    }
}

