package lk.ijse.fashionfiesta.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import lk.ijse.fashionfiesta.bo.BoFactory;
import lk.ijse.fashionfiesta.bo.custom.CustomerBO;
import lk.ijse.fashionfiesta.bo.custom.impl.CustomerBOImpl;
import lk.ijse.fashionfiesta.dto.CustomerDTO;
import lk.ijse.fashionfiesta.utill.Navigation;
import lk.ijse.fashionfiesta.utill.RegexUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static lk.ijse.fashionfiesta.bo.BoFactory.BOTypes.CUSTOMER_BO;

public class CustomerAddFormController implements Initializable {

   // CustomerDAO customerDAO = new CustomerDAOImpl();
   // CustomerBO customerBO= new CustomerBOImpl();

    CustomerBO customerBO= BoFactory.getBoFactory().getBO(CUSTOMER_BO);

    public JFXButton btnAdd;
    @FXML
    private TextField txtCustId;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtContactNumber;

    @FXML
    private TextField txtStreet;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtLane;

    @FXML
    public void addOnAction(ActionEvent actionEvent) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomer_Id(txtCustId.getText());
        customerDTO.setFirst_name(txtFirstName.getText());
        customerDTO.setLast_name(txtLastName.getText());
        customerDTO.setStreet(txtStreet.getText());
        customerDTO.setCity(txtCity.getText());
        customerDTO.setLane(txtLane.getText());
        customerDTO.setContact_number(txtContactNumber.getText());

        try {
            if (customerBO.save(customerDTO)){
                CustomerFormController.getInstance().loadDataTable();
                new Alert(Alert.AlertType.CONFIRMATION,"Successfully Added").show();
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

    public void lastNameKeyReleased(KeyEvent keyEvent) {
        System.out.println("lastNameKeyReleased");
        RegexUtil.regex(btnAdd, (JFXTextField) txtLastName,txtLastName.getText(),"[a-zA-Z-']+[ a-zA-Z-']");
    }

    public void FirstNameKeyReleased(KeyEvent keyEvent) {
        System.out.println("FirstNameKeyReleased");
        RegexUtil.regex(btnAdd, (JFXTextField) txtFirstName,txtFirstName.getText(),"[a-zA-Z-']+[ a-zA-Z-']");
    }


    public void contacKeyReleased(KeyEvent keyEvent) {
        System.out.println("contacKeyReleased");
        RegexUtil.regex(btnAdd, (JFXTextField) txtContactNumber,txtContactNumber.getText(),"^(?:0|94|\\+94)?(?:7(0|1|2|4|5|6|7|8)\\d)d{6}$");
    }

    public void streetKeyReleased(KeyEvent keyEvent) {
        System.out.println("streetKeyReleased");
      //  RegexUtil.regex(btnAdd, (JFXTextField) txtStreet,txtStreet.getText());
    }

    public void cityKeyReleased(KeyEvent keyEvent) {
        System.out.println("cityKeyReleased");
        RegexUtil.regex(btnAdd, (JFXTextField) txtCity,txtCity.getText(),"[a-zA-Z-']+[ a-zA-Z-']");
    }


    public void laneKeyReleased(KeyEvent keyEvent) {
        System.out.println("laneKeyReleased");
        RegexUtil.regex(btnAdd, (JFXTextField) txtLane,txtLane.getText(),"[a-zA-Z-']+[ a-zA-Z-']");
    }

    public void custIdKeyRelesead(KeyEvent keyEvent) {
        System.out.println("custIdKeyRelesead");
        RegexUtil.regex(btnAdd, (JFXTextField) txtCustId,txtCustId.getText(),"^[A-Za-z0-9]*$");
    }
}

