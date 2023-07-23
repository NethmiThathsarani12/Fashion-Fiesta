package lk.ijse.fashionfiesta.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import lk.ijse.fashionfiesta.dao.custom.UserDAO;
import lk.ijse.fashionfiesta.dao.custom.impl.UserDAOImpl;
import lk.ijse.fashionfiesta.utill.Navigation;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {

    UserDAO userDAO = new UserDAOImpl();
    public Button btnLogin;
    public JFXPasswordField txtPassword;
    public JFXTextField txtUserName;


    @FXML
    void LoginOnAction(ActionEvent event) throws IOException {

      try {
            System.err.println(userDAO.checkUsernameAndPassword(txtUserName.getText(),txtPassword.getText()));

            if (userDAO.checkUsernameAndPassword(txtUserName.getText(),txtPassword.getText()).equals("Admin")) {
              Navigation.switchNavigation("AdminDashboardForm.fxml", event);
            } else if (userDAO.checkUsernameAndPassword(txtUserName.getText(),txtPassword.getText()).equals("cashier")) {
                Navigation.switchNavigation("Cashier/CashierDashboardForm.fxml", event);
            } else {
                new Alert(Alert.AlertType.ERROR, "Error").show();
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
       // Navigation.switchNavigation("Cashier/CashierDashboardForm.fxml", event);
    // Navigation.switchNavigation("AdminDashboardForm.fxml", event);

    }
}
