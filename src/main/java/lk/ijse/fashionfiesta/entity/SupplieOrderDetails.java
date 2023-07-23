package lk.ijse.fashionfiesta.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data

public class SupplieOrderDetails {

    private String supplier_order_id;
    private String item_id;
    private int quantity;
    private double price;
}
