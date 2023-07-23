package lk.ijse.fashionfiesta.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

public class SupplierOrder {

    private String supplier_id;
    private String supplier_order_id;
    private String supplier_order_date;
    private String supplier_order_time;
    private double payment;
}
