package lk.ijse.fashionfiesta.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.fashionfiesta.bo.BoFactory;
import lk.ijse.fashionfiesta.bo.custom.AttendanceBO;
import lk.ijse.fashionfiesta.bo.custom.EmployeeBO;
import lk.ijse.fashionfiesta.bo.custom.impl.AttendanceBOImpl;
import lk.ijse.fashionfiesta.bo.custom.impl.EmployeeBOImpl;
import lk.ijse.fashionfiesta.dao.custom.QuaryDAO;
import lk.ijse.fashionfiesta.dao.custom.impl.QuaryDAOImpl;
import lk.ijse.fashionfiesta.dto.CustomEntityDTO;
import lk.ijse.fashionfiesta.tm.EmployeeAttendanceTm;
import lk.ijse.fashionfiesta.utill.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static lk.ijse.fashionfiesta.bo.BoFactory.BOTypes.ATTENDANCE_BO;
import static lk.ijse.fashionfiesta.bo.BoFactory.BOTypes.EMPLOYEE_BO;

public class EmployeeAttendanceController implements Initializable {
    //EmployeeAttendanceDAO employeeAttendanceDAO = new EmployeeAttendanceDAOImpl();

   //AttendanceBO attendanceBO = new AttendanceBOImpl();
   AttendanceBO attendanceBO = BoFactory.getBoFactory().getBO(ATTENDANCE_BO);

   // EmployeeDAO employeeDAO = new EmployeeDAOImpl();
   // EmployeeBO employeeBO = new EmployeeBOImpl();


   // QuaryDAO quaryDAO = new QuaryDAOImpl();


    public TableView EmployeeAttendanceTbl;
    public TableColumn tblId;
    public JFXTextField txtid;
    public TableColumn tblFirstNameCol;
    public TableColumn tblLastNameCol;
    public TableColumn tblTimeCol;
    public TableColumn tblDateCol;
    public JFXTextField txtSearch;
    public Label txtCustomer1;
    public Label txtCustomer;

    ObservableList<EmployeeAttendanceTm> list= FXCollections.observableArrayList();

    public void btnCustomerOnAction(ActionEvent actionEvent) {
        try {
            Navigation.switchNavigation("CustomerForm.fxml",actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnOrdersOnAction(ActionEvent actionEvent) {
        try {
            Navigation.switchNavigation("OrderForm.fxml",actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnStockOnAction(ActionEvent actionEvent) {
        try {
            Navigation.switchNavigation("CashierStockForm.fxml",actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnHomeOnAction(ActionEvent actionEvent) {
        try {
            Navigation.switchNavigation("CashierDashboardForm.fxml",actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tblMouseClick(MouseEvent mouseEvent) {
    }

    public void txtIdOnKeyRelease(KeyEvent keyEvent) {

    }
    public void setAllId(){
        try {
            ArrayList<String> ids = attendanceBO.getAllEmployeeId();
            for (int i = 0; i < ids.size(); i++) {
                setAttendanceData(ids.get(i));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void setAttendanceData(String id){
        System.out.println("a");
        try {
            CustomEntityDTO customEntityDTO = attendanceBO.getData(id);
            System.out.println(customEntityDTO.getEmployee_id());

            EmployeeAttendanceTm attendanceTm = new EmployeeAttendanceTm();

            attendanceTm.setEmployee_id(customEntityDTO.getEmployee_id());
            attendanceTm.setFirst_Name(customEntityDTO.getFirst_name());
            attendanceTm.setLast_Name(customEntityDTO.getLast_name());
            attendanceTm.setTime(customEntityDTO.getTime());
            attendanceTm.setDate(customEntityDTO.getDate());
            list.addAll(attendanceTm);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDataTable();
        tblId.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        tblFirstNameCol.setCellValueFactory(new PropertyValueFactory<>("first_Name"));
        tblLastNameCol.setCellValueFactory(new PropertyValueFactory<>("last_Name"));
        tblTimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        tblDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        EmployeeAttendanceTbl.setItems(list);
        setCount();
    }

    private void setCount() {
        try {
            txtCustomer.setText(attendanceBO.getEmployee());
            txtCustomer1.setText(attendanceBO.getAllEmpAttendanceCount());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadDataTable() {
        list.clear();
        EmployeeAttendanceTbl.getItems().clear();
        setAllId();
    }



    public void searchKeyReleased(KeyEvent keyEvent) {
        EmployeeAttendanceTbl.getItems().clear();
        list.clear();
        if(txtSearch.getText().equals("")){
            loadDataTable();
        }else {
            try {
                ArrayList<String> ids= attendanceBO.getAllEmpAttendanceSearchId(txtSearch.getText());
                for (int i = 0; i < ids.size(); i++) {
                    setAttendanceData(ids.get(i));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }


    }

    public void enterOnAction(ActionEvent actionEvent) {
        try {
            if (attendanceBO.employeeExist(txtid.getText())){
                if (attendanceBO.employeeTodayExist(txtid.getText())){
                    if (attendanceBO.employeeExist(txtid.getText())){
                        txtid.setText("");
                        loadDataTable();
                        new Alert(Alert.AlertType.CONFIRMATION,"Added").show();
                    }
                }else {
                    txtid.setText("");
                    new Alert(Alert.AlertType.ERROR,"Error").show();
                }
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    private String id() {
        try {
            ArrayList<String> allId =attendanceBO .getAllEmployeeId();
            String lastId = null;
            for (int i = 0; i < allId.size(); i++) {
                lastId = allId.get(i);
                //System.out.println(allId.get(i));
            }
            try {
                String[] e00s = lastId.split("A00");
                int idIndex = Integer.parseInt(e00s[1]);
                idIndex++;
                // System.out.println(idIndex);
                return "A00" + idIndex;
            } catch (Exception e) {
                return "A001";
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }


}

