package lk.ijse.fashionfiesta.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import lk.ijse.fashionfiesta.bo.custom.CashierBoardBO;
import lk.ijse.fashionfiesta.bo.custom.impl.CashierBoardBOImpl;
import lk.ijse.fashionfiesta.dao.custom.CustomerOrderDAO;
import lk.ijse.fashionfiesta.dao.custom.EmployeeAttendanceDAO;
import lk.ijse.fashionfiesta.dao.custom.StockDAO;
import lk.ijse.fashionfiesta.dao.custom.SupplierOrderDAO;
import lk.ijse.fashionfiesta.dao.custom.impl.CustomerOrderDAOImpl;
import lk.ijse.fashionfiesta.dao.custom.impl.EmployeeAttendanceDAOImpl;
import lk.ijse.fashionfiesta.dao.custom.impl.StockDAOImpl;
import lk.ijse.fashionfiesta.dao.custom.impl.SupplierOrderDAOImpl;
import lk.ijse.fashionfiesta.tm.StockTm;
import lk.ijse.fashionfiesta.utill.Navigation;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class CashierDashboardFormController implements Initializable {

//    CustomerOrderDAO customerOrderDAO = new CustomerOrderDAOImpl();
//    EmployeeAttendanceDAO employeeAttendanceDAO = new EmployeeAttendanceDAOImpl();
//    StockDAO stockDAO = new StockDAOImpl();
//    SupplierOrderDAO supplierOrderDAO = new SupplierOrderDAOImpl();

    CashierBoardBO cashierBoardBO = new CashierBoardBOImpl();

    public Label txtEmployee;
    public Label txtCustOrder;
    public Pane pane;
    public Text h;
    public Text m;
    public Text dae;

    public Label txtAtt;
    public Label txtSupOrder;
    public Label txtCusOrder;
    public Label txtAllItem;
    public Label txtAllOrder;
    public Label txtTodayOrdder;
    private void setCount() {
        try {
            txtAtt.setText(cashierBoardBO.getEmpAttendance());
            txtSupOrder.setText(cashierBoardBO.getSupOrderCount());
            txtAllOrder.setText(cashierBoardBO.getCustOrder());
            txtAllItem.setText(cashierBoardBO.getStock());
            txtCusOrder.setText(cashierBoardBO.getCustOrder());
            txtTodayOrdder.setText(cashierBoardBO.getCustOrder());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnAttendanceOnAction(ActionEvent event) {
        Navigation.onTheTopNavigation(pane,"Cashier/EmployeeAttendanceForm.fxml");
    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) {
        Navigation.onTheTopNavigation(pane,"Cashier/CustomerForm.fxml");
    }

    @FXML
    void btnOrderOnAction(ActionEvent event) {
        Navigation.onTheTopNavigation(pane,"Cashier/OrderForm.fxml");

    }

    @FXML
    void btnStockOnAction(ActionEvent event) {
        //CashierDashboardFormController.getData(stockTm);
        Navigation.onTheTopNavigation(pane,"Cashier/CashierStockForm.fxml");
    }

    private static void getData(StockTm stockTm) {
    }

    public void setEmpCount() {
   /*     try {
            txtEmployee.setText(EmployeeModel.getEmpCount());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
*/
    }

    public void setCustOrder() {
        try {
            txtCustOrder.setText(cashierBoardBO.getCustOrder());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //setEmpCount();
        //setCustOrder();

        setTime();
        setDate();
        setCount();
    }

    private void setDate() {
        SimpleDateFormat format=new SimpleDateFormat("EEE, MMM d");
        dae.setText(  format.format(new Date()));
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

    public void logout(MouseEvent mouseEvent) {
        try {
            Navigation.switchNavigation("LoginForm.fxml",mouseEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void homeOnAction(ActionEvent actionEvent) {
        pane.getChildren().clear();
    }
}
