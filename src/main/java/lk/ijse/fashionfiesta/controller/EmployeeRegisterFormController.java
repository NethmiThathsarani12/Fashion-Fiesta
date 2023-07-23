package lk.ijse.fashionfiesta.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.fashionfiesta.bo.BoFactory;
import lk.ijse.fashionfiesta.bo.custom.EmployeeBO;
import lk.ijse.fashionfiesta.bo.custom.impl.EmployeeBOImpl;
import lk.ijse.fashionfiesta.dto.EmployeeDTO;
import lk.ijse.fashionfiesta.tm.EmployeeTm;
import lk.ijse.fashionfiesta.utill.RegexUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static lk.ijse.fashionfiesta.bo.BoFactory.BOTypes.EMPLOYEE_BO;

public class EmployeeRegisterFormController implements Initializable {

   // EmployeeDAO employeeDAO = new EmployeeDAOImpl();

   // EmployeeBO employeeBO = new EmployeeBOImpl();
    EmployeeBO employeeBO = BoFactory.getBoFactory().getBO(EMPLOYEE_BO);

    private static EmployeeRegisterFormController controller;
    private static EmployeeTm employeeTm;
    private static String employee_id;
    @FXML
    public TableView EmployeeTbl;
    @FXML
    public TableColumn tblId;
    @FXML
    public TableColumn tblFirstName;
    @FXML
    public TableColumn tblLastName;
    @FXML
    public TableColumn tblCity;
    @FXML
    public TableColumn tblContact;
    public Label txtEmpAttendance2;
    public Label txtAllEmployee2;
    public JFXTextField txtFirstName;
    public JFXTextField txtLastName;
    public JFXTextField txtContact;
    public JFXComboBox txtRole;
    public JFXTextField txtStreet;
    public JFXTextField txtCity;
    public JFXTextField txtLane;
    public JFXButton update;
    public JFXButton remove;
    public JFXButton add;
    ObservableList<EmployeeTm> list = FXCollections.observableArrayList();
    @FXML
    private JFXTextField txtSearch;

    public EmployeeRegisterFormController() {
        controller = this;
    }

    public static EmployeeRegisterFormController getInstance() {
        return controller;
    }

    private String id() {
        try {
            ArrayList<String> allId =employeeBO.getAllId();
            String lastId = null;
            for (int i = 0; i < allId.size(); i++) {
                lastId = allId.get(i);
                //System.out.println(allId.get(i));
            }
            try {
                String[] e00s = lastId.split("E00");
                int idIndex = Integer.parseInt(e00s[1]);
                idIndex++;
               // System.out.println(idIndex);
                return "E00" + idIndex;
            } catch (Exception e) {
                return "E001";
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployee_id(id());
        employeeDTO.setEmployee_Fname(txtFirstName.getText());
        employeeDTO.setEmployee_Lname(txtLastName.getText());
        employeeDTO.setStreet(txtStreet.getText());
        employeeDTO.setCity(txtCity.getText());
        employeeDTO.setLane(txtLane.getText());
        employeeDTO.setRole(String.valueOf(txtRole.getValue()));
        employeeDTO.setContact(txtContact.getText());
        try {
            if (employeeBO.save(employeeDTO)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Ok Ok ").show();
                clear();
                loadDataTable();
            } else {
                new Alert(Alert.AlertType.WARNING, "something Wrong ! ").show();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getAllIds();
        tblId.setCellValueFactory(new PropertyValueFactory<>("Emp_Id"));
        tblFirstName.setCellValueFactory(new PropertyValueFactory<>("fistName"));
        tblLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tblCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        tblContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        EmployeeTbl.setItems(list);
        setEmployee();
        setEmpAttendance();
        setAllRoleComboBox();
    }

    private void setAllRoleComboBox() {
        ArrayList<String> role = new ArrayList<>();
        role.add("Admin");
        role.add("Cashier");
        role.add("Salesmen");
        role.add("Other");
        txtRole.getItems().addAll(role);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    /*    EmployeeUpdateFormController.getData(employeeTm);
        try {
            System.out.println();
            Navigation.popupNavigation("EmployeeUpdateForm.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        try {
            boolean update = employeeBO.update(new EmployeeDTO(
                    employee_id,
                    txtFirstName.getText(),
                    txtLastName.getText(),
                    txtStreet.getText(),
                    txtCity.getText(),
                    txtLane.getText(),
                    (String) txtRole.getValue(),
                    txtContact.getText()
            ));
            if (update) {
                EmployeeRegisterFormController.getInstance().loadDataTable();
                new Alert(Alert.AlertType.CONFIRMATION, "SuccessFully Updated").show();
                clear();
                loadDataTable();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Error").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void clear() {
        txtFirstName.clear();
        txtLastName.clear();
        txtStreet.clear();
        txtCity.clear();
        txtLane.clear();
        txtRole.getItems().clear();
        txtContact.clear();
        setAllRoleComboBox();
    }

    public void tblMouseClick(MouseEvent mouseEvent) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.toEntity((EmployeeTm) EmployeeTbl.getSelectionModel().getSelectedItem());

        txtFirstName.setText(employeeDTO.getEmployee_Fname());
        txtLastName.setText(employeeDTO.getEmployee_Lname());
        txtCity.setText(employeeDTO.getCity());
        txtContact.setText(employeeDTO.getContact());
        txtLane.setText(employeeDTO.getLane());
        txtStreet.setText(employeeDTO.getStreet());
        employee_id = employeeDTO.getEmployee_id();
        txtRole.setValue(employeeDTO.getRole());


    }

    public void btnEmployeeDelete(ActionEvent actionEvent) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ? .. .", ButtonType.OK, ButtonType.NO);
            alert.showAndWait();

            if (ButtonType.OK.equals(alert.getResult())) {
                System.out.println("in the if >>> ");

                if (employeeBO.remove(employee_id)) {
                    loadDataTable();
                    clear();
                    new Alert(Alert.AlertType.CONFIRMATION, "SuccessFully Updated").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Error").show();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getAllIds() {
        try {
            ArrayList<String> list = employeeBO.getAllId();
            for (int i = 0; i < list.size(); i++) {
                setEmployeeData(list.get(i));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private void setEmployeeData(String id) {

        try {
            EmployeeDTO employeeDTO = employeeBO.get(id);
            EmployeeTm tm = new EmployeeTm();
            tm.setEmp_Id(employeeDTO.getEmployee_id());
            tm.setFistName(employeeDTO.getEmployee_Fname());
            tm.setLastName(employeeDTO.getEmployee_Lname());
            tm.setCity(employeeDTO.getCity());
            tm.setContact(employeeDTO.getContact());
            tm.setLane(employeeDTO.getLane());
            tm.setRole(employeeDTO.getRole());
            tm.setStreet(employeeDTO.getStreet());
            list.add(tm);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void loadDataTable() {
        list.clear();
        EmployeeTbl.getItems().clear();
        getAllIds();
        setEmployee();
    }
    @FXML
    void searchKeyReleased(KeyEvent event) {
        EmployeeTbl.getItems().clear();
        list.clear();
        try {
            ArrayList<String> ids = employeeBO.getSearchIds(txtSearch.getText());
            for (int i = 0; i < ids.size(); i++) {
                setEmployeeData(ids.get(i));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void setEmployee() {
        try {
            txtAllEmployee2.setText("+ "+ employeeBO.getEmployee());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void setEmpAttendance() {
//        try {
//            txtEmpAttendance2.setText("+ "+ EmployeeAttendanceDAOImpl.getEmpAttendance());
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

    }

    public void firstNameKeyReleased(KeyEvent keyEvent) {
        System.out.println("firstNameKeyReleased");
        RegexUtil.regex(txtFirstName,txtFirstName.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill",add,remove,update);
    }

    public void lastNameKeyReleased(KeyEvent keyEvent) {
        System.out.println("lastNameKeyReleased");
        RegexUtil.regex(txtLastName,txtLastName.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill",add,remove,update);
    }

    public void streetKeyReleased(KeyEvent keyEvent) {
        System.out.println("streetKeyReleased");
        RegexUtil.regex(txtStreet,txtStreet.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill",add,remove,update);
    }

    public void cityKeyReleased(KeyEvent keyEvent) {
        System.out.println("cityKeyReleased");
        RegexUtil.regex(txtCity,txtCity.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill",add,remove,update);
    }

    public void laneKeyReleased(KeyEvent keyEvent) {
        System.out.println("laneKeyReleased");
        RegexUtil.regex(txtLane,txtLane.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill",add,remove,update);
    }

    public void contactNumberKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex(  txtContact,txtContact.getText(),"^(?:7|0|(?:\\+94))[0-9]{9,10}$","-fx-text-fill: black",add,remove,update);
    }


}
