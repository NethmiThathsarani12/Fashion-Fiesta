package lk.ijse.fashionfiesta.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

public class CustomerOrderDetails {

    private String customer_order_id;
    private String item_id;
    private int quantity;
    private double price;



}
