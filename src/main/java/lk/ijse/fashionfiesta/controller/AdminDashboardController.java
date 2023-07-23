package lk.ijse.fashionfiesta.controller;


import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import lk.ijse.fashionfiesta.bo.custom.DashboardBO;
import lk.ijse.fashionfiesta.bo.custom.impl.DashboardBOImpl;
import lk.ijse.fashionfiesta.dao.custom.CustomerOrderDAO;
import lk.ijse.fashionfiesta.dao.custom.EmployeeAttendanceDAO;
import lk.ijse.fashionfiesta.dao.custom.StockDAO;
import lk.ijse.fashionfiesta.dao.custom.SupplierOrderDAO;
import lk.ijse.fashionfiesta.dao.custom.impl.CustomerOrderDAOImpl;
import lk.ijse.fashionfiesta.dao.custom.impl.EmployeeAttendanceDAOImpl;
import lk.ijse.fashionfiesta.dao.custom.impl.StockDAOImpl;
import lk.ijse.fashionfiesta.dao.custom.impl.SupplierOrderDAOImpl;
import lk.ijse.fashionfiesta.utill.Navigation;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable{


//    EmployeeAttendanceDAO employeeAttendanceDAO = new EmployeeAttendanceDAOImpl();
//    SupplierOrderDAO supplierOrderDAO = new SupplierOrderDAOImpl();
//    StockDAO stockDAO = new StockDAOImpl();
//    CustomerOrderDAO customerOrderDAO = new CustomerOrderDAOImpl();

    DashboardBO dashboardBO = new DashboardBOImpl();

    public Pane pane;
    public Text m;
    public Text h;
    public Text dat;

    public Label txtAtt;
    public Label txtSupOrder;
    public Label txtCusOrder;
    public Label txtAllItem;
    public Label txtAllOrder;
    public Label txtTodayOrdder;
    private void setCount() {
        try {
            txtAtt.setText(dashboardBO.getEmpAttendance());
            txtSupOrder.setText(dashboardBO.getSupOrderCount());
            txtAllOrder.setText(dashboardBO.getCustOrder());
            txtAllItem.setText(dashboardBO.getStock());
            txtCusOrder.setText(dashboardBO.getCustOrder());
            txtTodayOrdder.setText(dashboardBO.getCustOrder());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void Employee(javafx.scene.input.MouseEvent mouseEvent) {
       /* try {
            Navigation.switchNavigation("CustomerForm.fxml",mouseEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void btnEmployeeOnAction(ActionEvent actionEvent) {
        Navigation.onTheTopNavigation(pane,"EmployeeRegisterForm.fxml");
    }

    public void btnSupplierOnAction(ActionEvent actionEvent) {
        Navigation.onTheTopNavigation(pane,"SupplierForm.fxml");
    }

    public void stockOnAction(ActionEvent actionEvent) {
            Navigation.onTheTopNavigation(pane,"StockForm.fxml");

    }

    public void btnReportOnAction(ActionEvent actionEvent) {
        Navigation.onTheTopNavigation(pane,"RepoartForm.fxml");
    }

    public void homeOnAction(ActionEvent actionEvent) {
        pane.getChildren().clear();
    }

    public void logOut(MouseEvent mouseEvent) {
        try {
            Navigation.switchNavigation("LoginForm.fxml",mouseEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDate();
        setTime();
        setCount();
    }



    private void setDate() {
        SimpleDateFormat format=new SimpleDateFormat("EEE, MMM d");
        dat.setText(  format.format(new Date()));
    }

    private void setTime() {
        Thread thread=new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (true){
                    Thread.sleep(1000);
                    SimpleDateFormat format=new SimpleDateFormat("HH-mm");
                    String format1 = format.format(new Date());
                    //System.out.println(format1);
                    String[] split = format1.split("-");
                    h.setText(split[0]);
                    m.setText(split[1]);


                }
            }
        });
        thread.start();

    }
}
