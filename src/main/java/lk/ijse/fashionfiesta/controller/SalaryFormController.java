package lk.ijse.fashionfiesta.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import lk.ijse.fashionfiesta.bo.BoFactory;
import lk.ijse.fashionfiesta.bo.custom.AttendanceBO;
import lk.ijse.fashionfiesta.bo.custom.EmployeeBO;
import lk.ijse.fashionfiesta.bo.custom.SalaryBO;
import lk.ijse.fashionfiesta.bo.custom.impl.AttendanceBOImpl;
import lk.ijse.fashionfiesta.bo.custom.impl.EmployeeBOImpl;
import lk.ijse.fashionfiesta.bo.custom.impl.SalaryBOImpl;
import lk.ijse.fashionfiesta.dto.EmployeeDTO;
import lk.ijse.fashionfiesta.dto.SalaryDTO;
import lk.ijse.fashionfiesta.tm.SalaryTm;
import lk.ijse.fashionfiesta.utill.DateTimeUtil;
import lk.ijse.fashionfiesta.utill.Navigation;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static lk.ijse.fashionfiesta.bo.BoFactory.BOTypes.ATTENDANCE_BO;
import static lk.ijse.fashionfiesta.bo.BoFactory.BOTypes.SALARY_BO;

public class SalaryFormController implements Initializable {
    //EmployeeAttendanceDAO employeeAttendanceDAO = new EmployeeAttendanceDAOImpl();
    //AttendanceBO attendanceBO = new AttendanceBOImpl();
   // AttendanceBO attendanceBO = BoFactory.getBoFactory().getBO(ATTENDANCE_BO);
    //EmployeeDAO employeeDAO = new EmployeeDAOImpl();
   // EmployeeBO employeeBO = new EmployeeBOImpl();
  //  EmployeeBO employeeBO =BoFactory.getBoFactory().getBO(SALARY_BO);
    //SalaryDAO salaryDAO = new SalaryDAOImpl();
    //SalaryBO salaryBO = new SalaryBOImpl();
    SalaryBO salaryBO = BoFactory.getBoFactory().getBO(SALARY_BO);


    static String employeeId = null;
    public TableColumn colEmployeeId;
    public TableColumn ColSalary;
    public TableColumn colDate;
    public TableColumn colSalary;
    public TableColumn colAttendance;
    public JFXButton btnDone;
    public JFXButton btnApply;
    public JFXComboBox cmbEmployeeId;
    public JFXTextField txtDailySalary;
    public JFXTextField txtBonus;
    public Text txtNetSalary;
    public Text txtName;
    public Text txtAttendance;
    public TableColumn ColSalaryId;
    public TableView tblSalary;
    String salaryId;
    int count = 0;
    ObservableList<SalaryTm> list = FXCollections.observableArrayList();

    public void btnDoneOnaction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        salaryId = id();
        SalaryDTO salaryDTO = new SalaryDTO();
        System.out.println("employeeId : " + employeeId);
        salaryDTO.setEmployee_id(employeeId);
        salaryDTO.setSalary_id(salaryId);
        salaryDTO.setDate(DateTimeUtil.dateNow());
        salaryDTO.setSalary(Double.parseDouble(txtNetSalary.getText()));
        salaryDTO.setEmployee_attandance_count(count);
        if (salaryBO.save(salaryDTO)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Done").show();
            loadDataTable();
            Navigation.close(actionEvent);
        } else {
            new Alert(Alert.AlertType.ERROR, "Error").show();
        }
        cmbEmployeeId.getItems().clear();
        setEmployeeId();
        txtName.setText("");
        txtDailySalary.setText("");
        txtAttendance.setText("");
        txtBonus.setText("");

    }

    public void loadDataTable() {
        list.clear();
        tblSalary.getItems().clear();
        loadAllId();
    }

    public void btnApplyOnAction(ActionEvent event) {
        try {
            count = attendanceCount();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(employeeId);
        if (txtNetSalary.getText().equals("")) {
            int deilySalary = Integer.parseInt(txtDailySalary.getText());
            int totalSalary = 0;
            try {
                totalSalary = deilySalary * attendanceCount();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            int bonusSalary = Integer.parseInt(txtBonus.getText());
            txtNetSalary.setText(String.valueOf(totalSalary + bonusSalary));

        } else {
            txtNetSalary.setText("");
            int deilySalary = Integer.parseInt(txtDailySalary.getText());
            int totalSalary = 0;
            try {
                totalSalary = deilySalary * attendanceCount();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            int bonusSalary = Integer.parseInt(txtBonus.getText());
            txtNetSalary.setText(String.valueOf(totalSalary + bonusSalary));

        }
    }

    public void cmbEmployeeIdOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        EmployeeDTO employeeDTO = salaryBO.get(String.valueOf(cmbEmployeeId.getValue()));
        txtName.setText(employeeDTO.getEmployee_Fname() + " " + employeeDTO.getEmployee_Lname());
        txtAttendance.setText(String.valueOf(attendanceCount()));
        employeeId = String.valueOf(cmbEmployeeId.getValue());
    }

    public void setEmployeeId() {
        ArrayList<String> allId = null;
        try {
            allId = salaryBO.getAllId();
            cmbEmployeeId.getItems().addAll(allId);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadAllId() {
        try {
            ArrayList<String> ids = salaryBO.getAllId();
            for (int i = 0; i < ids.size(); i++) {
                setData(ids.get(i));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void setData(String id) {
        SalaryTm salaryTm = new SalaryTm();


        try {
            SalaryDTO salaryDTO = salaryBO.getSalary(id);

            System.out.println(salaryDTO.getEmployee_id());
            salaryTm.setEmployee_id(salaryDTO.getEmployee_id());
            salaryTm.setSalary_id(salaryDTO.getSalary_id());
            salaryTm.setDate(salaryDTO.getDate());
            salaryTm.setSalary(String.valueOf(salaryDTO.getSalary()));
            salaryTm.setEmployee_attandance_count(String.valueOf(salaryDTO.getEmployee_attandance_count()));
            list.add(salaryTm);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setEmployeeId();
        loadDataTable();
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        ColSalaryId.setCellValueFactory(new PropertyValueFactory<>("salary_id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colAttendance.setCellValueFactory(new PropertyValueFactory<>("employee_attandance_count"));
        tblSalary.setItems(list);
    }

    public int attendanceCount() throws SQLException, ClassNotFoundException {
        String employeeCount = null;
        employeeCount = salaryBO.getEmloyeeCount(String.valueOf(cmbEmployeeId.getValue()));
        return Integer.parseInt(employeeCount);
    }

    public String id() {
        try {
            ArrayList<String> allId = salaryBO.getAllId();
            String lastId = null;
            for (int i = 0; i < allId.size(); i++) {
                lastId = allId.get(i);
            }
            try {
                String[] split = lastId.split("SA00");
                int index = Integer.parseInt(split[1]);
                index++;
                return "SA00" + index;
            } catch (Exception e) {
                return "SA001";
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
